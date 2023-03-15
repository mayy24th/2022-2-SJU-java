import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Withdraw extends JFrame implements ActionListener{
	JTextField amountField = new JTextField();
	JButton applyBtn = new JButton("Apply");
	String accountID = new String();
	String lastBalance = new String();
	public Withdraw(String ID, String lBalance) {
		super("Withdraw");
		accountID = ID;
		lastBalance = lBalance;
		
		JPanel Withdraw = new JPanel(new GridLayout(1, 3));
		
		JLabel amountLabel = new JLabel("AMOUNT : ");
		amountLabel.setFont(new Font("", Font.BOLD, 15));
		
		Withdraw.setFont(new Font("", Font.BOLD, 15));

		add(Withdraw, BorderLayout.CENTER);
		Withdraw.add(amountLabel);
		Withdraw.add(amountField);
		Withdraw.add(applyBtn);
		
		amountLabel.setHorizontalAlignment(JLabel.CENTER);

		applyBtn.addActionListener(this);
		
		setSize(380, 80);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == applyBtn) {
			JOptionPane showWithdraw = new JOptionPane();
			
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String newDate = dateFormat.format(now);
			
			String withDrawAmount = amountField.getText();
			int tmp = Integer.parseInt(lastBalance) - Integer.parseInt(withDrawAmount);
			
			if(tmp < 0) {
				showWithdraw.showMessageDialog(null, "Balance is insufficient !", "ERROR", JOptionPane.ERROR_MESSAGE);
				dispose();
				return ;
			}
			String newBalance = Integer.toString(tmp);
			String newTransaction = "\n" + accountID + " " + newBalance + " " + newDate + " " + "withdraw" + " " + withDrawAmount;
			
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
			
			showWithdraw.showMessageDialog(null, withDrawAmount + " has been withdrawn.", "Withdraw", JOptionPane.PLAIN_MESSAGE);
			dispose();
		}
	}

}
