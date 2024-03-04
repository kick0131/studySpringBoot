package com.example.springbatchh2.allinone;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.springbatchh2.tasks.ArgTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SampleBatchConfig {

  @Bean
  public Job firstJob(@NonNull Step step1, @NonNull Step step2, @NonNull Step step3,
      @NonNull JobRepository jobRepository,
      @NonNull JobCompletionNotificationListener listener) {
    return new JobBuilder("firstJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(step1)
        .next(step2)
        .next(step3)
        .next(step3)
        .build();
  }

  @Bean
  public Job secondJob(@NonNull Step step1, @NonNull Step step4,
      @NonNull JobRepository jobRepository,
      JobCompletionNotificationListener listener) {
    return new JobBuilder("secondJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .next(step4)
        .build();
  }

  @Bean
  public Job chunkJob(@NonNull Step chunkStep,
      @NonNull JobRepository jobRepository) {
    return new JobBuilder("thirdJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(chunkStep)
        .build();
  }

  @Bean
  public Step step1(@NonNull Tasklet tasklet1, @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep1", jobRepository)
        .tasklet(tasklet1, transactionManager)
        .build();
  }

  @Bean
  public Step step2(@NonNull Tasklet tasklet2, @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep2", jobRepository)
        .tasklet(tasklet2, transactionManager)
        .build();
  }

  @Bean
  public Step step3(@NonNull HelloWorldTasklet helloWorldTasklet,
      @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep3", jobRepository)
        .tasklet(helloWorldTasklet, transactionManager)
        .build();
  }

  @Bean
  public Step step4(@NonNull ArgTasklet argTasklet,
      @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("myStep4", jobRepository)
        .tasklet(argTasklet, transactionManager)
        .build();
  }

  @Bean
  public Step chunkStep(@NonNull ItemReader<String> reader,
      @NonNull ItemProcessor<String, String> processor,
      @NonNull ItemWriter<String> writer,
      @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("chunkStep", jobRepository)
        .<String, String>chunk(3, transactionManager)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build();
  }

}
