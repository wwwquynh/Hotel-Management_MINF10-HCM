package business;

import java.util.ArrayList;

public interface IEmployee {
	
	public boolean addEmployee();
	public IEmployee getEmployee(int empID);
	public ArrayList<IEmployee> searchEmployee();
	public int updateEmployee();
	public int updateStatus(int status);
	public int assignTask(ArrayList<ITask> tasks, int managerID);

}
