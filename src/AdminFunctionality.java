import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AdminFunctionality {
	Admin admin = new Admin();
	Menu menu= new Menu();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private Customer customer = null;
	private CustomerAccount acc;
	private JFrame f;
	private JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	private JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField,
			passwordTextField;
	private Container content;

	

	

	
	public void deleteCustomer() {
		boolean found = true, loop = true;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
			//dispose();
			admin.admin();
		} else {
			{
				Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");

				for (Customer aCustomer : customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						customer = aCustomer;
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

						admin.admin();
					}
				} else {
					if (customer.getAccounts().size() > 0) {
						JOptionPane.showMessageDialog(f,
								"This customer has accounts. \n You must delete a customer's accounts before deleting a customer ",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
					} else {
						customerList.remove(customer);
						JOptionPane.showMessageDialog(f, "Customer Deleted ", "Success.",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}
	}

	public void deleteAccount() {
		boolean found = true, loop = true;

		{
			Object customerID = JOptionPane.showInputDialog(f,
					"Customer ID of Customer from which you wish to delete an account");

			for (Customer aCustomer : customerList) {

				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					customer = aCustomer;
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

					admin.admin();
				}
			}
		}
	}

}
