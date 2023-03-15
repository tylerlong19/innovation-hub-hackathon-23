package com.innovationhub.hakathon.service;

import com.innovationhub.hakathon.model.DocumentAnomalyRecord;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestHighLevelClientBuilder;
import org.elasticsearch.client.ml.GetDatafeedRequest;
import org.elasticsearch.client.ml.GetDatafeedResponse;
import org.elasticsearch.client.ml.GetJobRequest;
import org.elasticsearch.client.ml.GetJobResponse;
import org.elasticsearch.client.ml.GetJobStatsRequest;
import org.elasticsearch.client.ml.GetJobStatsResponse;
import org.elasticsearch.client.ml.GetRecordsRequest;
import org.elasticsearch.client.ml.GetRecordsResponse;
import org.elasticsearch.client.ml.job.results.AnomalyRecord;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class AnomalyJobService {

  private RestHighLevelClient getRestHighLevelClient() {
    Header header = new BasicHeader(HttpHeaders.AUTHORIZATION, "Basic ZWxhc3RpYzpjaGFuZ2VtZQ==");
    RestClient httpClient = RestClient.builder(
            new HttpHost("10.2.1.92", 9200)
        ).setDefaultHeaders(new Header[]{header})
        .build();
    return new RestHighLevelClientBuilder(httpClient)
        .setApiCompatibilityMode(true)
        .build();
  }

  public GetJobResponse getJobs() throws IOException {
    GetJobRequest request = new GetJobRequest("j1");
    return getRestHighLevelClient().machineLearning()
        .getJob(request, RequestOptions.DEFAULT);
  }

  public GetJobStatsResponse getJobStats() throws IOException {
    GetJobStatsRequest request = new GetJobStatsRequest("j1");
    return getRestHighLevelClient().machineLearning()
        .getJobStats(request, RequestOptions.DEFAULT);
  }

  public GetDatafeedResponse getJobDataFeed(String feedId) throws IOException {
    GetDatafeedRequest request = new GetDatafeedRequest(feedId);
    return getRestHighLevelClient().machineLearning()
        .getDatafeed(request, RequestOptions.DEFAULT);
  }

  public DocumentAnomalyRecord getJobRecord(String orderNumber, String jobId, String createdDate)
      throws IOException {
    GetRecordsRequest request = new GetRecordsRequest(jobId);
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    LocalDateTime date = LocalDateTime.parse(createdDate, formatter);
    request.setStart(date.minusDays(1).toString());
    request.setEnd(date.plusDays(1).toString());
    GetRecordsResponse response = getRestHighLevelClient().machineLearning()
        .getRecords(request, RequestOptions.DEFAULT);
    AnomalyRecord anomalyRecord = response.records().stream().
        filter(anomaly -> anomaly.getInfluencers().stream()
            .anyMatch(influence -> influence.getInfluencerFieldName().equals("orderNumber.keyword")
                && influence.getInfluencerFieldValues().get(0).equals(orderNumber))
        ).findFirst().orElseThrow();

    /*List<DocumentAnomalyRecord> recordList = new ArrayList<>();
    for (AnomalyRecord anomalyRecord:  response.records()) {*/
      DocumentAnomalyRecord documentAnomalyRecord = new DocumentAnomalyRecord();
      documentAnomalyRecord.setRecordScore(anomalyRecord.getRecordScore());
      documentAnomalyRecord.setInfluences(anomalyRecord.getInfluencers());
      documentAnomalyRecord.setJobId(anomalyRecord.getJobId());
      documentAnomalyRecord.setByFieldName(anomalyRecord.getByFieldName());
      documentAnomalyRecord.setFieldName(anomalyRecord.getFieldName());
      documentAnomalyRecord.setBucketSpan(anomalyRecord.getBucketSpan());
      documentAnomalyRecord.setCorrelatedByFieldValue(anomalyRecord.getCorrelatedByFieldValue());
      documentAnomalyRecord.setProbability(anomalyRecord.getProbability());
      documentAnomalyRecord.setActual(anomalyRecord.getActual());
      documentAnomalyRecord.setDetectorIndex(anomalyRecord.getDetectorIndex());
      documentAnomalyRecord.setFunction(anomalyRecord.getFunction());
      //recordList.add(documentAnomalyRecord);
    //}
    return documentAnomalyRecord;
  }
}
