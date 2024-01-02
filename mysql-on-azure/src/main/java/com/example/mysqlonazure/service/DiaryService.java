package com.example.mysqlonazure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.mysqlonazure.dto.DiaryDTO;
import com.example.mysqlonazure.mapper.DiaryMapper;

@Service
public class DiaryService {
  private final DiaryMapper diaryMapper;

  // コンストラクタインジェクション
  public DiaryService(DiaryMapper diaryMapper) {
    this.diaryMapper = diaryMapper;
  }

  // 1件取得
  public DiaryDTO getDiaryById(String id) {
    return diaryMapper.findDiaryById(id);
  }

  // 全件取得
  public List<DiaryDTO> getAllDiaries() {
    return diaryMapper.findAll();
  }
  
}
