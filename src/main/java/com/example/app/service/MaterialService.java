package com.example.app.service;

import java.util.List;

import com.example.app.domain.Material;
import com.example.app.domain.MaterialType;

public interface MaterialService {

	List<Material> getMaterialList() throws Exception;

	Material getMaterialById(Integer id) throws Exception;

	void deleteMaterialById(Integer id) throws Exception;

	void addMaterial(Material material) throws Exception;

	void editMaterial(Material material) throws Exception;

	List<Material> getMaterialListPerPage(int page, int numPerPage) throws Exception;

	int getTotalPages(int numPerPage) throws Exception;

	List<MaterialType> getMaterialTypeList() throws Exception;

}
