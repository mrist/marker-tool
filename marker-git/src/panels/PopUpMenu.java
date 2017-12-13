package panels;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

public class PopUpMenu extends JPopupMenu {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	JMenuItem					textItem;
	JMenuItem					claim;
	JMenuItem					premise;

	public PopUpMenu() {
		textItem = new JMenuItem("");
		textItem.setEnabled(false);
		claim = new JMenuItem("claim");
		claim.setAccelerator(KeyStroke.getKeyStroke("c"));
		premise = new JMenuItem("premise");
		claim.setAccelerator(KeyStroke.getKeyStroke("s"));

		add(textItem);
		add(new Separator());
		add(claim);
		add(premise);
	}

	public void addActionListeners(ActionListener al) {
		this.claim.addActionListener(al);
		this.premise.addActionListener(al);

	}

	public void show(String text) {
		if (!text.isEmpty()) {
			this.textItem.setText(text);
		}
	}
}