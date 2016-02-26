package Administrator;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.ATMCard;
import Entity.AccountTransaction;
import Entity.Customer;
import Entity.CustomerCurrentAccount;
import Entity.CustomerDepositAccount;

public class Account {

	private Customer customer;
	private JFrame f;
	private ArrayList<Customer> customerList;

	public void account(ArrayList<Customer> customerList) {
		this.customerList = customerList;
		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Object customerID = JOptionPane.showInputDialog(f,
					"Customer ID of Customer You Wish to Add an Account to:");
			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					this.customer = aCustomer;
					handleAccount();
				} else {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						account(customerList);
					}
				}
			}
		}
	}

	public void handleAccount() {
		String[] choices = { "Current Account", "Deposit Account" };
		String account = (String) JOptionPane.showInputDialog(null, "Please choose account type", "Account Type",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

		if (account.equals("Current Account")) {
			boolean valid = true;
			double balance = 0;
			String number = String
					.valueOf("C" + (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() + 1));
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
			int randomPIN = (int) (Math.random() * 9000) + 1000;
			String pin = String.valueOf(randomPIN);

			ATMCard atm = new ATMCard(randomPIN, valid);

			CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance, transactionList);

			customer.getAccounts().add(current);
			JOptionPane.showMessageDialog(f, "Account number = " + number + "\n PIN = " + pin, "Account created.",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (account.equals("Deposit Account")) {
			double balance = 0, interest = 0;
			String number = String
					.valueOf("D" + (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() + 1));
			ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
			CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance, transactionList);
			customer.getAccounts().add(deposit);
			JOptionPane.showMessageDialog(f, "Account number = " + number, "Account created.",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}