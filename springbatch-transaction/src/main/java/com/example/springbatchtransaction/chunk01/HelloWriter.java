package com.example.springbatchtransaction.chunk01;

import java.util.List;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@StepScope
@Slf4j
public class HelloWriter implements ItemWriter<String> {
  // 昔はItemWriter<List<String>>だったが、Chunk<String>に変更された
  public void write(List<? extends String> items) throws Exception {

    log.info("Write:{}", items);
    for (String item : items) {
      log.info("Write:{}", item);
    }
    log.info("====================");
  }

  @Override
  public void write(@NonNull Chunk<? extends String> chunk) throws Exception {
    log.info("Write:{}", chunk);
    for (String item : chunk) {
      log.info("Write:{}", item);
    }
    log.info("====================");

  }

}
