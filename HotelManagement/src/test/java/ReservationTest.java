import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import business.Reservation;

public class ReservationTest {

	@Test 
	public void testReservationNotNull(){
		Reservation resv = new Reservation(1, null, null, 10.0, 2, 1, 0);
		assertNotNull(resv);
	}
	@Test 
	public void testDataSelectedFromDB(){
		Reservation resv = new Reservation(1);//select from database
		assertEquals(2, resv.getCustomerID());
		assertEquals(2, resv.getNumberOfChild());
	}
	
	@Test 
	public void testGenerateError(){
		Reservation resv = new Reservation(1);//select from database
		assertEquals(3, resv.getCustomerID());
		assertEquals(6, resv.getNumberOfChild());
	}
	@Test 
	public void testGenerateError2(){
		Reservation resv = new Reservation(1);//select from database
		assertEquals(3, resv.getCustomerID());
		assertEquals(6, resv.getNumberOfChild());// http://wiki.cloudbees.com/bin/view/DEV/How+to+use+Private+GitHub+Repositories+with+CloudBees
	}
}
