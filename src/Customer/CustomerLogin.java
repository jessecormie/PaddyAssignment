package Customer;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.Customer;

public class CustomerLogin {

	private JFrame f;
	private Customer customer;

	public void existingCustomer(ArrayList<Customer> customerList) {

		Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
		Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

		for (Customer aCustomer : customerList) {
			if (aCustomer.getCustomerID().equals(customerID) && aCustomer.getPassword().equals(customerPassword)) {
				this.customer = aCustomer;
				CustomerMenu cm = new CustomerMenu();
				cm.customer(customer);
			} else {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					existingCustomer(customerList);
				}
			}
		}
	}
}