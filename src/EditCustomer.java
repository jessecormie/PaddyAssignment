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

public class EditCustomer {

	private Object customerID;
	private ArrayList<Customer> customerList;
	private Customer customer;
	private JFrame f;
	private JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	private JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField,
			passwordTextField;

	public void editCustomer(ArrayList<Customer> customerList) {
		this.customerList=customerList;
		boolean loop = true;

		boolean found = false;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);

		} else {

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
				this.customerID = customerID;
				for (Customer aCustomer : customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						this.customer = aCustomer;
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
						Admin admin = new Admin();
						admin.admin();
					}
				} else {
					loop = false;
				}

			}

			f = new JFrame("Administrator Menu");
			f.setSize(400, 300);
			f.setLocation(200, 200);
			// closeWindow();
			// TODO dup 1.2
			firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
			surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
			pPPSLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
			dOBLabel = new JLabel("Date of birth", SwingConstants.LEFT);
			customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);// not
																				// dup
			passwordLabel = new JLabel("Password:", SwingConstants.LEFT);// not
																			// dup
			firstNameTextField = new JTextField(20);
			surnameTextField = new JTextField(20);
			pPSTextField = new JTextField(20);
			dOBTextField = new JTextField(20);
			customerIDTextField = new JTextField(20);// not dup
			passwordTextField = new JTextField(20);// not dup
			// end of dup
			JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

			JPanel cancelPanel = new JPanel();

			textPanel.add(firstNameLabel);
			textPanel.add(firstNameTextField);
			textPanel.add(surnameLabel);
			textPanel.add(surnameTextField);
			textPanel.add(pPPSLabel);
			textPanel.add(pPSTextField);
			textPanel.add(dOBLabel);
			textPanel.add(dOBTextField);
			textPanel.add(customerIDLabel);
			textPanel.add(customerIDTextField);
			textPanel.add(passwordLabel);
			textPanel.add(passwordTextField);

			firstNameTextField.setText(customer.getFirstName());
			surnameTextField.setText(customer.getSurname());
			pPSTextField.setText(customer.getPPS());
			dOBTextField.setText(customer.getDOB());
			customerIDTextField.setText(customer.getCustomerID());
			passwordTextField.setText(customer.getPassword());

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
	}

	public void saveCustomer(Customer customer, Object customerID ) {
		this.customer = customer;
		customer.setFirstName(firstNameTextField.getText());
		customer.setSurname(surnameTextField.getText());
		customer.setPPS(pPSTextField.getText());
		customer.setDOB(dOBTextField.getText());
		customer.setCustomerID(customerIDTextField.getText());
		customer.setPassword(passwordTextField.getText());	
		
		for (Customer aCustomer : customerList) {
			if (aCustomer.getCustomerID().equals(customerID)) {
				customerList.set(0, customer);
				Menu menu = new Menu();
				
			}
		}
		JOptionPane.showMessageDialog(null, "Changes Saved.");
		f.dispose();
	}
	
	
}
