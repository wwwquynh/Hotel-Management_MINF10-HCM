package connect.sqlite;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectData implements IConnectData{
	public Connection conn = null;	
	public boolean connect(){
		try{
			String url="jdbc:sqlite:E:\\PUF\\M2\\phuong\\spm\\hotel.s3db";
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection (url);
			if(conn!=null)
				System.out.println ("Connected!");
			return true;
		}
		catch (Exception e){
			System.err.println ("Fail Connected!");
		}
		return false;
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
	
	public int queryExcuteUpdateGenerateKey(String sql) {

		try{
			Statement stmt=	conn.createStatement();

			int reKey = -1;
			if(stmt.executeUpdate(sql) == 1)
			{
				
				ResultSet rs = stmt.getGeneratedKeys();
				while(rs.next()){
					reKey = (Integer) rs.getObject("last_insert_rowid()");
				}
			}
				return reKey;
		}
		catch(Exception e){
			System.out.print("Unsuccess!");
		}
		return -1;
	}
	
	public void dispose() throws SQLException{
		conn.close();
	}
}


