package Administrator;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entity.Customer;
import Entity.CustomerAccount;
import Entity.CustomerCurrentAccount;
import Entity.CustomerDepositAccount;

public class BankCharges {

	private Customer customer;
	private CustomerAccount acc;
	private JFrame f;

	public void bankCharges(ArrayList<Customer> customerList) {
		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Charges to:");
			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					this.customer = aCustomer;
					adminMenu(customer);
				} else {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						bankCharges(customerList);
					} else if (reply == JOptionPane.NO_OPTION) {
						f.dispose();
					}
				}
			}
		}
	}

	public void adminMenu(Customer customer) {
		f = new JFrame("Administrator Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		f.setVisible(true);

		JComboBox<String> box = new JComboBox<String>();
		for (int i = 0; i < customer.getAccounts().size(); i++) {
			box.addItem(customer.getAccounts().get(i).getNumber());
		}

		box.getSelectedItem();

		JPanel boxPanel = new JPanel();
		boxPanel.add(box);

		JPanel buttonPanel = new JPanel();
		JButton continueButton = new JButton("Apply Charge");
		JButton returnButton = new JButton("Return");
		buttonPanel.add(continueButton);
		buttonPanel.add(returnButton);
		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));

		content.add(boxPanel);
		content.add(buttonPanel);

		if (customer.getAccounts().isEmpty()) {
			JOptionPane.showMessageDialog(f,
					"This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!",
					JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
		} else {
			for (int i = 0; i < customer.getAccounts().size(); i++) {
				if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
					acc = customer.getAccounts().get(i);
				}
			}

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String euro = "\u20ac";

					if (acc instanceof CustomerDepositAccount) {

						JOptionPane.showMessageDialog(f, euro + "25" + " deposit account fee aplied.", "",
								JOptionPane.INFORMATION_MESSAGE);
						acc.setBalance(acc.getBalance() - 25);
						JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					}

					if (acc instanceof CustomerCurrentAccount) {

						JOptionPane.showMessageDialog(f, euro + "15" + " current account fee aplied.", "",
								JOptionPane.INFORMATION_MESSAGE);
						acc.setBalance(acc.getBalance() - 25);
						JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!",
								JOptionPane.INFORMATION_MESSAGE);
					}
					f.dispose();
				}
			});

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					f.dispose();
				}
			});
		}
	}

}
