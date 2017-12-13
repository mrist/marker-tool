package panels;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data.Review;

public class ReviewSelectionPanel extends JPanel {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private JLabel				lblReview			= new JLabel("Review:");
	public JComboBox<Review>	comboBox;
	public JButton				btnWriteReview		= new JButton("finished");

	public ReviewSelectionPanel() {
		initialize();
	}

	public void addReview(Review r) {
		this.comboBox.addItem(r);
	}

	public Review getSelectedReview() {
		return (Review) this.comboBox.getSelectedItem();
	}

	private void initialize() {
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		this.comboBox = new JComboBox<Review>();

		FlowLayout fl_panel_review = new FlowLayout(FlowLayout.LEFT, 5, 5);
		this.setLayout(fl_panel_review);

		this.lblReview.setHorizontalAlignment(SwingConstants.LEFT);

		this.add(lblReview);
		this.add(comboBox);
		this.add(btnWriteReview);
		this.setVisible(true);
	}

	public void removeReview(Review r) {
		this.comboBox.removeItem(r);
	}

	public void reset() {
		this.comboBox.removeAllItems();
	}
}
