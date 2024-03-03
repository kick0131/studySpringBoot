package com.example.springbatchh2.jobschedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduleTask {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

  @Scheduled(cron = "${job.start.cron}")
  public void startBatch() {
    log.info("Current Time: " + dateFormat.format(new Date()));
  }

  // アプリケーション自体を終了する
  @Scheduled(cron = "${job.stop.cron}")
  public void exitBatch() {
    log.info("[Exit] Current Time: " + dateFormat.format(new Date()));
    System.exit(0);
  }
}
