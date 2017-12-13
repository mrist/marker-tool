package panels;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actions.AddRelationAction;
import actions.AddWordAction;
import actions.DeleteEntryAction;
import actions.LoadReviewAction;
import actions.LoadReviewsAction;
import actions.SaveAction;
import actions.ToggleTopicAction;
import actions.UpdateReviewAction;
import data.DataHandler;
import data.Phrase;
import data.Relation;
import data.Review;

public class MainFrame {

	/**
	 * 
	 */

	public MainPanel			mainPanel		= null;
	public ReviewSelectionPanel	reviewSelection	= null;
	public ReviewViewFrame		reviewView		= null;
	public SidePanel			sidePanel		= null;
	private DataHandler			data			= null;
	public String				currentReviewID	= "";
	private JFileChooser		fc				= new JFileChooser();
	private File				currentFile		= new File("");
	private Phrase				currentPhrase	= null;
	private Relation			currentRelation	= null;

	public MainFrame() {
		initialize();
	}

	public void start() {
		this.mainPanel.add(new SidePanel());
		this.mainPanel.setVisible(true);
	}

	public void addRelation() {
		Phrase claim = this.sidePanel.getSelectedClaim();
		Phrase premise = this.sidePanel.getSelectedPremise();
		if (claim != null && premise != null) {
			Relation temp = new Relation(claim, premise, this.currentReviewID);
			this.data.addRelation(this.currentReviewID, temp);
			this.sidePanel.addRelation(temp);
			this.currentRelation = temp;
		}
	}

	public void addWord(String type) {
		Phrase phrase = null;
		if (this.reviewView.getSelectedWord() != null) {
			phrase = new Phrase(this.reviewView.getSelectedWordStart(), this.reviewView.getSelectedWordEnd(),
					this.reviewSelection.getSelectedReview().getReviewID(), this.reviewView.getSelectedWord(), type);
			this.data.addWord(this.currentReviewID, phrase);
			if (type.equals("premise")) {
				this.sidePanel.addPremise(phrase);
			}
			else if (type.equals("claim")) {
				this.sidePanel.addClaim(phrase);
			}
			this.currentPhrase = phrase;
		}
	}

	public void deleteEntry() {
		if (this.currentPhrase != null) {
			this.data.removePhrase(this.currentReviewID, this.currentPhrase);
			this.sidePanel.removeWord(this.currentPhrase);
			this.sidePanel.updateRelations(this.data.getRelations(currentReviewID));
		}
		if (this.currentRelation != null) {
			this.data.removeRelation(this.currentReviewID, this.currentRelation);
			this.sidePanel.removeRelation(this.currentRelation);
		}
	}

	public void deleteRelation() {
		Relation relation = this.sidePanel.getActiveRelation();
		if (relation != null) {
			this.data.removeRelation(this.currentReviewID, relation);
			this.sidePanel.removeRelation(relation);
		}
	}

	public void deleteWord() {
		Phrase word = this.sidePanel.getActiveWord();
		if (word != null) {
			this.data.removePhrase(this.currentReviewID, word);
			this.sidePanel.removeWord(word);
			this.sidePanel.updateRelations(this.data.getRelations(currentReviewID));
		}
	}

	public void highlightEntryInText() {
		this.reviewView.resetStyle();
		this.currentPhrase = this.sidePanel.getActiveWord();
		if (this.currentPhrase != null) {
			this.reviewView.highlightPhrase(this.currentPhrase);
		}
		this.currentRelation = this.sidePanel.getActiveRelation();
		if (this.currentRelation != null) {
			this.reviewView.highlightRelation(this.currentRelation.getAspect(), this.currentRelation.getStatement());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {

		this.mainPanel = new MainPanel();

		this.reviewSelection = new ReviewSelectionPanel();
		this.reviewView = new ReviewViewFrame();

		this.sidePanel = new SidePanel();

		this.data = new DataHandler();
		this.setReviewList();

		this.fc.setCurrentDirectory(new File("./"));

		Action addClaim = new AddWordAction(this, "claim");
		this.reviewView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "addClaim");
		this.reviewView.getActionMap().put("addClaim", addClaim);
		this.reviewView.menu.claim.addActionListener(addClaim);

		Action addPremise = new AddWordAction(this, "premise");
		this.reviewView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "addPremise");
		this.reviewView.getActionMap().put("addPremise", addPremise);

		this.reviewView.menu.premise.addActionListener(addPremise);

		Action addRelation = new AddRelationAction(this);
		this.sidePanel.btnRelations.addActionListener(addRelation);

		this.reviewView.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(' '), "addRelation");
		this.reviewView.getActionMap().put("addRelation", addRelation);

		this.sidePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(' '), "addRelation");
		this.sidePanel.getActionMap().put("addRelation", addRelation);

		this.mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(' '), "addRelation");
		this.mainPanel.getActionMap().put("addRelation", addRelation);

		Action deleteEntry = new DeleteEntryAction(this);
		this.sidePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),
				"deleteEntry");
		this.sidePanel.getActionMap().put("deleteEntry", deleteEntry);
		this.sidePanel.mntmDelete_claims.addActionListener(deleteEntry);
		this.sidePanel.mntmDelete_premises.addActionListener(deleteEntry);
		this.sidePanel.mntmDelete_relations.addActionListener(deleteEntry);

		ListSelectionListener listListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				highlightEntryInText();
			}
		};
		this.sidePanel.claimList.addListSelectionListener(listListener);
		this.sidePanel.premiseList.addListSelectionListener(listListener);
		this.sidePanel.relationList.addListSelectionListener(listListener);

		Action saveReview = new SaveAction(this);
		this.mainPanel.mntmSavejson.addActionListener(saveReview);
		this.reviewSelection.btnWriteReview.addActionListener(saveReview);

		Action updateReview = new UpdateReviewAction(this);
		this.reviewSelection.comboBox.addActionListener(updateReview);

		Action toggleTopic = new ToggleTopicAction(this);
		this.sidePanel.chckbxmntmIsTopic.addActionListener(toggleTopic);

		Action loadReviews = new LoadReviewsAction(this);
		this.mainPanel.mntmLoadjson.addActionListener(loadReviews);

		Action loadReview = new LoadReviewAction(this);
		this.mainPanel.mntmLoadtextfile.addActionListener(loadReview);

		final JSplitPane splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.sidePanel,
				this.reviewView);
		this.mainPanel.frame.getContentPane().setLayout(new BorderLayout(0, 0));
		this.mainPanel.frame.getContentPane().add(reviewSelection, BorderLayout.NORTH);
		this.mainPanel.frame.getContentPane().add(splitPaneHorizontal, BorderLayout.CENTER);
	}

	private void reset() {
		this.data.reset();
		this.sidePanel.reset();
		this.reviewView.reset();
		this.reviewSelection.reset();
	}

	public void saveReview() {
		try {
			this.data.rw.writeReviews(this.data.getReviews(), currentFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void setReviewList() {
		for (Review r : this.data.getReviews()) {
			this.reviewSelection.addReview(r);
		}
	}

	private void setSidePanel(String id) {
		this.sidePanel.setData(this.data.getAspects(id), this.data.getSubjects(id), this.data.getRelations(id));
	}

	public void updateReviews() {
		Review rev = this.reviewSelection.getSelectedReview();
		if (rev != null) {
			this.reviewView.setText(rev.getReviewText());
			this.currentReviewID = rev.getReviewID();
			this.setSidePanel(this.currentReviewID);
		}
	}

	public void toggleTopic() {
		if (this.currentPhrase != null) {
			if (this.currentPhrase.getType().equals("claim")) {
				this.currentPhrase.toggleTopic();
			}
		}
	}

	public void loadReview() {
		this.reset();
		int returnVal = fc.showOpenDialog(new JFrame());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.currentFile = fc.getSelectedFile();
			this.data.loadTextFile(this.currentFile);
			this.setReviewList();
			this.currentFile = new File(this.currentFile.getName() + ".json");
		}
	}

	public void loadReviews() {
		this.reset();
		int returnVal = fc.showOpenDialog(new JFrame());

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.currentFile = fc.getSelectedFile();
			this.data.loadData(this.currentFile);
			this.setReviewList();
		}
	}
}
