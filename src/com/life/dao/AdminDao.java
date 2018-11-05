package com.life.dao;

import com.life.bean.Admin;

//dao
public interface AdminDao {
	Admin selectAdminByAdminnameAndPassword(String username, String password);
}
