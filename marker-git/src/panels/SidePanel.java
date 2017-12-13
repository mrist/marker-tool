package panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import data.Phrase;
import data.Relation;

public class SidePanel extends JPanel {
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

	private JLabel						lblClaim				= new JLabel("Claims:");
	private JLabel						lblPremise				= new JLabel("Premises:");

	private JLabel						lblRelations			= new JLabel("Relations:");
	private DefaultListModel<Phrase>	premiseModel			= new DefaultListModel<Phrase>();

	private final JScrollPane			premiseScrollPane		= new JScrollPane();
	public JList<Phrase>				premiseList				= new JList<Phrase>(premiseModel);
	private DefaultListModel<Phrase>	claimModel				= new DefaultListModel<Phrase>();

	private final JScrollPane			claimsScrollPane		= new JScrollPane();
	public JList<Phrase>				claimList				= new JList<Phrase>(claimModel);
	private DefaultListModel<Relation>	relationModel			= new DefaultListModel<Relation>();

	private final JScrollPane			relationScrollPane		= new JScrollPane();
	public JList<Relation>				relationList			= new JList<Relation>(relationModel);

	private JPanel						panel_claims_premises	= new JPanel();
	private JPanel						panel_claims			= new JPanel();
	private JPanel						panel_premises			= new JPanel();
	private JPanel						panel_relations			= new JPanel();
	private JPopupMenu					popupMenu_claims		= new JPopupMenu();
	private JPopupMenu					popupMenu_premise		= new JPopupMenu();

	private JPopupMenu					popupMenu_relations		= new JPopupMenu();
	public JButton						btnRelations			= new JButton("link");
	public JMenuItem					mntmDelete_claims		= new JMenuItem("delete");
	private final JSeparator			separator				= new JSeparator();
	public JMenuItem					chckbxmntmIsTopic		= new JMenuItem("is topic");
	public JMenuItem					mntmDelete_premises		= new JMenuItem("delete");

	public JMenuItem					mntmDelete_relations	= new JMenuItem("delete");
	private final JSplitPane			splitPane				= new JSplitPane();

	public SidePanel() {
		initialize();
	}

	public void addClaim(Phrase claim) {
		this.claimModel.addElement(claim);
		this.claimList.setSelectedIndex(this.claimModel.indexOf(this.claimModel.get(this.claimModel.size() - 1)));
		this.claimList.grabFocus();
	}

	public void addPremise(Phrase premise) {
		this.premiseModel.addElement(premise);
		this.premiseList
				.setSelectedIndex(this.premiseModel.indexOf(this.premiseModel.get(this.premiseModel.size() - 1)));
		this.premiseList.grabFocus();
	}

	public void addRelation(Relation relation) {
		this.relationModel.addElement(relation);
	}

	public Relation getActiveRelation() {
		if (this.relationList.hasFocus())
			return getSelectedRelation();
		return null;
	}

	public Phrase getActiveWord() {
		if (this.claimList.hasFocus())
			return getSelectedClaim();
		else if (this.premiseList.hasFocus())
			return getSelectedPremise();
		return null;
	}

	public Phrase getSelectedClaim() {
		return (Phrase) this.claimList.getSelectedValue();
	}

	public Phrase getSelectedPremise() {
		return (Phrase) this.premiseList.getSelectedValue();
	}

	public Relation getSelectedRelation() {
		return (Relation) this.relationList.getSelectedValue();
	}

	public void initialize() {
		this.setMaximumSize(new Dimension(600, 2147483647));
		this.setMinimumSize(new Dimension(300, 237));
		this.setAlignmentX(Component.LEFT_ALIGNMENT);

		setLayout(new BorderLayout(0, 0));
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

		add(splitPane, BorderLayout.CENTER);

		lblPremise.setHorizontalAlignment(SwingConstants.LEFT);
		lblPremise.setAlignmentY(1.0f);

		this.premiseList.setToolTipText("Premises");
		this.premiseList.setVisibleRowCount(10);
		this.premiseList.setMaximumSize(new Dimension(200, 0));
		this.premiseList.setMinimumSize(new Dimension(200, 500));
		this.premiseList.setSize(new Dimension(200, 500));
		this.premiseList.setDropMode(DropMode.ON);
		this.premiseList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.premiseList.setAutoscrolls(false);
		this.premiseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		premiseScrollPane.setMinimumSize(new Dimension(100, 23));

		premiseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		premiseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.premiseScrollPane.setViewportView(premiseList);

		addPopup(premiseList, popupMenu_premise);
		popupMenu_premise.add(this.mntmDelete_premises);

		panel_premises.setMinimumSize(new Dimension(100, 500));
		panel_premises.setLayout(new BorderLayout(5, 5));
		panel_premises.add(lblPremise, BorderLayout.NORTH);
		panel_premises.add(premiseScrollPane, BorderLayout.CENTER);
		splitPane.setLeftComponent(panel_claims_premises);

		panel_claims_premises.setMinimumSize(new Dimension(10, 200));
		panel_claims_premises.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_claims_premises.setLayout(new BoxLayout(panel_claims_premises, BoxLayout.X_AXIS));

		lblClaim.setSize(new Dimension(100, 10));
		lblClaim.setBounds(new Rectangle(5, 5, 100, 10));
		lblClaim.setAlignmentY(0.0f);
		claimList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.claimList.setToolTipText("Claims");
		this.claimList.setVisibleRowCount(10);
		this.claimList.setMaximumSize(new Dimension(200, 0));
		this.claimList.setMinimumSize(new Dimension(200, 500));
		this.claimList.setSize(new Dimension(200, 500));
		this.claimList.setDropMode(DropMode.ON);
		this.claimList.setBounds(new Rectangle(0, 0, 200, 500));
		this.claimList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		claimsScrollPane.setMinimumSize(new Dimension(100, 23));
		claimsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		claimsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.claimsScrollPane.setViewportView(claimList);

		addPopup(this.claimList, this.popupMenu_claims);
		this.popupMenu_claims.add(this.mntmDelete_claims);
		this.popupMenu_claims.add(this.separator);
		this.popupMenu_claims.add(this.chckbxmntmIsTopic);

		panel_claims.setSize(new Dimension(200, 500));
		panel_claims.setMinimumSize(new Dimension(100, 500));
		panel_claims.setLayout(new BorderLayout(5, 5));
		panel_claims.add(lblClaim, BorderLayout.NORTH);
		panel_claims.add(claimsScrollPane, BorderLayout.CENTER);

		panel_claims_premises.add(panel_claims);
		panel_claims_premises.add(btnRelations);

		panel_claims_premises.add(panel_premises);

		relationList = new JList<Relation>(relationModel);
		relationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		addPopup(relationList, popupMenu_relations);
		popupMenu_relations.add(mntmDelete_relations);
		popupMenu_relations.add(mntmDelete_relations);

		lblRelations.setBounds(new Rectangle(5, 5, 0, 0));
		splitPane.setRightComponent(panel_relations);

		panel_relations.setLayout(new BorderLayout(0, 0));
		panel_relations.add(lblRelations, BorderLayout.NORTH);
		relationScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.relationScrollPane.setViewportView(relationList);
		this.panel_relations.add(relationScrollPane, BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void removeClaim(Phrase claim) {
		this.claimModel.removeElement(claim);
		this.claimList.grabFocus();
	}

	public void removePremise(Phrase premise) {
		this.premiseModel.removeElement(premise);
		this.premiseList.grabFocus();
	}

	public void removeRelation(Relation relation) {
		this.relationModel.removeElement(relation);
		this.relationList.grabFocus();
	}

	public void removeWord(Phrase word) {
		if (word.getType().equals("claim")) {
			this.removeClaim(word);
		}
		else if (word.getType().equals("premise")) {
			this.removePremise(word);
		}

	}

	public void reset() {
		this.premiseModel.removeAllElements();
		this.claimModel.removeAllElements();
		this.relationModel.removeAllElements();
	}

	public void setData(ArrayList<Phrase> claims, ArrayList<Phrase> premises, ArrayList<Relation> relations) {
		this.claimModel.removeAllElements();
		for (Phrase word : claims) {
			this.claimModel.addElement(word);
		}

		this.premiseModel.removeAllElements();
		for (Phrase word : premises) {
			this.premiseModel.addElement(word);
		}

		this.relationModel.removeAllElements();
		for (Relation relation : relations) {
			this.relationModel.addElement(relation);
		}
	}

	public void updateRelations(ArrayList<Relation> rels) {
		relationModel.removeAllElements();
		for (Relation relation : rels) {
			relationModel.addElement(relation);
		}
		this.relationList.grabFocus();
	}
}
