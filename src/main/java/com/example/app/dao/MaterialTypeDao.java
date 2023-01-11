package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.MaterialType;

@Mapper
public interface MaterialTypeDao {

	List<MaterialType> selectAll() throws Exception;

}
