package business;

import java.sql.SQLException;

import connect.sqlite.ConnectData;

public class RoomStatus {
	int roomStatusID;
	String roomStatusName;
	ConnectData conn;
	public RoomStatus(){
		
	}
	
	public RoomStatus(String roomStatusName){
		
		this.roomStatusName = roomStatusName;
	}
	
	public int addRoomStatus(){
		conn = new ConnectData();
		conn.connect();
		String sql ="insert into RoomStatus values(null, \""+ roomStatusName +"\")";
		int isOk = conn.queryExcuteUpdateGenerateKey(sql);
		
		try {
			conn.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isOk;
	}
	
}
