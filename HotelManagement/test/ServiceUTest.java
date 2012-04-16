import java.sql.ResultSet;

import org.easymock.EasyMock;
import org.junit.Test;

import business.ServiceManager;
import connect.sqlite.IConnectData;


public class ServiceUTest {
//getAvailableServices()
	@Test
	public void getAvailableServicesTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		
		ResultSet result = null;
		String sql = "Select * from Service";
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
		
		ServiceManager obj = new ServiceManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.getAvailableServices();
		EasyMock.verify(mockconnection);
		
	}
}
