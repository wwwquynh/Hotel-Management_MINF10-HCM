package core.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import connect.sqlite.ConnectData;

public class ReservationDetailService {
	int resDetailServiceID;
	int resDetailID;
	int serviceID;
	int qtyOfService;
	ConnectData conn;
	public ReservationDetailService(){
		
	}
	
	public ReservationDetailService(int resDetailID, int serviceID, int qtyOfService){
		this.resDetailID = resDetailID;
		this.serviceID = serviceID;
		this.qtyOfService = qtyOfService;
	}
	
	public int addReservationServiceDetail(){
		conn = new ConnectData();
		conn.connect();
		
		String sql ="insert into ReservationDetailServices values(null, "+ serviceID + ", " + qtyOfService + ", " + resDetailID +")";
		System.out.println(sql);
		int reID = conn.queryExcuteUpdateGenerateKey(sql);
		this.resDetailServiceID = reID; 
		try {
			conn.dispose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return reID;
	}
	
	public static ResultSet getExtraServices(int resID){
		ConnectData conn = new ConnectData();
		conn.connect();
		String sql = "select sv.serviceID, sv.serviceName, sv.serviceAmount from ReservationDetailServices rds join ReservationDetail rd ";
				sql += "  on rds.resDetailID = rd.resDetailID ";
				sql += " left join Service sv on rds.serviceID = sv.serviceID ";
				sql += " where rd.resID = " + resID;
	
		return conn.ExcuteQuery(sql);		
	}
}
