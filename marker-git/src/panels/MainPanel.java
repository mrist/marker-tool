package panels;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class MainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public JFrame				frame				= null;
	public JMenuItem			mntmExit			= new JMenuItem("Exit");
	public JMenuItem			mntmLoadjson		= new JMenuItem("LoadJSON");
	public JMenuItem			mntmSavejson		= new JMenuItem("SaveJSON");
	public JMenuItem			mntmLoadtextfile	= new JMenuItem("LoadTextFile");

	public MainPanel() {
		initialize();
	}

	public void initialize() {

		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 1158, 667);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		this.frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JSeparator separator = new JSeparator();

		mnFile.add(this.mntmSavejson);
		mnFile.add(this.mntmLoadjson);
		mnFile.add(this.mntmLoadtextfile);
		mnFile.add(separator);
		mnFile.add(this.mntmExit);

		this.frame.getContentPane().setLayout(new BorderLayout(0, 0));
	}
}
