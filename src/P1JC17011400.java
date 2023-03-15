import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class P1JC17011400 extends JFrame{
	
	public P1JC17011400() {
		setTitle("17011400 JeongJunMin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton loginBtn = new JButton("Login");
		JButton simBtn = new JButton("Simulation");
		
		c.add(loginBtn);
		c.setSize(20,30);
		c.add(simBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
			}
		});
		
		simBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SimThread th = new SimThread();
				th.start();
			}
		});
		
		setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
	}
	
	public static void main(String [] args){
		new P1JC17011400();
	}	
}
