package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.MainFrame;

public class AddRelationAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5954267664938054265L;
	private MainFrame			main;

	public AddRelationAction(MainFrame main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.addRelation();
	}
}
