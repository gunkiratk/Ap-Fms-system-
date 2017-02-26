// package fms;
// Gunkirat(2015032),Shaan(2015090)
import java.util.*;
abstract class Department{
	List<Staff> Staffers=new ArrayList<Staff>();
	Supervisor sv;
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
	public abstract Supervisor getSupervisor();
	
}