package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.StudentDao;
import com.example.app.domain.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDao dao;

	@Override
	public Student getStudentByLoginId(String loginId) throws Exception {
		return dao.selectByLoginId(loginId);
	}

}
