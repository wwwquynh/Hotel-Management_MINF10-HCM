package utest;


import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.Test;


import connect.sqlite.ConnectData;

public class test_service {
    @Test
    public void test_ExcuteUpdate() {
        // TODO do not put "println" inside the test code
        System.out.println("ExcuteQuery");
        String sql = "SELECT * FROM service";
        // TODO I do not understand this test!
        ConnectData instance = new ConnectData();
        ResultSet expResult = null;
        ResultSet result = instance.ExcuteQuery(sql);
        assertEquals(expResult, result);
    }

    /**
     * Test of ExcuteQueryUpdate method, of class ConnectData.
     */
    @Test
    public void test_falseExcuteUpdate() {
        // TODO do not put "println" inside the test code
        System.out.println("ExcuteQueryUpdate");
        String sql = "";
        ConnectData instance = new ConnectData();
        boolean expResult = false;
        // TODO I do not understand this test!
        boolean result = instance.queryExcuteUpdate(sql);
        assertEquals(expResult, result);

    }

    // TODO I do not understand the two following tests (do you just check if the method call was successful?) It looks
    // like C, return codes are usually used in languages like C where there is no exception mechanism to deal with
    // errors)

    @Test
    public void test_deleteRecord() {
    	/*
        System.out.println("Delete");
        
        ServiceForm instance = new ServiceForm();
        boolean expResult = true;
        boolean result = instance.deleteRecord();
        assertEquals(expResult, result);
	*/
    }

    @Test
    public void test_addRecord() {
    	/*
        System.out.println("Add");

        ServiceForm instance = new ServiceForm();
        boolean expResult = true;
        boolean result = instance.addRecord();
        assertEquals(expResult, result);
        */
    }
}
