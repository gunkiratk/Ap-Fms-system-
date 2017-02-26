// package fms;
// Gunkirat(2015032),Shaan(2015090)
import java.util.*;
import java.sql.*;
import java.io.*;
public class Staff extends User{
	private String status;
	Task task;
	static ArrayList<Staff> leaveAppList = new ArrayList<Staff>();
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
	public void sendLogisticreq()
	{
		
	}
	public void sendLeave()
	{

		leaveAppList.add(this);
		System.out.println(leaveAppList);
		System.out.println("Leave");
	}
	public void updateStatus(String status,Staff st)
	{
		if(status.equalsIgnoreCase("In progress")){
			st.setStatusStaff("IN PROGRESS");
			System.out.println("status to progress");
		}
		else if(status.equalsIgnoreCase("Finished")){
			st.setStatusStaff("FINISHED");
			System.out.println("Status send to finished");
		}

	}
	public int generate_taskreport(String taskname)
	{	

			Task t=null;
		BufferedReader br_2=null;
		try{
			br_2=new BufferedReader(new FileReader("task_super.csv"));
			String line_1;
			while((line_1=br_2.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			if(details[0].equalsIgnoreCase(taskname))
				t=new Task(details[0],details[1],details[2],details[3],details[4],details[5],details[6]);
				
			}
		}

		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
                try {
                br_2.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	
		int count=0;
		
		BufferedWriter br_1 = null;
			try{
				count=0;
			br_1 = new BufferedWriter(new FileWriter("taskreport.txt"));

			if(t.getStatus().equalsIgnoreCase("COMPLETE"))
			{
				br_1.write(t.getTaskname()+","+t.getStatus()+","+t.getTaskid()+","+t.getDeadline()+","+t.getItems_used()+","+t.getTime_taken()+","+t.getComments()+"\n");
				count++;
			}
			
		}
		
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
                try {
                br_1.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	
        	if(count==1)
        		return 1;
        	else
        		return 0;
		
	}

	public void sendLogisticreq(String item_quan,String tfid)
	{
		Random ran=new Random();
		int n=ran.nextInt(100)+1;
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("logistic_staff.csv"));{
				br_1.write(Integer.toString(n)+","+item_quan+","+tfid+"\n");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
                try {
                br_1.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	
	}	
	
		
	public static ArrayList<Staff> getLeaveAppList(){
		return leaveAppList;
	}
}