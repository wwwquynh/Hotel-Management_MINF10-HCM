package utest;


import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.junit.Test;

import windowsform.TaskForm;
import connect.sqlite.ConnectData;

// These tests are similar to the ones in the test_service class (see the comments in test_service)
public class test_task {

    @Test
    public void test_ExcuteUpdate() {
        System.out.println("ExcuteQuery");
        String sql = "SELECT * FROM Task";
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
        TaskForm instance = new TaskForm();
        boolean expResult = true;
        boolean result = instance.deleteRecord();
        assertEquals(expResult, result);

    }

    @Test
    public void test_addRecord() {
        System.out.println("Add");
        // String sql = "insert into Task values("+ null +",'Phuong')";
        TaskForm instance = new TaskForm();
        boolean expResult = true;
        boolean result = instance.addRecord();
        assertEquals(expResult, result);
    }
    /*
    protected static void backupDb() throws IOException {
        copyFile(dbFile, dbBackupFile);

    }

    protected static void restoreDb() throws DbException {
    HmsEntity.DB.closeConnection();
    dbFile.delete();
        dbBackupFile.renameTo(dbFile);
        HmsEntity.DB.createConnection();
    }*/
}
