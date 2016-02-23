import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Menu extends JFrame {

	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private int position = 0;
	private Customer customer = null;
	private CustomerAccount acc;
	private JFrame f, f1;
	private JLabel firstNameLabel, surnameLabel, pPPSLabel, dOBLabel, customerIDLabel, passwordLabel;
	private JTextField firstNameTextField, surnameTextField, pPSTextField, dOBTextField, customerIDTextField,
			passwordTextField;
	private Container content;
	private Customer e;
	private JPanel panel2;
	private JButton add, cancel;
	private String PPS, firstName, surname, DOB, CustomerID, password;
	private JRadioButton radioButton;
	private JComboBox<String> box;

	public static void main(String[] args) {
		Menu driver = new Menu();
		driver.menuStart();
	}

	public void menuStart() {
		f = new JFrame("User Type");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		closeWindow();

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
					newCustomer();
				} else if (user.equals("Administrator")) {
					administrator();
				} else if (user.equals("Customer")) {
					existingCustomer();
				}
			}
		});
		f.setVisible(true);
	}

	public void admin() {
		dispose();

		f = new JFrame("Administrator Menu");
		f.setSize(400, 400);
		f.setLocation(200, 200);
		closeWindow();
		f.setVisible(true);

		JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteCustomer = new JButton("Delete Customer");
		deleteCustomer.setPreferredSize(new Dimension(250, 20));
		deleteCustomerPanel.add(deleteCustomer);

		JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton deleteAccount = new JButton("Delete Account");
		deleteAccount.setPreferredSize(new Dimension(250, 20));
		deleteAccountPanel.add(deleteAccount);

		JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton bankChargesButton = new JButton("Apply Bank Charges");
		bankChargesButton.setPreferredSize(new Dimension(250, 20));
		bankChargesPanel.add(bankChargesButton);

		JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton interestButton = new JButton("Apply Interest");
		interestPanel.add(interestButton);
		interestButton.setPreferredSize(new Dimension(250, 20));

		JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton editCustomerButton = new JButton("Edit existing Customer");
		editCustomerPanel.add(editCustomerButton);
		editCustomerButton.setPreferredSize(new Dimension(250, 20));

		JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton navigateButton = new JButton("Navigate Customer Collection");
		navigatePanel.add(navigateButton);
		navigateButton.setPreferredSize(new Dimension(250, 20));

		JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton summaryButton = new JButton("Display Summary Of All Accounts");
		summaryPanel.add(summaryButton);
		summaryButton.setPreferredSize(new Dimension(250, 20));

		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton accountButton = new JButton("Add an Account to a Customer");
		accountPanel.add(accountButton);
		accountButton.setPreferredSize(new Dimension(250, 20));

		JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton returnButton = new JButton("Exit Admin Menu");
		returnPanel.add(returnButton);

		JLabel label1 = new JLabel("Please select an option");

		content = f.getContentPane();
		content.setLayout(new GridLayout(9, 1));
		content.add(label1);
		content.add(accountPanel);
		content.add(bankChargesPanel);
		content.add(interestPanel);
		content.add(editCustomerPanel);
		content.add(navigatePanel);
		content.add(summaryPanel);
		content.add(deleteCustomerPanel);
		content.add(returnPanel);

		bankChargesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				bankCharges();
			}
		});

		interestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				interest();
			}
		});

		editCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editCustomer();
			}
		});

		summaryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				summary();
			}
		});

		navigateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				navigate();
			}
		});

		accountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				account();
			}
		});

		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteCustomer();
			}
		});

		deleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				deleteAccount();
			}

		});
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				returnToMenu();
			}
		});
	}

	public void customer(final Customer customer) {
		f = new JFrame("Customer Menu");
		f.setSize(400, 300);
		f.setLocation(200, 200);
		closeWindow();
		f.setVisible(true);

		if (customer.getAccounts().size() == 0) {
			JOptionPane.showMessageDialog(f,
					"This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ",
					"Oops!", JOptionPane.INFORMATION_MESSAGE);
			f.dispose();
			menuStart();
		} else {
			JPanel buttonPanel = new JPanel();
			JPanel boxPanel = new JPanel();
			JPanel labelPanel = new JPanel();

			JLabel label = new JLabel("Select Account:");
			labelPanel.add(label);

			JButton returnButton = new JButton("Return");
			buttonPanel.add(returnButton);
			JButton continueButton = new JButton("Continue");
			buttonPanel.add(continueButton);

			box = new JComboBox<String>();
			for (int i = 0; i < customer.getAccounts().size(); i++) {
				box.addItem(customer.getAccounts().get(i).getNumber());
			}

			boxPanel.add(box);
			content = f.getContentPane();
			content.setLayout(new GridLayout(3, 1));
			content.add(labelPanel);
			content.add(boxPanel);
			content.add(buttonPanel);

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					returnToMenu();
				}
			});

			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					for (int i = 0; i < customer.getAccounts().size(); i++) {
						if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
							System.out.println(customer.getAccounts().get(i));
							acc = customer.getAccounts().get(i);
						}
					}

					f = new JFrame("Customer Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					closeWindow();
					f.setVisible(true);

					JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton statementButton = new JButton("Display Bank Statement");
					statementButton.setPreferredSize(new Dimension(250, 20));

					statementPanel.add(statementButton);

					JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton lodgementButton = new JButton("Lodge money into account");
					lodgementPanel.add(lodgementButton);
					lodgementButton.setPreferredSize(new Dimension(250, 20));

					JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
					JButton withdrawButton = new JButton("Withdraw money from account");
					withdrawalPanel.add(withdrawButton);
					withdrawButton.setPreferredSize(new Dimension(250, 20));

					JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
					JButton returnButton = new JButton("Exit Customer Menu");
					returnPanel.add(returnButton);

					JLabel label1 = new JLabel("Please select an option");

					content = f.getContentPane();
					content.setLayout(new GridLayout(5, 1));
					content.add(label1);
					content.add(statementPanel);
					content.add(lodgementPanel);
					content.add(withdrawalPanel);
					content.add(returnPanel);

					statementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							statement();
						}
					});

					lodgementButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							lodgement();
						}
					});

					withdrawButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							withdraw();
						}
					});

					returnButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							returnToMenu();
						}
					});
				}
			});
		}
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void closeWindow() {
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}

	public void newCustomer() {
		f.dispose();
		f1 = new JFrame("Create New Customer");
		f1.setSize(400, 300);
		f1.setLocation(200, 200);
		closeWindow();

		Container content = f1.getContentPane();
		content.setLayout(new BorderLayout());

		firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
		pPPSLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
		dOBLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
		firstNameTextField = new JTextField(20);
		surnameTextField = new JTextField(20);
		pPSTextField = new JTextField(20);
		dOBTextField = new JTextField(20);
		JPanel panel = new JPanel(new GridLayout(6, 2));
		panel.add(firstNameLabel);
		panel.add(firstNameTextField);
		panel.add(surnameLabel);
		panel.add(surnameTextField);
		panel.add(pPPSLabel);
		panel.add(pPSTextField);
		panel.add(dOBLabel);
		panel.add(dOBTextField);

		panel2 = new JPanel();
		add = new JButton("Add");

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add();
			}
		});

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnToMenu();
			}
		});
		panel2.add(add);
		panel2.add(cancel);

		content.add(panel, BorderLayout.CENTER);
		content.add(panel2, BorderLayout.SOUTH);

		f1.setVisible(true);
	}

	public void administrator() {
		boolean loop = true, loop2 = true;
		boolean cont = false;
		// while (loop) {
		Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

		if (!adminUsername.equals("admin")) {
			int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				loop = true;
			} else if (reply == JOptionPane.NO_OPTION) {
				f1.dispose();
				loop = false;
				loop2 = false;
				menuStart();
			}
		} else {
			loop = false;
		}

		while (loop2) {
			Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

			if (!adminPassword.equals("admin11")) {
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

				} else if (reply == JOptionPane.NO_OPTION) {
					f1.dispose();
					loop2 = false;
					menuStart();
				}
			} else {
				loop2 = false;
				cont = true;
			}
		}

		if (cont) {
			loop = false;
			admin();
		}

	}

	public void existingCustomer() {
		boolean loop = true, loop2 = true;
		boolean cont = false;
		boolean found = false;
		Customer customer = null;
		while (loop) {
			Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

			for (Customer aCustomer : customerList) {

				if (aCustomer.getCustomerID().equals(customerID)) {
					found = true;
					customer = aCustomer;
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
					loop2 = false;
					menuStart();
				}
			} else {
				loop = false;
			}
		}

		while (loop2) {
			Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

			if (!customer.getPassword().equals(customerPassword)) {
				int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {

				} else if (reply == JOptionPane.NO_OPTION) {
					f.dispose();
					loop2 = false;
					menuStart();
				}
			} else {
				loop2 = false;
				cont = true;
			}
		}

		if (cont) {
			f.dispose();
			loop = false;
			System.out.println("Test4 " + customer.toString());
			customer(customer);
		}
	}

	public void bankCharges() {
		boolean loop = true;
		boolean found = false;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			returnToAdmin();

		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Apply Charges to:");

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
						admin();
					}
				} else {
					f.dispose();
					f = new JFrame("Administrator Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					closeWindow();

					f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < customer.getAccounts().size(); i++) {

						box.addItem(customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();
					boxPanel.add(box);

					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Charge");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(f,
								"This customer has no accounts! \n The admin must add acounts to this customer.",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
						returnToAdmin();
					} else {

						for (int i = 0; i < customer.getAccounts().size(); i++) {
							if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								acc = customer.getAccounts().get(i);
							}
						}

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";

								if (acc instanceof CustomerDepositAccount) {

									JOptionPane.showMessageDialog(f, "25" + euro + " deposit account fee aplied.", "",
											JOptionPane.INFORMATION_MESSAGE);
									acc.setBalance(acc.getBalance() - 25);
									JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}

								if (acc instanceof CustomerCurrentAccount) {

									JOptionPane.showMessageDialog(f, "15" + euro + " current account fee aplied.", "",
											JOptionPane.INFORMATION_MESSAGE);
									acc.setBalance(acc.getBalance() - 25);
									JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!",
											JOptionPane.INFORMATION_MESSAGE);
								}

								returnToAdmin();
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								returnToMenu();
							}
						});

					}
				}
			}
		}

	}

	public void interest() {

		boolean loop = true;

		boolean found = false;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			returnToAdmin();

		} else {
			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Apply Interest to:");

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

						admin();
					}
				} else {
					f.dispose();
					f = new JFrame("Administrator Menu");
					f.setSize(400, 300);
					f.setLocation(200, 200);
					closeWindow();
					f.setVisible(true);

					JComboBox<String> box = new JComboBox<String>();
					for (int i = 0; i < customer.getAccounts().size(); i++) {

						box.addItem(customer.getAccounts().get(i).getNumber());
					}

					box.getSelectedItem();

					JPanel boxPanel = new JPanel();

					JLabel label = new JLabel("Select an account to apply interest to:");
					boxPanel.add(label);
					boxPanel.add(box);
					JPanel buttonPanel = new JPanel();
					JButton continueButton = new JButton("Apply Interest");
					JButton returnButton = new JButton("Return");
					buttonPanel.add(continueButton);
					buttonPanel.add(returnButton);
					Container content = f.getContentPane();
					content.setLayout(new GridLayout(2, 1));

					content.add(boxPanel);
					content.add(buttonPanel);

					if (customer.getAccounts().isEmpty()) {
						JOptionPane.showMessageDialog(f,
								"This customer has no accounts! \n The admin must add acounts to this customer.",
								"Oops!", JOptionPane.INFORMATION_MESSAGE);
						returnToAdmin();
					} else {

						for (int i = 0; i < customer.getAccounts().size(); i++) {
							if (customer.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
								acc = customer.getAccounts().get(i);
							}
						}

						continueButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								String euro = "\u20ac";
								double interest = 0;
								boolean loop = true;

								while (loop) {
									String interestString = JOptionPane.showInputDialog(f,
											"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
									if (isNumeric(interestString)) {

										interest = Double.parseDouble(interestString);
										loop = false;

										acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest / 100)));

										JOptionPane
												.showMessageDialog(f,
														interest + "% interest applied. \n new balance = "
																+ acc.getBalance() + euro,
														"Success!", JOptionPane.INFORMATION_MESSAGE);
									}

									else {
										JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
												JOptionPane.INFORMATION_MESSAGE);
									}

								}

								returnToAdmin();
							}
						});

						returnButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								returnToMenu();
							}
						});

					}
				}
			}
		}

	}

	public void editCustomer() {
		boolean loop = true;

		boolean found = false;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			returnToAdmin();

		} else {

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

				for (Customer aCustomer : customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						customer = aCustomer;
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

						admin();
					}
				} else {
					loop = false;
				}

			}

			f.dispose();
			// Need 2?

			f.dispose();
			f = new JFrame("Administrator Menu");
			f.setSize(400, 300);
			f.setLocation(200, 200);
			closeWindow();

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
					saveCustomer();
				}
			});

			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					returnToAdmin();
				}
			});
		}
	}

	public void summary() {
		f.dispose();

		f = new JFrame("Summary of Transactions");
		f.setSize(400, 700);
		f.setLocation(200, 200);
		closeWindow();
		f.setVisible(true);

		JLabel label1 = new JLabel("Summary of all transactions: ");

		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton("Return");
		returnPanel.add(returnButton);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);

		for (int a = 0; a < customerList.size(); a++) {
			for (int b = 0; b < customerList.get(a).getAccounts().size(); b++) {
				acc = customerList.get(a).getAccounts().get(b);
				for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++) {

					textArea.append(acc.getTransactionList().get(c).toString());
				}
			}
		}

		textPanel.add(textArea);
		content.removeAll();

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		content.add(textPanel);
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				returnToAdmin();
			}
		});
	}

	public void navigate() {
		f.dispose();

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
			admin();
		} else {

			JButton first, previous, next, last, cancel;
			JPanel gridPanel, buttonPanel, cancelPanel;

			Container content = getContentPane();

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
					first();
				}
			});

			previous.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					previous();
				}
			});

			next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					next();
				}
			});

			last.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					last();
				}
			});

			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					dispose();
					admin();
				}
			});
			setContentPane(content);
			setSize(400, 300);
			setVisible(true);
		}
	}

	public void account() {
		f.dispose();

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
			returnToAdmin();
		} else {
			boolean loop = true;

			boolean found = false;

			while (loop) {
				Object customerID = JOptionPane.showInputDialog(f,
						"Customer ID of Customer You Wish to Add an Account to:");

				for (Customer aCustomer : customerList) {

					if (aCustomer.getCustomerID().equals(customerID)) {
						found = true;
						customer = aCustomer;
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

						admin();
					}
				} else {
					loop = false;
					String[] choices = { "Current Account", "Deposit Account" };
					String account = (String) JOptionPane.showInputDialog(null, "Please choose account type",
							"Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

					if (account.equals("Current Account")) {
						boolean valid = true;
						double balance = 0;
						String number = String.valueOf(
								"C" + (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() + 1));
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
						int randomPIN = (int) (Math.random() * 9000) + 1000;
						String pin = String.valueOf(randomPIN);

						ATMCard atm = new ATMCard(randomPIN, valid);

						CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,
								transactionList);

						customer.getAccounts().add(current);
						JOptionPane.showMessageDialog(f, "Account number = " + number + "\n PIN = " + pin,
								"Account created.", JOptionPane.INFORMATION_MESSAGE);

						returnToAdmin();
					}

					if (account.equals("Deposit Account")) {
						double balance = 0, interest = 0;
						String number = String.valueOf(
								"D" + (customerList.indexOf(customer) + 1) * 10 + (customer.getAccounts().size() + 1));
						ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

						CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,
								transactionList);

						customer.getAccounts().add(deposit);
						JOptionPane.showMessageDialog(f, "Account number = " + number, "Account created.",
								JOptionPane.INFORMATION_MESSAGE);

						returnToAdmin();
					}

				}
			}
		}
	}

	public void deleteCustomer() {
		boolean found = true, loop = true;

		if (customerList.isEmpty()) {
			JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
			dispose();
			admin();
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

						admin();
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

					admin();
				}
			} 
		}
	}

	public void returnToMenu() {
		f.dispose();
		menuStart();
	}

	public void statement() {
		f.dispose();
		f = new JFrame("Customer Menu");
		f.setSize(400, 600);
		f.setLocation(200, 200);
		closeWindow();
		f.setVisible(true);

		JLabel label1 = new JLabel("Summary of account transactions: ");

		JPanel returnPanel = new JPanel();
		JButton returnButton = new JButton("Return");
		returnPanel.add(returnButton);

		JPanel textPanel = new JPanel();

		textPanel.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(40, 20);
		textArea.setEditable(false);
		textPanel.add(label1, BorderLayout.NORTH);
		textPanel.add(textArea, BorderLayout.CENTER);
		textPanel.add(returnButton, BorderLayout.SOUTH);

		JScrollPane scrollPane = new JScrollPane(textArea);
		textPanel.add(scrollPane);

		for (int i = 0; i < acc.getTransactionList().size(); i++) {
			textArea.append(acc.getTransactionList().get(i).toString());

		}

		textPanel.add(textArea);
		content.removeAll();

		Container content = f.getContentPane();
		content.setLayout(new GridLayout(1, 1));
		content.add(textPanel);

		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f.dispose();
				customer(customer);
			}
		});
	}

	public void lodgement() {
		boolean loop = true;
		boolean on = true;
		double balance = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					customer(e);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}
			}

		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");
			if (isNumeric(balanceTest)) {

				balance = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() + balance);
			Date date = new Date();
			String date2 = date.toString();
			String type = "Lodgement";
			double amount = balance;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(f, balance + euro + " added do you account!", "Lodgement",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Lodgement",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void withdraw() {
		boolean loop = true;
		boolean on = true;
		double withdraw = 0;

		if (acc instanceof CustomerCurrentAccount) {
			int count = 3;
			int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
			loop = true;

			while (loop) {
				if (count == 0) {
					JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
							JOptionPane.INFORMATION_MESSAGE);
					((CustomerCurrentAccount) acc).getAtm().setValid(false);
					customer(e);
					loop = false;
					on = false;
				}

				String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
				int i = Integer.parseInt(Pin);

				if (on) {
					if (checkPin == i) {
						loop = false;
						JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						count--;
						JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining.", "Pin",
								JOptionPane.INFORMATION_MESSAGE);

					}

				}
			}

		}
		if (on == true) {
			String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to withdraw (max 500):");
			if (isNumeric(balanceTest)) {

				withdraw = Double.parseDouble(balanceTest);
				loop = false;

			} else {
				JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
			}
			if (withdraw > 500) {
				JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.", "Oops!",
						JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}
			if (withdraw > acc.getBalance()) {
				JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
				withdraw = 0;
			}

			String euro = "\u20ac";
			acc.setBalance(acc.getBalance() - withdraw);
			Date date = new Date();
			String date2 = date.toString();

			String type = "Withdraw";
			double amount = withdraw;

			AccountTransaction transaction = new AccountTransaction(date2, type, amount);
			acc.getTransactionList().add(transaction);

			JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Withdraw",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void add() {
		PPS = pPSTextField.getText();
		firstName = firstNameTextField.getText();
		surname = surnameTextField.getText();
		DOB = dOBTextField.getText();
		CustomerID = "ID" + PPS;

		f1.dispose();

		boolean loop = true;
		while (loop) {
			password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");

			if (password.length() != 7) {
				JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
			} else {
				loop = false;
				ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
				Customer customer = new Customer(PPS, surname, firstName, DOB, CustomerID, password, accounts);
				System.out.println("Test" + customer.toString());
				customerList.add(customer);

				JOptionPane.showMessageDialog(f, "CustomerID = " + CustomerID + "\n Password = " + password,
						"Customer created.", JOptionPane.INFORMATION_MESSAGE);
				menuStart();
			}
		}
	}

	public void saveCustomer() {
		customer.setFirstName(firstNameTextField.getText());
		customer.setSurname(surnameTextField.getText());
		customer.setPPS(pPSTextField.getText());
		customer.setDOB(dOBTextField.getText());
		customer.setCustomerID(customerIDTextField.getText());
		customer.setPassword(passwordTextField.getText());

		JOptionPane.showMessageDialog(null, "Changes Saved.");
	}

	public void returnToAdmin() {
		f.dispose();
		admin();
	}

	public void first() {
		position = 0;
		firstNameTextField.setText(customerList.get(0).getFirstName());
		surnameTextField.setText(customerList.get(0).getSurname());
		pPSTextField.setText(customerList.get(0).getPPS());
		dOBTextField.setText(customerList.get(0).getDOB());
		customerIDTextField.setText(customerList.get(0).getCustomerID());
		passwordTextField.setText(customerList.get(0).getPassword());
	}

	public void previous() {
		if (position < 1) {
			// don't do anything
		} else {
			position = position - 1;

			firstNameTextField.setText(customerList.get(position).getFirstName());
			surnameTextField.setText(customerList.get(position).getSurname());
			pPSTextField.setText(customerList.get(position).getPPS());
			dOBTextField.setText(customerList.get(position).getDOB());
			customerIDTextField.setText(customerList.get(position).getCustomerID());
			passwordTextField.setText(customerList.get(position).getPassword());
		}
	}

	public void next() {
		if (position == customerList.size() - 1) {
			// don't do anything
		} else {
			position = position + 1;

			firstNameTextField.setText(customerList.get(position).getFirstName());
			surnameTextField.setText(customerList.get(position).getSurname());
			pPSTextField.setText(customerList.get(position).getPPS());
			dOBTextField.setText(customerList.get(position).getDOB());
			customerIDTextField.setText(customerList.get(position).getCustomerID());
			passwordTextField.setText(customerList.get(position).getPassword());
		}
	}
	
	public void last(){
		position = customerList.size() - 1;
		firstNameTextField.setText(customerList.get(position).getFirstName());
		surnameTextField.setText(customerList.get(position).getSurname());
		pPSTextField.setText(customerList.get(position).getPPS());
		dOBTextField.setText(customerList.get(position).getDOB());
		customerIDTextField.setText(customerList.get(position).getCustomerID());
		passwordTextField.setText(customerList.get(position).getPassword());
	}
}