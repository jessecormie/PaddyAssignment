package Customer;
import java.awt.BorderLayout;
import java.awt.Container;
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
import Entity.CustomerAccount;

public class RegisterCustomer {

	private ArrayList<Customer> customerList;
	private JFrame f, f1;
	private JLabel firstNameLabel, surnameLabel, ppsLabel, dobLabel;
	private JTextField firstNameTextField, surnameTextField, ppsTextField, dobTextField;
	private JPanel panel2;
	private JButton add, cancel;
	private String pps, firstName, surname, dob, customerID, password;

	public void newCustomer(final ArrayList<Customer> customerList) {
		this.customerList = customerList;
		f1 = new JFrame("Create New Customer");
		f1.setSize(400, 300);
		f1.setLocation(200, 200);

		Container content = f1.getContentPane();
		content.setLayout(new BorderLayout());

		firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		ppsLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		dobLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		ppsTextField = new JTextField(20);
		dobTextField = new JTextField(20);
		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameTextField);
		panel.add(surnameLabel);
		panel.add(surnameTextField);
		panel.add(ppsLabel);
		panel.add(ppsTextField);
		panel.add(dobLabel);
		panel.add(dobTextField);

		panel2 = new JPanel();
		add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
				f1.dispose();
			}
		});

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
			}
		});
		panel2.add(add);
		panel2.add(cancel);

		content.add(panel, BorderLayout.CENTER);
		content.add(panel2, BorderLayout.SOUTH);

		f1.setVisible(true);
	}

	public void add() {
		pps = ppsTextField.getText();
		firstName = firstNameTextField.getText();
		surname = surnameTextField.getText();
		dob = dobTextField.getText();
		customerID = "ID" + pps;

		boolean notValid = true;
		while (notValid) {
			password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

			if (password.length() != 7) {
				JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
			} else {
				notValid = false;
				ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
				Customer customer = new Customer(pps, surname, firstName, dob, customerID, password, accounts);
				customerList.add(customer);
				JOptionPane.showMessageDialog(f, "CustomerID = " + customerID + "\n Password = " + password,
						"Customer created.", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

}