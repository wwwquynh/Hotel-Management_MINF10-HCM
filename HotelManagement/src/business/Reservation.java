package business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connect.sqlite.ConnectData;
import connect.sqlite.SQLItem;
import connect.sqlite.SQLSupport;

public class Reservation implements IReservation{

	int resID;
	int customerID;
	Date resDate;
	Date resLeaveDate;
	double preTotalCost;
	int numberOfAdult;
	int numberOfChild;
	int resStatus;
	
	ConnectData conn;
	
	public Reservation(){
		
	}
	
	public Reservation(int resID, int customerID, Date resDate, Date resLeaveDate, double preTotalCost, int numberOfAdult, int numberOfChild, int resStatus){
		this.resID = resID;
		this.customerID = customerID;
		this.resDate = resDate;
		this.resLeaveDate = resLeaveDate;
		this.preTotalCost = preTotalCost;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChild = numberOfChild;
		this.resStatus = resStatus;
	}
	public Reservation(int customerID, Date resDate, Date resLeaveDate, double preTotalCost, int numberOfAdult, int numberOfChild, int resStatus){
		this.customerID = customerID;
		this.resDate = resDate;
		this.resLeaveDate = resLeaveDate;
		this.preTotalCost = preTotalCost;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChild = numberOfChild;
		this.resStatus = resStatus;
	}
	@Override
	public boolean addReservation() {
		// TODO Auto-generated method stub
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "resID", null));
		items.add(new SQLItem(1, "customerID", customerID));
		items.add(new SQLItem(1, "resDate", resDate));
		items.add(new SQLItem(1, "resLeaveDate", resLeaveDate));
		items.add(new SQLItem(1, "preTotalCost", preTotalCost));
		items.add(new SQLItem(1, "numberOfAdult", numberOfAdult));
		items.add(new SQLItem(1, "numberOfChild", numberOfChild));
		items.add(new SQLItem(1, "resStatus", resStatus));
		String sql = SQLSupport.prepareAddSql("Reservation", items);
		System.out.println(sql);
		conn = new ConnectData();
		conn.connect();
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
	public boolean updateReservation() {
		// TODO Auto-generated method stub
		return false;
	}

	//update status: 0: reserved. 1: occupied. 2: complete. 3: cancel
	@Override
	public boolean cancelReservation() {
		// TODO Auto-generated method stub
		String sql = "UPDATE Reservation set resStatus = 3 where resID = " + resID;
		conn = new ConnectData();
		conn.connect();
		boolean isOk = conn.queryExcuteUpdate(sql);
		return isOk;
	}

	@Override
	public boolean addServices() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getPreAmount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static ResultSet searchReservation(){
		
		return null;
	}
	
	public static boolean cancelReservation(int resID){
		String sql = "UPDATE Reservation set resStatus = 3 where resID = " + resID;
		ConnectData conn = new ConnectData();
		conn.connect();
		boolean isOk = conn.queryExcuteUpdate(sql);
		return isOk;
	}
} 
