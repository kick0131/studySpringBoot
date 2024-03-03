package com.example.springbatchh2.allinone;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
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
    return RepeatStatus.FINISHED;
  }
}
