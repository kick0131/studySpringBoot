package com.example.springbatchh2.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SampleStepListener implements StepExecutionListener {

  @Override
  public void beforeStep(@NonNull StepExecution stepExecution) {
    log.info("== [before]stepExecution : {}", stepExecution);
  }

  @Override
  public ExitStatus afterStep(@NonNull StepExecution stepExecution) {
    log.info("== [after ]stepExecution : {}", stepExecution);
    return stepExecution.getExitStatus();
  }

}