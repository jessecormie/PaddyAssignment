import java.util.ArrayList;

public class CustomerAccount {

	private String number;
	private double balance;
	private ArrayList<AccountTransaction> transactionList;

	public CustomerAccount() {
		
	}

	public CustomerAccount(String number, double balance, ArrayList<AccountTransaction> transactionList) {
		this.number = number;
		this.balance = balance;
		this.transactionList = transactionList;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<AccountTransaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(ArrayList<AccountTransaction> transactionList) {
		this.transactionList = transactionList;
	}
}
