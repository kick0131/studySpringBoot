package com.example.springbatchh2.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.common.util.StringUtils;


// 自作Validator
@Configuration
public class MyParamValidator implements JobParametersValidator{
  @SuppressWarnings("null")
  @Override
  public void validate(JobParameters parameters) 
  throws JobParametersInvalidException{
    // パラメータ取得
    String argValue = parameters.getString("argStr");

    // 必須チェック
    if(StringUtils.isEmpty(argValue)){
      throw new JobParametersInvalidException("argStr is required");
    }
  }

  @Bean
  public JobParametersValidator compositeValidator(){
    // 自作Validatorをリストに追加
    List<JobParametersValidator> validators = new ArrayList<>();
    validators.add(new MyParamValidator());

    // 自作Validatorを含めたCompositを返す
    CompositeJobParametersValidator composite = new CompositeJobParametersValidator();
    composite.setValidators(validators);
    return composite;
  }

}
