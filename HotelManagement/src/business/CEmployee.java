package business;

import java.util.ArrayList;
import java.util.Date;

import connect.sqlite.ConnectData;

public class CEmployee implements Employee{

	String empName = "";
	String empUserName = "";
	String empPass = "";
	Date empDOB = null;
	String empAddress = "";
	String empPhone = "";
	double empSalary = 0;
	ConnectData conn;
	public CEmployee(){
		
	}
	@Override
	public boolean addEmployee() {
		// TODO Auto-generated method stub
		conn = new ConnectData();
		conn.connect();
		String sql ="insert into Employee values(null, \""+ empName +"\", \"" + empAddress + "\", \""  + empPhone + "\"," + "null, null,\""+empUserName+"\", \"" + empPass +"\")";
		return conn.queryExcuteUpdate(sql);
	}

	@Override
	public Employee getEmployee(int empID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Employee> searchEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatus(int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int assignTask(ArrayList<Task> tasks) {
		// TODO Auto-generated method stub
		return 0;
	}

}
