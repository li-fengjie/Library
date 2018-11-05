package com.life.utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class C3P0Utils {
	private static DataSource dataSource= new ComboPooledDataSource();
	public static DataSource getDataSource() {
		return dataSource;
		
	}
	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器忙...");
		}
		
	}
	
}
