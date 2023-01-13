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
@Transactional
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
	public void deleteMaterialById(Integer id) throws Exception {
		materialDao.setDeleteById(id);
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
	public List<Material> getMaterialListPerPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return materialDao.selectLimited(offset, numPerPage);
	}

	@Override
	public int getTotalPages(int numPerPage) throws Exception {
		long count = materialDao.countActive();
		return (int) Math.ceil((double) count / numPerPage);
	}

	@Override
	public List<Material> getBorrowingMaterialList(int studentId) throws Exception {
		return materialDao.selectBorrowingStudentId(studentId);
	}

	@Override
	public List<Material> getBorrowableMaterialListPerPage(int page, int numPerPage) throws Exception {
		int offset = numPerPage * (page - 1);
		return materialDao.selectBorrowableWithOffset(offset, numPerPage);
	}

	@Override
	public int getTotalBorrowableMaterialPages(int numPerPage) throws Exception {
		long count = materialDao.countBorrowable();
		return (int) Math.ceil((double) count / numPerPage);
	}

	@Override
	public boolean isBorrowable(Integer materialId) throws Exception {
		Material material = materialDao.selectById(materialId);

		if (material == null) {
			return false;
		} else if (material.getRentalId() != null) {
			return false;
		}

		return true;
	}

	@Override
	public List<MaterialType> getMaterialTypeList() throws Exception {
		return materialTypeDao.selectAll();
	}

}
