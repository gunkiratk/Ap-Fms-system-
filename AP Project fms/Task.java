// package fms;
class Task{
	private String deadline;
	private String status;
	private int taskid;
	private String taskname;
	private String items_used;
	private String time_taken;
	private String comments;
	String def_status = "NOT STARTED";

	public Task(String d, def_status, int tid, String tname, String i, String tt, String c){
		deadline = d;
		status = def_status;
		taskid = tid;
		taskname = tname;
		items_used = i;
		time_taken = tt;
		comments = c;
	}

	public String getDeadline(){
		return deadline;
	}
	public String getStatus(){
		return status;
	}
	public int getTaskid(){
		return taskid;
	}
	public String getTaskname(){
		return taskname;
	}
	public String getItems_used(){
		return items_used;
	}
	public String getTime_taken(){
		return time_taken;
	}
	public String getComments(){
		return comments;
	}

	public void setDeadline(String d){
		this.deadline = d;
	}
	public void setStatus(String s){
		this.status = s;
	}
	public void setTaskid(int tid){
		this.taskid = tid;
	}
	public void setTaskname(String tname){
		this.taskname = tname;
	}
	public void setItems_used(String i){
		this.items_used = i;
	}
	public void setTime_taken(String tt){
		this.time_taken = tt;
	}
	public void setComments(String c){
		this.comments = c;
	}
}
