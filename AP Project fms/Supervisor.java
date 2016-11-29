// package fms;
import java.util.*;
import java.io.*;
public class Supervisor extends User{

	private String status;
	private ArrayList<Task> tasks=new ArrayList<Task>();
	ArrayList<Staff> staffList2=new ArrayList<Staff>();
	ArrayList<Staff> staffPendingList2=new ArrayList<Staff>();
	static ArrayList<Supervisor> leaveAppListSup = new ArrayList<Supervisor>();

	Supervisor(String n,String i, String u, String d, String a, String de, String t,String p)
	{
		super(n,i,u,d,a,de,t,p);
	}
	// public void addStaff(Staff s,String name, int id, String username, String dob, String address, String dept, String type, String pass)
	// {
	// 	staffList2 = getDeptStaffDatabase();
	// 	s.setName(name);
	// 	s.setId(id);
	// 	s.setUsername(username);
	// 	s.setDob(dob);
	// 	s.setAddress(address);
	// 	s.setDept(dept);
	// 	s.setType(type);
	// 	s.setPassword(pass);
	// 	staffList2.add(s);
	// }
	public String getStatusSupervisor()
	{
			return status;
	}
	public void setStatusSupervisor(String s)
	{
		this.status = s;
	}

public void approveStaffMember(String instruction){
	staffPendingList2 = getDeptPendingStaffDatabase();
	staffList2 = getDeptStaffDatabase();
	for(Staff st : staffPendingList2){
		if(instruction.equals("Accept")){
			staffList2.add(st);
			staffPendingList2.remove(st);
		}
		else if(instruction.equals("Reject")){
			staffPendingList2.remove(st);
		}
		else if(instruction.equals("Hold")){

		}
	}
		setDeptStaffDatabase(staffList2);
		setDeptPendingStaffDatabase(staffPendingList2);
	}
	public void viewStaff(String username)
	{
		staffList2 = getDeptStaffDatabase();
		for(Staff st : staffList2){
			if((st.getUsername()).equals(username)){
				System.out.println(st);
				break;
			}
		}
	}
	public void deleteStaff(String username)
	{
		staffList2 = getDeptStaffDatabase();
		for(Staff st : staffList2){
			if((st.getUsername()).equals(username)){
				staffList2.remove(st);
				break;
			}
		}
	}
	public void assignTasktoStaff(Task t,int num_staffers, String tname, ArrayList<String> staffernames)
	{
		staffList2 = getDeptStaffDatabase();
		if(staffList2.contains(t)){
			int i;
			for(i = 0; i<num_staffers ; i++){
				for(Staff j : staffList2){
					if((j.getUsername()).equals(staffernames.get(i))){
						j.setTask(t);
						j.setStatusStaff("Busy");
						j.setTask(t);
					break;
					}	
				}
			}
			setDeptStaffDatabase(staffList2);	
		}
		else{
			System.out.println("This task doesn't belong to your department.");
		}
		
	}
	
	public void maintainLog()
	{

	}
	public void sendLogisticApproval(int id,String quantity,String department)
	{

	}
	public void sendLeave()
	{
		leaveAppListSup.add(this);
	}
	public void viewTask()
	{
		for(Task tl : tasks){
			System.out.println(tl);
		}
		
	}
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	public void approveLeaveApp(String instruction,Staff st){
		ArrayList<Staff> ap = new ArrayList<Staff>();
		if(instruction.equalsIgnoreCase("Accept")){
			(Staff.getLeaveAppList()).remove(st);
			st.setStatusStaff("On Leave");
		}
		else if(instruction.equalsIgnoreCase("Reject")){
			(Staff.getLeaveAppList()).remove(st)
		}

	}

	public static ArrayList<Supervisor> getLeaveAppListSup(){
		return leaveAppListSup;
	}
	public ArrayList<Staff> getDeptStaffDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff.csv"));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			if(details[4].equals(this.getDept()))
			{	Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
				staffList2.add(s);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return staffList2;
		//make arrayList of only those staffers belonging to this supervisor's department
	}
	public ArrayList<Staff> getDeptPendingStaffDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff_pending.csv"));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			if(details[4].equals(this.getDept()))
			{	Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
				staffList2.add(s);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return staffPendingList2;
		//make arrayList of only those pending staffers belonging to this supervisor's department
	}

	public void setDeptStaffDatabase(ArrayList<Staff> staffList2){
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("staff.csv"));
			for(Staff s : staffList2)
			{	if(this.getDept().equals(s.getDept()))
					br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//update only entries of this department in the staff database
	}
	public void setDeptPendingStaffDatabase(ArrayList<Staff> staffPendingList2){
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("staff_pending.csv"));
			for(Staff s : staffPendingList2)
			{	if(this.getDept().equals(s.getDept()))
					br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//update only entries of this department in the pending staff database
	}


}