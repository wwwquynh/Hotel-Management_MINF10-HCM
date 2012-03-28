package business;

import java.sql.ResultSet;
import java.sql.SQLException;

import connect.sqlite.ConnectData;

public class Room {

	int roomID;
	String roomName;
	int roomFloor;
	int roomNoOfChild;
	int roomNoOfAdult;
	double roomFee;
	int roomStatusID;
	ConnectData conn;
	public Room(){
		
	}
	
	public Room(int roomID){
		try {
			this.roomID = roomID;
			String sql = "select  * from Room where roomID = " + roomID;
			conn = new ConnectData();
			conn.connect();
			ResultSet rs = conn.ExcuteQuery(sql);
		
			while(rs.next()){
				this.roomName = rs.getString("roomName");
				this.roomFloor = rs.getInt("roomFloor");
				this.roomNoOfChild = rs.getInt("roomNoOfChild");
				this.roomNoOfAdult = rs.getInt("roomNoOfAdult");
				this.roomFee = rs.getDouble("empPassword");
				this.roomStatusID = rs.getInt("roomStatusID");
			}
			conn.dispose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//for insert new
	public Room(String roomName, int roomFloor, int roomNoOfChild, int roomNoOfAdult,  double roomFee, int roomStatusID){//for add
		
	}
	
	public static ResultSet getAvailableRoom(){
		return null;
	}
}
