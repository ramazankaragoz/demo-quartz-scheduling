package com.example.quartz.demoquartzscheduling.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Ramazan Karagöz
 * @date 10/16/2019
 */
@Data
public class SendEmailDTO {

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  private String subject;

  @NotEmpty
  private String body;

  @NotNull
  private LocalDateTime dateTime;

  @NotNull
  private ZoneId timeZone;
}
