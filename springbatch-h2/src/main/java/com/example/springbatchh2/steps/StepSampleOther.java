package com.example.springbatchh2.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

// Stepはファイル分割できるサンプル
@Configuration
@RequiredArgsConstructor
public class StepSampleOther {
  @Bean
  public Step OtherStep(@NonNull Tasklet tasklet1, @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("OtherStep", jobRepository)
        .tasklet(tasklet1, transactionManager)
        .build();
  }

}
