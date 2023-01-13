package com.example.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Student;

@Mapper
public interface StudentDao {

	Student selectByLoginId(String loginId) throws Exception;

}
