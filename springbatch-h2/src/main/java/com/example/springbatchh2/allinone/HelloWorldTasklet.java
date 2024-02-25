package com.example.springbatchh2.allinone;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class HelloWorldTasklet implements Tasklet {
  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
    System.out.println("★★★★★ Hello, World! ★★★★★");
    return RepeatStatus.FINISHED;
  }
}
