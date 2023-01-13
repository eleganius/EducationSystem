package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.domain.Material;

@Mapper
public interface MaterialDao {

	List<Material> selectAll() throws Exception;

	Material selectById(Integer id) throws Exception;

	void setDeleteById(Integer id) throws Exception;

	void insert(Material material) throws Exception;

	void update(Material material) throws Exception;

	List<Material> selectLimited(@Param("offset") int offset, @Param("num") int num) throws Exception;

	long countActive() throws Exception;

}
