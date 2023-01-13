package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.MaterialDao;
import com.example.app.dao.RentalRecordDao;
import com.example.app.domain.RentalRecord;

@Service
@Transactional
public class RentalRecordServiceImpl implements RentalRecordService {

	@Autowired
	RentalRecordDao rentalRecordDao;

	@Autowired
	MaterialDao materialDao;

	@Override
	public void borrowMaterial(int studentId, int materialId) throws Exception {
		RentalRecord rentalRecord = new RentalRecord();
		rentalRecord.setStudentId(studentId);
		rentalRecord.setMaterialId(materialId);
		rentalRecordDao.addBorrowedRecord(rentalRecord);
		materialDao.addBorrowedRecord(materialId, rentalRecord.getId());
	}

	@Override
	public void returnMaterial(int materialId) throws Exception {
		rentalRecordDao.addReturnedRecord(materialId);
		materialDao.addReturnedRecord(materialId);
	}

	@Override
	public boolean isAbleToBorrow(int studentId, int limitation) throws Exception {
		return rentalRecordDao.countBorrowingByStudentId(studentId) < limitation;
	}

	@Override
	public boolean byAuthenticatedStudent(int studentId, int materialId) throws Exception {
		RentalRecord record = rentalRecordDao.selectBorrowingRecordByMaterialId(materialId);

		if (record == null) {
			return false;
		}

		return true;
	}

}
