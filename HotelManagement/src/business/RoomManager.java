package business;

import java.sql.ResultSet;
import java.sql.SQLException;

import connect.sqlite.IConnectData;
import entities.IRoom;
import entities.RoomStatus;

public class RoomManager implements IRoomManager{

	int roomID;
	String roomName;
	int roomFloor;
	int roomNoOfChild;
	int roomNoOfAdult;
	double roomFee;
	int roomStatusID;
	private IConnectData conn;
	public RoomManager(IConnectData conn){
		this.conn = conn;
	}
	
	public RoomManager(int roomID){
		try {
			this.roomID = roomID;
			String sql = "select  * from Room where roomID = " + roomID;
			//conn = new ConnectData();
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
	public RoomManager(String roomName, int roomFloor, int roomNoOfChild, int roomNoOfAdult,  double roomFee, int roomStatusID){//for add
		
	}
	
	public ResultSet getAvailableRoom(){
		return null;
	}
	
	@Override
	public boolean isExist(IRoom room) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRoomStatus(IRoom room, RoomStatus status) {
		// TODO Auto-generated method stub
		if (!isExist(room)){
			// do some thing.
			return false;
		}
		return true;
		// can update the status to room object.
		
	}

	@Override
	public boolean addRoom(IRoom room) throws NullPointerException{
		// TODO Auto-generated method stub
		if (room == null){
			throw new NullPointerException();
		}
		return false;
	}
}
