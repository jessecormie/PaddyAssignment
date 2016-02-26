import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DeleteCustomer {

	private Customer customer = null;
	private JFrame f;

	public void deleteCustomer(ArrayList<Customer> customerList) {
		boolean found = true, loop = true;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
		} else {
			Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");

			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					this.customer = aCustomer;
					loop = false;
				}
			}

			if (found == false) {
				int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					loop = true;
				} else if (reply == JOptionPane.NO_OPTION) {
					f.dispose();
					loop = false;
				}
			} else {
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
	}
}