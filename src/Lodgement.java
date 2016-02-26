import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lodgement {

	private JFrame f;
	int count = 3;
	private CustomerAccount acc;
	private double balance;

	public void lodgement(CustomerAccount acc) {
		this.acc = acc;
		if (acc instanceof CustomerCurrentAccount) {
			CheckPin cp = new CheckPin();
			cp.checkPin(acc);
		} else {
			lodgeMoney(acc);
		}
	}

	public void lodgeMoney(CustomerAccount acc) {
		String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");
		if (Menu.isNumeric(balanceTest)) {
			balance = Double.parseDouble(balanceTest);
		} else {
			JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
		}

		String euro = "\u20ac";
		acc.setBalance(acc.getBalance() + balance);
		Date date = new Date();
		String date2 = date.toString();
		String type = "Lodgement";
		double amount = balance;

		AccountTransaction transaction = new AccountTransaction(date2, type, amount);
		acc.getTransactionList().add(transaction);

		JOptionPane.showMessageDialog(f, balance + euro + " added do you account!", "Lodgement",
				JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Lodgement",
				JOptionPane.INFORMATION_MESSAGE);
	}
}