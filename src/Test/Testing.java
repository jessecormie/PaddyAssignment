package Test;

import org.junit.Assert;
import org.junit.Test;

import Entity.Customer;

public class Testing {
	
	@Test
	public void createCustomer(){
		
		Customer c = new Customer("54321", "Murphy", "Tom", "11/05/1994", "IDa", "1234567", null);
		Assert.assertNotNull(c);
	}
}