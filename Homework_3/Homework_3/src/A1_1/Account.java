package A1_1;

public class Account {
	public String owner; 
	private double balance; 
	protected int pin; 
	String internalNote;
	
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getInternalNote() {
		return internalNote;
	}

	public void setInternalNote(String internalNote) {
		this.internalNote = internalNote;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	} 
	
	public void changePin(int pin1, int pin2) {
		if (this.pin == pin1) {
			this.pin = pin2; 
			System.out.println("New PIN: " + pin2); 
		}
		else {
			System.out.println("ERROR: wrong PIN, try again");
		}
		
	}
	
	public static void main(String[] args) {
		
		Account sam_acc = new Account();
		String sam = "Sam"; 
		double sam_balance = 1200.00; 
		int sam_pin = 1234; 
		String sam_note = "When is your birthday?";
		
		sam_acc.owner = sam; 
		sam_acc.internalNote = sam_note; 
		sam_acc.balance = sam_balance; 
		sam_acc.pin = sam_pin; 
		
	
		sam_acc.changePin(1234, 1000); 
		System.out.println(sam_acc.getBalance());
	}
}
