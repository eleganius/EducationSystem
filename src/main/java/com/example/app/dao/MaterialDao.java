package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.Material;

@Mapper
public interface MaterialDao {

	List<Material> selectAll() throws Exception;

	Material selectById(Integer id) throws Exception;

	void insert(Material material) throws Exception;

	void update(Material material) throws Exception;

	void delete(Integer id) throws Exception;

}
