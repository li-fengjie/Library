package com.life.service;


import com.life.bean.Admin;

public interface AdminService {
    //	登陆的业务逻辑
    Admin login(String username, String password);
}
