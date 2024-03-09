package com.example.springbatchh2.tasks;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// ExecutionContextを使って値を受け取る
@Component
@StepScope
@Slf4j
public class ArgTasklet implements Tasklet {

  // コンテキスト[<キー名>]で値を取得
  @Value("#{JobExecutionContext['jobKey']}")
  private String jobValue;

  // StepExecutionContextはStep内でしか共有できない
  @Value("#{StepExecutionContext['stepKey']}")
  private String stepValue;

  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("ArgTaskletが実行されました");

    // ChunkContextを使った例
    String jobValue2 = (String) chunkContext.getStepContext()
        .getJobExecutionContext()
        .get("jobKey2");

    log.info("-----------------------------");
    log.info("jobKey={} jobKey2={} stepKey={}", jobValue, jobValue2, stepValue);
    log.info("-----------------------------");

    return RepeatStatus.FINISHED;
  }
}
