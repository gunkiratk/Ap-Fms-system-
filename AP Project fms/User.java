// package fms;
// Gunkirat(2015032),Shaan(2015090)
abstract class User{
	private String name;
	private String id;
	private String username;
	private String dob;
	private String address;
	private String dept;
	private String type;
	private String password;

	public User(String n,String i, String u, String d, String a, String de, String t,String p){
		name = n;
		id = i;
		username = u;
		dob = d;
		address = a;
		dept = de;
		type = t;
		password = p;
	}

	public String getName(){
		return name;
	}
	public String getId(){
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

	public void setName(String n){
		this.name = n;
	}
	public void setId(String id){
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

	public String toString(){
		return "Name" + getName() + "\n" + "Username : " + getUsername() + "\n" + "User id : " + getId() + "\n" + "Date of Birth : " + getDob() + "\n" + "Address : " + getAddress() + "\n" + "Department : " + getDept() + "\n" + "Type : " + getType() + "\n" + "Password : " + getPassword() + "\n";
	}
}