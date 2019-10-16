package com.example.quartz.demoquartzscheduling.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Ramazan Karagöz
 * @date 10/15/2019
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailResponseDTO {

  private boolean success;
  private String jobId;
  private String jobGroup;
  private String message;

  public EmailResponseDTO(boolean success, String message) {
    this.success = success;
    this.message = message;
  }

  public EmailResponseDTO(boolean success, String jobId, String jobGroup, String message) {
    this.success = success;
    this.jobId = jobId;
    this.jobGroup = jobGroup;
    this.message = message;
  }
}
