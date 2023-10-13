package edu.pro.crypto.testtaskcrypto.service;
/*
  @author   george
  @project   test-task-crypto
  @class  ScheduleService
  @version  1.0.0 
  @since 13.10.23 - 12.49
*/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Value("telegram.bot.back-end.refresh-interval")
    String interval;


    @Scheduled(cron = "*/100 * * * * *") //  TODO via properties, create string for cron
    public void doChecking() {

    }
}
