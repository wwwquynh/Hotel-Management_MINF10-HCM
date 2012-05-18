package utest;

import org.junit.Assert;
import org.junit.Test;

import core.business.Room;


public class test_room {

    @Test
    public void testSelected(){
    	Room room = new Room(5);
    	Assert.assertEquals(5, room.getRoomID());
    }
    
    @Test
    public void testGetAll(){
    	
    	Assert.assertNotNull(Room.getAllRoom());
    }
    
    @Test
    public void testSelectedName(){
    	Room room = new Room(5);
    	Assert.assertEquals("105", room.getRoomName());
    }
    
}
