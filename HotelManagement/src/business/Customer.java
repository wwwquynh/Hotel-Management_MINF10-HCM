package business;

import java.sql.SQLException;
import java.util.ArrayList;

import connect.sqlite.ConnectData;
import connect.sqlite.SQLItem;
import connect.sqlite.SQLSupport;

public class Customer {
	ConnectData conn;
	int custID;
	String custName;
	String custAddress;
	String custPhone;
	String custEmail;
	String custPassport;
	public Customer(){
		
	}
	public Customer(int custID, String custName, String custAddress, String custPhone, String custEmail, String custPassport){
		this.custID = custID;
		this.custName = custName;
		this.custAddress = custAddress;
		this.custPhone = custPhone;
		this.custEmail = custEmail;
		this.custPassport = custPassport;
	}
	
	public int addNewCustomer(){
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "custID", null));
		items.add(new SQLItem(2, "custName", custName));
		items.add(new SQLItem(2, "custAddress", custAddress));
		items.add(new SQLItem(2, "custPhone", custPhone));
		items.add(new SQLItem(2, "custEmail", custEmail));
		items.add(new SQLItem(2, "custPassport", custPassport));
		String sql = SQLSupport.prepareAddSql("Customer", items);
		System.out.println(sql);
		conn = new ConnectData();
		conn.connect();
		int reID = conn.queryExcuteUpdateGenerateKey(sql);
		try {
			conn.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reID;
	}
}
