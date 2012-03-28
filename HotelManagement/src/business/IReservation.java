package business;

public interface IReservation {
	public boolean addReservation();
	public boolean updateReservation();
	public boolean cancelReservation();
	public boolean addServices();
	public double getPreAmount();
	//public static ResultSet getReservation();
}
