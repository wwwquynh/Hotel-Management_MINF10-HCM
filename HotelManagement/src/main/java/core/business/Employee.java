package core.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import connect.sqlite.ConnectData;

public class Employee implements IEmployee{

	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpUserName() {
		return empUserName;
	}
	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}
	public String getEmpPass() {
		return empPass;
	}
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}
	public Date getEmpEmpDate() {
		return empEmpDate;
	}
	public void setEmpEmpDate(Date empEmpDate) {
		this.empEmpDate = empEmpDate;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public double getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}
	public int getTypeID() {
		return empTypeID;
	}
	public void setTypeID(int typeID) {
		this.empTypeID = typeID;
	}
	int empID;
	String empName = "";
	String empUserName = "";
	String empPass = "";
	Date empEmpDate = null;
	String empAddress = "";
	String empPhone = "";
	double empSalary = 0;
	int empTypeID;
	ConnectData conn;
	public Employee(){
		
	}
	public Employee(int empID, String empName, String empAddress, String empPhone,  String empUserName, String empPass, int empType){
		this.empID = empID;
		this.empName = empName;
		this.empAddress = empAddress;
		this.empPhone = empPhone;
		this.empUserName = empUserName;
		this.empPass = empPass;
		this.empTypeID = empType;
	}
	
	public Employee(int empID){
		try {
			this.empID = empID;
			String sql = "select  * from Employee where empID = " + empID;
			conn = new ConnectData();
			conn.connect();
			ResultSet rs = conn.ExcuteQuery(sql);
		
			while(rs.next()){
				this.empName = rs.getString("empName");
				this.empAddress = rs.getString("empAddress");
				this.empPhone = rs.getString("empPhone");
				this.empUserName = rs.getString("empUserName");
				this.empPass = rs.getString("empPassword");
				this.empTypeID = rs.getInt("empTypeID");
			}
			conn.dispose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean addEmployee() {
		// TODO Auto-generated method stub
		conn = new ConnectData();
		conn.connect();
		String sql ="insert into Employee values(null, \""+ empName +"\", \"" + empAddress + "\", \""  + empPhone + "\"," + "null, null,\""+empUserName+"\", \"" + empPass +"\")";
		boolean isOk = conn.queryExcuteUpdate(sql);
		try {
			conn.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isOk;
	}

	@Override
	public IEmployee getEmployee(int empID) {
		
		return null;
	}

	@Override
	public ArrayList<IEmployee> searchEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmployee() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStatus(int status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int assignTask(ArrayList<ITask> tasks, int managerID) {
		// TODO Auto-generated method stub
		//add master
		String sql = "insert into AssignTask value(null, "+ managerID +","+ this.empID+")";
		conn = new ConnectData();
		conn.connect();
		boolean isOk = conn.queryExcuteUpdate(sql);
		if(isOk){
			/*
			for(ITask t:tasks)
			{
				
			}
			*/
		}
		
		return 0;
	}
	
	public static ResultSet searchEmployee(String empName){
		return null;
	}
	
	public static boolean login(String uname, String pass){
		try {
			
			String sql = "select  * from Employee where empUserName = '" + uname + "' and empPassword = '" + pass + "'";
			ConnectData conn = new ConnectData();
			conn.connect();
			ResultSet rs = conn.ExcuteQuery(sql);
		
			while(rs.next()){
				JOptionPane.showMessageDialog(null, "Halo " + rs.getString("empName"));
				return true;
			}
			JOptionPane.showMessageDialog(null, "Invalid username, password");
			conn.dispose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
