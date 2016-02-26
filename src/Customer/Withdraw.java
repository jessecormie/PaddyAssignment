package Customer;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.AccountTransaction;
import Entity.CustomerAccount;
import Entity.CustomerCurrentAccount;
import Main.Menu;

public class Withdraw {

	private JFrame f;
	private double withdraw;

	public void withdraw(CustomerAccount acc) {
		if (acc instanceof CustomerCurrentAccount) {
			CheckPin cp = new CheckPin();
			cp.checkPin(acc);
			if (true) {
				withdrawMoney(acc);
			}
		} else {
			withdrawMoney(acc);
		}
	}
	public void withdrawMoney(CustomerAccount acc) {
		String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");
		if (Menu.isNumeric(balanceTest)) {
			withdraw = Double.parseDouble(balanceTest);
		} else {
			JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		if (withdraw > 500) {
			JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			withdraw = 0;
		}
		if (withdraw > acc.getBalance()) {
			JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			withdraw = 0;
		}

		String euro = "\u20ac";
		acc.setBalance(acc.getBalance() - withdraw);
		Date date = new Date();
		String date2 = date.toString();

		String type = "Withdraw";
		double amount = withdraw;

		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		acc.getTransactionList().add(transaction);

		JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Withdraw",
				JOptionPane.INFORMATION_MESSAGE);
	}
}