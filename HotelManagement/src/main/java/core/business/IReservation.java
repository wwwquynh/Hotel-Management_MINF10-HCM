package core.business;

public interface IReservation {
	public int addReservation();
	public boolean updateReservation();
	public boolean cancelReservation();
	public boolean addServices();
	public double getPreAmount();
	//public static ResultSet getReservation();
}
