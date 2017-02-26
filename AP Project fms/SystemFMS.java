// package fms;
// import net.java.dev.d/esigngridlayout.DesignGridLayout;
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
public class SystemFMS{

	// ArrayList<Department> depts = new ArrayList<Department>();
	// Admin admin = new Admin();
	Admin ad ;
	Supervisor sp;
	Staff st;
	JFrame frame;
	JPanel panel;
	JLabel label1,label2;
	JButton bttn_str,bttn_exit;
	String username_1;
	String name_1,type_1,password_1,email_1,dob_1,address_1;
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
			final JPanel panel1=new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
			panel2.setPreferredSize(new Dimension(130, 130));
			panel1.setPreferredSize(new Dimension(130, 130));
		 	JButton username=new JButton("Username");
			JButton password=new JButton("Password");
			JButton type =new JButton("Type");
			final JTextField txt=new JTextField();
			final JTextField txt5=new JPasswordField();
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
					// System.out.println(type_1_login);
					if(username_1.equals("") || password_1.equals(""))
						{
							JOptionPane.showMessageDialog(frame,"Invalid or empty field");
							new Login_Form();
						}
						else
						{
					if(type_1_login=="Admin")
					{	System.out.println("dede");
						filename="admin.csv";
						if(check_user_pass(filename,username_1,password_1)==1)
							{System.out.println("chala");
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
						if(check_user_pass(filename,username_1,password_1)==1)
							new View_Supervisor(username_1);
						else
						{
							JOptionPane.showMessageDialog(frame,"wrong username or password");
							new Login_Form();
							// frame.getContentPane().add(panel,BorderLayout.SOUTH);

						}
					}
					else if(type_1_login=="Staff")
					{
						filename="staff.csv";
						if(check_user_pass(filename,username_1,password_1)==1)
							new View_Staff(username_1);
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

		public int check_user_pass(String filename,String username_1,String password_1)
		{int check=0;
			BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader(filename));
			String line_1;
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
			final JTextField txt=new JTextField();
			final JTextField txt2=new JTextField();
			final JTextField txt3=new JTextField();
			final JTextField txt4=new JTextField();
			final JTextField txt5=new JPasswordField();
			final JTextField txt6=new JTextField();
			final JTextField txt7=new JTextField();

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
			if(type_1.equals("") || name_1.equals("") || username_1.equals("") || email_1.equals("") || password_1.equals("") || dob_1.equals("") || address_1.equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Invalid or empty field");
					new Register_Form();
				}
			// public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
   // 			 Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

			// 	public static boolean validate(String emailStr) {
   //      		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
   //      		if(!(matcher.find()))
   //      		JOptionPane.showMessageDialog(frame,"Invalid email address");
           // Action that you want to take. For ex. make email id field red
           // or give message box saying invalid email id.
        
   			else{	// }
			System.out.println(txt.getText()+txt2.getText());
					Random generator=new Random();
					x=generator.nextInt(10000)+1;
					System.out.println(x);
					// list=new User()
				
			
			System.out.println(type_1);
			if(type_1.equalsIgnoreCase("Admin"))
			{
				// admin_write();
				if(GetDatabase("admin.csv")==0)
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
			{	if(GetDatabase("admin.csv")!=0)
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
				if(GetDatabase("admin.csv")!=0)
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
			new Login_Form();
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
   				

				FileWriter fileWriter = null;
			 	 try {
			     	fileWriter = new FileWriter(filename,true);	
            		// fileWriter.append(FILE_HEADER.toString());
            		// fileWriter.append(NEW_LINE_SEPARATOR);
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
    			// String line_1=filereader.readLine();
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
		JPanel panel1,panel2,panel3,panel4;
		View_Admin()
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			panel1=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
			JButton home=new JButton("Home");
			JButton staff=new JButton("Add ");
			JButton view_2=new JButton("View");
			JButton delete_2=new JButton("Delete");
			JButton task=new JButton("Assign Task");
			JButton request=new JButton("Request");
			JButton exit=new JButton("exit");
			JLabel lbl=new JLabel(" Welcome Admin");
			JLabel timeLabel = new JLabel();
			panel1.add(lbl);
			panel1.add(home);
			panel1.add(staff);
			panel1.add(view_2);
			panel1.add(delete_2);
			panel1.add(task);
			// panel.add(request);
			panel.add(new JLabel(""));
			panel.add(panel1);
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel1.add(timeLabel);
			panel1.add(exit);
			exit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			 System.exit(0);
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
         			System.out.println("Add");
         			sup = new JButton("Supervisor");
         			sta = new JButton("Staff");
         			// back = new JButton("back");
         			//panel.removeAll();
					//panel.updateUI();
         			panel2.add(sup);
         			panel2.add(sta);
         			// panel1.add(back);
         			panel.add(panel2);
         			panel.updateUI();
         			frame.getContentPane().add(panel,BorderLayout.CENTER);
         			
         			sup.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         			
         	
         				JLabel l=new JLabel("Enter instruction");
         				final JTextField intruct=new JTextField();
         				intruct.setPreferredSize(new Dimension(100, 40));
         				JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				panel5.add(l);
         				panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				// frame.getContentPane().add(panel,BorderLayout.CENTER);
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String line=intruct.getText();
         						String name=user.getText();
         						System.out.println(line);
								int check=ad.approveSupervisorMember(line,name);
								JPanel panel6=new JPanel();
								if(check==1)
									panel6.add(new JLabel("supervisor intstructed"));
								else 
									panel6.add(new JLabel("supervisor not instructed"));
								panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);
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
         				JPanel panel5=new JPanel(new FlowLayout());
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
         			 supview = new JButton("Supervisor");
         			staview = new JButton("Staff");
         			 // back = new JButton("back");
         			// panel1.removeAll();
					// panel.remove(panel2);
         			panel3.add(supview);
         			panel3.add(staview);
         			// panel1.add(back);
         			panel.add(panel3);
         			panel.updateUI();
         			// frame.getContentPane().add(panel,BorderLayout.CENTER);  
         			supview.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){ 
         						
         						ad.viewSupervisor();
         						JPanel panel6=new JPanel();
         						panel6.add(new JLabel("VIEWED ON CONSOLE"));
         						panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);

         					}    
            		
            	});
         			staview.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){ 
         						
         						ad.viewStaff();
         						JPanel panel6=new JPanel();
         						panel6.add(new JLabel("Viewed on console"));
         						panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);

         					}    
            		
            	});
            }
         	});
         	delete_2.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			panel2=new JPanel(new FlowLayout());
         			System.out.println("Add");
         			 supdel = new JButton("Supervisor");
         			 stadel = new JButton("Staff");
         			 // back = new JButton("back");
         			// panel.remove(panel1);
         			panel2.add(supdel);
         			panel2.add(stadel);
         			// panel1.add(back);
         			panel.add(panel2);
         			panel.updateUI();
         			frame.getContentPane().add(panel,BorderLayout.CENTER);  
         			supdel.addActionListener(new ActionListener(){
         			public void actionPerformed(ActionEvent e){
         			JLabel l1=new JLabel("Enter username");
         				JTextField user=new JTextField();
         				user.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				// panel5.add(l);
         				// panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				// frame.getContentPane().add(panel,BorderLayout.CENTER);
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						// String line=intruct.getText();
         						String name=user.getText();
         						System.out.println(name);
								int check=ad.deleteSupervisor(name);
								JPanel panel6=new JPanel();
								if(check==1)
									panel6.add(new JLabel("supervisor deleted"));
								else 
									panel6.add(new JLabel("supervisor not deleted"));
								panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);
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
         				JPanel panel5=new JPanel(new FlowLayout());
         				// panel5.add(l);
         				// panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				// frame.getContentPane().add(panel,BorderLayout.CENTER);
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						// String line=intruct.getText();
         						String name=user.getText();
         						// System.out.println(line);
								int check=ad.deleteStaff(name);
								JPanel panel6=new JPanel();
								if(check==1)
									panel6.add(new JLabel("staff deleted"));
								else 
									panel6.add(new JLabel("staff not deleted"));
								panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);
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
         				final JTextField user2=new JTextField();
         				user2.setPreferredSize(new Dimension(100, 40));
         				JLabel l3=new JLabel("Enter deadline");
         				final JTextField user3=new JTextField();
         				user3.setPreferredSize(new Dimension(100, 40));
         				JLabel l4=new JLabel("Enter items used");
         				final JTextField user4=new JTextField();
         				user4.setPreferredSize(new Dimension(100, 40));
         				JLabel l5=new JLabel("Enter timetaken");
         				final JTextField user5=new JTextField();
         				user5.setPreferredSize(new Dimension(100, 40));
         				JLabel l6=new JLabel("Enter comments");
         				final JTextField user6=new JTextField();
         				user6.setPreferredSize(new Dimension(100, 40));
         				JButton submit= new JButton("Submit");
         				JPanel panel5=new JPanel(new FlowLayout());
         				// panel5.add(l);
         				// panel5.add(intruct);
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
         						// String line=intruct.getText();
         						String dept=user.getText();
         						String tname=user2.getText();
         						String deadline=user3.getText();
         						String i=user4.getText();
         						String tt=user5.getText();
         						String comm=user6.getText();
         						// System.out.println(line);
         							Random num=new Random();
								int n=num.nextInt(99)+1;
								int check=ad.assignTasktoSupervisor(dept,tname,deadline,"NOT STARTED",Integer.toString(n),i,tt,comm);
								JPanel panel6=new JPanel();
								if(check==1)
									panel6.add(new JLabel("Tassked assigned to supervisor of "+dept));
								else 
									panel6.add(new JLabel("no supervisor of "+dept+"exists"));
								panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);
         					}
         				});
         			}
         		});

         
         	
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			System.out.println(ad.getName());
			// Scanner scan=new Scanner(System.in);
			// // System.out.println("1.Add Supervisor");
			// // System.out.println("2.View Supervisor");
			// // System.out.println("3.Delete Supervisor");
			// // System.out.println("4.Add Staff");
			// // System.out.println("5.View Staff");
			// // System.out.println("6.Delete Staff");
			// System.out.println("7.AssignTask");
			// System.out.println("8.Approve log");
			// System.out.println("9.Reject log");
			// int x=scan.nextInt();
			// Scanner in=new Scanner(System.in);
			// while(x!=9){

			// if(x==1)
			// 	{	String line=in.nextLine();
			// 		ad.approveSupervisorMember(line);
			// 	}
			// else if(x==2)
			// {
			// 	ad.viewSupervisor();
			// }
			// else if(x==3)
			// {
			// 	String name=in.nextLine();
			// 	ad.deleteSupervisor(name);
			// }
			// else if(x==4)
			// {
			// 	String line=in.nextLine();
			// 	ad.approveStaffMember(line);
			// }
			// else if(x==5)
			// {
			// 	ad.viewStaff();
			// }
			// else if(x==6)
			// {
			// 	String name=in.nextLine();
			// 	ad.deleteStaff(name);
			// }
			// else if(x==7)
			// {
			// 	String dept=in.nextLine();
			// 	String taskname=in.nextLine();
			// 	String deadline=in.nextLine();
			// 	String iused=in.nextLine();
			// 	String time=in.nextLine();
			// 	String comm=in.nextLine();
			// 	Random num=new Random();
			// 	int n=num.nextInt(99)+1;
			// 	ad.assignTasktoSupervisor(dept,taskname,deadline,"NOT STARTED",Integer.toString(n),iused,time,comm);
			// }
			// x=scan.nextInt();

		// }
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
			// panel1.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 60));
			JButton home=new JButton("Home");
			JButton task_report=new JButton("Task Report");
			JButton log=new JButton("Send Logistic");
			JButton status=new JButton("Update Status");
			JButton leave=new JButton("Leave");
			JButton exit=new JButton("Exit");
			JLabel lbl=new JLabel("");
			JLabel timeLabel = new JLabel();
			panel1.add(lbl);
			panel1.add(home);
			panel1.add(status);
			panel1.add(log);
			panel1.add(task_report);
			panel1.add(leave);
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel1.add(timeLabel);
			panel1.add(exit);
			panel.add(panel1);
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			read_staff(username_1);
			exit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			System.exit(0);
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
         		panel6.add(new JLabel("status updated to "+update));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         		});	
         	}
         });

         		leave.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			st.sendLeave();
         			JPanel panel6=new JPanel();
         		panel6.add(new JLabel("Leave request send"));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         	});
         		task_report.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			JLabel l=new JLabel("Enter task name");
         			JTextField intruct=new JTextField();
         			intruct.setPreferredSize(new Dimension(100, 40));
         			JButton submit =new JButton("Submit");
         			panel2=new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 60));
         			panel2.add(l);
         			panel2.add(intruct);
         			panel2.add(submit);
         			panel.add(panel2);
         			panel.updateUI();
         			submit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 	
         			String update=intruct.getText();
         			st.generate_taskreport(update);
         			JPanel panel6=new JPanel();
         		panel6.add(new JLabel("Task report generated"));
         		panel.add(panel6);
         		panel.updateUI();
         		}
         	});
         }
     });

         		frame.getContentPane().add(panel,BorderLayout.CENTER);
			// frame.getContentPane().add(panel,BorderLayout.CENTER);

		// 	System.out.println("4.update status");
		// 	System.out.println("5.send leave");
		// 	System.out.println("6.generate");
		// 	System.out.println("7.AssignTask");
		// 	System.out.println("8.Approve log");
		// 	System.out.println("9.Reject log");
		// 	Scanner scan =new Scanner(System.in);
		// 	int x=scan.nextInt();
		// 	Scanner in=new Scanner(System.in);
		// 	while(x!=9){
		// 	if(x==4)
		// 	{
		// 		String line=in.nextLine();
		// 		st.updateStatus(line,st);
		// 	}
		// 	else if(x==5)
		// 	{
		// 		st.sendLeave();
		// 	}
		// 	else if(x==6)
		// 	{
				
		// 		// st.generate_taskReport();
		// 	}
		// 	else if(x==7)
		// 	{
		// 		String taskname=in.nextLine();
		// 		int n=in.nextInt();

		// 		sp.assignTasktoStaff(taskname,n,sp);
		// 	}

		// 	x=scan.nextInt();
		// }

			
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
			JButton home=new JButton("Home");
			JButton staff=new JButton("Add ");
			JButton view_2=new JButton("View");
			JButton delete_2=new JButton("Delete");
			JButton leave=new JButton("Leave");
			JButton task=new JButton("AssignTask");
			// JButton maintain=new JButton("Maintain Logistics");
			// JButton sendLog=new JButton("Send Logistics");
			JButton view=new JButton("View Task");
			JButton exit=new JButton("Exit");

			JLabel lbl=new JLabel("");
			JLabel timeLabel = new JLabel();
			panel1.add(lbl);
			panel1.add(home);
			panel1.add(staff);
			panel1.add(view_2);
			panel1.add(delete_2);
			// panel1.add(report);
			panel1.add(leave);
			panel1.add(task);
			// // panel.add(maintain);
			// panel.add(sendLog);
			panel1.add(view);
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
			String date1 = formatter.format(today);
			timeLabel.setText(date1);
			panel1.add(timeLabel);
			panel1.add(exit);
			panel.add(panel1);
			frame.getContentPane().add(panel,BorderLayout.CENTER);
			Scanner scan=new Scanner(System.in);
			read_supervisor(s);
			exit.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) { 
         			System.exit(0);
         		}});
		staff.addActionListener(new ActionListener(){
         		public void actionPerformed(ActionEvent e) {  
         			panel2=new JPanel(new FlowLayout());
         			System.out.println("Add");
         			sta = new JButton("Staff");
         			
         			panel2.add(sta);
         			// panel1.add(sta);
         			// panel1.add(back);
         			panel.add(panel2);
         			panel.updateUI();
         			// frame.getContentPane().add(panel,BorderLayout.CENTER);

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
         				panel5.add(l);
         				panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				// frame.getContentPane().add(panel,BorderLayout.CENTER);
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						String line=intruct.getText();
         						String name=user.getText();
         						System.out.println(line);
								int check=sp.approveStaffMember(line,sp,name);
								JPanel panel6=new JPanel();
								if(check==1)
									panel6.add(new JLabel("staff intstructed"));
								else 
									panel6.add(new JLabel("staff not instructed"));
								panel.add(panel6);
								panel.updateUI();
								// frame.getContentPane().add(panel,BorderLayout.CENTER);
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
         			 // supview = new JButton("Supervisor");
         			staview = new JButton("Staff");
         			 // back = new JButton("back");
         			// panel1.removeAll();
					// panel.remove(panel2);
         			// panel3.add(supdel);
         			panel3.add(staview);
         			// panel1.add(back);
         			panel.add(panel3);
         			panel.updateUI();
         			// frame.getContentPane().add(panel,BorderLayout.CENTER);  
         		
         			staview.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){ 
         						
         						sp.viewStaff(sp);
         						JPanel panel6=new JPanel();
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
         			 // supview = new JButton("Supervisor");
         			 stadel = new JButton("Staff");
         			 // back = new JButton("back");
         			// panel.remove(panel1);
         			// panel2.add(supview);
         			panel2.add(stadel);
         			// panel1.add(back);
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
         				// panel5.add(l);
         				// panel5.add(intruct);
         				panel5.add(l1);
         				panel5.add(user);
         				panel5.add(submit);
         				panel.add(panel5);
         				panel.updateUI();
         				// frame.getContentPane().add(panel,BorderLayout.CENTER);
         				submit.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						System.out.println("in action");
         						// String line=intruct.getText();
         						String name=user.getText();
         						// System.out.println(line);
								int check=sp.deleteStaff(name,sp);
								JPanel panel6=new JPanel();
								if(check==1)
									panel6.add(new JLabel("staff deleted"));
								else
									panel6.add(new JLabel("staff not deleted"));
								// else 
									// panel6.add(new JLabel("staff not deleted"));
								panel.add(panel6);
								panel.updateUI();
								frame.getContentPane().add(panel,BorderLayout.CENTER);
         					}
         				});
         			}
         			});    
            	}
         	});

		leave.addActionListener(new ActionListener()
         {
         	public void actionPerformed(ActionEvent e){
         		JPanel panel6=new JPanel();
				panel6.add(new JLabel("staff on leave"));
				panel.add(panel6);
				panel.updateUI();
				// frame.getContentPane().add(panel,BorderLayout.CENTER);
         		sp.sendLeave();
         		}
         	});
		task.addActionListener(new ActionListener()
         {
         	public void actionPerformed(ActionEvent e){
         		JLabel l1=new JLabel("Enter taskname");
         		JTextField user=new JTextField();
         		user.setPreferredSize(new Dimension(100, 40));
         		JLabel l2=new JLabel("Enter number of staff");
         		final JTextField user1=new JTextField();
         		JButton submit= new JButton("Submit");
         		user1.setPreferredSize(new Dimension(100, 40));
         		JPanel panel5=new JPanel(new FlowLayout());
         		panel5.add(l1);
         		panel5.add(user);
         		panel5.add(l2);
         		panel5.add(user1);
         		panel5.add(submit);
         		panel.add(panel5);
				panel.updateUI();
				// frame.getContentPane().add(panel,BorderLayout.CENTER);
				
				// int n=in.nextInt();
		submit.addActionListener(new ActionListener()
         {public void actionPerformed(ActionEvent e){
         	String taskname=user.getText();
         	String n=user1.getText();
				int check=sp.assignTasktoStaff(taskname,Integer.parseInt(n),sp);
         		JPanel panel6=new JPanel();
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
	view.addActionListener(new ActionListener()
         {
         	public void actionPerformed(ActionEvent e){
         		sp.viewTask(sp);
         		JPanel panel6=new JPanel();
				panel6.add(new JLabel("View on console"));
				panel.add(panel6);
				panel.updateUI();
				frame.getContentPane().add(panel,BorderLayout.CENTER);
			}
		});


			// System.out.println("4.Add Staff");
			// System.out.println("5.View Staff");
			// System.out.println("6.Delete Staff");
			// System.out.println("7.AssignTask");
			// System.out.println("8.Send leave");
			// System.out.println("9.Reject log");
			// int x=scan.nextInt();
			// Scanner in=new Scanner(System.in);
			// while(x!=9){
			// if(x==4)
			// {
			// 	String line=in.nextLine();
			// 	sp.approveStaffMember(line,sp);
			// }
			// else if(x==5)
			// {
			// 	sp.viewStaff(sp);
			// }
			// else if(x==6)
			// {
			// 	String name=in.nextLine();
			// 	sp.deleteStaff(name,sp);
			// }
			// else if(x==7)
			// {
			// 	String taskname=in.nextLine();
			// 	int n=in.nextInt();

			// 	sp.assignTasktoStaff(taskname,n,sp);
			// }
			// else if(x==8)
			// {
			// 	sp.sendLeave();
			// }

			// x=scan.nextInt();
	home.addActionListener(new ActionListener()
         				{
         					public void actionPerformed(ActionEvent e){
         						new View_Supervisor(s);
         					}
         				});
		
	}
}
	public void read_admin()
	{
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("admin.csv"));
			// String line_1=br_1.readLine();
			String line_2=br_1.readLine();
			String[] details=line_2.split(",");
			ad=new Admin(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
			
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
	public void read_staff(String s)
	 {
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("staff.csv"));
			String line_2;
			while((line_2=br_1.readLine())!=null){
			String[] details=line_2.split(",");
				if(details[2].equals(s))
					{
						st=new Staff(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
						break;
					}	
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
	public void read_supervisor(String s)
	{
		BufferedReader br_1 = null;
			try{
			br_1 = new BufferedReader(new FileReader("supervisor.csv"));
			String line_2;
			while((line_2=br_1.readLine())!=null){
			String[] details=line_2.split(",");
				if(details[2].equals(s))
					{
						sp=new Supervisor(details[1],details[0],details[2],details[5],details[6],details[4],details[5],details[7]);
						break;
					}	
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

	public static void main(String[] args){
		// System.out.printf("gfhefhbhf");
		new SystemFMS();
	}

}

