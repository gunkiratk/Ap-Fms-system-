// package fms;
public class Staff extends User{
	private String status;
	static ArrayList<Staff> leaveAppList = new ArrayList<Staff>();
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
	public String getStatusStaff()
	{
			return status;
	}
	public void setStatusStaff(String s)
	{
		this.status = s;
	}
	public void sendLogisticreq(int id,String quantity,String taskrefid)
	{

	}
	public void sendLeave()
	{

		leaveAppList.add(this);
	}
	public void updateStatus(String stat)
	{
		if(stat.equalsIgnoreCase("In progress")){
			getTask().setStatus("IN PROGRESS");
		}
		else if(stat.equalsIgnoreCase("Finished")){
			getTask().setStatus("FINISHED");
		}
	}
	public void generate_taskreport(){
		

	}
	public void setTaskDatabase(){
		
	}

	public static ArrayList<Staff> getLeaveAppList(){
		return leaveAppList;
	}
}