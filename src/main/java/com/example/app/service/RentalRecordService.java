package com.example.app.service;

public interface RentalRecordService {

	// 教材を借りる
	void borrowMaterial(int studentId, int materialId) throws Exception;

	// 教材を返却する
	void returnMaterial(int materialId) throws Exception;

	// 生徒が教材を借りられる状態か判別する
	boolean isAbleToBorrow(int studentId, int limitation) throws Exception;

	// 本人による返却か確認する
	boolean byAuthenticatedStudent(int studentId, int materialId) throws Exception;

}
