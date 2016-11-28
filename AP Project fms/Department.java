// package fms;
import java.util.*;
abstract class Department{
	List<Staff> Staffers=new ArrayList<Staff>();
	Supervisor sv= new Supervisor();
	Department()
	{

	}
	public List<Staff> getStaffers()
	{
		return Staffers;
	}
	
	public void setStaffers(List<Staff> s)
	{

	}
	
	public void setSupervisor(Supervisor s)
	{

	}
	public Supervisor getSupervisor()
	{
		return sv;
	}
}