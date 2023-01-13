package com.example.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class RentalRecord {

	private Integer id;
	private Integer studentId;
	private Integer materialId;
	private Date borrowedAt;
	private Date returnedAt;

	// 貸し出し記録表示用
	private String studentName;

}
