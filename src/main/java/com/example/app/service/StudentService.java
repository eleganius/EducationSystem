package com.example.app.service;

import com.example.app.domain.Student;

public interface StudentService {

	Student getStudentByLoginId(String loginId) throws Exception;

}
