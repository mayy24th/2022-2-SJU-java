import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimBalance {
	String simBalanceRes;
	
	public SimBalance(String ID) {
		String readID;
		String [] readInfo = new String[5];
		Scanner scanner;
		try {
			scanner = new Scanner(new File("c:\\temp\\transactions.txt"));
			while (scanner.hasNext()) {
				for(int i=0; i<5; i++) {
					readInfo[i] = scanner.next();
				}
				if(readInfo[0].equals(ID)) {
					// 최신 거래내역 받아올 기준 어떻게 ? 
					if(readInfo[2].compareTo(ID) >= 0) {
						simBalanceRes = readInfo[1];
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getSimBalanceRes() {
		return simBalanceRes;
	}
}
