import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
	JTextField amountField = new JTextField();
	JButton applyBtn = new JButton("Apply");
	String accountID = new String();
	String lastBalance = new String();
	
	public Deposit(String ID, String lBalance) {
		super("Deposit");
		accountID = ID;
		lastBalance = lBalance;
		
		JPanel Deposit = new JPanel(new GridLayout(2, 3));
		
		JLabel blank1 = new JLabel("");
		JLabel blank2 = new JLabel("");
		
		JLabel amountLabel1 = new JLabel("( Deposit and Wait for a moment . . . )");
		JLabel amountLabel2 = new JLabel("Enter the cash or check to Deposit : ");
		amountLabel1.setFont(new Font("", Font.PLAIN, 13));
		amountLabel1.setForeground(Color.darkGray);
		amountLabel2.setFont(new Font("", Font.BOLD, 14));
		
		Deposit.setFont(new Font("", Font.BOLD, 15));

		add(Deposit, BorderLayout.CENTER);
		Deposit.add(blank1);
		Deposit.add(amountLabel1);
		Deposit.add(blank2);
		Deposit.add(amountLabel2);
		Deposit.add(amountField);
		Deposit.add(applyBtn);
		
		amountLabel1.setHorizontalAlignment(JLabel.CENTER);
		amountLabel2.setHorizontalAlignment(JLabel.RIGHT);
		applyBtn.addActionListener(this);
		
		setSize(800, 110);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane showWithdraw = new JOptionPane();
		// TODO Auto-generated method stub
		if (e.getSource() == applyBtn) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String newDate = dateFormat.format(now);
			
			String DepositAmount = amountField.getText();
			int tmp = Integer.parseInt(lastBalance) + Integer.parseInt(DepositAmount);
			String newBalance = Integer.toString(tmp);
			
			String newTransaction = "\n" + accountID + " " + newBalance + " " + newDate + " " + "deposit" + " " + DepositAmount;
			
			File file = new File("c:\\temp\\transactions.txt");
			try {
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter writer = new BufferedWriter(fw);
				
				writer.write(newTransaction);
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			showWithdraw.showMessageDialog(null, DepositAmount + " has been deposit. Thank you.", "Deposit", JOptionPane.PLAIN_MESSAGE);
			dispose();
		}
	}
}
