package connect.sqlite;

import java.util.ArrayList;

public class SQLSupport {
	
	public static String prepareAddSql(String table, ArrayList<SQLItem> items){
		String sql="insert into " + table + "(";
		for(SQLItem o:items){
			
				sql+=o.getName()+", ";
		} 
		sql = sql.substring(0, sql.length()- 2);
		sql+=") values(";
		for(SQLItem o:items){
			if(o.getType() == 1)//khong can ""
			
				sql += o.getValue()+", ";
			else //can ""
				sql += "\"" + o.getValue().toString()+"\", ";
		}
		sql = sql.substring(0, sql.length()- 2);
		sql+=")";
		return sql;
	}
	
	public static String prepareAddSqlAllField(String table, ArrayList<SQLItem> items){
		String sql="insert into " + table + " values(";
		for(SQLItem o:items){
			if(o.getType() == 1)//khong can ""
				sql += o.getValue()+", ";
			else //can ""
				sql += "\"" + o.getValue()+"\", ";
		}
		sql = sql.substring(0, sql.length()- 2);
		sql+=")";
		return sql;
	}
}
