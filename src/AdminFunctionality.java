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

	

	public void navigate() {
		f.dispose();

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
			admin.admin();
		} else {

			JButton first, previous, next, last, cancel;
			JPanel gridPanel, buttonPanel, cancelPanel;

			Container content = menu.getContentPane();

			content.setLayout(new BorderLayout());

			buttonPanel = new JPanel();
			gridPanel = new JPanel(new GridLayout(8, 2));
			cancelPanel = new JPanel();
			// TODO dup 1.3
			firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
			passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
			firstNameTextField = new JTextField(20);
			surnameTextField = new JTextField(20);
			pPSTextField = new JTextField(20);
			dOBTextField = new JTextField(20);
			customerIDTextField = new JTextField(20);
			passwordTextField = new JTextField(20);
			// end dup
			first = new JButton("First");
			previous = new JButton("Previous");
			next = new JButton("Next");
			last = new JButton("Last");
			cancel = new JButton("Cancel");
			// TODO dup 2.1
			firstNameTextField.setText(customerList.get(0).getFirstName());
			surnameTextField.setText(customerList.get(0).getSurname());
			pPSTextField.setText(customerList.get(0).getPPS());
			dOBTextField.setText(customerList.get(0).getDOB());
			customerIDTextField.setText(customerList.get(0).getCustomerID());
			passwordTextField.setText(customerList.get(0).getPassword());

			firstNameTextField.setEditable(false);
			surnameTextField.setEditable(false);
			pPSTextField.setEditable(false);
			dOBTextField.setEditable(false);
			customerIDTextField.setEditable(false);
			passwordTextField.setEditable(false);

			gridPanel.add(firstNameLabel);
			gridPanel.add(firstNameTextField);
			gridPanel.add(surnameLabel);
			gridPanel.add(surnameTextField);
			gridPanel.add(pPPSLabel);
			gridPanel.add(pPSTextField);
			gridPanel.add(dOBLabel);
			gridPanel.add(dOBTextField);
			gridPanel.add(customerIDLabel);
			gridPanel.add(customerIDTextField);
			gridPanel.add(passwordLabel);
			gridPanel.add(passwordTextField);

			buttonPanel.add(first);
			buttonPanel.add(previous);
			buttonPanel.add(next);
			buttonPanel.add(last);

			cancelPanel.add(cancel);

			content.add(gridPanel, BorderLayout.NORTH);
			content.add(buttonPanel, BorderLayout.CENTER);
			content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);

			first.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					menu.first();
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					menu.previous();
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					menu.next();
				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					menu.last();
				}
			});

			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					//dispose();
					admin.admin();
				}
			});
			menu.setContentPane(content);
			menu.setSize(400, 300);
			menu.setVisible(true);
		}
	}

	
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
