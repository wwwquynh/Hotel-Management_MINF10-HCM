package business;

import java.sql.ResultSet;
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
	
	public Customer(int custID){
		this.custID = custID;
		String sql = "select  * from Customer where custID = " + custID;
		conn = new ConnectData();
		conn.connect();
		ResultSet rs = conn.ExcuteQuery(sql);
		try {
			while(rs.next()){
				this.custName = rs.getString("custName");
				this.custAddress = rs.getString("custAddress");
				this.custPhone = rs.getString("custPhone");
				this.custEmail = rs.getString("custEmail");
				this.custPassport = rs.getString("custPassport");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustPassport() {
		return custPassport;
	}

	public void setCustPassport(String custPassport) {
		this.custPassport = custPassport;
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
