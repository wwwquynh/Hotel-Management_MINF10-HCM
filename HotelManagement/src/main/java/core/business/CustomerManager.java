package core.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import connect.sqlite.IConnectData;
import connect.sqlite.SQLItem;
import connect.sqlite.SQLSupport;

public class CustomerManager {

	private IConnectData conn;
	public CustomerManager(IConnectData connection) {
		// TODO Auto-generated constructor stub
		conn = connection;
	}
	public void listCustomer() {
		// TODO Auto-generated method stub
		
	}
	public ResultSet searchCustomer(Customer cus) {
		// TODO Auto-generated method stub
		if (cus == null){
			throw new NullPointerException("Search Customer not accept the null parameter.");
		}
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "custID", cus.custID));
		items.add(new SQLItem(2, "custName", cus.custName));
		items.add(new SQLItem(2, "custAddress", cus.custAddress));
		items.add(new SQLItem(2, "custPhone", cus.custPhone));
		items.add(new SQLItem(2, "custEmail", cus.custEmail));
		items.add(new SQLItem(2, "custPassport", cus.custPassport));
		String sql = SQLSupport.prepareAddSql("Customer", items);
		System.out.println(sql);
		ResultSet result = null;
		if (conn.connect()){
			
			result = conn.ExcuteQuery(sql);
			
			try {
				if (result.isLast()){
					result = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = null;
			}
		}
		return result;
	}
	public boolean addNewCustomer(Customer cus) {
		// TODO Auto-generated method stub
		if (cus == null){
			throw new NullPointerException("Can not add new customer with the null object.");
		}
		if (searchCustomer(cus) != null){
			return false;
		}
		return true;
	}
	public boolean deleteCustomer(Customer cus) {
		// TODO Auto-generated method stub
		return true;
	}

}
