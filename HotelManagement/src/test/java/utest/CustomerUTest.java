package utest;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.*;

import core.business.Customer;
import core.business.CustomerManager;

import connect.sqlite.ConnectData;
import connect.sqlite.IConnectData;
import connect.sqlite.SQLItem;
import connect.sqlite.SQLSupport;

//TODO Why are all the tests in comments?

public class CustomerUTest {
    // searchCustomer()
    
	@Test 
	public void searchCustomer_StrictTest(){ 
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);

		ResultSet result = null; 
		Customer cus = new Customer(0, "TestName", "TestAddress", "TestPhone", "TestEmail", "TestPassport");
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "custID", 0));
		items.add(new SQLItem(2, "custName", "TestName"));
		items.add(new SQLItem(2, "custAddress", "TestAddress"));
		items.add(new SQLItem(2, "custPhone", "TestPhone"));
		items.add(new SQLItem(2, "custEmail", "TestEmail"));
		items.add(new SQLItem(2, "custPassport", "TestPassport"));
		String sql = SQLSupport.prepareAddSql("Customer", items);
		System.out.println(sql);
		
		
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);

		
		CustomerManager obj = new CustomerManager(mockconnection);
		
		
		EasyMock.replay(mockconnection); 
		try{
			obj.searchCustomer(cus);
		}catch(Exception e){
			
		}
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
		Customer cus = new Customer(-1, "", "", "", "", "");
		EasyMock.replay(mockconnection); 
		assertFalse(obj.searchCustomer(cus) == null);
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
		IConnectData conn = new ConnectData(); 
		CustomerManager mockcustomermanager = new CustomerManager(conn);

		
		Customer cus = new Customer(0, "TestName", "TestAddress", "TestPhone", "TestEmail", "TestPassport");
		try{
			assertTrue(mockcustomermanager.searchCustomer(cus)!= null);
			assertFalse(mockcustomermanager.addNewCustomer(cus)); 
		}catch(Exception e){
			
		}
		
	}

	@Test 
	public void addNewCustomer_SuccessTest(){ 
		IConnectData conn = new ConnectData(); 
		
		CustomerManager mockcustomermanager = new CustomerManager(conn);

		Customer cus = new Customer(0, "TestName1", "TestAddress1", "TestPhone1", "TestEmail1", "TestPassport1");
		try{
			assertFalse(mockcustomermanager.searchCustomer(cus)==null);
			assertTrue(mockcustomermanager.addNewCustomer(cus)); 
			mockcustomermanager.deleteCustomer(cus); 
		}catch(Exception e){
			
		}
		
	}
}
