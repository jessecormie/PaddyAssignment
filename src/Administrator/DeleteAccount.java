package Administrator;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.Customer;

public class DeleteAccount {

	private JFrame f;
	private Customer customer;

	public void deleteAccount(ArrayList<Customer> customerList) {
		Object customerID = JOptionPane.showInputDialog(f,
				"Customer ID of Customer from which you wish to delete an account");

		for (Customer aCustomer : customerList) {
			if (aCustomer.getCustomerID().equals(customerID)) {
				this.customer = aCustomer;
				// Here I would make the user select a an account to delete from
				// a combo box. If the account had a balance of 0 then it would
				// be deleted. (I do not have time to do this)
			} else {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					deleteAccount(customerList);
				}
			}
		}
	}
}