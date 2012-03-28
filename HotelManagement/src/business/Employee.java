package business;

import java.util.ArrayList;

public interface Employee {
	
	public boolean addEmployee();
	public Employee getEmployee(int empID);
	public ArrayList<Employee> searchEmployee();
	public int updateEmployee();
	public int updateStatus(int status);
	public int assignTask(ArrayList<Task> tasks);

}
