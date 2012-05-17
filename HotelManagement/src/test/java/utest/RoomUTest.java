package utest;


import static org.junit.Assert.assertFalse;

import java.sql.ResultSet;

import org.easymock.EasyMock;
import org.junit.Test;

import core.business.IRoom;
import core.business.IRoomManager;
import core.business.RoomManager;
import core.business.RoomStatus;
import connect.sqlite.IConnectData;

//TODO there are not enough tests for the Room class
public class RoomUTest {
    // getAvailableRoom()
    @Test
    public void getRoomListTest() {
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

    // getAvailableRoom()
    @Test
    public void getAvailableRoomTest() {
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

    // updateRoomStatus(Room room, RoomStatus status)
    private class TmpTestRoom{
    	private IRoomManager rm;
    	public TmpTestRoom(IRoomManager rmanager){
    		this.rm = rmanager;
    	}
    	public void run_UpdateRoomStatus_NullRoom(IRoom room, RoomStatus status){
    		if(rm.isExist(room))
    			rm.updateRoomStatus(room, status);
    	}
    }
    @Test
    public void updateRoomStatusTest() {
    	
        IRoomManager mockroommamanger = EasyMock.createMock(IRoomManager.class);
        RoomStatus status = new RoomStatus(); 
        IRoom room = null;
        EasyMock.expect(mockroommamanger.isExist(room)).andReturn(true);
        EasyMock.expect(mockroommamanger.updateRoomStatus(room,status )).andReturn(false);
        
        
        TmpTestRoom tmp = new TmpTestRoom(mockroommamanger);

        EasyMock.replay(mockroommamanger);
        tmp.run_UpdateRoomStatus_NullRoom(null, status);
        EasyMock.verify(mockroommamanger);
    }

    @Test(expected = NullPointerException.class)
    public void addRoom_NULLTest() {
        IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
        IRoomManager rm = new RoomManager(mockconnection);
        assertFalse(rm.addRoom(null));

    }
}
