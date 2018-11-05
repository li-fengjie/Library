package com.life.dao;

import com.life.bean.RoomPageBean;
import com.life.bean.Room;
import com.life.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class RoomDao {
	public RoomPageBean queryRoomPageBean(int pageno, int pagesize) {
		QueryRunner qr=new QueryRunner(C3P0Utils.getDataSource());
		String sql="select * from rooms limit ?,?";
		RoomPageBean RoomPageBean=new RoomPageBean();
		try {
			List<Room> Rooms=qr.query(sql, new BeanListHandler<Room>(Room.class),(pageno-1)*pagesize,pagesize);
			RoomPageBean.setPageNo(pageno);
			RoomPageBean.setPageSize(pagesize);
			RoomPageBean.setRoom(Rooms);
			Object object=qr.query("select count(*) from rooms", new ScalarHandler());
			long num=(Long)object;

			num=num/pagesize+(num%pagesize==0?0:1);
			RoomPageBean.setPageSum(num);
			return RoomPageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
