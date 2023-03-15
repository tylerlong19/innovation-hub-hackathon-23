package com.innovationhub.hakathon.controller;

import com.innovationhub.hakathon.model.DocumentAnomalyRecord;
import com.innovationhub.hakathon.service.AnomalyJobService;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.client.ml.datafeed.DatafeedConfig;
import org.elasticsearch.client.ml.job.config.Job;
import org.elasticsearch.client.ml.job.stats.JobStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/elastic", produces = "application/json")
public class AnomalyJobController {

  @Autowired
  AnomalyJobService anomalyJobService;

  @GetMapping(value = "/get-jobs")
  public List<Job> getJobs() throws IOException {
    return anomalyJobService.getJobs().jobs();
  }

  @GetMapping(value = "/get-job-stats")
  public JobStats getJobStats() throws IOException {
    return anomalyJobService.getJobStats().jobStats().get(0);
  }

  @GetMapping(value = "/get-job-datafeed/{feedId}")
  public DatafeedConfig getJobDataFeed(@PathVariable String feedId) throws IOException {
    return anomalyJobService.getJobDataFeed(feedId).datafeeds().get(0);
  }

  @GetMapping(value = "/get-job-record/{orderNumber}/{jobId}/{createdDate}")
  public String getJobRecord(
      @PathVariable String orderNumber,
      @PathVariable String jobId,
      @PathVariable String createdDate) throws IOException {
    return String.valueOf(
        anomalyJobService.getJobRecord(orderNumber, jobId, createdDate).getRecordScore());
  }

}
