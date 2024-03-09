package com.example.springbatchtransaction.tasks;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Component
@StepScope
@Slf4j
public class LogTasklet implements Tasklet {
  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public RepeatStatus execute(@NonNull StepContribution contribution,@NonNull ChunkContext chunkContext) throws Exception {
    // コンテキストからデータを取得
    @SuppressWarnings("unchecked")
    List<String> data = (List<String>) chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext()
        .get("data");

    log.info("LogTasklet is executed. data: {}", data);
    Thread.sleep(100);
    return RepeatStatus.FINISHED;
  }
}
