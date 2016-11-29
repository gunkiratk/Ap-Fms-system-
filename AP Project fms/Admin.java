// package fms;
import java.util.*;
import java.io.*;
public class Admin extends User{
	ArrayList<Supervisor> supervisorsList=new ArrayList<Supervisor>();
	ArrayList<Staff> staffList=new ArrayList<Staff>();
	ArrayList<Staff> staffPendingList=new ArrayList<Staff>();
	ArrayList<Supervisor> supervisorPendingList=new ArrayList<Supervisor>();
	// ArrayList<Logistics> logisticList=new ArrayList<Logistics>();	//ArrayList<Task> taskList;

	Admin(String n,String i, String u, String d, String a, String de, String t,String p)
	{
		super(n,i,u,d,a,de,t,p);

	}
// 	public void addSupervisor(Supervisor s, String name, int id, String username, String dob, String address, String dept, String type, String pass)
// 	{
// 		supervisorsList = getSupervisorDatabase();
// 		s.setName(name);
// 		s.setId(id);
// 		s.setUsername(username);
// 		s.setDob(dob);
// 		s.setAddress(address);
// 		s.setDept(dept);
// 		s.setType(type);
// 		s.setPassword(pass);
// 		supervisorsList.add(s);
// 		setSupervisorDatabase();

// 	}
// 	public void addStaff(Staff s,String name,int id, String username, String dob, String address, String dept, String type, String pass)
// 	{
// 		staffList = getStaffDatabase();
// 		s.setName(name);
// 		s.setId(id);
// 		s.setUsername(username);
// 		s.setDob(dob);
// 		s.setAddress(address);
// 		s.setDept(dept);
// 		s.setType(type);
// 		s.setPassword(pass);
// 		staffList.add(s);
// 		setStaffDatabase();
// 	}
	public void viewStaff(String username)
	{
		staffList = getStaffDatabase();
		for(Staff st : staffList){
			if((st.getUsername()).equals(username)){
				System.out.println(st);
				break;
			}
		}
		
	}
	public void viewSupervisor(String username)
	{
		supervisorsList = getSupervisorDatabase();
		for(Supervisor sup : supervisorsList){
			if((sup.getUsername()).equals(username)){
				System.out.println(sup);
				break;
			}
		}

	}
	public void deleteSupervisor(String username)
	{
		supervisorsList = getSupervisorDatabase();
		for(Supervisor sup : supervisorsList){
			if((sup.getUsername()).equals(username)){
				supervisorsList.remove(sup);
				break;
			}
		}
		setSupervisorDatabase(supervisorsList);
	}
	public void deleteStaff(String username)
	{
		staffList = getStaffDatabase();
		for(Staff st : staffList){
			if((st.getUsername()).equals(username)){
				staffList.remove(st);
				break;
			}
		}
		setStaffDatabase(staffList);
	}
	public void assignTasktoSupervisor(String dept, String tname, String d, String stat, int tid, String i, String tt, String c)
	{	Supervisor s=null;
		if(dept.equalsIgnoreCase("Hvac"))
			{
				Hvac hv= new Hvac();
				s = hv.getSupervisor();
			}
		else if(dept.equalsIgnoreCase("HouseKeeping"))
			{
				Housekeeping hv= new Housekeeping();
				 s = hv.getSupervisor();
			}
		else if(dept.equalsIgnoreCase("Security"))
			{
				Security ss= new Security();
				s = ss.getSupervisor();
			}
		else if(dept.equalsIgnoreCase("AudioVideo"))
			{
				AudioVideo av= new AudioVideo();	
				 s = av.getSupervisor();
			}
		else if(dept.equalsIgnoreCase("Electricity"))
			{Electricity ele= new Electricity();
			 s = ele.getSupervisor();
		}
		ArrayList taskList = s.getTasks();
		Task t=new Task(tname,stat,tid,d,i,tt,c);
		taskList.add(t);
	}
	// public void approveLog(Logistic l)
	// {
	// 	logisticList = getLogisticsDatabase();
	// 	l.
	// }
	public void rejectLog()
	{
		
	}
	public void approveStaffMember(String instruction){
		staffPendingList = getPendingStaffDatabase();
		staffList = getStaffDatabase();
		for(int i=0;i<staffPendingList.size();i++){
			if(instruction.equals("Accept")){
				staffList.add(staffPendingList.get(i));
				staffPendingList.remove(staffPendingList.get(i));
			}
			else if(instruction.equals("Reject")){
				staffPendingList.remove(staffPendingList.get(i));
			}
			else if(instruction.equals("Hold")){

			}
		}
		setStaffDatabase(staffList);
		setPendingStaffDatabase(staffPendingList);
	}

	public void approveSupervisorMember(String instruction){
		supervisorPendingList = getPendingSupervisorDatabase();
		System.out.println(supervisorPendingList);
		supervisorsList = getSupervisorDatabase();
		System.out.println(supervisorsList);
		Scanner in=new Scanner(System.in);
		String name=in.nextLine();
		System.out.println(name);
		if(instruction.equals("Accept")){
		for(int i=0;i<supervisorPendingList.size();i++){
				if(supervisorPendingList.get(i).getUsername().equals(name))
				{	
					for(int j=0;j<supervisorsList.size();j++){
						System.out.println(supervisorsList.size());
						System.out.println(supervisorPendingList.size());
						System.out.println(supervisorsList.get(j).getDept());
						System.out.println(supervisorPendingList.get(i).getDept());
					if(supervisorsList.get(j).getDept().equals(supervisorPendingList.get(i).getDept())){
						supervisorsList.remove(supervisorsList.get(j));
						supervisorsList.add(supervisorPendingList.get(i));
						supervisorPendingList.remove(supervisorPendingList.get(i));
						System.out.println(supervisorsList);
						System.out.println(supervisorPendingList);
						break;
					}
					else
					{
						supervisorsList.add(supervisorPendingList.get(i));
						supervisorPendingList.remove(supervisorPendingList.get(i));
					}
					break;
				
				}
				
			}
		}
	}
			else if(instruction.equals("Reject")){

				
				for(int i=0;i<supervisorPendingList.size();i++){
				if(supervisorPendingList.get(i).getUsername().equals(name))
				{
				supervisorPendingList.remove(supervisorPendingList.get(i));
				}
			}
		}
			else if(instruction.equals("Hold")){
				System.out.println("request on hold");
			}
		
		setSupervisorDatabase(supervisorsList);
		setPendingSupervisorDatabase(supervisorPendingList);
	}

	public void approveLeaveSup(String instruction, Supervisor st){
		ArrayList<Supervisor> ap = new ArrayList<Supervisor>();
		if(instruction.equalsIgnoreCase("Accept")){
			(Supervisor.getLeaveAppListSup()).remove(st);
			st.setStatusSupervisor("On Leave");
		}
		else if(instruction.equalsIgnoreCase("Reject")){
			(Supervisor.getLeaveAppListSup()).remove(st);
		}

	}

	public ArrayList<Supervisor> getSupervisorDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("supervisor.csv"));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Supervisor s=new Supervisor(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			supervisorsList.add(s);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return supervisorsList;
	}
	public ArrayList<Staff> getStaffDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff.csv"));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			staffList.add(s);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return staffList;
	}
	public ArrayList<Staff> getPendingStaffDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff_pending.csv"));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			staffPendingList.add(s);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return staffPendingList;
	}
	public ArrayList<Supervisor> getPendingSupervisorDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("supervisor_pending.csv"));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Supervisor s=new Supervisor(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			supervisorPendingList.add(s);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return supervisorPendingList;
	}
	// public ArrayList<Logistics> getLogisticsDatabase(){
		// BufferedReader br_1 = null;
		// 	try{
		// 	br_1 = new BufferedReader(new FileReader("supervisorPendingList.csv"));
		// 	String line_1=br_1.readLine();
		// 	while((line_1=br_1.readLine())!=null)
		// 	{	
		// 	String[] details=line_1.split(",");
		// 	Supervisor s=new Supervisor(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7])
		// 	supervisorPendingList.add(s);
		// }
		// }
		// catch(Exception e)
		// {
		// 	e.printstacktrace();
		// }
		// return supervisorPendingList;
	
	public void setSupervisorDatabase(ArrayList<Supervisor> supervisorsList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("supervisor.csv"));
			for(int i=0;i<supervisorsList.size();i++)
				{	Supervisor s=supervisorsList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
				
	}
}
	public void setStaffDatabase(ArrayList<Staff> staffList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("staff.csv"));
			for(int i=0;i<staffList.size();i++)
				{	Staff s=staffList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
				}

	}
	public void setPendingStaffDatabase(ArrayList<Staff> staffPendingList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("staff_pending.csv"));
			for(int i=0;i<staffList.size();i++)
				{	Staff s=staffList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
				}

	}
	public void setPendingSupervisorDatabase(ArrayList<Supervisor> supervisorPendingList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("supervisor_pending.csv"));
			for(int i=0;i<supervisorsList.size();i++)
				{	Supervisor s=supervisorsList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
				}

	}
	public void setLogisticsDatabase(){

	}

}