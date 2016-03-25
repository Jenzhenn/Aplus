package ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class TicketPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public TicketPanel() {
		this.setLayout(new MigLayout("", "[grow]", "[grow]"));
		JLabel lblMovieInfo = new JLabel("Ticket");
		this.add(lblMovieInfo, "cell 0 0,alignx left,aligny top");
	}

}
