package com.masai.team6.Controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyScheduler {

  @Scheduled(fixedRate = 24 * 60 * 60 * 1000)
  public void runTask() {
    
  }

}
