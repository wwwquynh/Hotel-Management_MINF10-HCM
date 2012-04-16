import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Test;

import java.sql.ResultSet;


import connect.sqlite.ConnectData;
public class test_connectdata {
	Connection conn = null;	
	
	@Test
	public void test_connect() throws ClassNotFoundException, SQLException {		
		String url="jdbc:sqlite:e:\\PUF\\M2\\phuong\\spm\\hotel.s3db";
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection (url);
		if(conn!=null)
		System.out.println ("Connected!");}
	
	
	@Test
    public void test_ExcuteUpdate() {
        System.out.println("ExcuteQuery");
        String sql="SELECT * FROM Room";
        ConnectData instance = new ConnectData();
        ResultSet expResult = null;
        ResultSet result = instance.ExcuteQuery(sql);
        assertEquals(expResult, result);       
    }

    /**
     * Test of ExcuteQueryUpdate method, of class ConnectData.
     */
    @Test
    public void test_queryExcuteUpdate() {
        System.out.println("ExcuteQueryUpdate");
        String sql = "";
        ConnectData instance = new ConnectData();
        boolean expResult = false;
        boolean result = instance.queryExcuteUpdate(sql);
        assertEquals(expResult, result);
       
    }
	
}
