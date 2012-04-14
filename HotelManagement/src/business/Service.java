package business;

import java.sql.ResultSet;

import connect.sqlite.ConnectData;

public class Service {
	public static ResultSet getAllService(){
		ConnectData cnn = new ConnectData();
		String sql = "select  * from Service";
		cnn.connect();
		ResultSet rs = cnn.ExcuteQuery(sql);
		return rs;
	}
}
