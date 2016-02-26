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
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			if (count == 0) {
				JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
						JOptionPane.INFORMATION_MESSAGE);
				((CustomerCurrentAccount) acc).getAtm().setValid(false);
			} else {
				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);
				if (checkPin == i) {
					JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin", JOptionPane.INFORMATION_MESSAGE);
					lodgeMoney();
				} else {
					count--;
					JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					lodgement(acc);
				}
			}
		} else {
			lodgeMoney();
		}
	}

	public void lodgeMoney() {
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