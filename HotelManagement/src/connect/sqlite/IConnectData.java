package connect.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnectData {
	public boolean connect();
	public boolean queryExcuteUpdate(String sql) ;
	public ResultSet ExcuteQuery(String sql);
	public void dispose() throws SQLException;
	public int queryExcuteUpdateGenerateKey(String sql);
}
