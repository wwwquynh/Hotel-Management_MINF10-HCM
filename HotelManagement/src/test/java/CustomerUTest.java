import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.easymock.EasyMock;
import org.junit.Test;


import connect.sqlite.IConnectData;
import core.business.ICustomer;



public class CustomerUTest {
//searchCustomer()
	/*
	@Test
	public void searchCustomer_StrictTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		
		ResultSet result = null;
		String sql = "Select * from customer";
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
		
		CustomerManager obj = new CustomerManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.searchCustomer(new Customer());
		EasyMock.verify(mockconnection);
		
	}
	
	@Test(expected=NullPointerException.class)
	public void searchCustomer_NullParamTest(){
		IConnectData mockconnection = EasyMock.createNiceMock(IConnectData.class);
		
		CustomerManager obj = new CustomerManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.searchCustomer(null);
		EasyMock.verify(mockconnection);
	}
	
	@Test
	public void searchCustomer_Test(){
		IConnectData mockconnection = EasyMock.createNiceMock(IConnectData.class);
		
		CustomerManager obj = new CustomerManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		assertFalse(obj.searchCustomer(new Customer()));
		EasyMock.verify(mockconnection);
	}

	//addNewCustomer()
	@Test(expected=NullPointerException.class)
	public void addNewCustomer_NullParamTest(){
		IConnectData mockconnection = EasyMock.createNiceMock(IConnectData.class);
		
		CustomerManager obj = new CustomerManager(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.addNewCustomer(null);
		EasyMock.verify(mockconnection);
	}
	@Test
	public void addNewCustomer_ExistTest(){
		IConnectData conn = null;
		ICustomerManager mockcustomermanager = new CustomerManager(conn);
		
		Customer customer = new Customer();
		
		assertTrue(mockcustomermanager.searchCustomer(customer));
		assertFalse(mockcustomermanager.addNewCustomer(customer));
	}
	
	@Test
	public void addNewCustomer_SuccessTest(){
		IConnectData conn = null;
		ICustomerManager mockcustomermanager = new CustomerManager(conn);
		
		Customer customer = new Customer();
		
		assertFalse(mockcustomermanager.searchCustomer(customer));
		assertTrue(mockcustomermanager.addNewCustomer(customer));
	}
		*/
}
