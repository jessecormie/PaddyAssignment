import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DeleteAccount {

	private JFrame f;

	public void deleteAccount(ArrayList<Customer> customerList) {
		boolean found = true, loop = true;

		{
			Object customerID = JOptionPane.showInputDialog(f,
					"Customer ID of Customer from which you wish to delete an account");

			for (Customer aCustomer : customerList) {

				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					//customer = aCustomer;
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

					//admin();
				}
			} else {
				// Here I would make the user select a an account to delete from
				// a combo box. If the account had a balance of 0 then it would
				// be deleted. (I do not have time to do this)
			}

		}
	}

}
