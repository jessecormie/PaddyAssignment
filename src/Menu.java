import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private JFrame f;
	private JRadioButton radioButton;

	public static void main(String[] args) {
		Menu driver = new Menu();
		driver.menuStart();
	}

	public void menuStart() {
		f = new JFrame("User Type");
		f.setSize(400, 300);
		f.setLocation(200, 200);

		JPanel userTypePanel = new JPanel();
		final ButtonGroup userType = new ButtonGroup();

		userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
		radioButton.setActionCommand("Customer");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("Administrator"));
		radioButton.setActionCommand("Administrator");
		userType.add(radioButton);

		userTypePanel.add(radioButton = new JRadioButton("New Customer"));
		radioButton.setActionCommand("New Customer");
		userType.add(radioButton);

		JPanel continuePanel = new JPanel();
		JButton continueButton = new JButton("Continue");
		continuePanel.add(continueButton);

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(2, 1));
		content.add(userTypePanel);
		content.add(continuePanel);

		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String user = userType.getSelection().getActionCommand();
				if (user.equals("New Customer")) {
					RegisterCustomer cf = new RegisterCustomer();
					cf.newCustomer(customerList);
					setCustomerList(cf.getCustomerList());
				} else if (user.equals("Administrator")) {
					Admin admin = new Admin();
					admin.login(customerList);
				} else if (user.equals("Customer")) {
					CustomerLogin cl = new CustomerLogin();
					cl.existingCustomer(customerList);
				}
			}
		});
		f.setVisible(true);
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

}