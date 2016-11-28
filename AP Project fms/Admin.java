// package fms;
public class Admin extends User{
	ArrayList<Supervisor> supervisorsList;
	ArrayList<Staff> staffList;
	//ArrayList<Task> taskList;

	Admin()
	{
		super(null,0,null,null,null,null,null,null);

	}
	public void addSupervisor(Supervisor s, String name, int id, String username, String dob, String address, String dept, String type, String pass)
	{
		supervisorsList = getSupervisorDatabase();
		s.setName(name);
		s.setId(id);
		s.setUsername(username);
		s.setDob(dob);
		s.setAddress(address);
		s.setDept(dept);
		s.setType(type);
		s.setPassword(pass);
		supervisorsList.add(s);
		setSupervisorDatabase();

	}
	public void addStaff(Staff s,String name,int id, String username, String dob, String address, String dept, String type, String pass)
	{
		staffList = getStaffDatabase();
		s.setName(name);
		s.setId(id);
		s.setUsername(username);
		s.setDob(dob);
		s.setAddress(address);
		s.setDept(dept);
		s.setType(type);
		s.setPassword(pass);
		staffList.add(s);
		setStaffDatabase();
	}
	public void viewStaff(Staff s)
	{
		staffList = getStaffDatabase();
		for(Staff st : staffList){
			if((st.getUsername).equals(s.getUsername)){
				System.out.println(st);
				break;
			}
		}
		
	}
	public void viewSupervisor(Supervisor s)
	{
		supervisorList = getSupervisorDatabase();
		for(Supervisor sup : supervisorList){
			if((sup.getUsername).equals(s.getUsername)){
				System.out.println(sup);
				break;
			}
		}

	}
	public void deleteSupervisor(Supervisor s)
	{
		supervisorList = getSupervisorDatabase();
		for(Supervisor sup : supervisorList){
			if((sup.getUsername).equals(s.getUsername)){
				supervisorsList.remove(sup);
				break;
			}
		}
		setSupervisorDatabase();
	}
	public void deleteStaff(Staff s)
	{
		staffList = getStaffDatabase();
		for(Staff st : staffList){
			if((st.getUsername).equals(s.getUsername)){
				staffList.remove(st);
				break;
			}
		}
		setStaffDatabase();
	}
	public void assignTasktoSupervisor(Task t, String dept, String tname, String d, String stat, int tid, String i, String tt, String c)
	{
		Supervisor s = dept.getSupervisor();
		ArrayList taskList = s.getTasks();
		//Task t;
		t.setTaskname(tname);
		t.setDeadline(d);
		t.setStatus(stat);
		t.setTaskid(tid);
		t.setItems_used(i);
		t.setTime_taken(tt);
		t.setComments(c);
		taskList.add(t);
		//setTasksDatabase();
	}
	public void approveLog()
	{

	}
	public void rejectLog()
	{
		
	}
	public ArrayList<Supervisor> getSupervisorDatabase(){

	}
	public ArrayList<Staff> getStaffDatabase(){
		
	}
	public void setSupervisorDatabase(){

	}
	public void setStaffDatabase(){

	}

}