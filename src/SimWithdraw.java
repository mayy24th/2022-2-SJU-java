import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class SimWithdraw {
	String simWithdrawRes;
	int tmp, isError;
	public SimWithdraw(String ID, String withDrawAmount, String lastBalance) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = dateFormat.format(now);
		
		int tmp = Integer.parseInt(lastBalance) - Integer.parseInt(withDrawAmount);
		if(tmp < 0) {
			simWithdrawRes = "Balance is insufficient !";
			isError = 1;
		}
		else {
			simWithdrawRes = "Withdraw Completed";
			isError = 0;
			String newBalance = Integer.toString(tmp);
			String newTransaction = "\n" + ID + " " + newBalance + " " + newDate + " " + "withdraw" + " " + withDrawAmount;
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
		}
	}
	public int isError() {
		return isError;
	}
	public String getSimWithdrawRes() {
		return simWithdrawRes;
	}
}
