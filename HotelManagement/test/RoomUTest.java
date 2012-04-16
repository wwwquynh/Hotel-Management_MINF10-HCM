import static org.junit.Assert.*;


import java.sql.ResultSet;

import org.easymock.EasyMock;
import org.junit.Test;

import business.IRoomManager;
import business.RoomManager;
import connect.sqlite.IConnectData;
import entities.IRoom;
import entities.RoomStatus;


public class RoomUTest {
//getAvailableRoom()
	@Test
	public void getRoomListTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		
		ResultSet result = null;
		String sql = "Select * from Room";
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
		
		RoomManager obj = new RoomManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.getAvailableRoom();
		EasyMock.verify(mockconnection);
		
	}
	
	//getAvailableRoom()
	@Test
	public void getAvailableRoomTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		
		ResultSet result = null;
		String sql = "Select * from Room";
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
		
		RoomManager obj = new RoomManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.getAvailableRoom();
		EasyMock.verify(mockconnection);
		
	}
	//updateRoomStatus(Room room, RoomStatus status)
	@Test
	public void updateRoomStatusTest(){
		IRoomManager mockroom = EasyMock.createMock(IRoomManager.class);
		
		IRoom room = null;
		
		EasyMock.replay(mockroom);
		assertTrue(mockroom.isExist(room));
		assertTrue(mockroom.updateRoomStatus(room, new RoomStatus()));
		EasyMock.verify(mockroom);
	}
	@Test
	public void addRoomTest(){
		IRoomManager mockroom = EasyMock.createMock(IRoomManager.class);
		
		IRoom room = null;
		
		EasyMock.replay(mockroom);
		assertTrue(mockroom.addRoom(room));
		assertTrue(mockroom.isExist(room));
		EasyMock.verify(mockroom);
	}
	@Test(expected=NullPointerException.class)
	public void addRoom_NULLTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		IRoomManager rm = new RoomManager(mockconnection);
		assertFalse(rm.addRoom(null));
		
	}
}
