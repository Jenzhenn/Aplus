package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MovieInfoPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MovieInfoPanel() {
		this.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JLabel lblMovieInfo = new JLabel("Movie Info");
		this.add(lblMovieInfo, "cell 0 0,alignx left,aligny top");
	}

}
