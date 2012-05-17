package utest;


import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.Test;

import windowsform.EmployeeForm;
import connect.sqlite.ConnectData;

//TODO add at least on test by main methods of the Employee class

public class test_Employee {

    @Test
    public void test_ExcuteUpdate() {
        System.out.println("ExcuteQuery");
        String sql = "SELECT * FROM Employee";
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
        System.out.println("ExcuteQueryUpdate");
        String sql = "";
        ConnectData instance = new ConnectData();
        boolean expResult = false;
        boolean result = instance.queryExcuteUpdate(sql);
        assertEquals(expResult, result);

    }

    @Test
    public void test_deleteRecord() {
        System.out.println("Delete");
        EmployeeForm instance = new EmployeeForm();
        boolean expResult = true;
        boolean result = instance.delete();
        assertEquals(expResult, result);

    }

    @Test
    public void test_addRecord() {
        System.out.println("Add");
        // String sql = "insert into Task values("+ null +",'Phuong')";
        EmployeeForm instance = new EmployeeForm();
        boolean expResult = true;
        boolean result = instance.addEmp();
        assertEquals(expResult, result);
    }

}
