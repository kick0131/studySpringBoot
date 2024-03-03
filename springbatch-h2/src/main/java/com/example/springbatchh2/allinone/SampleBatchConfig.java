package com.example.springbatchh2.allinone;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.springbatchh2.tasks.ArgTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SampleBatchConfig {

  @SuppressWarnings("null")
  @Bean
  public Job firstJob(Step step1, Step step2, Step step3, JobRepository jobRepository,
      JobCompletionNotificationListener listener) {
    return new JobBuilder("firstJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(step1)
        .next(step2)
        .next(step3)
        .next(step3)
        .build();
  }

  @SuppressWarnings("null")
  @Bean
  public Job secondJob(Step step1, Step step4, JobRepository jobRepository,
      JobCompletionNotificationListener listener) {
    return new JobBuilder("secondJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .next(step4)
        .build();
  }

  @SuppressWarnings("null")
  @Bean
  public Step step1(Tasklet tasklet1, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep1", jobRepository)
        .tasklet(tasklet1, transactionManager)
        .build();
  }

  @SuppressWarnings("null")
  @Bean
  public Step step2(Tasklet tasklet2, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep2", jobRepository)
        .tasklet(tasklet2, transactionManager)
        .build();
  }

  @SuppressWarnings("null")
  @Bean
  public Step step3(HelloWorldTasklet helloWorldTasklet, JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep3", jobRepository)
        .tasklet(helloWorldTasklet, transactionManager)
        .build();
  }

  @SuppressWarnings("null")
  @Bean
  public Step step4(ArgTasklet argTasklet, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep4", jobRepository)
        .tasklet(argTasklet, transactionManager)
        .build();
  }

}
