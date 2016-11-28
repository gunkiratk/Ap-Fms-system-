// package fms;
public class Supervisor extends User{

	private ArrayList<Task> tasks;
	ArrayList<Staff> staffList2;

	Supervisor()
	{
		super(null,0,null,null,null,null,null,null);
	}
	public void addStaff(Staff s,String name, int id, String username, String dob, String address, String dept, String type, String pass)
	{
		staffList2 = getStaffDatabase();
		s.setName(name);
		s.setId(id);
		s.setUsername(username);
		s.setDob(dob);
		s.setAddress(address);
		s.setDept(dept);
		s.setType(type);
		s.setPassword(pass);
		staffList2.add(s);
	}

	public void viewStaff(Staff s)
	{
		staffList2 = getStaffDatabase();
		for(Staff st : staffList2){
			if((st.getUsername).equals(s.getUsername)){
				System.out.println(st);
				break;
			}
		}
	}
	public void deleteStaff(Staff s)
	{
		staffList2 = getStaffDatabase();
		for(Staff st : staffList2){
			if((st.getUsername).equals(s.getUsername)){
				staffList.remove(st);
				break;
			}
		}
	}
	public void assignTasktoStaff(Task t, int num_staffers, String tname, ArrayList<String> staffernames)
	{
		/*int i;
		for(i = 0; i<num_staffers ; i++){
			for(Task j : getTasks()){

			}
		}*/
	}
	public void maintainLog()
	{

	}
	public void sendLogisticApproval(int id,String quantity,String department)
	{

	}
	public void sendLeave(Admin a,String reason,String daterange)
	{

	}
	public void viewTask()
	{
		for(Task tl : tasks){
			System.out.println(tl);
		}
		
	}
	public ArrayList<Tasks> getTasks(){
		return tasks;
	}

}