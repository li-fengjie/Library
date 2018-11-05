package com.life.dao.impl;


import com.life.bean.Admin;
import com.life.dao.AdminDao;
import com.life.utils.DBUtils;

public class AdminDaoImpl implements AdminDao{

	@Override
	public Admin selectAdminByAdminnameAndPassword(String username, String password) {
		Admin user=new DBUtils().getAdmin("select * from admin where id=? and password=?",username,password);
		return user;
	}

}
