// package fms;
public class Staff extends User{
	private String status;
	Task task;
	Staff(String n,String i, String u, String d, String a, String de, String t,String p)
	{
		super(n,i,u,d,a,de,t,p);
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
	public void setTaskDatabase(){
		
	}
}