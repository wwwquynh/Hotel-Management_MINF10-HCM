package connect.sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectData{
	public Connection conn = null;	
	public void connect(){
		try{
			String url="jdbc:sqlite:d:\\Phuong\\hotel.s3db";
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection (url);
			if(conn!=null)
				System.out.println ("Connected!");
		}
		catch (Exception e){
			System.err.println ("Fail Connected!");
		}
	}
	public boolean queryExcuteUpdate(String sql) {

		try{
			Statement stmt=conn.createStatement();
			if(stmt.executeUpdate(sql)==1)
				return true;
		}
		catch(Exception e){
			System.out.print("Unsuccess!");
		}
		return false;
	}

	public ResultSet ExcuteQuery(String sql){
		ResultSet rs=null;
		try{
			Statement stmt=conn.createStatement();
			rs = stmt.executeQuery(sql);
		}
		catch (Exception e){
			System.out.print("");
		}
		return rs;
	}
	public void dispose() throws SQLException{
		conn.close();
	}
}


