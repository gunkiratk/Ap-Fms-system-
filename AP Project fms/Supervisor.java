// package fms;
// Gunkirat(2015032),Shaan(2015090)
import java.util.*;
import java.io.*;
public class Supervisor extends User{
	private String status;
	private ArrayList<Task> tasks=new ArrayList<Task>();
	ArrayList<Staff> staffList2=new ArrayList<Staff>();
	ArrayList<Staff> staffPendingList2=new ArrayList<Staff>();
	ArrayList<Logistics> logisticsList = new ArrayList<Logistics>();
	ArrayList<Logistics> logisticsPendingList = new ArrayList<Logistics>();
	static ArrayList<Supervisor> leaveAppListSup = new ArrayList<Supervisor>();
	ArrayList<Task> taskList=new ArrayList<Task>();
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


	public int approveStaffMember(String instruction,Supervisor sp,String name){
	staffPendingList2.clear();
	staffList2.clear();
	staffPendingList2 = getDeptPendingStaffDatabase(sp);
	staffList2 = getDeptStaffDatabase(sp);
	// System.out.println(staffList2);
	// System.out.println(staffPendingList2);
	// Scanner in=new Scanner(System.in);
	// String name=in.nextLine();
	int count;
	if(instruction.equals("Accept")){
		count=0;
	for(int i=0;i<staffPendingList2.size();i++){
		Staff st=staffPendingList2.get(i);
		if(st.getUsername().equals(name)){
			if(st.getDept().equals(sp.getDept()))
			{	staffList2.add(st);
			staffPendingList2.remove(st);
			setDeptStaffDatabase(staffList2);
			setDeptPendingStaffDatabase(staffPendingList2);
			System.out.println(staffList2);
			System.out.println(staffPendingList2);
			count++;
			break;
			}
			else
			System.out.println("not of my department");
		
			}
		}
		if(count==1)
			return 1;
		else 
			return 0;

	}
		else if(instruction.equals("Reject")){
			count=0;
		for(int i=0;i<staffPendingList2.size();i++){
		Staff st=staffPendingList2.get(i);
		if(st.getUsername().equals(name)){
			if(st.getDept().equals(sp.getDept())){
		staffPendingList2.remove(st);
		setDeptPendingStaffDatabase(staffPendingList2);
		System.out.println(staffList2);
		System.out.println(staffPendingList2);
		count++;
		break;
		}
		else
			System.out.println("not of my department");
		}
		}
		if(count==1)
			return 1;
		else 
			return 0;

	}	
	
		else if(instruction.equals("Hold")){
			System.out.println("On hold");
			return 1;
		}
		return 1;
	}
		
	
	public void viewStaff(Supervisor sp)
	{	staffList2.clear();
		staffList2 = getDeptStaffDatabase(sp);
		System.out.println(staffList2);
	}
	public int deleteStaff(String username,Supervisor sp)
	{	staffList2.clear();
		staffList2 = getDeptStaffDatabase(sp);
		System.out.println(staffList2);
		System.out.println(staffPendingList2);
		// Scanner in=new Scanner(System.in);
		// String name=in.nextLine();
		int count=0;
		for(int i=0;i<staffList2.size();i++){
			Staff st=staffList2.get(i);
			if((st.getUsername()).equals(username))
				{
					if(st.getDept().equals(sp.getDept())){
					staffList2.remove(st);
				
				setDeptStaffDatabase(staffList2);
				System.out.println(staffList2);
				count++;
				break;
			}
				else
					System.out.println("Different dep");
			
			


			}

		}
		if(count==1)
			return 1;
		else 
			return 0;
	}
	public int assignTasktoStaff(String taskname,int num_staffers,Supervisor sp)
	{	
		taskList.clear();
		taskList=gettaskdatabase();
		int count=0;;
		for(int k=0;k<taskList.size();k++){
			Task t=taskList.get(k);
		if(t.getTaskname().equals(taskname)){
			int i;
			System.out.println(t);
			for(i = 0; i<num_staffers ; i++){
				System.out.println(i);
				for(int j=0;j<staffList2.size();j++){
					Staff st=staffList2.get(j);
					System.out.println(st.getDept());
					System.out.println(sp.getDept());
					if(st.getDept().equals(sp.getDept())){
					 if(!(st.getStatusStaff().equals("Busy"))){
						st.setTask(t);
						st.setStatusStaff("Busy");
						st.setTask(t);
						t.setStatus("In Progress");
						count++;
						System.out.println("in");
						settaskDatabase_supervisor(t);
					}
					// else
					// 	System.out.println("staff busy");
						
				}
			// 	else{
			// System.out.println("This task doesn't belong to your department.");
		// }
			}
			}
			
				
		}
		// 
		
		}
		System.out.println(count);
		if(count!=0)
				return 1;
			else 
				return 0;	
	}
	
	// public void maintainLog()
	// {
	// 	logisticsList = getLogisticsDatabase();
	// 	for(Logistics tl=0;tl<logisticsList.size();tl++){
	// 		System.out.println(tl);
	// 	}
	// }
// 	public void sendLogisticApproval(String instruction, Supervisor sp)
// 	{
// 	logisticsPendingList.clear();
// 	logisticsList.clear();
// 	logisticsPendingList = getPendingLogisticsDatabase(sp);
// 	logisticsList = getLogisticsDatabase(sp);
// 	Scanner in=new Scanner(System.in);
// 	String name=in.nextLine();
	
// 	if(instruction.equals("Accept")){
// 	for(int i=0;i<logisticsPendingList.size();i++){
// 		Logistics st=logisticsPendingList.get(i);
// 		if(st.getTaskId().equals(name)){
			
// 		logisticsList.add(st);
// 		logisticsPendingList.remove(st);
// 		setDeptStaffDatabase(logisticsList);
// 		setLogisticsPendingDatabase(logisticsPendingList);
// 		//send to admin
// 		break;
// 		}
// 	}

// }
// 		else if(instruction.equals("Reject")){
// 		for(int i=0;i<logisticsPendingList.size();i++){
// 		Staff st=logisticsPendingList.get(i);
// 		if(st.getTaskId().equals(name)){
			
// 		logisticsPendingList.remove(st);
// 		setLogisticsPendingDatabase(logisticsPendingList);
// 		System.out.println(logisticsList);
// 		System.out.println(logisticsPendingList);
// 		break;
// 		}
// 		}
// 	}	
	
// 		else if(instruction.equals("Hold")){
// 			System.out.println("On hold");
// 		}
// 	}
	public void sendLeave()
	{
		leaveAppListSup.add(this);
		System.out.println(leaveAppListSup);
	}
	public void viewTask(Supervisor sp)
	{	BufferedReader br_1 = null;
		// int count=0;
			try{
			br_1 = new BufferedReader(new FileReader("task_admin.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			// System.out.println(details[4]);
			// System.out.println(details[4].equalsIgnoreCase(sp.getDept()));
			Task s=new Task(details[0],details[1],details[2],details[3],details[4],details[5],details[6]);
			System.out.println(s);
		}}
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
			// return staffList2;
		
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
			Staff.getLeaveAppList().remove(st);
		}

	}

	public static ArrayList<Supervisor> getLeaveAppListSup(){
		return leaveAppListSup;
	}
	
	public ArrayList<Staff> getDeptStaffDatabase(Supervisor sp){
		BufferedReader br_1 = null;
		// int count=0;
			try{
			br_1 = new BufferedReader(new FileReader("staff.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			System.out.println(details[4]);
			System.out.println(details[4].equalsIgnoreCase(sp.getDept()));
			Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[3],details[7]);
			staffList2.add(s);
				
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
			return staffList2;
		//make arrayList of only those staffers belonging to this supervisor's department
	}
public ArrayList<Staff> getDeptPendingStaffDatabase(Supervisor sp){
		BufferedReader br_1 = null;
		int count=0;
			try{
			br_1 = new BufferedReader(new FileReader("staff_pending.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			staffPendingList2.add(s);
			
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
		
				return staffPendingList2;
		
		//make arrayList of only those pending staffers belonging to this supervisor's department
	}

	public void setDeptStaffDatabase(ArrayList<Staff> staffList2){
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("staff.csv"));
			for(int i=0;i<staffList2.size();i++)
			{	Staff s=staffList2.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
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
		
		//update only entries of this department in the staff database
	}
	public void setDeptPendingStaffDatabase(ArrayList<Staff> staffPendingList2){
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("staff_pending.csv"));
			for(int i=0;i<staffPendingList2.size();i++)
			{	Staff s=staffPendingList2.get(i);
					br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}}
		
		
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
		//update only entries of this department in the pending staff database
	}
	public void settaskDatabase_supervisor(Task t){
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("task_super.csv",true));{
				br_1.write(t.getTaskname()+","+t.getStatus()+","+t.getTaskid()+","+t.getDeadline()+","+t.getItems_used()+","+t.getTime_taken()+","+t.getComments()+"\n");
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

		
		//update only entries of this department in the staff database
	}
	public ArrayList<Task> gettaskdatabase(){
		BufferedReader br_1 = null;
		// int count=0;
			try{
			br_1 = new BufferedReader(new FileReader("task_admin.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			
			Task t=new Task(details[0],details[1],details[2],details[3],details[4],details[5],details[6]);
			taskList.add(t);
				
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
			return taskList;
		//make arrayList of only those staffers belonging to this supervisor's department
	}

// 	public ArrayList<Logistics> getPendingLogisticsDatabase(){
// 		BufferedReader br_1 = null;
// 			try{
// 			br_1 = new BufferedReader(new FileReader("logisting_pending.csv"));
// 			String line_1;
// 			while((line_1=br_1.readLine())!=null)
// 			{	
// 			String[] details=line_1.split(",");
// 			Logistics s=new logisticList2(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
// 			logisticsPendingList.add(s);
// 		}
// 		}
// 		catch(Exception e)
// 		{
// 			e.printStackTrace();
// 		}
// 		finally {
//                 try {
//                 br_1.close();
//             } catch (IOException ed) {
//                 System.out.println("Error while flushing/closing fileWriter !!!");
//                 ed.printStackTrace();
//             }

//         	}	

// 		return logisticsPendingList;
// 	}
	
// 	public ArrayList<Logistics> getLogisticsDatabase(){

// 		BufferedReader br_1 = null;
// 			try{
// 			br_1 = new BufferedReader(new FileReader("logisting.csv"));
// 			String line_1;
// 			while((line_1=br_1.readLine())!=null)
// 			{	
// 			String[] details=line_1.split(",");
// 			Logistics s=new logisticList2(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
// 			logisticsList.add(s);
// 		}
// 		}
// 		catch(Exception e)
// 		{
// 			e.printStackTrace();
// 		}
// 		finally {
//                 try {
//                 br_1.close();
//             } catch (IOException ed) {
//                 System.out.println("Error while flushing/closing fileWriter !!!");
//                 ed.printStackTrace();
//             }

//         	}	

// 		return logisticsList;
// 	}
// 	public void setPendingLogisticsDatabase(ArrayList<Logistics> logisticsPendingList){
// 			BufferedWriter br_1=null;
// 		try{
// 			br_1=new BufferedWriter(new FileWriter("logisting_pending.csv"));
// 			// br_1.write("id,Name,UserName,Type,Department,DOB,Address,password\n");
// 			for(int i=0;i<logisticPendingList.size();i++)
// 				{	Logistics s=logisticPendingList.get(i);
// 				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
// 			}
// 		}
// 			catch(Exception e)
// 				{e.printStackTrace();
// 				}
// 				finally {
//                 try {
//                 br_1.close();
//             } catch (IOException ed) {
//                 System.out.println("Error while flushing/closing fileWriter !!!");
//                 ed.printStackTrace();
//             }

//         	}	
		

// 	}
// 	}
// 	public void setLogisticsDatabase(ArrayList<Logistics> logisticList){
// 		BufferedWriter br_1=null;
// 		try{
// 			br_1=new BufferedWriter(new FileWriter("logisting.csv"));
// 			// br_1.write("id,Name,UserName,Type,Department,DOB,Address,password\n");
// 			for(int i=0;i<logisticList.size();i++)
// 				{	Logistics s=logisticList.get(i);
// 				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
// 			}
// 		}
// 			catch(Exception e)
// 				{e.printStackTrace();
// 				}
// 				finally {
//                 try {
//                 br_1.close();
//             } catch (IOException ed) {
//                 System.out.println("Error while flushing/closing fileWriter !!!");
//                 ed.printStackTrace();
//             }

//         	}	
		

// 	}
// }

	// }/
	public void setTaskDatabase(){

	}
	
	
	

}