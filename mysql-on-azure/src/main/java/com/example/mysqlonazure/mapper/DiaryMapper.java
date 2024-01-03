package com.example.mysqlonazure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.example.mysqlonazure.dto.DiaryDTO;

@Mapper
public interface DiaryMapper {
    DiaryDTO findDiaryById(String id);
    List<DiaryDTO> findAll();
}
