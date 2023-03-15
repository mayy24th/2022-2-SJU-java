import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Simulation extends JFrame implements ActionListener{
	JPanel simPanel = new JPanel(new BorderLayout());

	JButton simBtn = new JButton(" Start simulation ");
	JLabel resLabel = new JLabel("  Result  ");
	
	String simContents = new String("");
	JTextArea simContentsArea = new JTextArea(simContents);
	JScrollPane scroll = new JScrollPane(simContentsArea);
	
	public Simulation() {
		setTitle("Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(simPanel, BorderLayout.CENTER);
	
	
		simPanel.add(simBtn, BorderLayout.NORTH);
		simPanel.add(resLabel, BorderLayout.WEST);
		simPanel.add(scroll, BorderLayout.CENTER);
		
		simContentsArea.setSize(5,5);
		simBtn.addActionListener(this);
		setSize(600, 500);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	
	}
	public void dataParsing() {
		String [] data = new String[4];
		int flag;
		Scanner scanner;
		try {
			scanner = new Scanner(new File("c:\\temp\\simulation.txt"));
			while (scanner.hasNext()) {
				data[3] = "0";
				flag = 0;
				for(int i=0; i<4; i++) {
					data[i] = scanner.next();
					if(data[i].equals("balance")) {
						simContents = simContents + "\n" + data[0] + " " + data[1] + " " + data[2];
						flag = 1;
						break;
					}
				}
				if(flag == 0) simContents = simContents + "\n" + data[0] + " " + data[1] + " " + data[2] + " " + data[3];
				
				// 시뮬레이션 계정 확인
				SimLoginCheck slc = new SimLoginCheck(data[0], data[1]);
				// 아이디 비밀번호 불일치
				if(slc.GetRes() == 0) simContents = simContents + "\n ---> ERROR : Please verify your ID or PASSWORD again.\n";
				// 아이디 비밀번호 일치
				else if(slc.GetRes() == 1) {
					 SimBalance lastBalance = new SimBalance(data[0]);
					 if(data[2].equals("balance")) {
						 simContents = simContents + "\n ---> Account's current balance : " + 	lastBalance.getSimBalanceRes() + "\n";
					 }
					 else if(data[2].equals("withdraw")) {
						 SimWithdraw sWd = new SimWithdraw(data[0], data[3], lastBalance.getSimBalanceRes());
						 if(sWd.isError == 1) simContents = simContents + "\n ---> ERROR : " + sWd.getSimWithdrawRes() + "\n";
						 else if(sWd.isError == 0) simContents = simContents + "\n ---> " + sWd.getSimWithdrawRes() + "\n";
					 }
					 else if(data[2].equals("deposit")) {
						 SimDeposit sD = new SimDeposit(data[0], data[3], lastBalance.getSimBalanceRes());
						 simContents = simContents + "\n ---> " + sD.getSimDepositRes() + "\n";
					 }
				}
				
				simContentsArea.setText(simContents);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == simBtn) {
			dataParsing();
		}
		
	}
}
