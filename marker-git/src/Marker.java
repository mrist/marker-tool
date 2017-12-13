import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panels.MainFrame;

public class Marker implements ActionListener {

	public enum type {
		claim, premise
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MainFrame marker = new MainFrame();
					marker.start();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
