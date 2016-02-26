import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DeleteCustomer {

	private ArrayList<Customer> customerList;
	private Customer customer;
	private JFrame f;

	public void deleteCustomer(ArrayList<Customer> customerList) {
		this.customerList = customerList;
		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
		} else {
			Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");
			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					this.customer = aCustomer;
					deleteChosenCustomer();
				} else {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						deleteCustomer(customerList);
					}
				}
			}
		}
	}
	public void deleteChosenCustomer() {
		if (customer.getAccounts().size() > 0) {
			JOptionPane.showMessageDialog(f,
					"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
					"Oops!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			customerList.remove(customer);
			JOptionPane.showMessageDialog(f, "Customer Deleted ", "Success.", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
