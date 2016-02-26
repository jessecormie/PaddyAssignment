package Administrator;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Entity.Customer;

public class EditCustomer {

	private Object customerID;
	private ArrayList<Customer> customerList;
	private Customer customer;
	private JFrame f;
	private JLabel firstNameLabel, surnameLabel, ppsLabel, dobLabel;
	private JTextField firstNameTextField, surnameTextField, ppsTextField, dobTextField;

	public void editCustomer(ArrayList<Customer> customerList) {
		this.customerList = customerList;
		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
			this.customerID = customerID;
			for (Customer aCustomer : customerList) {
				if (aCustomer.getCustomerID().equals(customerID)) {
					this.customer = aCustomer;
					adminMenu();
				} else {
					int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",
							JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						editCustomer(customerList);
					} 
				}
			}
		}
	}

	public void adminMenu() {
		f = new JFrame("Administrator Menu");
		f.setSize(600, 300);
		f.setLocation(200, 200);

		firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
		surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
		ppsLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
		dobLabel = new JLabel("Date of birth", SwingConstants.LEFT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		ppsTextField = new JTextField(20);
		dobTextField = new JTextField(20);

		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel cancelPanel = new JPanel();

		textPanel.add(firstNameLabel);
		textPanel.add(firstNameTextField);
		textPanel.add(surnameLabel);
		textPanel.add(surnameTextField);
		textPanel.add(ppsLabel);
		textPanel.add(ppsTextField);
		textPanel.add(dobLabel);
		textPanel.add(dobTextField);

		firstNameTextField.setText(customer.getFirstName());
		surnameTextField.setText(customer.getSurname());
		ppsTextField.setText(customer.getPps());
		dobTextField.setText(customer.getDob());

		JButton saveButton = new JButton("Save");
		JButton cancelButton = new JButton("Exit");

		cancelPanel.add(cancelButton, BorderLayout.SOUTH);
		cancelPanel.add(saveButton, BorderLayout.SOUTH);
		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(textPanel, BorderLayout.NORTH);
		content.add(cancelPanel, BorderLayout.SOUTH);

		f.setContentPane(content);
		f.setSize(340, 350);
		f.setLocation(200, 200);
		f.setVisible(true);
		f.setResizable(false);

		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				saveCustomer(customer, customerID);
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
			}
		});
	}

	public void saveCustomer(Customer customer, Object customerID) {
		this.customer = customer;
		customer.setFirstName(firstNameTextField.getText());
		customer.setSurname(surnameTextField.getText());
		customer.setPps(ppsTextField.getText());
		customer.setDob(dobTextField.getText());

		for (Customer aCustomer : customerList) {
			if (aCustomer.getCustomerID().equals(customerID)) {
				customerList.set(0, customer);
			}
		}
		JOptionPane.showMessageDialog(null, "Changes Saved.");
		f.dispose();
	}
}