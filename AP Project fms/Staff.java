// package fms;
public class Staff extends User{
	private String status;
	Task task = new Task(null,null,0,null,null,null,null);
	Staff()
	{
		super(0,null,null,null,null,null,null);
	}
	public String getStatus()
	{
			return status;
	}
	public void setStatus(String s)
	{
		
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