// package fms;
abstract class User{
	private int id;
	private String username;
	private String dob;
	private String address;
	private String dept;
	private String type;
	private String password;

	public User(int i, String u, String d, String a, String de, String t,String p){
		id = i;
		username = u;
		dob = d;
		address = a;
		dept = de;
		type = t;
		password = p;
	}

	public int getId(){
		return id;
	}
	public String getUsername(){
		return username;
	}
	public String getDob(){
		return dob;
	}
	public String getAddress(){
		return address;
	}
	public String getDept(){
		return dept;
	}
	public String getType(){
		return type;
	}
	public String getPassword(){
		return password;
	}

	public void setId(int id){
		this.id = id;
	}
	public void setUsername(String u){
		this.username = u;
	}
	public void setDob(String dob){
		this.dob = dob;
	}
	public void setAddress(String add){
		this.address = add;
	}
	public void setDept(String dept){
		this.dept = dept;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setPassword(String pass){
		this.password = pass;
	}
}