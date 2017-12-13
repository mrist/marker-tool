package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.MainFrame;

public class AddWordAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4838041603026282981L;
	private MainFrame			main				= null;
	private String				type				= "";

	public AddWordAction(MainFrame main, String type) {
		this.main = main;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.addWord(type);
	}
}
