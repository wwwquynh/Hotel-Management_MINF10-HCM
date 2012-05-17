




import static org.junit.Assert.*;


import java.sql.ResultSet;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;


import connect.sqlite.ConnectData;
import connect.sqlite.IConnectData;
import core.business.Employee;
import core.business.Reservation;

public class EmployeeUTest {
	

	/*@Test
	public void getListTest(){
		IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);
		
		ResultSet result = null;
		String sql = "Select * from Employee";
		EasyMock.expect(mockconnection.connect()).andReturn(true);
		EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);
		
		Employee obj = new Employee(mockconnection);
		
		EasyMock.replay(mockconnection);
		obj.searchEmployee();
		EasyMock.verify(mockconnection);
		
	}*/
	

	/////////////////////Employee's Name//////////////////////
	
	@Test
	public void setempName_Test(){
		Employee obj = new Employee();
		obj.setEmpName("abc");
		assertTrue(obj.getEmpName().equals("abc"));
	}
	@Test(expected=NullPointerException.class)
	public void setempName_Null_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpName(null);
	}
	@Test(expected=NullPointerException.class)
	public void setempName_Null_String_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpName("");
	}
	@Test(expected=NullPointerException.class)
	public void getempName_Null_Exception_Test(){
		Employee obj = new Employee();
		obj.getEmpName();
	}
	@Test
	public void getempName_Test(){
		
		Employee obj = new Employee();
		assertNotNull(obj.getEmpName());
		try{
			obj.setEmpName(null);
		}catch(NullPointerException e){
			
		}
		assertNotNull(obj.getEmpName());
	}
	/////////////////////Employee's address//////////////////////
	@Test
	public void setempAddress_Test(){
		Employee obj = new Employee();
		String address = "Emp address is here.";
		obj.setEmpAddress(address);
		assertTrue(obj.getEmpName().equals(address));
	}
	@Test(expected=NullPointerException.class)
	public void setempAddress_NameNullException_Test(){
		Employee obj = new Employee();
		obj.setEmpAddress(null);
	}
	@Test
	public void getempAddress_Null_Test(){
		Employee obj = new Employee();
		assertEquals("",  obj.getEmpAddress());
	}
/////////////////////Employee's Phone//////////////////////
	@Test
	public void setempPhone_Test(){
		Employee obj = new Employee();
		String str = "0123456789";
		obj.setEmpPhone(str);
		assertTrue(obj.getEmpName().equals(str));
	}
	/*@Test(expected=FormatNotMatchException.class)
	public void getempAddress_Format_Test(){
		Employee obj = new Employee();
		String str = "abcdef";
		obj.setEmpPhone(str);
	}*/
/////////////////////Employee's UserName//////////////////////
	@Test
	public void setempUserName_Test(){
		Employee obj = new Employee();
		String str = "0123456789";
		obj.setEmpUserName(str);
		assertTrue(obj.getEmpUserName().equals(str));
	}
	@Test(expected=NullPointerException.class)
	public void setempUserName_NULL_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpUserName(null);
	}
	@Test(expected=NullPointerException.class)
	public void setempUserName_NULLString_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpUserName("");
	}
	/*@Test(expected=FormatNotMatchException.class)
	public void setempUserName_WhiteSpace_Exception_Test(){
		Employee obj = new Employee();
		String str = "this is username.";
		obj.setEmpUserName(str);
	}*/
	
/////////////////////Employee's Password//////////////////////
	@Test
	public void setempPass_Test(){
		Employee obj = new Employee();
		String str = "0123456789";
		obj.setEmpPass(str);
		assertTrue(obj.getEmpPass().equals(str));
	}
	@Test(expected=NullPointerException.class)
	public void setempPass_NULL_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpPass(null);
	}
	@Test(expected=NullPointerException.class)
	public void setempPass_NULLString_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpPass("");
	}
	/*@Test(expected=FormatNotMatchException.class)
	public void setempPass_Length_Exception_Test(){
		Employee obj = new Employee();
		String str = "abc";
		assertTrue(str.length() < 6);
		obj.setEmpUserName(str);
	}*/
	@Test
	public void setempPass_Length2_Exception_Test(){
		Employee obj = new Employee();
		String str = "abc123";
		assertTrue(str.length() >= 6);
		obj.setEmpPass(str);
		assertTrue(obj.getEmpPass().equals(str));
	}
	
/////////////////////Employee's Salary//////////////////////
	@Test
	public void setempSalary_Test(){
		Employee obj = new Employee();
		obj.setEmpSalary(10);
		assertTrue(10F==obj.getEmpSalary());
		
	}
	/*@Test(expected=FormatNotMatchException.class)
	public void setempSalary_Negative_Test(){
		Employee obj = new Employee();
		obj.setEmpSalary(-1);
	}*/
	@Test(expected=NullPointerException.class)
	public void setempSalary_ZERO_Exception_Test(){
		Employee obj = new Employee();
		obj.setEmpSalary(0);
	}
	
/////////////////////Employee's getEmployee()//////////////////////
	@Test
	public void getEmployee_NotFound_Test(){
		Employee obj = new Employee();
		assertNull(obj.getEmployee(-1));
	}
	@Test
	public void getEmployee_Exist_Test(){
		Employee obj = new Employee();
		// obj.setEmpID(1);
		//"insert into Employee values(null, \""+ empName +"\", \"" + empAddress + "\", \""  + empPhone + "\"," + "null, null,\""+empUserName+"\", \"" + empPass +"\")";
		obj.setEmpName("aaa");
		obj.setEmpAddress("aaa");
		obj.setEmpPhone("123456");
		obj.setEmpUserName("aaa");
		obj.setEmpPass("aaaaaa");
		assertTrue(obj.addEmployee());
		assertNotNull(obj.getEmployee(1));
		//obj.removeEmployee(1);
	}

}
