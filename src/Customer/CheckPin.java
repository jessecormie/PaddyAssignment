package Customer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entity.CustomerAccount;
import Entity.CustomerCurrentAccount;

public class CheckPin {
	
	private JFrame f;
	int count = 3;
	private boolean success = false;
	
	public boolean checkPin(CustomerAccount acc){
		int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
		if (count == 0) {
			JOptionPane.showMessageDialog(f, "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
					JOptionPane.INFORMATION_MESSAGE);
			((CustomerCurrentAccount) acc).getAtm().setValid(false);
		} else {
			String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
			int i = Integer.parseInt(Pin);
			if (checkPin == i) {
				JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin", JOptionPane.INFORMATION_MESSAGE);
				success = true;
			} else {
				count--;
				JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining.", "Pin",
						JOptionPane.INFORMATION_MESSAGE);
				Lodgement l = new Lodgement();
				l.lodgement(acc);
			}
		}
		return success;
	}

}
