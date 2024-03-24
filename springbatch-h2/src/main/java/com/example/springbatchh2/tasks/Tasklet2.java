package com.example.springbatchh2.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// Tasklet1との違いは
// execute外でExecutionContextを使う
@Component
@StepScope
@Slf4j
public class Tasklet2 implements Tasklet {

  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("tasklet2が実行されました");
    Thread.sleep(100);

    // ChunkContextを使った例
    chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext()
        .put("jobKey2", "jobValue2");

    return RepeatStatus.FINISHED;
  }
}