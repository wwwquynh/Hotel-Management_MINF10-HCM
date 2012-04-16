package business;

import java.sql.ResultSet;
import java.sql.SQLException;

import connect.sqlite.ConnectData;

public class Task implements ITask{
	int taskID;
	String taskName;
	ConnectData conn;
	public Task(){
		
	}
	
	public Task(int taskID){
		try {
			this.taskID = taskID;
			String sql = "select  * from Task where taskID = " + taskID;
			conn = new ConnectData();
			conn.connect();
			ResultSet rs = conn.ExcuteQuery(sql);
		
			while(rs.next()){
				
				this.taskName = rs.getString("taskName");
				
			}
			conn.dispose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean addTask() {
		// TODO Auto-generated method stub
		conn = new ConnectData();
		conn.connect();
		String sql ="insert into Task values(null, \""+ taskName +"\")" ;
		boolean isOk = conn.queryExcuteUpdate(sql);
		try {
			conn.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isOk;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public ITask getTask(int taskID) {
		// TODO Auto-generated method stub
		return null;
	}

}
