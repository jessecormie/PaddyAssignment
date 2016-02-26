package Test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Customer.RegisterCustomer;
import Entity.Customer;

public class Testing {
		
	@Test
	public void createCustomer(){
		
		RegisterCustomer rc = new RegisterCustomer();
		Customer c = new Customer("54321", "Murphy", "Tom", "11/05/1994", "IDa", "1234567", null);
	//	assertTrue("Customer creation should return true", );
		
	}
}