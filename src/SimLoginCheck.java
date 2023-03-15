import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimLoginCheck {
	int sameFlag = 0;
	
	public SimLoginCheck(String ID, String PW) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("c:\\temp\\bankaccount.txt"));
			while (scanner.hasNext()) {
				String getId = scanner.next();
				String getPw = scanner.next();
				//System.out.println(getId +" "+ getPw);
				if(ID.equals(getId) && PW.equals(getPw)) {
					sameFlag = 1;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int GetRes() {
		return sameFlag;
	}
}
