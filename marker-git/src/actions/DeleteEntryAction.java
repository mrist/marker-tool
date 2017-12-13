package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.MainFrame;

public class DeleteEntryAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4838041603026282981L;
	private MainFrame			main				= null;

	public DeleteEntryAction(MainFrame main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.deleteEntry();
	}
}
