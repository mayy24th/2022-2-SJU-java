import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class LoginFunc extends JFrame implements ActionListener{
	String accountID = new String();
	String accountPW = new String();
	
	JPanel l_FuncP = new JPanel(new GridLayout(2,3));
	
	JLabel accountLabel1 = new JLabel("Account ");
	JLabel accountLabel2 = new JLabel("Number  :");
	JLabel customID = new JLabel();
	JLabel blank = new JLabel("");
	
	JButton balanceBtn = new JButton("Balance");
	JButton withdrawBtn = new JButton("Withdraw");
	JButton depositBtn = new JButton("Deposit");
	
	String balance = new String();
	
	public LoginFunc(String ID, String PW) {
		super("Service");
		accountID = ID;
		accountPW = PW;
		customID.setText(ID);
		
		add(l_FuncP, BorderLayout.CENTER);
		accountLabel1.setFont(new Font("", Font.BOLD, 15));
		accountLabel2.setFont(new Font("", Font.BOLD, 15));
		customID.setFont(new Font("", Font.BOLD, 15));
		
		l_FuncP.add(accountLabel1);
		l_FuncP.add(accountLabel2);
		l_FuncP.add(customID);
		
		l_FuncP.add(balanceBtn);
		l_FuncP.add(withdrawBtn);
		l_FuncP.add(depositBtn);
		
		
		accountLabel1.setHorizontalAlignment(JLabel.RIGHT);
		accountLabel2.setHorizontalAlignment(JLabel.LEFT);
		customID.setHorizontalAlignment(JLabel.LEFT);
		
		balanceBtn.addActionListener(this);
		withdrawBtn.addActionListener(this);
		depositBtn.addActionListener(this);
		
		setSize(400, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ReadData();
		
	}
	public void ReadData() {
		String[] readInfo = new String[5];
		String[] info = new String[5];
		String Account = "";
		info[2] = "EMPTY";
		
		Scanner scanner;
		try {
			scanner = new Scanner(new File("c:\\temp\\transactions.txt"));
			while (scanner.hasNext()) {
				for(int i=0; i<5; i++) {
					readInfo[i] = scanner.next();
				}
				if(readInfo[0].equals(accountID)) {
					// 최신 거래내역 받아올 기준 어떻게 ? 
					if(info[2].equals("EMPTY") || readInfo[2].compareTo(info[2]) >= 0) {
						
						for(int i=0; i<5; i++) info[i] = readInfo[i];
					}
				}
			}
			balance = info[1];	// 현재 잔고
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(balanceBtn.equals(e.getSource())) {
			ReadData();
			JOptionPane showBalance = new JOptionPane();
			showBalance.showMessageDialog(null, "Account's current balance : " + balance, "Balance", JOptionPane.PLAIN_MESSAGE);
		}
		if(withdrawBtn.equals(e.getSource())) {
			new PwchkBeforeWithdraw(accountID, accountPW, balance);
			ReadData();
		}
		if(depositBtn.equals(e.getSource())) {
			new Deposit(accountID, balance);
			ReadData();
		}
	}
}
