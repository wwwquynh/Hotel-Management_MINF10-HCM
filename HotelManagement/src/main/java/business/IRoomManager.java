package business;

import java.sql.ResultSet;

public interface IRoomManager {
	public ResultSet getAvailableRoom();
	boolean isExist(IRoom room);
	public boolean updateRoomStatus(IRoom room, RoomStatus status);
	public boolean addRoom(IRoom room) throws NullPointerException;
}
