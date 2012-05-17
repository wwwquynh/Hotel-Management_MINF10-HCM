import java.sql.ResultSet;

import org.easymock.EasyMock;
import org.junit.Test;



import connect.sqlite.IConnectData;
import core.business.Service;


public class ServiceUTest {
//getAvailableServices()
	/*
	@Test
	public void getAvailableServicesTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		
		ResultSet result = null;
		String sql = "Select * from Service";
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
		
		Service obj = new Service(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.getAvailableServices();
		EasyMock.verify(mockconnection);
		
	}
	*/
}
