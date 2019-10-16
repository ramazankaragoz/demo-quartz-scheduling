package com.example.quartz.demoquartzscheduling.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Ramazan Karagöz
 * @date 10/15/2019
 */
@Data
@Entity
@Table(name = "email")
public class Email {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;

  @Column(name = "email")
  private String email;

  @Column(name = "subject")
  private String subject;

  @Column(name = "body")
  private String body;

  @Column(name = "date_time")
  private LocalDateTime dateTime;

  @Column(name = "time_zone")
  private ZoneId timeZone;

}
