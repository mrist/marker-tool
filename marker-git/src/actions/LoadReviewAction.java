package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.MainFrame;

public class LoadReviewAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -1164141697122563248L;
	private MainFrame			main				= null;

	public LoadReviewAction(MainFrame main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.loadReview();
	}
}
