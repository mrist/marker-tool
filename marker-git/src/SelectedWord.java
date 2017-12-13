import javax.swing.JFrame;

public class SelectedWord {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public SelectedWord() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
