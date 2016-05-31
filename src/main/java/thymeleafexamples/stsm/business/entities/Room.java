package thymeleafexamples.stsm.business.entities;

import java.util.*;

// 电影房间列表
// 该房间指的是在特定时空在的电影放映房间
// 即在某电影院，某具体时间段的电影放映时间
// 即场次

public class Room {
	private Integer id = null;
	
	// 限定条件
	
	// 放映影院
	private Integer thearterId = null;
	// 放映影院房间号
	private Integer thearterRoomId = null;
	// 假设房间都为正方形
	// 房间位置长度
	private Integer roomMaxLength = null;
	
	// 电影放映时间，即场次
	private java.util.Date screenTime = null;
	
	// 放映的电影, 可以设置和Movie表的做join后再填入
	private Integer movieId = null;
	
	// 价格
	private Integer price = null;
	
	// 座位表，表示是否电影票被购买
	// 第一个参数是座位编号，具体座位对房间座位长度分别取模，取余数即可
	private Map<Integer, Boolean> seatingTable = new HashMap<Integer, Boolean>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getThearterId() {
		return thearterId;
	}

	public void setThearterId(Integer thearterId) {
		this.thearterId = thearterId;
	}

	public Integer getThearterRoomId() {
		return thearterRoomId;
	}

	public void setThearterRoomId(Integer thearterRoomId) {
		this.thearterRoomId = thearterRoomId;
	}

	public Integer getRoomMaxLength() {
		return roomMaxLength;
	}

	public void setRoomMaxLength(Integer roomMaxLength) {
		this.roomMaxLength = roomMaxLength;
	}

	public java.util.Date getScreenTime() {
		return screenTime;
	}

	public void setScreenTime(java.util.Date screenTime) {
		this.screenTime = screenTime;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public Map<Integer, Boolean> getSeatingTable() {
		return seatingTable;
	}

	public void setSeatingTable(Map<Integer, Boolean> seatingTable) {
		this.seatingTable = seatingTable;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	} 
	
}
