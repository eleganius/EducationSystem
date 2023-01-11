package com.example.app.service;

import java.util.List;

import com.example.app.domain.Material;
import com.example.app.domain.MaterialType;

public interface MaterialService {

	List<Material> getMaterialList() throws Exception;

	Material getMaterialById(Integer id) throws Exception;

	void addMaterial(Material material) throws Exception;

	void editMaterial(Material material) throws Exception;

	void deleteMaterial(Integer id) throws Exception;

	List<MaterialType> getTypeList() throws Exception;

}
