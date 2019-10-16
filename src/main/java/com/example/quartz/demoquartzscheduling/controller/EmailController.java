package com.example.quartz.demoquartzscheduling.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quartz.demoquartzscheduling.dto.EmailResponseDTO;
import com.example.quartz.demoquartzscheduling.dto.SendEmailDTO;
import com.example.quartz.demoquartzscheduling.service.IEmailService;

/**
 * @author Ramazan Karagöz
 * @date 10/15/2019
 */
@RestController
@RequestMapping(value = "email")
public class EmailController {

  private final IEmailService emailService;

  public EmailController(IEmailService emailService) {
    this.emailService = emailService;
  }


  @PostMapping("/scheduleEmail")
  public ResponseEntity<EmailResponseDTO> scheduleEmail(@Valid @RequestBody SendEmailDTO sendEmailDTO) {
    return ResponseEntity.ok(emailService.sendEmailByDefinedTime(sendEmailDTO));
  }
}
