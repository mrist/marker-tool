package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import panels.MainFrame;

public class ToggleTopicAction extends AbstractAction {
	private MainFrame main = null;

	public ToggleTopicAction(MainFrame main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		main.toggleTopic();
	}
}
