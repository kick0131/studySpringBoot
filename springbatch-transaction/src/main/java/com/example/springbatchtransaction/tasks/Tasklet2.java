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

// DB書き込み
@Component
@StepScope
@Slf4j
public class Tasklet2 implements Tasklet {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("tasklet2が実行されました");
    Thread.sleep(100);

    // DB書き込み
    jdbcTemplate.execute("INSERT INTO sampleTable (id, name) VALUES (2, 'Name2')");

    return RepeatStatus.FINISHED;
  }
}
