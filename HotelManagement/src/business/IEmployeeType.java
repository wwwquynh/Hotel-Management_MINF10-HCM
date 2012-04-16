package business;

import java.sql.ResultSet;

public interface IEmployeeType {
	public boolean addEmp();
	public boolean update();
	public boolean delete();
	public ResultSet find(int ID);
}
