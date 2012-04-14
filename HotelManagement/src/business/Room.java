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
	int roomOrder;
	public int getRoomOrder() {
		return roomOrder;
	}

	public void setRoomOrder(int roomOrder) {
		this.roomOrder = roomOrder;
	}
	ConnectData conn;
	public Room(){
		
	}
	
	public Room(ResultSet rs){
		try{
			setRoomID(rs.getInt("roomID"));
			setRoomName(rs.getString("roomName"));
			setRoomFloor(rs.getInt("roomFloor"));
			setRoomNoOfAdult(rs.getInt("roomNoOfAdult"));
			setRoomFee(rs.getDouble("roomFee"));
			setRoomNoOfChild(rs.getInt("roomNoOfChild"));
			setRoomOrder(rs.getInt("roomOrder"));
			setRoomStatusID(rs.getInt("roomStatusID"));
		}catch(Exception ex){
			
		}
		
	}
	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomFloor() {
		return roomFloor;
	}

	public void setRoomFloor(int roomFloor) {
		this.roomFloor = roomFloor;
	}

	public int getRoomNoOfChild() {
		return roomNoOfChild;
	}

	public void setRoomNoOfChild(int roomNoOfChild) {
		this.roomNoOfChild = roomNoOfChild;
	}

	public int getRoomNoOfAdult() {
		return roomNoOfAdult;
	}

	public void setRoomNoOfAdult(int roomNoOfAdult) {
		this.roomNoOfAdult = roomNoOfAdult;
	}

	public double getRoomFee() {
		return roomFee;
	}

	public void setRoomFee(double roomFee) {
		this.roomFee = roomFee;
	}

	public int getRoomStatusID() {
		return roomStatusID;
	}

	public void setRoomStatusID(int roomStatusID) {
		this.roomStatusID = roomStatusID;
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
				this.roomFee = rs.getDouble("roomFee");
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
	//room status
	public static ResultSet getAllRoom(){
		ConnectData cnn = new ConnectData();
		String sql = "select  * from Room";
		cnn.connect();
		ResultSet rs = cnn.ExcuteQuery(sql);
		return rs;
	}
	
	public static void updateStatus(int roomID, int roomStatusID){
		ConnectData cnn = new ConnectData();
		String sql = "update Room set roomStatusID = " + roomStatusID;
		cnn.connect();
		cnn.queryExcuteUpdate(sql);	
	}
}
