package com.innovationhub.hakathon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import org.elasticsearch.client.ml.job.results.AnomalyCause;
import org.elasticsearch.client.ml.job.results.Influence;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentAnomalyRecord {

  private String jobId;
  private int detectorIndex;
  private double probability;
  private Double multiBucketImpact;
  private String byFieldName;
  private String byFieldValue;
  private String correlatedByFieldValue;
  private String partitionFieldName;
  private String partitionFieldValue;
  private String function;
  private String functionDescription;
  private List<Double> typical;
  private List<Double> actual;
  private boolean isInterim;

  private String fieldName;

  private String overFieldName;
  private String overFieldValue;
  private List<AnomalyCause> causes;

  private double recordScore;

  private double initialRecordScore;

  private Date timestamp;
  private long bucketSpan;

  private List<Influence> influences;

  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  public int getDetectorIndex() {
    return detectorIndex;
  }

  public void setDetectorIndex(int detectorIndex) {
    this.detectorIndex = detectorIndex;
  }

  public double getProbability() {
    return probability;
  }

  public void setProbability(double probability) {
    this.probability = probability;
  }

  public Double getMultiBucketImpact() {
    return multiBucketImpact;
  }

  public void setMultiBucketImpact(Double multiBucketImpact) {
    this.multiBucketImpact = multiBucketImpact;
  }

  public String getByFieldName() {
    return byFieldName;
  }

  public void setByFieldName(String byFieldName) {
    this.byFieldName = byFieldName;
  }

  public String getByFieldValue() {
    return byFieldValue;
  }

  public void setByFieldValue(String byFieldValue) {
    this.byFieldValue = byFieldValue;
  }

  public String getCorrelatedByFieldValue() {
    return correlatedByFieldValue;
  }

  public void setCorrelatedByFieldValue(String correlatedByFieldValue) {
    this.correlatedByFieldValue = correlatedByFieldValue;
  }

  public String getPartitionFieldName() {
    return partitionFieldName;
  }

  public void setPartitionFieldName(String partitionFieldName) {
    this.partitionFieldName = partitionFieldName;
  }

  public String getPartitionFieldValue() {
    return partitionFieldValue;
  }

  public void setPartitionFieldValue(String partitionFieldValue) {
    this.partitionFieldValue = partitionFieldValue;
  }

  public String getFunction() {
    return function;
  }

  public void setFunction(String function) {
    this.function = function;
  }

  public String getFunctionDescription() {
    return functionDescription;
  }

  public void setFunctionDescription(String functionDescription) {
    this.functionDescription = functionDescription;
  }

  public List<Double> getTypical() {
    return typical;
  }

  public void setTypical(List<Double> typical) {
    this.typical = typical;
  }

  public List<Double> getActual() {
    return actual;
  }

  public void setActual(List<Double> actual) {
    this.actual = actual;
  }

  public boolean isInterim() {
    return isInterim;
  }

  public void setInterim(boolean interim) {
    isInterim = interim;
  }

  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getOverFieldName() {
    return overFieldName;
  }

  public void setOverFieldName(String overFieldName) {
    this.overFieldName = overFieldName;
  }

  public String getOverFieldValue() {
    return overFieldValue;
  }

  public void setOverFieldValue(String overFieldValue) {
    this.overFieldValue = overFieldValue;
  }

  public List<AnomalyCause> getCauses() {
    return causes;
  }

  public void setCauses(List<AnomalyCause> causes) {
    this.causes = causes;
  }

  public double getRecordScore() {
    return recordScore;
  }

  public void setRecordScore(double recordScore) {
    this.recordScore = recordScore;
  }

  public double getInitialRecordScore() {
    return initialRecordScore;
  }

  public void setInitialRecordScore(double initialRecordScore) {
    this.initialRecordScore = initialRecordScore;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

  public long getBucketSpan() {
    return bucketSpan;
  }

  public void setBucketSpan(long bucketSpan) {
    this.bucketSpan = bucketSpan;
  }

  public List<Influence> getInfluences() {
    return influences;
  }

  public void setInfluences(List<Influence> influences) {
    this.influences = influences;
  }
}
