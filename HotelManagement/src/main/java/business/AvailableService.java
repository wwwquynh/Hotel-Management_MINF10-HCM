package business;

import java.sql.ResultSet;

import connect.sqlite.ConnectData;


public class AvailableService {
	int serviceID;
	String serviceName;
	int serviceTypeID;
	double serviceAmount;
	public AvailableService(){
		
	}
	
	public static ResultSet getAvailableServiceByRoom(int roomID){
		ConnectData cnn = new ConnectData();
		String sql = "select ass.serviceID, s.serviceName, s.serviceAmount from AvailableService ass join Service s on s.serviceID = ass.serviceID where roomID = " + roomID;
		cnn.connect();
		ResultSet rs = cnn.ExcuteQuery(sql);
		return rs;
	}
}
