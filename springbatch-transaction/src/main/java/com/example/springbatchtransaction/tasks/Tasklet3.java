package com.example.springbatchtransaction.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@StepScope
@Slf4j
public class Tasklet3 implements Tasklet {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("tasklet2が実行されました");
    Thread.sleep(100);
    jdbcTemplate.query("SELECT * FROM sampleTable", (rs, rowNum) -> {
      log.info("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
      return null;
    });

    return RepeatStatus.FINISHED;
  }
}
