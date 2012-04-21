package business;

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
}
