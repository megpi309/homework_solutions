package A1_2;

import A1_1.Account;

public class CompanyAccount extends Account{

	public static void main(String[] args) {
		
		Account sam_acc = new Account();
		String sam = "Sam"; 
		double sam_balance = 1200.00; 
		int sam_pin = 1234; 
		String sam_note = "When is your birthday?";
		
		sam_acc.owner = sam; 
		sam_acc.setInternalNote(sam_note); 
		sam_acc.setBalance(sam_balance); 
		sam_acc.pin = sam_pin; 
		
	
		sam_acc.changePin(1234, 1000); 
		System.out.println(sam_acc.getBalance());	
	}
}
