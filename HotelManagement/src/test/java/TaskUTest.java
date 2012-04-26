import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;


public class TaskUTest {
	//getTaskName
/////////////////////Room's ID//////////////////////
	
	@Test
	public void setTaskName_Test(){
		business.Task obj = new business.Task();
		String taskName = "tEst Task NAme";
		obj.setTaskName(taskName);
		assertEquals(taskName,obj.getTaskName());
	}
	@Test
	public void setTaskName2_Test(){
		business.Task obj = new business.Task();
		String taskName = "";
		obj.setTaskName(taskName);// expected throwing exception
		assertFalse(obj.getTaskName().equals(taskName));
	}
	@Test
	public void setTaskName3_Test(){
		business.Task obj = new business.Task();
		String taskName = null;
		obj.setTaskName(taskName);// expected throwing exception
		assertFalse(obj.getTaskName() == null);
	}
}
