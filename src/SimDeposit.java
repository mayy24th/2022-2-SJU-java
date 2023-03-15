import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimDeposit {
	String simDepositRes;
	int tmp;
	public SimDeposit(String ID, String depositAmount, String lastBalance) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = dateFormat.format(now);
		
		tmp = Integer.parseInt(lastBalance) + Integer.parseInt(depositAmount);
		String newBalance = Integer.toString(tmp);
		
		String newTransaction = "\n" + ID + " " + newBalance + " " + newDate + " " + "deposit" + " " + depositAmount;
		
		File file = new File("c:\\temp\\transactions.txt");
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			writer.write(newTransaction);
			simDepositRes = "Deposit Completed";
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public String getSimDepositRes() {
		return simDepositRes;
	}
}
