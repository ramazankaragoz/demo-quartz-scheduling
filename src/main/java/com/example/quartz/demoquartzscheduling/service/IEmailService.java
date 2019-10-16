package com.example.quartz.demoquartzscheduling.service;

import com.example.quartz.demoquartzscheduling.dto.EmailResponseDTO;
import com.example.quartz.demoquartzscheduling.dto.SendEmailDTO;

/**
 * @author Ramazan Karagöz
 * @date 10/16/2019
 */

public interface IEmailService {

  EmailResponseDTO sendEmailByDefinedTime(SendEmailDTO sendEmailDTO);
}
