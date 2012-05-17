package core.business;

import java.sql.ResultSet;

public interface IReservation {
	public int addReservation();
	public boolean updateReservation();
	public boolean cancelReservation();
	public boolean addServices();
	public double getPreAmount();
	public ResultSet getReservatedList();

	//public static ResultSet getReservation();
}
