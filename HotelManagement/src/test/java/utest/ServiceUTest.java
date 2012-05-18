package utest;

import java.sql.ResultSet;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import connect.sqlite.IConnectData;

import core.business.Service;


//TODO why is it commented?
public class ServiceUTest {
	@Test
	public void getService(){
		core.business.Service srv = new core.business.Service(1);
		Assert.assertEquals("Massage", srv.getSrvName());
		
	}
    // getAvailableServices()
    
      @Test public void getAvailableServicesTest(){ IConnectData mockconnection =
      EasyMock.createStrictMock(IConnectData.class);
      
      ResultSet result = null; String sql = "Select * from Service";
      EasyMock.expect(mockconnection.connect()).andReturn(true);
      EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
      
     // Service obj = new Service();
      
      //EasyMock.replay(mockconnection); 
      //obj.getAvailableServices(); 
      //EasyMock.verify(mockconnection);
      
      }
      
      @Test
      public void getAllService(){
    	  Assert.assertNotNull(Service.getAllService());
      }
      
      @Test
      public void testAddAndGet(){
    	  int id = Service.addNew("Test", 50.0);
    	  
    	  Service srv = new Service(id);
    	  Assert.assertEquals("Test", srv.getSrvName());
      }
     
}
