// Gunkirat(2015032),Shaan(2015090)
import java.util.*;
public class Logistics{
	private String logId;
	private String taskId;
	static ArrayList<String> item_quan;
	public Logistics(String tid){
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
	public void addItem_quan(String i){
		item_quan.add(i);
	}
	public static ArrayList<String> getItem_quan(){
		return item_quan;
	}
	public String toString(){
		return "Task ID : " + getTaskId() + "\n" + "Logistics ID : " + getLogId() + "\n" + "Items-Quantities" + getItem_quan() + "\n";
	}
}