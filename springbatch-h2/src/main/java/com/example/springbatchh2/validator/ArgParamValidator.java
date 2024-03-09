package com.example.springbatchh2.validator;

import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArgParamValidator {
  @Bean
  public JobParametersValidator defaultValidator() {
    DefaultJobParametersValidator validator = new DefaultJobParametersValidator();

    // 必須チェック
    String[] requiredKeys = { "argStr" };
    validator.setRequiredKeys(requiredKeys);

    // オプション引数チェック
    String[] optionalKeys = { "optStr" };
    validator.setOptionalKeys(optionalKeys);

    // 必須キーとオプションキーの重複チェック
    validator.afterPropertiesSet();

    return validator;
  }

}
