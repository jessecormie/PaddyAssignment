import java.util.ArrayList;

public class Customer {

	private String pps, surname, firstName, dob, customerID, password;
	private ArrayList<CustomerAccount> accounts;

	public Customer() {

	}

	public Customer(String pps, String surname, String firstName, String dob, String customerID, String password,
			ArrayList<CustomerAccount> accounts) {
		this.pps = pps;
		this.surname = surname;
		this.firstName = firstName;
		this.dob = dob;
		this.customerID = customerID;
		this.password = password;
		this.accounts = accounts;
	}

	public String getPps() {
		return this.pps;
	}

	public String getSurname() {
		return this.surname;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getDob() {
		return this.dob;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public String getPassword() {
		return this.password;
	}

	public ArrayList<CustomerAccount> getAccounts() {
		return this.accounts;
	}

	public void setPps(String pps) {
		this.pps = pps;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccounts(ArrayList<CustomerAccount> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [PPS=" + pps + ", surname=" + surname + ", firstName=" + firstName + ", DOB=" + dob
				+ ", customerID=" + customerID + ", password=" + password + ", accounts=" + accounts + "]";
	}
}