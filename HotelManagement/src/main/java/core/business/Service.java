package core.business;

import java.sql.ResultSet;
import java.sql.SQLException;

import connect.sqlite.ConnectData;

public class Service {
	public static ResultSet getAllService(){
		ConnectData cnn = new ConnectData();
		String sql = "select  * from Service";
		cnn.connect();
		ResultSet rs = cnn.ExcuteQuery(sql);
		return rs;
	}
	
	int srvID;
	String srvName;
	public int getSrvID() {
		return srvID;
	}

	public void setSrvID(int srvID) {
		this.srvID = srvID;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public Double getSrvAmount() {
		return srvAmount;
	}

	public void setSrvAmount(Double srvAmount) {
		this.srvAmount = srvAmount;
	}

	Double srvAmount;
	public Service(int srvID){
		try {
			this.srvID = srvID;
			String sql = "select  * from Service where serviceID = " + srvID;
			ConnectData conn = new ConnectData();
			conn.connect();
			ResultSet rs = conn.ExcuteQuery(sql);
		
			while(rs.next()){
				this.srvID = srvID;
				this.srvName = rs.getString("serviceName");
				this.srvAmount = rs.getDouble("serviceAmount");
			}
			conn.dispose();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int addNew(String name, Double amount){
		ConnectData ds=new ConnectData();
		ds.connect();				

		String sql_insert="insert into Service values(null ,'"+name+"',1 , "+amount+")";
		System.out.println(sql_insert);
		return ds.queryExcuteUpdateGenerateKey(sql_insert);
	}
}
