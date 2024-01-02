package com.example.mysqlonazure.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DiaryDTO {
  private String id;
  private String bodytext;
  private LocalDateTime create_datetime;
}
