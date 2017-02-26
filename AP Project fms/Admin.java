// package fms;
// Gunkirat(2015032),Shaan(2015090)
import java.util.*;
import java.io.*;
public class Admin extends User{
	ArrayList<Supervisor> supervisorsList=new ArrayList<Supervisor>();
	ArrayList<Staff> staffList=new ArrayList<Staff>();
	ArrayList<Staff> staffPendingList=new ArrayList<Staff>();
	ArrayList<Supervisor> supervisorPendingList=new ArrayList<Supervisor>();
	ArrayList<Logistics> logisticList2 = new ArrayList<Logistics>();
	ArrayList<Logistics> logisticPendingList2 = new ArrayList<Logistics>();
	Admin(String n,String i, String u, String d, String a, String de, String t,String p)
	{
		super(n,i,u,d,a,de,t,p);

	}
	public void viewStaff()
	{	staffList.clear();
		staffList = getStaffDatabase();
		System.out.println(staffList);
	}
	public void viewSupervisor()
	{	supervisorsList.clear();
		supervisorsList = getSupervisorDatabase();
		System.out.println(supervisorsList);
		
	}
	public int deleteSupervisor(String username)
	{	supervisorsList.clear();
		supervisorsList = getSupervisorDatabase();
		System.out.println(supervisorsList);
		int count=0;
		for(int i=0;i<supervisorsList.size();i++){
			Supervisor sup=supervisorsList.get(i);
			if(sup.getUsername().equals(username)){
				System.out.println("System");
				supervisorsList.remove(sup);
				setSupervisorDatabase(supervisorsList);
				System.out.println(supervisorsList);
				count++;
				break;
			}
		}
		if(count==1)
				return 1;
			else
				return 0;
		
	}
	public int deleteStaff(String username)
	{	staffList.clear();
		staffList = getStaffDatabase();
		int count=0;
		for(int i=0;i<staffList.size();i++){
			Staff st=staffList.get(i);
			if((st.getUsername()).equals(username)){
				System.out.println("syss");
				staffList.remove(st);
				setStaffDatabase(staffList);
				count++;
				break;
			}
			
		}
		if(count==1)
				return 1;
			else
				return 0;
	}
	public int assignTasktoSupervisor(String dept, String tname, String d, String stat, String tid, String i, String tt, String c)
	{	supervisorsList.clear();
		supervisorsList = getSupervisorDatabase();
		System.out.println(supervisorsList);
		int count=0;
		for(int k=0;k<supervisorsList.size();k++)
		{
			Supervisor st=supervisorsList.get(k);
			if(st.getDept().equals(dept))
			{
				Task t=new Task(tname,stat,tid,d,i,tt,c);
				settaskDatabase_admin(t);
				System.out.print(t);
				count++;
				break;
			}
			
		}
		
		if(count==1)
				return 1;
			else
				return 0;
		
	}
	public int approveLog(String taskid,String instruction)
	{
		
		logisticList2.clear();
		logisticPendingList2.clear();
		logisticPendingList2 = getLogisticsDatabase2();
		System.out.println(logisticPendingList2);
		logisticList2 = getLogisticsDatabase2_admin();
		System.out.println(logisticList2);
		int count;
		if(instruction.equals("Accept")){
			count=0;
		for(int i=0;i<logisticPendingList2.size();i++){
				if(logisticPendingList2.get(i).getTaskId().equals(taskid))
				{	System.out.println("sss");
						logisticList2.add(logisticPendingList2.get(i));
						logisticPendingList2.remove(logisticPendingList2.get(i));
						System.out.println(logisticList2);
						System.out.println(logisticPendingList2);
						setLogisticsDatabase2_admin(logisticList2);
						setLogisticsDatabase2(logisticPendingList2);
						count++;
	
					break;}
		}
		if(count==1)
			return 1;
		else
			return 0;
	}
			else if(instruction.equals("Reject")){
				count=0;
				
				for(int i=0;i<logisticPendingList2.size();i++){
				if(logisticPendingList2.get(i).getTaskId().equals(taskid))
				{
				logisticPendingList2.remove(logisticPendingList2.get(i));
				setLogisticsDatabase2_admin(logisticList2);
				setLogisticsDatabase2(logisticPendingList2);
				count++;
				}
			}
			if(count==1)
				return 1;
		else
			return 0;
		}
			
		return 1;
		
	}
	public int approveStaffMember(String instruction,String name){
		staffList.clear();
		staffPendingList.clear();
		staffPendingList = getPendingStaffDatabase();
		staffList = getStaffDatabase();
		System.out.println(staffList);
		System.out.println(staffPendingList);
		int count=0;

		if(instruction.equals("Accept")){
		for(int i=0;i<staffPendingList.size();i++){
			if(staffPendingList.get(i).getUsername().equals(name)){
				staffList.add(staffPendingList.get(i));
				staffPendingList.remove(staffPendingList.get(i));
				setStaffDatabase(staffList);
				setPendingStaffDatabase(staffPendingList);
				count++;
				System.out.println("dejn");
				break;
				}
			}
			if(count==1)
				return 1;
			else 
				return 0;
		}	
		else if(instruction.equals("Reject")){
			count=0;
			for(int i=0;i<staffPendingList.size();i++){
				if(staffPendingList.get(i).getUsername().equals(name)){
				staffPendingList.remove(staffPendingList.get(i));
				setPendingStaffDatabase(staffPendingList);
				count++;
				break;
				}
			}
			if(count==1)
				return 1;
			else 
				return 0;
		}
			else if(instruction.equals("Hold")){
				return 1;
			}
			return 1;
		}
	public int approveSupervisorMember(String instruction,String name){
		supervisorsList.clear();
		supervisorPendingList.clear();
		supervisorPendingList = getPendingSupervisorDatabase();
		System.out.println(supervisorPendingList);
		supervisorsList = getSupervisorDatabase();
		int count=0;
		System.out.println(supervisorsList);
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
						setSupervisorDatabase(supervisorsList);
						setPendingSupervisorDatabase(supervisorPendingList);
						count++;
						break;
					}
					else
					{
						System.out.println(supervisorsList);
						System.out.println(supervisorPendingList);
						supervisorsList.add(supervisorPendingList.get(i));
						supervisorPendingList.remove(supervisorPendingList.get(i));
						setSupervisorDatabase(supervisorsList);
						setPendingSupervisorDatabase(supervisorPendingList);
						count++;
						break;
					}
				}
				
			}
			
		}
		if(count!=0)
			return 1;
		else
			return 0;
	}
			else if(instruction.equals("Reject")){
				count=0;
				for(int i=0;i<supervisorPendingList.size();i++){
				if(supervisorPendingList.get(i).getUsername().equals(name))
				{
				supervisorPendingList.remove(supervisorPendingList.get(i));
				setSupervisorDatabase(supervisorsList);
				setPendingSupervisorDatabase(supervisorPendingList);
				count++;
				break;
				}
			
			}
			if(count!=0)
				return 1;
		else
			return 0;

		}
			else if(instruction.equals("Hold")){
				System.out.println("request on hold");
				return 1;
			}
			return 1;
		
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
			String line_1;
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
		finally {
                try {
                br_1.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	

		return supervisorsList;
	}
	public ArrayList<Staff> getStaffDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff.csv"));
			String line_1;
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
		}finally {
                try {
                br_1.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	
		return staffList;
	}
	public ArrayList<Staff> getPendingStaffDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff_pending.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Staff s=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[3],details[7]);
			staffPendingList.add(s);
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
		return staffPendingList;
	}
	public ArrayList<Supervisor> getPendingSupervisorDatabase(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("supervisor_pending.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Supervisor s=new Supervisor(details[1],details[0],details[2],details[5],details[6],details[4],details[3],details[7]);
			supervisorPendingList.add(s);
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
		return supervisorPendingList;
	}
	
	public void setSupervisorDatabase(ArrayList<Supervisor> supervisorsList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("supervisor.csv"));
			// br_1.write("id,Name,UserName,Type,Department,DOB,Address,password\n");
			for(int i=0;i<supervisorsList.size();i++)
				{	Supervisor s=supervisorsList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
				
	}finally {
                try {
                br_1.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

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
				finally {
                try {
                br_1.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	
		}
	public void setPendingStaffDatabase(ArrayList<Staff> staffPendingList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("staff_pending.csv"));
			if(staffPendingList.size()!=0)
			{for(int i=0;i<staffPendingList.size();i++)
				{	Staff s=staffPendingList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
			}
			else
			{
				br_1.write("");

			}
		}
			catch(Exception e)
				{e.printStackTrace();
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
	public void setPendingSupervisorDatabase(ArrayList<Supervisor> supervisorPendingList){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("supervisor_pending.csv"));

			for(int i=0;i<supervisorPendingList.size();i++)
				{	Supervisor s=supervisorPendingList.get(i);
				br_1.write(s.getId()+","+s.getName()+","+s.getUsername()+","+s.getType()+","+s.getDept()+","+s.getDob()+","+s.getAddress()+","+s.getPassword()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
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
	public void settaskDatabase_admin(Task t){
		BufferedWriter br_1 = null;
			try{
			br_1 = new BufferedWriter(new FileWriter("task_admin.csv",true));{
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
		
	}
	
	public ArrayList<Logistics> getLogisticsDatabase2(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("logistic_pending.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Logistics s=new Logistics(details[2],details[0],details[1]);
			logisticPendingList2.add(s);
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

		return logisticPendingList2;
	}
	public void setLogisticsDatabase2(ArrayList<Logistics> logisticPendingList2){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("logistic_pending.csv"));
			for(int i=0;i<logisticPendingList2.size();i++)
				{	Logistics s=logisticPendingList2.get(i);
				br_1.write(s.getLogId()+","+s.getItem_quan()+","+s.getTaskId()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
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
	public ArrayList<Logistics> getLogisticsDatabase2_admin(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("logistic.csv"));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			Logistics s=new Logistics(details[2],details[0],details[1]);
			logisticList2.add(s);
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

		return logisticList2;
	}
	public void setLogisticsDatabase2_admin(ArrayList<Logistics> logisticList2){
		BufferedWriter br_1=null;
		try{
			br_1=new BufferedWriter(new FileWriter("logistic.csv"));
			for(int i=0;i<logisticList2.size();i++)
				{	Logistics s=logisticList2.get(i);
				br_1.write(s.getLogId()+","+s.getItem_quan()+","+s.getTaskId()+"\n");
			}
		}
			catch(Exception e)
				{e.printStackTrace();
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
}