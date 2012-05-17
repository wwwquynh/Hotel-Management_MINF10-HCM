import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import core.business.Reservation;


public class ReservationTest {

	@Test 
	public void testReservationNotNull(){
		Reservation resv = new Reservation(1, null, null, 10.0, 2, 1, 0, 0);
		assertNotNull(resv);
	}
	@Test 
	public void testDataSelectedFromDB(){
		Reservation resv = new Reservation(1);//select from database
		assertEquals(25, resv.getCustomerID());
		assertEquals(0, resv.getNumberOfChild());
	}
}
