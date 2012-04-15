package business;

import connect.sqlite.ConnectData;

public class ReservationDetail {
	int resDetailID;
	int resID;
	int roomID;
	ConnectData conn;
	public ReservationDetail(){
		
	}
	
	public ReservationDetail(int resID, int roomID){
		this.resID = resID;
		this.roomID = roomID;
	}
	
	public int addReservationDetail(){
		int reID = -1;
		String sql = "INSERT into ReservationDetail values(null, " + resID + ", " + roomID + ")";
		System.out.println(sql);
		conn = new ConnectData();
		conn.connect();
		reID = conn.queryExcuteUpdateGenerateKey(sql);
		return reID;
	}

	public int getResDetailID() {
		return resDetailID;
	}

	public void setResDetailID(int resDetailID) {
		this.resDetailID = resDetailID;
	}

	public int getResID() {
		return resID;
	}

	public void setResID(int resID) {
		this.resID = resID;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}
}
