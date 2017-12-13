package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.MainFrame;

public class SaveAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5899888913716133879L;
	private MainFrame			main				= null;

	public SaveAction(MainFrame main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.saveReview();
	}

}
