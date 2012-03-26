import java.util.ArrayList;


public interface Employee {
	public int addEmployee(Employee e);
	public Employee getEmployee(int empID);
	public ArrayList<Employee> searchEmployee();
	public int updateCustomer(Employee e);
}
