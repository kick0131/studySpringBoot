package com.example.springbatchtransaction.tasks;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// ⅮB書き込み
@Component
@StepScope
@Slf4j
public class Tasklet1 implements Tasklet {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @SuppressWarnings("null")
  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    log.info("tasklet1が実行されました");
    // コンテキストにデータを設定し、他のタスクレットで利用できるようにする
    List<String> data = List.of("data1", "data2", "data3");
    chunkContext.getStepContext()
        .getStepExecution()
        .getJobExecution()
        .getExecutionContext()
        .put("data", data);

    // ロールバック挙動確認
    boolean roleback = false;
    if(roleback){
      throw new RuntimeException("Exception in step 1");
    }
    
    // DB書き込み
    jdbcTemplate.execute("INSERT INTO sampleTable (id, name) VALUES (1, 'Name1')");
    return RepeatStatus.FINISHED;
  }
}
