package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.MaterialDao;
import com.example.app.dao.MaterialTypeDao;
import com.example.app.domain.Material;
import com.example.app.domain.MaterialType;

@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	MaterialDao materialDao;

	@Autowired
	MaterialTypeDao materialTypeDao;

	@Override
	public List<Material> getMaterialList() throws Exception {
		return materialDao.selectAll();
	}

	@Override
	public Material getMaterialById(Integer id) throws Exception {
		return materialDao.selectById(id);
	}

	@Override
	public void addMaterial(Material material) throws Exception {
		materialDao.insert(material);

	}

	@Override
	public void editMaterial(Material material) throws Exception {
		materialDao.update(material);

	}

	@Override
	public void deleteMaterial(Integer id) throws Exception {
		materialDao.delete(id);

	}

	@Override
	public List<MaterialType> getTypeList() throws Exception {
		return materialTypeDao.selectAll();
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		double totalNum = (double) materialDao.count();
		return (int) Math.ceil(totalNum / numPerPage);
	}

	@Override
	public List<Material> getMaterialListByPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return materialDao.selectLimited(offset, numPerPage);
	}
}
