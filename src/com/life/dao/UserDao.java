package com.life.dao;

import java.sql.SQLException;
import java.util.List;

import com.life.bean.PageBean;
import com.life.bean.User;
import com.life.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class UserDao {
	public PageBean queryPageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from user limit ?,?";
		PageBean pageBean=new PageBean();
		try {
			List<User> users=qr.query(sql, new BeanListHandler<User>(User.class),(pageno-1)*pagesize,pagesize);
			pageBean.setPageNo(pageno);
			pageBean.setPageSize(pagesize);
			pageBean.setUser(users);
			Object object=qr.query("select count(*) from user", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			pageBean.setPageSum(num);
			return pageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insertToUser(String id,String college,String grade,String profession,String classes,String name) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="insert into user(id,college,grade,profession,classes,name) values(?,?,?,?,?,?)";
		try {
			qr.update(sql,id,college,grade,profession,classes,name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	(id, college,grade,profession,classes,name);
	public void updateUser(String id,String college,String grade,String profession,String classes,String name) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="update user set college=?,grade=?,profession=?,classes=?,name=? where id=?";
		try {
			qr.update(sql,college,grade,profession,classes,name,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUser(String id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="delete from user where id=?";
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public User selectUser(String id) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from user where id=?";
		try {
			User users = qr.query(sql, new BeanHandler<User>(User.class),id);
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
