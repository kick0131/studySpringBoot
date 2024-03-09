package com.example.springbatchh2.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@StepScope
@Slf4j
public class Tasklet1 implements Tasklet{
  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("tasklet1が実行されました");

    // JobExecutionContextの取得、値の設定
    ExecutionContext jobContext = contribution.getStepExecution()
      .getJobExecution()
      .getExecutionContext();
    jobContext.put("jobKey", "jobValue");

    // StepExecutionContextの取得、値の設定
    ExecutionContext stepContext = contribution.getStepExecution()
      .getExecutionContext();
    stepContext.put("stepKey", "stepValue");

    return RepeatStatus.FINISHED;
  }
}
