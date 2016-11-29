// package fms;
// import net.java.dev.d/esigngridlayout.DesignGridLayout;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;
import java.io.*;
import javax.swing.*;
public class SystemFMS{

	// ArrayList<Department> depts = new ArrayList<Department>();
	// Admin admin = new Admin();
	Admin ad ;
	JFrame frame;
	JPanel panel;
	JLabel label1,label2;
	JButton bttn_str,bttn_exit;
	String username_1,name_1,type_1,password_1,email_1,dob_1,address_1;
	String department_1="";
	HashMap<String,String> login_credentials=new HashMap<String,String>();
	SystemFMS(){
		frame=new JFrame();
		panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label1=new JLabel("Facility Management Services");
		JLabel label2=new JLabel("System");
		bttn_str=new JButton("Login");
		bttn_exit=new JButton("Register");
		bttn_exit.setBackground(Color.BLUE);
		bttn_exit.setOpaque(true);
		bttn_exit.setBorderPainted(false);
		bttn_str.setBackground(Color.BLUE);
		bttn_str.setOpaque(true);
		bttn_str.setBorderPainted(false);
		panel.setLayout(null);
		label1.setBounds(200,120,500,40);
		label2.setBounds(350,180,450,40);
		label1.setFont(new Font("Arial",Font.BOLD,30));
		label2.setFont(new Font("Arial",Font.BOLD,30));
		bttn_str.setBounds(330,250,150,40);
		bttn_exit.setBounds(350,350,100,40);
		panel.add(label1);
		panel.add(label2);
		panel.add(bttn_str);
		panel.add(bttn_exit);
		panel.setBackground(new Color(255,255,244));
		bttn_str.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				new Login_Form();
			}
		});
		bttn_exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				new Register_Form();
			}
		});
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900,900);
		frame.setVisible(true);
	}

	public void register(int id, String type, String username, String password, String dob, String address, String department){

	}

	public void login(String username, String password){

	}

	public void logout(){

	}
	public class Login_Form
	{
		String type_1_login;
		String filename=null;
		Login_Form()
		{
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout());
			JPanel panel2=new JPanel();
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
			JPanel panel1=new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
			panel2.setPreferredSize(new Dimension(130, 130));
			panel1.setPreferredSize(new Dimension(130, 130));
			JButton username=new JButton("Username");
			JButton password=new JButton("Password");
			JButton type =new JButton("Type");
			JTextField txt=new JTextField();
			JTextField txt5=new JPasswordField();
			JRadioButton admin = new JRadioButton("admin");
			JRadioButton staff = new JRadioButton("staff");
      		JRadioButton supervisor = new JRadioButton("supervisor");
			ButtonGroup bg =new ButtonGroup();
			bg.add(admin);
			bg.add(staff);
			bg.add(supervisor);
			panel2.add(username);
			panel2.add(password);
			panel2.add(type);
			panel1.add(txt);
			panel1.add(txt5);
			panel1.add(admin);
			panel1.add(staff);
			panel1.add(supervisor);
			
			panel.add(panel2);
			panel.add(panel1);
			
			admin.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	type_1_login="Admin";
         	}           
     		 });	
			staff.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	type_1_login="Staff";
         	}           
     		 });
				supervisor.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	type_1_login="Supervisor";
         	}           
     		 });
			// System.out.println(login_credentials);
			JButton submit=new JButton("Submit");
			JLabel error=new JLabel();

			submit.addActionListener(new ActionListener()
			{	
				public void actionPerformed(ActionEvent e)
				{	String username_1=txt.getText();
					String password_1=txt5.getText();
					System.out.println(type_1_login);
					if(type_1_login=="Admin")
					{	System.out.println("dede");
						filename="admin.csv";
						if(check_user_pass(filename,username_1,password_1)==1)
							{System.out.println("chala");
							new View_Admin();
						}
						else
						{
							error.setText("Wrong username or password");
							frame.getContentPane().add(panel,BorderLayout.SOUTH);

						}
					}
					else if(type_1_login=="Supervisor")
					{
						filename="supervisor.csv";
						if(check_user_pass(filename,username_1,password_1)==1)
							new View_Supervisor();
						else
						{
							error.setText("Wrong username or password");
							frame.getContentPane().add(panel,BorderLayout.SOUTH);

						}
					}
					else if(type_1_login=="Staff")
					{
						filename="staff.csv";
						if(check_user_pass(filename,username_1,password_1)==1)
							new View_Staff();
						else
						{
							error.setText("Wrong username or password");
							frame.getContentPane().add(panel,BorderLayout.SOUTH);

						}

					}
							
						
				}
			});
			panel.add(submit);
		frame.getContentPane().add(panel,BorderLayout.CENTER);

		}

		public int check_user_pass(String filename,String username_1,String password_1)
		{int check=0;
			BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader(filename));
			String line_1=br_1.readLine();
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			System.out.println(details[2]+details[7]);
			System.out.println(username_1+password_1);
			if(details[2].equals(username_1) && details[7].equals(password_1))
				check=1;
			System.out.println(check);
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(check==1)
				return 1;
			else return 0;

		}
	}
	public class Register_Form 
	{
		int x;
		Register_Form()
		{
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout());
			JPanel panel2=new JPanel();
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
			JPanel panel1=new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
			panel2.setPreferredSize(new Dimension(110, 350));
			panel1.setPreferredSize(new Dimension(300, 350));
			JButton type=new JButton("Type");
			JButton name=new JButton("Name");
			JButton username=new JButton("Username");
			JButton email=new JButton("Email");
			JButton password=new JButton("Password");
			JButton dob=new JButton("Date of birth");
			JButton address=new JButton("Address");
			JButton department=new JButton("Department");
			// type.setBounds(150,0,800,50);
			// JLabel empty=new JLabel("");
			JTextField txt=new JTextField();
			JTextField txt2=new JTextField();
			JTextField txt3=new JTextField();
			JTextField txt4=new JTextField();
			JTextField txt5=new JPasswordField();
			JTextField txt6=new JTextField();
			JTextField txt7=new JTextField();

			txt2.setPreferredSize(new Dimension(40, 100));
			txt3.setPreferredSize(new Dimension(40, 120));
			txt.setPreferredSize(new Dimension(50, 120));
			txt4.setPreferredSize(new Dimension(50, 120));
			txt5.setPreferredSize(new Dimension(50, 120));
			txt6.setPreferredSize(new Dimension(50, 120));
			txt7.setPreferredSize(new Dimension(50, 120));
			
			JRadioButton elec = new JRadioButton("Electricity");
			JRadioButton housekeeping = new JRadioButton("Housekeeping");
      		JRadioButton hvac = new JRadioButton("HVAC");
      		JRadioButton av = new JRadioButton("AudioVideo");
      		JRadioButton security = new JRadioButton("Security");
      		JRadioButton other = new JRadioButton("Others(for Admin)");
			ButtonGroup group=new ButtonGroup();
			group.add(elec);
			group.add(housekeeping);
			group.add(hvac);
			group.add(av);
			group.add(security);
			group.add(other);
			panel2.add(type);
			// panel2.add(empty);
			panel2.add(name);
			// panel2.add(empty);
			panel2.add(username);
			panel2.add(email);
			panel2.add(password);
			panel2.add(dob);
			panel2.add(address);
			panel2.add(department);
			// panel2.add(empty);
			panel1.add(txt);
			// panel2.add(name);
			panel1.add(txt2);
			// panel2.add(username);
			panel1.add(txt3);
			panel1.add(txt4);
			panel1.add(txt5);
			panel1.add(txt6);
			panel1.add(txt7);
			panel1.add(elec);
			panel1.add(housekeeping);
			panel1.add(hvac);
			panel1.add(av);
			panel1.add(security);
			panel1.add(other);
			panel.add(panel2);
			panel.add(panel1);

			elec.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	department_1="Electricity";
         }           
      });
			housekeeping.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	department_1="Housekeeping";
         }           
      });
			hvac.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	department_1="HVAC";
         }           
      });
			security.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	department_1="Security";
         }           
      });
			av.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	department_1="AudioVideo";
         }           
      });
			other.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {         
            if(e.getStateChange()==1)
            	department_1="Null";
         }           
      });
			JButton submit=new JButton("Submit");
			submit.addActionListener(new ActionListener()
			{	
				public void actionPerformed(ActionEvent e)
				{

			type_1=txt.getText();
			name_1=txt2.getText();
			username_1=txt3.getText();
			email_1=txt4.getText();
			password_1=txt5.getText();
			dob_1=txt6.getText();
			address_1=txt7.getText();
			System.out.println(txt.getText()+txt2.getText());
					Random generator=new Random();
					x=generator.nextInt(10000)+1;
					System.out.println(x);
					// list=new User()
				
			
			System.out.println(type_1);
			if(type_1.equalsIgnoreCase("Admin"))
			{
				// admin_write();
				if(GetDatabase("admin.csv")==-1)
					{
					System.out.println("One admin already existes");
					JLabel l=new JLabel("One admin already exists");
					// JPanel p1=new JPanel();
					panel1.add(l);
					// frame.getContentPane().add(p1,BorderLayout.SOUTH);
				}
				else
				{User dat = new Admin(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				// Admin ad=new Admin(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				login_credentials.put(ad.getUsername(),ad.getPassword());
				System.out.print("Admin created :");
				System.out.println(ad.getUsername());
				writeCsvDatabase("admin.csv",dat);
				}
			}
			else if(type_1.equalsIgnoreCase("Supervisor"))
			{	if(GetDatabase("admin.csv")==0)
				{User s= new Supervisor(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				// supervisor_write(s);
				login_credentials.put(s.getUsername(),s.getPassword());
				writeCsvDatabase("supervisor_pending.csv",s);
				}
				else
				{
					System.out.println("No admin registered");
					JLabel l=new JLabel("No admin registered");
					frame.getContentPane().add(l,BorderLayout.SOUTH);
				}
			}
			else if(type_1.equalsIgnoreCase("Staff"))
			{
				if(GetDatabase("admin.csv")==0)
				{User s= new Staff(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				// staff_write(s);
				login_credentials.put(s.getUsername(),s.getPassword());
				writeCsvDatabase("staff_pending.csv",s);
			}
			else{
				System.out.println("No admin registered");
					JLabel l=new JLabel("No admin registered");
					frame.getContentPane().add(l,BorderLayout.SOUTH);
				}
			}
		}	
    	});
		panel.add(submit);
		frame.getContentPane().add(panel,BorderLayout.CENTER);
    	}
    	public void writeCsvDatabase(String filename,User s)
    	{
    		final String COMMA_DELIMITER = ",";
				final String NEW_LINE_SEPARATOR = "\n";
   				final String FILE_HEADER = "id,Name,UserName,Type,Department,DOB,Address,password";

				FileWriter fileWriter = null;
			 	 try {
			     	fileWriter = new FileWriter(filename);	
            		fileWriter.append(FILE_HEADER.toString());
            		fileWriter.append(NEW_LINE_SEPARATOR);
					fileWriter.append(String.valueOf(s.getId()));
				    fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getName());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getUsername());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getType());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getDept());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getDob());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getAddress());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(s.getPassword());
					fileWriter.append(NEW_LINE_SEPARATOR);
					}
        		 catch (Exception ew) {
            System.out.println("Error in CsvFileWriter !!!");
            ew.printStackTrace();
        } finally {
                try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ed) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                ed.printStackTrace();
            }

        	}	
    	}
    	public int GetDatabase(String filename)
    	{int read_1=0;
    		BufferedReader filereader=null;
    		try{
    			filereader=new BufferedReader(new FileReader(filename));
    			String line_1=filereader.readLine();
    			if(filereader.readLine()!=null)
    				read_1=1;
    		}
    		catch(Exception e)
    		{	    			

    			e.printStackTrace();
    		}
    		finally{
    			System.out.println(read_1);
    			if(read_1==1)
    				return -1;
    			else 
    				return 0;
    	}
    	}
	
	}
	
	public class View_Admin
	{
		View_Admin()
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 60));
			JButton Home=new JButton("Home");
			JButton Staff=new JButton("Add Super");
			JButton Log=new JButton("View");
			JButton report=new JButton("Delete");
			JButton request=new JButton("Request");
			JLabel lbl=new JLabel(" Welcome Admin");
			JLabel timeLabel = new JLabel();
			panel.add(lbl);
			panel.add(Home);
			panel.add(Staff);
			panel.add(Log);
			panel.add(report);
			panel.add(request);
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel.add(timeLabel);
			
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			read_admin();
			System.out.println(ad.getName());
			Scanner scan=new Scanner(System.in);
			System.out.println("1.Add Supervisor");
			System.out.println("2.View Supervisor");
			System.out.println("3.Delete Supervisor");
			System.out.println("4.Add Staff");
			System.out.println("5.View Staff");
			System.out.println("6.Delete Staff");
			System.out.println("7.AssignTask");
			System.out.println("8.Approve log");
			System.out.println("9.Reject log");
			int x=scan.nextInt();
			Scanner in=new Scanner(System.in);
			if(x==1)
				{	String line=in.nextLine();
					ad.approveSupervisorMember(line);
				}
			else if(x==2)
			{
				String name=in.nextLine();
				ad.viewSupervisor(name);
			}
			else if(x==3)
			{
				String name=in.nextLine();
				ad.deleteSupervisor(name);
			}
			else if(x==4)
			{
				String line=in.nextLine();
				ad.approveSupervisorMember(line);
			}
			else if(x==5)
			{
				String name=in.nextLine();
				ad.viewStaff(name);
			}
			else if(x==6)
			{
				String name=in.nextLine();
				ad.deleteStaff(name);
			}
			// else if(x==7)
			// {
			// 	String staff=scan.nextLine();
			// 	ad.viewStaff(name);
			// }


			}	

			
		
	}


	public class View_Staff
	{
		View_Staff()
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 60));
			JButton Home=new JButton("Home");
			JButton Staff=new JButton("Task Report");
			JButton Log=new JButton("Logistic");
			JButton report=new JButton("Status");
			JButton request=new JButton("Leave");
			JLabel lbl=new JLabel("");
			JLabel timeLabel = new JLabel();
			panel.add(lbl);
			panel.add(Home);
			panel.add(Staff);
			panel.add(Log);
			panel.add(report);
			panel.add(request);
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel.add(timeLabel);
			
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			
		}
	}
		public class View_Supervisor
	{
		View_Supervisor()
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 60));
			JButton Home=new JButton("Home");
			JButton Staff=new JButton("Report");
			JButton Log=new JButton("Logistic");
			JButton report=new JButton("Task");
			JButton request=new JButton("Leave");
			JLabel lbl=new JLabel("");
			JLabel timeLabel = new JLabel();
			panel.add(lbl);
			panel.add(Home);
			panel.add(Staff);
			panel.add(Log);
			panel.add(report);
			panel.add(request);
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel.add(timeLabel);
			
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			
		}
	}
	public void read_admin()
	{
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("admin.csv"));
			String line_1=br_1.readLine();
			String line_2=br_1.readLine();
			String[] details=line_2.split(",");
			ad=new Admin(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		// System.out.printf("gfhefhbhf");
		new SystemFMS();
	}

}