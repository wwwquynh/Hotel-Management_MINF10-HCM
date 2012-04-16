import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import connect.sqlite.ConnectData;


public class test_room {

	@Test
	public void test() throws SQLException {
		//fail("Not yet implemented");
		ConnectData ds=new ConnectData();
		ds.connect();		
		String query_room = "SELECT * FROM Room";
		ResultSet rs=ds.ExcuteQuery(query_room);
		while (rs.next()) {
			System.out.print(rs.getString("roomID") + "\t");
			System.out.print(rs.getString("roomFloor") + "\t");
			System.out.print(rs.getString("roomName") + "\t");
			System.out.print(rs.getString("roomNoOfAdult") + "\t");
			System.out.print(rs.getString("roomNoOfChild") + "\t");
			System.out.print(rs.getString("roomStatusID") + "\t");
			System.out.print(rs.getString("roomFee") + "\t");
			System.out.println();
		}
		ds.dispose();		 
		ConnectData ds1=new ConnectData();
		ds1.connect();
		int idroom= 50;
		int floor=5;
		String roomName="P";
		int roomNoOfAdult=2;
		int roomNoOfChild=4;
		int roomStatusID=3;
		int roomFee=3;				

		String sql_insert="insert into Room values('"+idroom+"','"+roomName+ "','"+ floor+ "','" +roomStatusID+"','"+roomNoOfAdult+"','"+roomNoOfChild+"','"+ roomFee +"')";

		if(ds1.queryExcuteUpdate(sql_insert))
			System.out.print("insert successfull");
		else
			System.out.print("insert fail");
	}

}


