import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PwchkBeforeWithdraw extends JFrame implements ActionListener{
	String accountPW = new String();
	String accountID = new String();
	String lastBalance = new String();
	
	JPasswordField pwField = new JPasswordField();
	JButton submitBtn = new JButton("Submit");
	
	public PwchkBeforeWithdraw(String ID, String PW, String lBalance) {
		super("PASSWORD Check again");
		// PASSWORD Check
		accountID = ID;
		accountPW = PW;
		lastBalance = lBalance;
		JPanel pwCHK = new JPanel(new GridLayout(1, 3));
		JLabel pwLabel = new JLabel("PASSWORD again : ");

		add(pwCHK, BorderLayout.CENTER);
		pwCHK.add(pwLabel);
		pwCHK.add(pwField);
		pwCHK.add(submitBtn);
		
		pwLabel.setHorizontalAlignment(JLabel.CENTER);

		submitBtn.addActionListener(this);
		
		setSize(450, 70);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		
		String pwData = "";
		char[] secret_pw = pwField.getPassword();
		if (e.getSource() == submitBtn) {
			for(char c : secret_pw){         
		         Character.toString(c);
		         pwData += (pwData.equals("")) ? ""+c+"" : ""+c+"";   
		     }
			if(!pwData.equals(accountPW)) {
				JOptionPane errorM = new JOptionPane();
				JOptionPane.showMessageDialog(null,"Please verify your PASSWORD again.", "ERROR", JOptionPane.WARNING_MESSAGE);
				pwField.setText("");
			}
			else {
				dispose();
				new Withdraw(accountID, lastBalance);
			}
		}
	}
}
