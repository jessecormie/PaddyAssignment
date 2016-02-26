import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Navigate {

	private ArrayList<Customer> customerList;
	private int position = 0;
	private JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	private JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField,
			passwordTextField;

	public void navigate(final ArrayList<Customer> customerList) {
		this.customerList = customerList;
		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display");
		} else {
			JButton first, previous, next, last, cancel;
			JPanel gridPanel, buttonPanel, cancelPanel;

			Menu menu = new Menu();
			Container content = menu.getContentPane();

			content.setLayout(new BorderLayout());

			buttonPanel = new JPanel();
			gridPanel = new JPanel(new GridLayout(8, 2));
			cancelPanel = new JPanel();

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

			first = new JButton("First");
			previous = new JButton("Previous");
			next = new JButton("Next");
			last = new JButton("Last");
			cancel = new JButton("Cancel");

			getUser(0);

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
					getUser(0);
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if (position > 0) {
						position = position - 1;
						getUser(position);
					}
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					if (position != customerList.size() - 1) {
						position = position + 1;
						getUser(position);
					}
				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					int position = customerList.size() - 1;
					getUser(position);
				}
			});

			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					Admin a = new Admin();
					a.admin();
				}
			});
			menu.setContentPane(content);
			menu.setSize(400, 300);
			menu.setVisible(true);
		}
	}

	public void getUser(int position) {
		firstNameTextField.setText(customerList.get(position).getFirstName());
		surnameTextField.setText(customerList.get(position).getSurname());
		pPSTextField.setText(customerList.get(position).getPPS());
		dOBTextField.setText(customerList.get(position).getDOB());
		customerIDTextField.setText(customerList.get(position).getCustomerID());
		passwordTextField.setText(customerList.get(position).getPassword());
	}

}
