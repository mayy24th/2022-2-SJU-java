import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Login extends JFrame implements ActionListener{
	
	JPanel loginP = new JPanel(new GridLayout(3,2));
	JLabel idLabel = new JLabel("ID");
	JLabel pwLabel = new JLabel("PASSWORD");

	JLabel Blank = new JLabel("");
	JTextField idText = new JTextField();
	JPasswordField pwText = new JPasswordField();
	JButton loginBtn = new JButton("Login");
	
	public Login() {
		super("Login");
		this.setContentPane(loginP);
		loginP.add(idLabel);
		loginP.add(idText);
		loginP.add(pwLabel);
		loginP.add(pwText);
		loginP.add(Blank);
		loginP.add(loginBtn);
		
		idLabel.setHorizontalAlignment(NORMAL);
		pwLabel.setHorizontalAlignment(NORMAL);
		
		loginBtn.addActionListener(this);
		setSize(350, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		String pwData = "";
		char[] secret_pw = pwText.getPassword();
		if (actionEvent.getSource() == loginBtn) {
		
			String idData = idText.getText();
			for(char c : secret_pw){         
		         Character.toString(c);
		         pwData += (pwData.equals("")) ? ""+c+"" : ""+c+"";   
		     }
			
//			System.out.println(idData +" "+ pwData);
//			System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
			int sameFlag = 0;
			Scanner scanner;
			try {
				scanner = new Scanner(new File("c:\\temp\\bankaccount.txt"));
				while (scanner.hasNext()) {
					String getId = scanner.next();
					String getPw = scanner.next();
					//System.out.println(getId +" "+ getPw);
					if(idData.equals(getId) && pwData.equals(getPw)) {
						sameFlag = 1;
						break;
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(sameFlag == 0) {
				JOptionPane e = new JOptionPane();
				JOptionPane.showMessageDialog(null,"Please verify your ID or PASSWORD again.", "ERROR", JOptionPane.WARNING_MESSAGE);
				idText.setText("");
				pwText.setText("");
				//System.out.println("Please verify your ID or PASSWORD again.");
			}
			else {
				new LoginFunc(idData, pwData);
				dispose();
			}
		}
	}
}
	