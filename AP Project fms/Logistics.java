public class Logistics{
	private String logId;
	private String taskId;
	private ArrayList<String> item_quan;
	public Logistics(String id, String tid){
		logId = id;
		taskId = tid;
	}
	public void setLogId(String id){
		logId = id;
	}
	public void setTaskId(String tid){
		taskId = tid;
	}
	public String getLogId(){
		return logId;
	}
	public String getTaskId(){
		return taskId;
	}
}