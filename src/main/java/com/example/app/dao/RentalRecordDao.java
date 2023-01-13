package com.example.app.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.domain.RentalRecord;

@Mapper
public interface RentalRecordDao {

	// 借りるに対応する処理：生徒ID、教材ID、貸し出し日を記録
	void addBorrowedRecord(RentalRecord rentalRecord) throws Exception;

	// 返却に対応する処理：返却日を記録
	void addReturnedRecord(int materialId) throws Exception;

	// ある生徒の未返却の教材数
	int countBorrowingByStudentId(int studentId) throws Exception;

	// 教材IDから現在の貸し出し状況（どの生徒が借りているか）を取得
	RentalRecord selectBorrowingRecordByMaterialId(int materialId) throws Exception;

}
