package com.example.springbatchtransaction.chunk01;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@StepScope
@Slf4j
public class HelloProcessor implements ItemProcessor<String, String> {

  @Override
  public String process(@NonNull String item) throws Exception {

    // 文字列の加工
    item = item + "===";

    log.info("Processor:{}", item);
    return item;
  }

}
