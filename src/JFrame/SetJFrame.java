package JFrame;

import javax.swing.JFrame;

public class SetJFrame {
	
	public JFrame setJFrame(String title, int size1, int size2, int location1, int location2, boolean visible){
		JFrame f = new JFrame(title);
		f.setSize(size1, size2);
		f.setLocation(location1, location2);
		f.setVisible(visible);
		return f;
	}

}
