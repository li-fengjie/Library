package com.life.service.impl;

import com.life.bean.Admin;
import com.life.dao.AdminDao;
import com.life.dao.impl.AdminDaoImpl;
import com.life.service.AdminService;

public class AdminServiceImpl implements AdminService {

	protected AdminDao AdminDao=new AdminDaoImpl();
	@Override
	public Admin login(String Adminname, String password) {
		Admin Admin=AdminDao.selectAdminByAdminnameAndPassword(Adminname, password);
		return Admin;
	}

}
