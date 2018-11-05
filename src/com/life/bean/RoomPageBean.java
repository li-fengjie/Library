package com.life.bean;

import java.util.List;

public class RoomPageBean {
	private long pageNo;
	private long pageSum;
	private List<Room> room;
	private int pageSize;
	public long getPageNo() {
		return pageNo;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public long getPageSum() {
		return pageSum;
	}
	public void setPageSum(long pageSum) {
		this.pageSum = pageSum;
	}
	public List<Room> getRoom() {
		return room;
	}
	public void setRoom(List<Room> room) {
		this.room = room;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "RoomPageBean{" +
				"pageNo=" + pageNo +
				", pageSum=" + pageSum +
				", room=" + room +
				", pageSize=" + pageSize +
				'}';
	}
}
