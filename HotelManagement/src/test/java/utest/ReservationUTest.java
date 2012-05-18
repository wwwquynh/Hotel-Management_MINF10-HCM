package utest;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.Test;

import core.business.Reservation;
import connect.sqlite.ConnectData;
import connect.sqlite.IConnectData;

//TODO you should merge all the classes that are in charge of testing the Reservation class (ReservationTest and ReservationUTest)

public class ReservationUTest {
    // getReservatedList()
    @Test
    public void getReservatedListTest() {
        IConnectData mockconnection = EasyMock.createStrictMock(IConnectData.class);

        ResultSet result = null;
        String sql = "Select * from Reservation";
        EasyMock.expect(mockconnection.connect()).andReturn(true);
        EasyMock.expect(mockconnection.ExcuteQuery(sql)).andReturn(result);

        Reservation obj = new Reservation(mockconnection);

        EasyMock.replay(mockconnection);
        obj.getReservatedList();
        EasyMock.verify(mockconnection);

    }

    // addReservation(Customer)
    @Test
    public void addReservation_NullCustomerTest() {
        // TODO this code seems common to most of the methods, you can put it in a method annotated @Before
        IConnectData mockconnection = EasyMock.createNiceMock(IConnectData.class);

        //IReservation obj = new Reservation(mockconnection);

        EasyMock.replay(mockconnection);
        // TODO why the last line is commented?
        // assertEquals(0, obj.addReservation(null));
        EasyMock.verify(mockconnection);
    }

    private void setAllParam(Reservation obj) {
        if (obj == null) {
            return;
        }
        obj.setCustomerID(1);
        obj.setNumberOfAdult(0);
        obj.setNumberOfChild(2);
        obj.setPreTotalCost(100);
        obj.setResDate(new Date());
        obj.setResLeaveDate(new Date());
        obj.setResStatus(1);
    }

    @Test
    public void setResDate_Test() {
        IConnectData mockconnection = new ConnectData();
        Reservation obj = new Reservation(mockconnection);
        Date date = new Date(12345);
        obj.setResDate(date);
        assertNotNull(obj.getResDate());
    }

    @Test
    public void getResDate_Test() {
        
        Reservation obj = new Reservation(1);
        
        assertNotNull(obj.getResDate());
        //obj.setResDate(null);
        //assertNotNull(obj.getResDate());
    }

    
   
    @Test
    public void addReservation_NumberOfAdult_Test() {
        IConnectData mockconnection = new ConnectData();
        Reservation obj = new Reservation(mockconnection);
        setAllParam(obj);
        obj.setNumberOfAdult(0);
        assertTrue(obj.getNumberOfAdult() <= 0);
        // assertTrue(obj.addReservation(new Customer()) < 0);
    }

    @Test
    public void addReservation_ResDate_ResLeaveDate_Test() {
        IConnectData mockconnection = new ConnectData();
        Reservation obj = new Reservation(mockconnection);
        setAllParam(obj);
        obj.setResDate(new Date(2012, 2, 1));
        obj.setResLeaveDate(new Date(2012, 1, 1));
        assertTrue(obj.getResDate().compareTo(obj.getResLeaveDate()) > 0);
        // assertTrue(obj.addReservation(new Customer()) < 0);
    }

    //addReservation
    @Test
    public void addReservation_Test() {
        IConnectData mockconnection = new ConnectData();
        Reservation obj = new Reservation(mockconnection);
        setAllParam(obj);
        obj.setResDate(new Date(2012, 2, 1));
        obj.setResLeaveDate(new Date(2012, 1, 1));
        assertTrue(obj.getResDate().compareTo(obj.getResLeaveDate()) > 0);
        // assertTrue(obj.addReservation(new Customer()) < 0);
    }
}
