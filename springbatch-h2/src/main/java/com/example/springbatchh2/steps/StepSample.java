package com.example.springbatchh2.steps;

import org.springframework.batch.core.Step;
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

import com.example.springbatchh2.listener.SampleStepListener;
import com.example.springbatchh2.tasks.ArgTasklet;
import com.example.springbatchh2.tasks.HelloWorldTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StepSample {
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
  public Step helloStep(@NonNull HelloWorldTasklet helloWorldTasklet,
      @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager,
      @NonNull SampleStepListener stepListener) {
    return new StepBuilder("helloStep", jobRepository)
        .tasklet(helloWorldTasklet, transactionManager)
        .listener(stepListener)
        .build();
  }

  // 別ステップから値を受け取る
  @Bean
  public Step argStep(@NonNull ArgTasklet argTasklet,
      @NonNull JobRepository jobRepository,
      @NonNull PlatformTransactionManager transactionManager) {
    return new StepBuilder("argStep", jobRepository)
        .tasklet(argTasklet, transactionManager)
        .build();
  }

  // chunk01ディレクトリの内容を実行
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
