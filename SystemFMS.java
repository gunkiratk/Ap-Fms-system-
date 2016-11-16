import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class SystemFMS{

	ArrayList<Department> depts = new ArrayList<Department>();
	Admin admin = new Admin();

	SystemFMS(){
		frame.getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		label1 = new JLabel("Facilities Management Services System");
		JButton start_button = new JButton("LOGIN");
		start_button.setBackground(Color.CYAN);
		JButton exit_button = new JButton("REGISTER");
		exit_button.setBackground(Color.CYAN);
		panel.add(label1, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(start_button, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(exit_button, gbc);
		exit_button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				System.exit(0);
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
	}

	public void register(int id, String type, String username, String password, String dob, String address, String department){

	}

	public void login(String username, String password){

	}

	public void logout(){

	}

	public static void main(String[] args){

	}
}