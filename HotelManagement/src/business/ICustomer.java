package business;

public interface ICustomer {
	public boolean addCustomer();
	public boolean updateCustomer();
	public boolean deleteCustomer();
	public int getRowCount();
	public Object getValueAt(int row, int column);
}
