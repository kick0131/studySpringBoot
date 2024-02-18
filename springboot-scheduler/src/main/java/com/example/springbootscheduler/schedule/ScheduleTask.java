package com.example.springbootscheduler.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat; // Add this import statement
import java.util.Date; // Add this import statement

@Component
public class ScheduleTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "${job.start.cron}")
    public void startBatch() {
        System.out.println("Current Time: " + dateFormat.format(new Date()));
    }

    // アプリケーション自体を終了する
    @Scheduled(cron = "${job.check.timeout.cron}")
    public void exitBatch() {
        System.out.println("[Exit] Current Time: " + dateFormat.format(new Date()));
        System.exit(0);
    }    
}
