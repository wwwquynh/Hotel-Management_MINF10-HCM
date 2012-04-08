import java.sql.Date;
import java.util.ArrayList;

import connect.sqlite.ConnectData;
import connect.sqlite.SQLItem;
import connect.sqlite.SQLSupport;
import business.Employee;
import business.Reservation;
import business.ReservationDetail;
import business.RoomStatus;
import business.Task;
import business.ITask;


public class ConsoleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		ConnectData cnn = new ConnectData();
		cnn.connect();
		
		CEmployee emp = new CEmployee();
		emp.setEmpName("Nhu Quynh");
		emp.setEmpAddress("HM");
		emp.setEmpPass("nothin");
		emp.setEmpUserName("811");
		emp.setTypeID(1);
		System.out.println(emp.addEmployee());
		CEmployee em2 = new CEmployee(1);
		System.out.println(em2.getEmpName()); 
		
		Task task = new Task();
		task.setTaskName("Laundry");
		task.addTask();
		
		ArrayList<SQLItem> items = new ArrayList<SQLItem>();
		items.add(new SQLItem(1, "resID", resID));
		items.add(new SQLItem(1, "customerID", customerID));
		items.add(new SQLItem(1, "resDate", resDate));
		items.add(new SQLItem(1, "resLeaveDate", resLeaveDate));
		items.add(new SQLItem(1, "preTotalCost", preTotalCost));
		items.add(new SQLItem(1, "numberOfAdult", numberOfAdult));
		items.add(new SQLItem(1, "numberOfChild", numberOfChild));
		items.add(new SQLItem(1, "resStatus", resStatus));
		String sql = SQLSupport.prepareAddSql("Customer", items);
		System.out.println(sql);*/
		
		//Reservation res = new Reservation(1, null, null, 10.0, 2, 0, 0);
		//res.addReservation();Compose the Code Standard for the team
		//Reservation.cancelReservation(1);
		//RoomStatus rs = new RoomStatus("Available");
		//System.out.println(rs.addRoomStatus());
		
		ReservationDetail res = new ReservationDetail(1, 10);
		System.out.println(res.addReservationDetail());
	}

}
