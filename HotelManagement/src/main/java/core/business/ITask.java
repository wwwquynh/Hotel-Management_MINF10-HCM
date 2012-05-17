package core.business;

public interface ITask {
	public int getTaskID();
	public String getTaskName();
	public boolean addTask();
	public ITask getTask(int taskID);
}
