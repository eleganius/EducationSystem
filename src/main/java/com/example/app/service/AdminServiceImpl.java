package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dao.AdminDao;
import com.example.app.domain.Admin;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao dao;

	@Override
	public Admin getAdminByLoginId(String loginId) throws Exception {
		return dao.selectByLoginId(loginId);
	}

}
