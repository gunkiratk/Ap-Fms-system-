// package fms;
public class Staff extends User{
	private String status;
	Task task;
	Staff()
	{
		super(null,0,null,null,null,null,null,null);
	}
	public void setTask(Task t){
		task = t;
	}
	public Task getTask(){
		return task;
	}
	public String getStatus()
	{
			return status;
	}
	public void setStatus(String s)
	{
		this.status = s;
	}
	public void sendLogisticreq(int id,String quantity,String taskrefid)
	{

	}
	public void sendLeave(Supervisor s,String reason,String dateRange)
	{

	}
	public void updateStatus()
	{

	}
	public void generate_taskreport()
	{
		
	}
}