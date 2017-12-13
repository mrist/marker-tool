package panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import data.Phrase;

public class ReviewViewFrame extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public JTextPane					textField;

	public PopUpMenu					menu;
	private final StyleContext			styleContext	= StyleContext.getDefaultStyleContext();

	private final AttributeSet			defaultColour	= styleContext.addAttribute(styleContext.getEmptySet(),
			StyleConstants.Foreground, Color.BLACK);
	private final AttributeSet			premiseColour	= styleContext.addAttribute(styleContext.getEmptySet(),
			StyleConstants.Foreground, Color.GREEN);

	private final AttributeSet			claimColour		= styleContext.addAttribute(styleContext.getEmptySet(),
			StyleConstants.Foreground, Color.BLUE);
	private final SimpleAttributeSet	textNormal		= new SimpleAttributeSet();
	private final SimpleAttributeSet	textBold		= new SimpleAttributeSet();

	public ReviewViewFrame() {
		StyleConstants.setBold(textNormal, false);
		StyleConstants.setBold(textBold, true);
		this.initialize();
	}

	public void addActionListeners(ActionListener al) {
		this.menu.addActionListeners(al);
	}

	public String getSelectedWord() {
		return this.textField.getSelectedText();
	}

	public int getSelectedWordEnd() {
		return this.textField.getSelectionEnd();
	}

	public int getSelectedWordStart() {
		return this.textField.getSelectionStart();

	}

	public void highlightPhrase(Phrase p) {
		StyledDocument styleDoc = this.textField.getStyledDocument();
		if (p.getType().equals("claim")) {
			styleDoc.setCharacterAttributes(p.getStart(), p.getEnd() - p.getStart(), claimColour, false);
		}
		else {
			styleDoc.setCharacterAttributes(p.getStart(), p.getEnd() - p.getStart(), premiseColour, false);
		}
		styleDoc.setCharacterAttributes(p.getStart(), p.getEnd() - p.getStart(), textBold, false);
	}

	public void highlightRelation(Phrase aspect, Phrase statement) {
		StyledDocument styleDoc = this.textField.getStyledDocument();

		styleDoc.setCharacterAttributes(aspect.getStart(), aspect.getEnd() - aspect.getStart(), textBold, false);
		styleDoc.setCharacterAttributes(statement.getStart(), statement.getEnd() - statement.getStart(), textBold,
				false);

		styleDoc.setCharacterAttributes(aspect.getStart(), aspect.getEnd() - aspect.getStart(), claimColour, false);
		styleDoc.setCharacterAttributes(statement.getStart(), statement.getEnd() - statement.getStart(), premiseColour,
				false);
	}

	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		this.textField = new JTextPane();
		textField.setMaximumSize(new Dimension(700, 700));
		this.textField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.textField.setEditable(false);
		this.textField.setFont(new Font("Monospaced", Font.PLAIN, 15));
		add(this.textField);
		this.menu = new PopUpMenu();

		addPopup(textField, menu);

		JScrollPane scrollPane = new JScrollPane(this.textField);
		add(scrollPane, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void reset() {
		this.textField.setText("");
	}

	public void resetStyle() {
		StyledDocument styleDoc = this.textField.getStyledDocument();
		styleDoc.setCharacterAttributes(0, styleDoc.getLength(), defaultColour, false);
		styleDoc.setCharacterAttributes(0, styleDoc.getLength(), textNormal, false);
	}

	public void setText(String t) {
		this.textField.setText(t);
	}
}
