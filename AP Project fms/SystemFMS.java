// package fms;
// Gunkirat(2015032),Shaan(2015090)
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
public class SystemFMS {

	
	Admin ad ;
	ArrayList<Department> deps;
	Supervisor sp;
	Staff st;
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
		JButton exit=new JButton("Exit");
		bttn_exit.setBackground(Color.BLUE);
		bttn_exit.setOpaque(true);
		bttn_exit.setBorderPainted(false);
		exit.setBackground(Color.BLUE);
		exit.setOpaque(true);
		exit.setBorderPainted(false);
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
		exit.setBounds(350,450,100,40);
		panel.add(label1);
		panel.add(label2);
		panel.add(bttn_str);
		panel.add(bttn_exit);
		panel.add(exit);
		panel.setBackground(new Color(255,255,244));
		bttn_str.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				new Login_Form();
			}
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				System.exit(0);
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
			panel2.setBackground(new Color(255,255,244));
			JPanel panel1=new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
			panel1.setBackground(new Color(255,255,244));
			JPanel panel3=new JPanel();
			panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
			panel3.setBackground(new Color(255,255,244));
			panel2.setPreferredSize(new Dimension(130, 130));
			panel1.setPreferredSize(new Dimension(130, 130));
			JButton username=new JButton("Username");
			JButton password=new JButton("Password");
			JButton type =new JButton("Type");
			JButton back1=new JButton ("Back");
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
			panel3.add(back1);
			panel.add(panel2);
			panel.add(panel1);
			panel.add(panel3);
			back1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { 
         		new SystemFMS();
         	}});    

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
			JButton submit=new JButton("Submit");
			JLabel error=new JLabel();

			submit.addActionListener(new ActionListener()
			{	
				public void actionPerformed(ActionEvent e)
				{	String username_1=txt.getText();
					String password_1=txt5.getText();
					
					if(username_1.equals("") || password_1.equals(""))
						{
							JOptionPane.showMessageDialog(frame,"Invalid or empty field");
							new Login_Form();
						}
						else
						{
						if(type_1_login=="Admin")
						{	
						// System.out.println("dede");
						filename="admin.csv";
					if(check_user_pass(filename,username_1,password_1)==1)
							{
								System.out.println("chala");
								new View_Admin();
							}
						else
						{
							JOptionPane.showMessageDialog(frame,"wrong username or password");
							new Login_Form();

						}
					}
					else if(type_1_login=="Supervisor")
					{
						filename="supervisor.csv";
						if(check_user_pass(filename,username_1,password_1)==1 && check_user_pass("supervisor_pending.csv",username_1,password_1)==0)
							new View_Supervisor(username_1);
						else if(check_user_pass("supervisor_pending.csv",username_1,password_1)==1)
						{
							JOptionPane.showMessageDialog(frame,"Supervisor not approved");
							new Login_Form();

						}
						else
						{
							JOptionPane.showMessageDialog(frame,"wrong username or password");
							new Login_Form();
					

						}
					}
					else if(type_1_login=="Staff")
					{
						filename="staff.csv";
						if(check_user_pass(filename,username_1,password_1)==1 && check_user_pass("supervisor_pending.csv",username_1,password_1)==0)
							new View_Staff(username_1);
						else if(check_user_pass("staff_pending.csv",username_1,password_1)==1)
						{
							JOptionPane.showMessageDialog(frame,"Staff not approved");
							new Login_Form();

						}
						else
						{
							JOptionPane.showMessageDialog(frame,"wrong username or password");
							new Login_Form();

						}

					}
					}		
						
				}
			});
			panel.add(submit);
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		public int check_user_pass_pending(String filename,String username_1,String password_1)
		{int check=0;
			BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader(filename));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
			if(details[2].equals(username_1))
				check=1;
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
	
		public int check_user_pass(String filename,String username_1,String password_1)
		{int check=0;
			BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader(filename));
			String line_1;
			while((line_1=br_1.readLine())!=null)
			{	
			String[] details=line_1.split(",");
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
	public class Register_Form extends databasetemplate
	{
		int x;
		Register_Form()
		{
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout());
			JPanel panel2=new JPanel();
			panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
			panel2.setBackground(new Color(255,255,244));
			JPanel panel1=new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
			panel1.setBackground(new Color(255,255,244));
			panel2.setPreferredSize(new Dimension(200, 350));
			panel1.setPreferredSize(new Dimension(300, 350));
			JButton type=new JButton("Type");
			JButton name=new JButton("Name");
			JButton username=new JButton("Username");
			JButton email=new JButton("Email");
			JButton password=new JButton("Password");
			JButton dob=new JButton("Date of birth(dd/mm/yyyy)");
			JButton address=new JButton("Address");
			JButton department=new JButton("Department");
			JButton back=new JButton("Back");
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
			panel2.add(name);
			panel2.add(username);
			panel2.add(email);
			panel2.add(password);
			panel2.add(dob);
			panel2.add(address);
			panel2.add(department);
			panel1.add(txt);
			panel1.add(txt2);
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

			back.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					new SystemFMS();
				}
			});
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
			if(type_1.equals("") || name_1.equals("") || username_1.equals("") || email_1.equals("") || password_1.equals("") || dob_1.equals("") || address_1.equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Invalid or empty field");
					new Register_Form();
		
        }
   			else{	
   				if(check_date(dob_1)==1)
				{
					if(check_email(email_1)==1)
				{
					Random generator=new Random();
					x=generator.nextInt(10000)+1;
					System.out.println(x);			
					System.out.println(type_1);
					if(type_1.equalsIgnoreCase("Admin"))
					{
				if(GetDatabase("admin.csv")==-1)
					{
					System.out.println("One admin already existes");
					JOptionPane.showMessageDialog(frame,"Admin already exists");
				}
				else
				{User dat = new Admin(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				writeCsvDatabase("admin.csv",dat);
				}
			}
			else if(type_1.equalsIgnoreCase("Supervisor"))
			{	if(GetDatabase("admin.csv")!=0)
				{User s= new Supervisor(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				writeCsvDatabase("supervisor_pending.csv",s);
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"No admin registered");
					new Register_Form();
				}
			}
			else if(type_1.equalsIgnoreCase("Staff"))
			{
				if(GetDatabase("admin.csv")!=0)
				{User s= new Staff(name_1,Integer.toString(x),username_1,dob_1,address_1,department_1,type_1,password_1);
				writeCsvDatabase("staff_pending.csv",s);
			}
			else{
				JOptionPane.showMessageDialog(frame,"No admin registered");
					new Register_Form();
				}
			}
		}
			else
			{
				JOptionPane.showMessageDialog(frame,"Email not valid");
				new Register_Form();
				
			}
		}
			else
			{
				JOptionPane.showMessageDialog(frame,"Date of birth not valid");
				new Register_Form();
				
			}
		
			new SystemFMS();
		}
		}	
    	});
		panel.add(submit);
		panel.add(back);
		frame.getContentPane().add(panel,BorderLayout.CENTER);
    	}
    	public int check_email(String name)
    	{
    		int count=0;
    		for(int i=0;i<name.length();i++)
    		{
    			if(name.charAt(i)=='@' && i!=0)
    			{
    				for(int j=i+1;j<name.length();j++)
    				{
    					if(name.charAt(j)=='.' && j<(name.length()-1))
    						{	System.out.println("in");
    							count++;
    						break;
    					}
    				}
    			}
    		}
    		System.out.println(count);
    		if(count==1)
    			return 1;
    		else
    			 return 0;
    	}
    	public int check_date(String name)
    	{
    		if(name.length()==10)
    		{	System.out.println("1");
    			if(name.charAt(2)=='/' && name.charAt(5)=='/' )
    				{	System.out.println("2");
    					if((name.charAt(3)=='0' && name.charAt(4)<='9' && name.charAt(4)>'0' ) ||(name.charAt(3)=='1' && name.charAt(4)<='2' && name.charAt(4)>'0'))
    					{	System.out.println("3");
    						if(name.charAt(6)=='1' && name.charAt(7)=='9')
    						{	System.out.println("1");
    							return 1;
    						}
    						else
    							return 0;
    					}
    					else
    						return 0;

    				}
    				else
    					return 0;		
    		}
    		else
    			return 0;
    	}
    	public void writeCsvDatabase(String filename,User s)
    	{
    		final String COMMA_DELIMITER = ",";
				final String NEW_LINE_SEPARATOR = "\n";
   				

				FileWriter fileWriter = null;
			 	 try {
			     	fileWriter = new FileWriter(filename,true);	
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
	{	JButton sup=null,sta=null,supview=null,staview=null,supdel=null,stadel=null,accept=null,reject=null,hold=null;
		JPanel panel1,panel2,panel3,panel4,panel5;
		View_Admin()
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel1=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
			panel1.setBackground(new Color(255,255,244));
			JButton home=new JButton("Home");
			JButton staff=new JButton("Add ");
			JButton view_2=new JButton("View");
			JButton delete_2=new JButton("Delete");
			JButton task=new JButton("Assign Task");
			JButton request=new JButton(" Approve/Reject Request");
			JButton logout=new JButton("logout");
			JLabel lbl=new JLabel(" Welcome Admin");
			JLabel timeLabel = new JLabel();
			panel1.add(lbl);
			panel1.add(home);
			panel1.add(staff);
			panel1.add(view_2);
			panel1.add(delete_2);
			panel1.add(task);
			panel1.add(request);
			panel1.add(new JLabel(""));
			panel.add(panel1);
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel1.add(timeLabel);
			panel1.add(logout);
			logout.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			 new SystemFMS();
 				}
         	});
			
			home.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			  new View_Admin();
 				}
         	});
         	read_admin();
         	staff.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			panel2=new JPanel(new FlowLayout());
         			panel2.setBackground(new Color(255,255,244));
         			System.out.println("Add");
         			sup = new JButton("Supervisor");
         			sta = new JButton("Staff");
         			panel2.add(sup);
         			panel2.add(sta);
         			panel.add(panel2);
         			panel.updateUI();
         			frame.getContentPane().add(panel,BorderLayout.CENTER);
         			
         			sup.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         				JLabel l=new JLabel("Enter instruction");
         				JTextField intruct=new JTextField();
         				intruct.setPreferredSize(new Dimension(100, 40));
         				JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				 panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l);
         				panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String line=intruct.getText();
         						String name=user.getText();
         						System.out.println(line);
								int check=ad.approveSupervisorMember(line,name);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								System.out.println(check);
								if(check==1)
									panel6.add(new JLabel("supervisor intstructed"));
								else 
									panel6.add(new JLabel("supervisor not instructed"));
								panel.add(panel6);
								panel.updateUI();
								
         					}
         				});
         			}
         			});

         				sta.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
           				JLabel l=new JLabel("Enter instruction");
         				JTextField intruct=new JTextField();
         				intruct.setPreferredSize(new Dimension(100, 40));
         				JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				 panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l);
         				panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				frame.getContentPane().add(panel,BorderLayout.CENTER);
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String line=intruct.getText();
         						String name=user.getText();
         						System.out.println(name);
								int check=ad.approveStaffMember(line,name);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								System.out.println(check);
								if(check==1)
									panel6.add(new JLabel("staff intstructed"));
								else 
									panel6.add(new JLabel("staff not instructed"));
								panel.add(panel6);
								panel.updateUI();
								frame.getContentPane().add(panel,BorderLayout.CENTER);
         					}
         				});
         			}
					});
         		}
         	});

            	
            	view_2.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			System.out.println("Add");
         			 panel3=new JPanel(new FlowLayout());
         			 panel3.setBackground(new Color(255,255,244));
         			 supview = new JButton("Supervisor");
         			staview = new JButton("Staff");
         			panel3.add(supview);
         			panel3.add(staview);
         			panel.add(panel3);
         			panel.updateUI();
         			supview.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){ 
         						
         						ad.viewSupervisor();
         						JPanel panel6=new JPanel();
         						panel6.setBackground(new Color(255,255,244));
         						panel6.add(new JLabel("VIEWED ON CONSOLE"));
         						panel.add(panel6);
								panel.updateUI();

         					}    
            		
            	});
         			staview.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){ 
         						ad.viewStaff();
         						JPanel panel6=new JPanel();
         						panel6.add(new JLabel("Viewed on console"));
         						panel6.setBackground(new Color(255,255,244));
         						panel.add(panel6);
								panel.updateUI();

         					}    
            		
            	});
            }
         	});
         	delete_2.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			panel2=new JPanel(new FlowLayout());
         			panel2.setBackground(new Color(255,255,244));
         			System.out.println("Add");
         			 supdel = new JButton("Supervisor");
         			 stadel = new JButton("Staff");
         			panel2.add(supdel);
         			panel2.add(stadel);
         			panel.add(panel2);
         			panel.updateUI();
         			frame.getContentPane().add(panel,BorderLayout.CENTER);  
         			supdel.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         			JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String name=user.getText();
         						System.out.println(name);
								int check=ad.deleteSupervisor(name);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								if(check==1)
									panel6.add(new JLabel("supervisor deleted"));
								else 
									panel6.add(new JLabel("supervisor not deleted"));
								panel.add(panel6);
								panel.updateUI();
         					}
         				});
         			}
         			});	 
         			stadel.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         			JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				 panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String name=user.getText();
								int check=ad.deleteStaff(name);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								if(check==1)
									panel6.add(new JLabel("staff deleted"));
								else 
									panel6.add(new JLabel("staff not deleted"));
								panel.add(panel6);
								panel.updateUI();
         					}
         				});
         			}
         			});    
            	}
         	});
	
         	task.addActionListener(new ActionListener()
         		{
         			public void actionPerformed(ActionEvent e){
         				JLabel l1=new JLabel("Enter department");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JLabel l2=new JLabel("Enter taskname");
         				JTextField user2=new JTextField();
         				user2.setPreferredSize(new Dimension(100, 40));
         				JLabel l3=new JLabel("Enter deadline");
         				JTextField user3=new JTextField();
         				user3.setPreferredSize(new Dimension(100, 40));
         				JLabel l4=new JLabel("Enter items used");
         				JTextField user4=new JTextField();
         				user4.setPreferredSize(new Dimension(100, 40));
         				JLabel l5=new JLabel("Enter timetaken");
         				JTextField user5=new JTextField();
         				user5.setPreferredSize(new Dimension(100, 40));
         				JLabel l6=new JLabel("Enter comments");
         				JTextField user6=new JTextField();
         				user6.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(l2);
         				panel5.add(user2);
         				panel5.add(l3);
         				panel5.add(user3);
         				panel5.add(l4);
         				panel5.add(user4);
         				panel5.add(l5);
         				panel5.add(user5);
         				panel5.add(l6);
         				panel5.add(user6);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String dept=user.getText();
         						String tname=user2.getText();
         						String deadline=user3.getText();
         						String i=user4.getText();
         						String tt=user5.getText();
         						String comm=user6.getText();
         							Random num=new Random();
								int n=num.nextInt(99)+1;
								int check=ad.assignTasktoSupervisor(dept,tname,deadline,"NOT STARTED",Integer.toString(n),i,tt,comm);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								if(check==1)
									panel6.add(new JLabel("Tassked assigned to supervisor of "+dept));
								else 
									panel6.add(new JLabel("no supervisor of "+dept+"exists"));
								panel.add(panel6);
								panel.updateUI();
         					}
         				});
         			}
         		});

         request.addActionListener(new ActionListener()
         		{
         			public void actionPerformed(ActionEvent e){
         				JLabel l3=new JLabel("Enter instruction");
         				JTextField user3=new JTextField();
         				user3.setPreferredSize(new Dimension(100, 40));
         				JLabel l1=new JLabel("Enter Task id");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit=new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				panel5.add(l3);
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(user3);
         				panel5.add(l1);
         				panel5.add(user);
         				panel.add(panel5);
         				panel5.add(submit);
         				panel.updateUI();
         			submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String instruction=user3.getText();
         						String taskid=user.getText();
         						int check=ad.approveLog(taskid,instruction);
         						JPanel panel6=new JPanel();
         						panel6.setBackground(new Color(255,255,244));
         						if(check==1)
         							panel6.add(new JLabel("Logistics request instructed"));
         						else
         							panel6.add(new JLabel("Logistics request not instructed"));
         							panel.add(panel6);
         							panel.updateUI();
         					}
         				});
         		}
         		});
         	
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			System.out.println(ad.getName());
		
	}	
	}


	public class View_Staff
	{
		JPanel panel1,panel2;
		View_Staff(String username_1)
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel1=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
			JButton home=new JButton("Home");
			JButton task_report=new JButton("Task Report");
			JButton log=new JButton("Send Logistic");
			JButton status=new JButton("Update Status");
			JButton leave=new JButton("Leave");
			JButton logout=new JButton("Logout");
			JLabel lbl=new JLabel("");
			JLabel timeLabel = new JLabel();
			panel1.add(lbl);
			panel1.add(home);
			panel1.add(status);
			panel1.add(log);
			panel1.add(task_report);
			panel1.add(leave);
			panel1.setBackground(new Color(255,255,244));
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel1.add(timeLabel);
			panel1.add(logout);
			panel.add(panel1);
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			read_staff(username_1);
			logout.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			new SystemFMS();
         			}
         			});
			log.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {
         		  
         		JLabel l2=new JLabel("Enter quantity");
         		JTextField intruct2=new JTextField();
         		intruct2.setPreferredSize(new Dimension(100, 40));
         		JLabel l=new JLabel("Enter Taskid");
         		JTextField intruct=new JTextField();
         		intruct.setPreferredSize(new Dimension(100, 40));
         		JButton submit =new JButton("Submit");
         		JPanel panel5=new JPanel(new FlowLayout());
         		panel5.setBackground(new Color(255,255,244));
         		panel5.add(l2);
         		panel5.add(intruct2);
         		panel5.add(l);
         		panel5.add(intruct);
         		panel5.add(submit);
         		panel.add(panel5);
         		panel.updateUI();

         	
         	submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         		String id=intruct.getText();
         		String quantity=intruct2.getText();
         		st.sendLogisticreq(quantity,id);
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
         			panel6.add(new JLabel("Logistics send to supervisor"));
         		panel.add(panel6);
         		panel.updateUI();
         }
     });
         		}
         		});  
         	home.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         		new View_Staff(username_1);
				}
				});
			status.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			 	JLabel l=new JLabel("Status to be updated");
         				JTextField intruct=new JTextField();
         				intruct.setPreferredSize(new Dimension(100, 40));
         				JButton submit =new JButton("Submit");
         				panel2=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
         				panel2.setBackground(new Color(255,255,244));
         				panel2.add(l);
         				panel2.add(intruct);
         				panel2.add(submit);
         				panel.add(panel2);
         				panel.updateUI();
         		submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 	
         		String update=intruct.getText();
         		st.updateStatus(update,st);
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
         		panel6.add(new JLabel("status updated to "+update));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         		});	
         	}
         });

         		leave.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         		JLabel l=new JLabel("Enter end Date");
         			JTextField intruct=new JTextField();
         			intruct.setPreferredSize(new Dimension(100, 40));
         			JLabel l1=new JLabel("Reason");
         			JTextField reason=new JTextField();
         			reason.setPreferredSize(new Dimension(100, 40));
         			JButton submit =new JButton("Submit");
         			panel2=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
         			panel2.setBackground(new Color(255,255,244));
         			panel2.add(l);
         			panel2.add(intruct);
         			panel2.add(l1);
         			panel2.add(reason);
         			panel2.add(submit);
         			panel.add(panel2);
         			panel.updateUI();
         			submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 	
         			st.sendLeave();
         			JPanel panel6=new JPanel();
         			panel6.setBackground(new Color(255,255,244));
         		panel6.add(new JLabel("Leave request send"));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         	});
         		}
         	});
         		task_report.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			JLabel l=new JLabel("Enter task name");
         			JTextField intruct=new JTextField();
         			intruct.setPreferredSize(new Dimension(100, 40));
         			JButton submit =new JButton("Submit");
         			panel2=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
         			panel2.setBackground(new Color(255,255,244));
         			panel2.add(l);
         			panel2.add(intruct);
         			panel2.add(submit);
         			panel.add(panel2);
         			panel.updateUI();
         			submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 	
         			String update=intruct.getText();
         			int check=st.generate_taskreport(update);
         			JPanel panel6=new JPanel();
         			panel6.setBackground(new Color(255,255,244));
         			if(check==1)
         				panel6.add(new JLabel("Task report generated"));
         			else
         				panel6.add(new JLabel("Task report not generated"));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         	});
         }
     });

         		frame.getContentPane().add(panel,BorderLayout.CENTER);
			
			
		}
	}
public class View_Supervisor
	{
		JButton sup=null,sta=null,supview=null,staview=null,supdel=null,stadel=null,accept=null,reject=null,hold=null;
		JPanel panel1,panel2,panel3,panel4;
		View_Supervisor(String s)
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel1=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
			panel1.setBackground(new Color(255,255,244));
			JButton home=new JButton("Home");
			JButton staff=new JButton("Add ");
			JButton view_2=new JButton("View");
			JButton delete_2=new JButton("Delete");
			JButton leave=new JButton("Leave");
			JButton task=new JButton("AssignTask");
			JButton maintain=new JButton("Maintain Logistics");
			JButton sendLog=new JButton("Send Logistics");
			JButton approve=new JButton("Approve Logistic");
			JButton view=new JButton("View Task");
			JButton logout=new JButton("Logout");

			JLabel lbl=new JLabel("");
			JLabel timeLabel = new JLabel();
			panel1.add(lbl);
			panel1.add(home);
			panel1.add(staff);
			panel1.add(view_2);
			panel1.add(delete_2);
			panel1.add(leave);
			panel1.add(task);
			panel1.add(approve);
			panel1.add(maintain);
			panel1.add(sendLog);
			panel1.add(view);
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel1.add(timeLabel);
			panel1.add(logout);
			panel.add(panel1);
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			Scanner scan=new Scanner(System.in);
			read_supervisor(s);
			logout.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			new SystemFMS();
         		}});
			maintain.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         		sp.maintainLog();
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
         		panel6.add(new JLabel("Logistics view on console"));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         	});
         	sendLog.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         		JLabel l=new JLabel("Enter Taskid");
         		JTextField intruct=new JTextField();
         		intruct.setPreferredSize(new Dimension(100, 40));
         		JButton submit =new JButton("Submit");
         		JPanel panel5=new JPanel(new FlowLayout());
         		panel5.setBackground(new Color(255,255,244));
         		panel5.add(l);
         		panel5.add(intruct);
         		panel5.add(submit);
         		panel.add(panel5);
         		panel.updateUI();

         	
         	submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         		String id=intruct.getText();
         		int check=sp.sendLogisticApproval(sp,id);
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
         		if(check==1)
         			panel6.add(new JLabel("Logistics send to admin"));
         		else
         			panel6.add(new JLabel("log not send"));
         		panel.add(panel6);
         		panel.updateUI();
         }
     });
         		}
         	});
         	approve.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         		JLabel l1=new JLabel("Enter instruction");
         		JTextField intruct1=new JTextField();
         		intruct1.setPreferredSize(new Dimension(100, 40));
         		JLabel l=new JLabel("Enter Taskid");
         		JTextField intruct=new JTextField();
         		intruct.setPreferredSize(new Dimension(100, 40));
         		JButton submit =new JButton("Submit");
         		JPanel panel5=new JPanel(new FlowLayout());
         		panel5.setBackground(new Color(255,255,244));
         		panel5.add(l1);
         		panel5.add(intruct1);
         		panel5.add(l);
         		panel5.add(intruct);
         		panel5.add(submit);
         		panel.add(panel5);
         		panel.updateUI();

         	
         	submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         		String id=intruct.getText();
         		String ints=intruct1.getText();
         		int check=sp.approvelogisticApproval(ints,sp,id);
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
         		if(check==1)
         			panel6.add(new JLabel("Approve instructed"));
         		else
         			panel6.add(new JLabel("not instructed"));
         		panel.add(panel6);
         		panel.updateUI();
         }
     });
         		}
         	});

		staff.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			panel2=new JPanel(new FlowLayout());
         			System.out.println("Add");
         			sta = new JButton("Staff");
         			panel2.setBackground(new Color(255,255,244));
         			panel2.add(sta);
         			panel.add(panel2);
         			panel.updateUI();

         				sta.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         			
         	
         				JLabel l=new JLabel("Enter instruction");
         				JTextField intruct=new JTextField();
         				intruct.setPreferredSize(new Dimension(100, 40));
         				JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l);
         				panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String line=intruct.getText();
         						String name=user.getText();
         						System.out.println(line);
								int check=sp.approveStaffMember(line,sp,name);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								if(check==1)
									panel6.add(new JLabel("staff intstructed"));
								else 
									panel6.add(new JLabel("staff not instructed"));
								panel.add(panel6);
								panel.updateUI();
         					}
         				});
         			}
					});
         		}
         	});

            	
            	view_2.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			System.out.println("Add");
         			 panel3=new JPanel(new FlowLayout());
         			 panel3.setBackground(new Color(255,255,244));
         			staview = new JButton("Staff");
         			panel3.add(staview);
         			panel.add(panel3);
         			panel.updateUI();
         		
         			staview.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){ 
         						
         						sp.viewStaff(sp);
         						JPanel panel6=new JPanel();
         						panel6.setBackground(new Color(255,255,244));
         						panel6.add(new JLabel("Viewed on console"));
         						panel.add(panel6);
								panel.updateUI();
								frame.getContentPane().add(panel,BorderLayout.CENTER);

         					}    
            		
            	});
            }
         	});
         	delete_2.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			panel2=new JPanel(new FlowLayout());
         			System.out.println("Add");
         			 stadel = new JButton("Staff");
         			panel2.add(stadel);
         			panel2.setBackground(new Color(255,255,244));
         			panel.add(panel2);
         			panel.updateUI();
         			frame.getContentPane().add(panel,BorderLayout.CENTER);  
         			
         			stadel.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         			JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				panel5.setBackground(new Color(255,255,244));
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				submit.addActionListener(new ActionListener(){
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String name=user.getText();
								int check=sp.deleteStaff(name,sp);
								JPanel panel6=new JPanel();
								panel6.setBackground(new Color(255,255,244));
								if(check==1)
									panel6.add(new JLabel("staff deleted"));
								else
									panel6.add(new JLabel("staff not deleted"));

								panel.add(panel6);
								panel.updateUI();
								frame.getContentPane().add(panel,BorderLayout.CENTER);
         					}
         				});
         			}
         			});    
            	}
         	});

		leave.addActionListener(new ActionListener(){
         	public void actionPerformed(ActionEvent e){
         		JLabel l=new JLabel("Enter end Date");
         			JTextField intruct=new JTextField();
         			intruct.setPreferredSize(new Dimension(100, 40));
         			JLabel l1=new JLabel("Reason");
         			JTextField reason=new JTextField();
         			reason.setPreferredSize(new Dimension(100, 40));
         			JButton submit =new JButton("Submit");
         			panel2=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
         			panel2.setBackground(new Color(255,255,244));
         			panel2.add(l);
         			panel2.add(intruct);
         			panel2.add(l1);
         			panel2.add(reason);
         			panel2.add(submit);
         			panel.add(panel2);
         			panel.updateUI();
         			submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
				panel6.add(new JLabel("staff on leave"));
				panel.add(panel6);
				panel.updateUI();
         		sp.sendLeave();
         		}
         	});
         		}
         	});
		task.addActionListener(new ActionListener(){
         	public void actionPerformed(ActionEvent e){
         		JLabel l1=new JLabel("Enter taskname");
         		JTextField user=new JTextField();
         		user.setPreferredSize(new Dimension(100, 40));
         		JLabel l2=new JLabel("Enter number of staff");
         		JTextField user1=new JTextField();
         		JButton submit= new JButton("Submit");
         		user1.setPreferredSize(new Dimension(100, 40));
         		JPanel panel5=new JPanel(new FlowLayout());
         		panel5.setBackground(new Color(255,255,244));
         		panel5.add(l1);
         		panel5.add(user);
         		panel5.add(l2);
         		panel5.add(user1);
         		panel5.add(submit);
         		panel.add(panel5);
				panel.updateUI();
				
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
         	String taskname=user.getText();
         	String n=user1.getText();
				int check=sp.assignTasktoStaff(taskname,Integer.parseInt(n),sp);
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
         		System.out.println(check);
				if(check==1)
					panel6.add(new JLabel("Task assigned"));
				else
					panel6.add(new JLabel("Task not assigned(busy or department not same)"));
				panel.add(panel6);
				panel.updateUI();
				frame.getContentPane().add(panel,BorderLayout.CENTER);
         		
         		}
         	});
	}
});
	view.addActionListener(new ActionListener(){
         	public void actionPerformed(ActionEvent e){
         		sp.viewTask(sp);
         		JPanel panel6=new JPanel();
         		panel6.setBackground(new Color(255,255,244));
				panel6.add(new JLabel("View on console"));
				panel.add(panel6);
				panel.updateUI();
				frame.getContentPane().add(panel,BorderLayout.CENTER);
			}
		});

	home.addActionListener(new ActionListener(){
         					public void actionPerformed(ActionEvent e){
         						new View_Supervisor(s);
         					}
         				});
		}
}
	public void read_admin(){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("admin.csv"));
			String line_2=br_1.readLine();
			String[] details=line_2.split(",");
			ad=new Admin(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
		}
		
		catch(Exception e){
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
	public void read_staff(String s){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff.csv"));
			String line_2;
			while((line_2=br_1.readLine())!=null){
			String[] details=line_2.split(",");
				if(details[2].equals(s)){
						st=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
						break;
					}	
			}
		}
		
		catch(Exception e){
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
	public void read_supervisor(String s){
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("supervisor.csv"));
			String line_2;
			while((line_2=br_1.readLine())!=null){
			String[] details=line_2.split(",");
				if(details[2].equals(s)){
					sp=new Supervisor(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
					break;
				}	
			}
		}
		
		catch(Exception e){
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

	public static void main(String[] args){
		new SystemFMS();
	}
}

