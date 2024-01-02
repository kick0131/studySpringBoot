package com.example.mysqlonazure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.mysqlonazure.dto.DiaryDTO;

@Mapper
public interface DiaryMapper {
    @Select("SELECT * FROM diary WHERE id = #{id}")
    DiaryDTO findDiaryById(@Param("id") String id);

    @Select("SELECT * from diary")
    List<DiaryDTO> findAll();
}

