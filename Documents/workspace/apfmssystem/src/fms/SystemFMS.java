package fms;
import java.util.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.*;

import javax.swing.*;
public class SystemFMS{

	ArrayList<Department> depts = new ArrayList<Department>();
	Admin admin = new Admin();
	JFrame frame;
	JPanel panel;
	JLabel label1,label2;
	JButton bttn_str,bttn_exit;
	
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
		label1.setBounds(100,120,500,40);
		label2.setBounds(250,180,450,40);
		label1.setFont(new Font("Arial",Font.BOLD,30));
		label2.setFont(new Font("Arial",Font.BOLD,30));
		bttn_str.setBounds(230,250,150,40);
		bttn_exit.setBounds(250,350,100,40);
		panel.add(label1);
		panel.add(label2);
		panel.add(bttn_str);
		panel.add(bttn_exit);
		panel.setBackground(new Color(255,255,244));
		bttn_str.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				new Login();
			}
		});
		
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,600);
		frame.setVisible(true);
	}

	public void register(int id, String type, String username, String password, String dob, String address, String department){

	}

	public void login(String username, String password){

	}

	public void logout(){

	}

	public static void main(String[] args){
		new SystemFMS();
	}
	public class Login
	{
		Login()
		{
			
			panel.removeAll();
			panel.updateUI();
			panel.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 60));
			JButton Home=new JButton("Home");
			JButton Staff=new JButton("Staff");
			JButton Log=new JButton("Logistic");
			JButton report=new JButton("Report");
			JButton request=new JButton("Request");
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
}