package core.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connect.sqlite.ConnectData;
import connect.sqlite.IConnectData;
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
	int bookType;
	ConnectData conn;
	
	public Reservation(){
		
	}
	
	public Reservation(IConnectData cnn){
		this.conn = (ConnectData) cnn;
	}
	
	public Reservation(int resID){
		try {
			this.resID = resID;
			String sql = "select  * from Reservation where resID = " + resID;
			conn = new ConnectData();
			conn.connect();
			ResultSet rs = conn.ExcuteQuery(sql);
		
			while(rs.next()){
				this.resID = resID;
				this.customerID = rs.getInt("customerID");
				this.resDate = rs.getDate("resDate");
				this.resLeaveDate = rs.getDate("resLeaveDate");
				this.preTotalCost = rs.getDouble("preTotalCost");
				this.numberOfAdult = rs.getInt("numberOfAdult");
				this.numberOfChild = rs.getInt("numberOfChild");
				this.resStatus = rs.getInt("resStatus");
				this.bookType = rs.getInt("bookType");
			}
			conn.dispose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getResID() {
		return resID;
	}
	public void setResID(int resID) {
		this.resID = resID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public Date getResDate() {
		return resDate;
	}
	public void setResDate(Date resDate) {
		this.resDate = resDate;
	}
	public Date getResLeaveDate() {
		return resLeaveDate;
	}
	public void setResLeaveDate(Date resLeaveDate) {
		this.resLeaveDate = resLeaveDate;
	}
	public double getPreTotalCost() {
		return preTotalCost;
	}
	public void setPreTotalCost(double preTotalCost) {
		this.preTotalCost = preTotalCost;
	}
	public int getNumberOfAdult() {
		return numberOfAdult;
	}
	public void setNumberOfAdult(int numberOfAdult) {
		this.numberOfAdult = numberOfAdult;
	}
	public int getNumberOfChild() {
		return numberOfChild;
	}
	public void setNumberOfChild(int numberOfChild) {
		this.numberOfChild = numberOfChild;
	}
	public int getResStatus() {
		return resStatus;
	}
	public void setResStatus(int resStatus) {
		this.resStatus = resStatus;
	}
	public Reservation(int resID, int customerID, Date resDate, Date resLeaveDate, double preTotalCost, int numberOfAdult, int numberOfChild, int resStatus, int bookType){
		this.resID = resID;
		this.customerID = customerID;
		this.resDate = resDate;
		this.resLeaveDate = resLeaveDate;
		this.preTotalCost = preTotalCost;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChild = numberOfChild;
		this.resStatus = resStatus;
		this.bookType = bookType;
	}
	public int getBookType() {
		return bookType;
	}

	public void setBookType(int bookType) {
		this.bookType = bookType;
	}

	public Reservation(int customerID, Date resDate, Date resLeaveDate, double preTotalCost, int numberOfAdult, int numberOfChild, int resStatus, int bookType){
		this.customerID = customerID;
		this.resDate = resDate;
		this.resLeaveDate = resLeaveDate;
		this.preTotalCost = preTotalCost;
		this.numberOfAdult = numberOfAdult;
		this.numberOfChild = numberOfChild;
		this.resStatus = resStatus;
		this.bookType = bookType;
	}
	
	public static int addReservationStat(int customerID, String resDate, String resLeaveDate, double preTotalCost, int numberOfAdult, int numberOfChild, int resStatus, int bookType){
		ConnectData conn;
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "resID", null));
		items.add(new SQLItem(1, "customerID", customerID));
		items.add(new SQLItem(2, "resDate", resDate));
		items.add(new SQLItem(2, "resLeaveDate", resLeaveDate));
		items.add(new SQLItem(1, "preTotalCost", preTotalCost));
		items.add(new SQLItem(1, "numberOfAdult", numberOfAdult));
		items.add(new SQLItem(1, "numberOfChild", numberOfChild));
		items.add(new SQLItem(1, "resStatus", resStatus));
		items.add(new SQLItem(1, "bookType", bookType));
		String sql = SQLSupport.prepareAddSql("Reservation", items);
		System.out.println(sql);
		conn = new ConnectData();
		conn.connect();
		int reID = conn.queryExcuteUpdateGenerateKey(sql);
		try {
			conn.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return reID;
	}
	@Override
	public int addReservation() {
		// TODO Auto-generated method stub
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "resID", null));
		items.add(new SQLItem(1, "customerID", customerID));
		items.add(new SQLItem(2, "resDate", resDate));
		items.add(new SQLItem(2, "resLeaveDate", resLeaveDate));
		items.add(new SQLItem(1, "preTotalCost", preTotalCost));
		items.add(new SQLItem(1, "numberOfAdult", numberOfAdult));
		items.add(new SQLItem(1, "numberOfChild", numberOfChild));
		items.add(new SQLItem(1, "resStatus", resStatus));//
		items.add(new SQLItem(1, "bookType", bookType));// 0 la reservation, 1 la checkin
		String sql = SQLSupport.prepareAddSql("Reservation", items);
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
	
	public static ResultSet searchReservation(String custName){
		ConnectData conn = new ConnectData();
		conn.connect();
//		  String colRes[] = {"Reservation ID", "Customer", "Address", "Phone", "Passport/ID", "Room", "From", "To"};
		
		StringBuilder sb = new StringBuilder();
		  sb.append("select res.resID, cus.custName, cus.custAddress, cus.custPhone, cus.custPassport, r.roomName, res.resDate, res.resLeaveDate from Reservation res join Customer cus on res.customerID = cus.custID ");
		  sb.append(" left join ReservationDetail resDet on res.resID = resDet.resID ");
		  sb.append(" left join Room r on r.roomID = resDet.roomID ");
		  sb.append(" where res.bookType = 0 and cus.custName like \"%" + custName + "%\"");
		  String sql = sb.toString();
		  System.out.println(sql);
		return conn.ExcuteQuery(sql);
	}
	
	public static ResultSet getReservationInfo(int resID){
		ConnectData conn = new ConnectData();
		conn.connect();
		String sql = "select * from Reservation res ";
				sql += " left join ReservationDetail resdet on res.resID = resdet.resID ";
				sql += " where res.resID = " + resID;
	
		return conn.ExcuteQuery(sql);		
	}
	
	public static void makeCheckin(int resID){
		String sql = "UPDATE Reservation set resStatus = 1 where resID = " + resID;
		ConnectData conn = new ConnectData();
		conn.connect();
		conn.queryExcuteUpdate(sql);

	}
	
	public static void updateAmount(int resID, double amt){
		String sql = "UPDATE Reservation set preTotalCost = " + amt + " where resID = " + resID;
		ConnectData conn = new ConnectData();
		conn.connect();
		conn.queryExcuteUpdate(sql);
	}
	
	public static void makeComplete(int resID){
		String sql = "UPDATE Reservation set resStatus = 2 where resID = " + resID;
		ConnectData conn = new ConnectData();
		conn.connect();
		conn.queryExcuteUpdate(sql);

	}
	
	public ResultSet getReservatedList() {
		// TODO Auto-generated method stub
		String sql = "Select * from Reservation";
		ResultSet result = null;
		if (conn.connect()){
			result = conn.ExcuteQuery(sql);
		}
		return result;
	}

} 
