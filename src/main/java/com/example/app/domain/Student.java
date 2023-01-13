package com.example.app.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Student {

	private Integer id;
	private String name;
	private Date birthday;
	private String loginId;
	private String loginPass;
	private String status;

}
