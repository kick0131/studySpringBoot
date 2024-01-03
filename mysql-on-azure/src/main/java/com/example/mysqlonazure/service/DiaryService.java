package com.example.mysqlonazure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mysqlonazure.dto.DiaryDTO;
import com.example.mysqlonazure.mapper.DiaryMapper;

@Service
public class DiaryService {

  @Autowired
  DiaryMapper diaryMapper;

  // 1件取得
  @Transactional(readOnly = true)
  public DiaryDTO getDiaryById(String id) {
    return diaryMapper.findDiaryById(id);
  }

  // 全件取得
  @Transactional(readOnly = true)
  public List<DiaryDTO> getAllDiaries() {
    return diaryMapper.findAll();
  }

}
