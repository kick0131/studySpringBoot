package com.example.springbatchh2.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import com.example.springbatchh2.listener.JobCompletionNotificationListener;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SampleBatchConfig {

  @Bean
  public Job firstJob(@NonNull Step step1, @NonNull Step step2, @NonNull Step helloStep,
      @NonNull Step OtherStep,
      @NonNull JobRepository jobRepository,
      @NonNull JobCompletionNotificationListener listener) {
    return new JobBuilder("firstJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(step1)
        .next(step2)
        .next(helloStep)
        .next(helloStep)
        .next(OtherStep)
        .build();
  }

  @Bean
  public Job secondJob(@NonNull Step step1, @NonNull Step argStep,
      @NonNull JobRepository jobRepository,
      JobCompletionNotificationListener listener) {
    return new JobBuilder("secondJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(step1)
        .next(argStep)
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


}
