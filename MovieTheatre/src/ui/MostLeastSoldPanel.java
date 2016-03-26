package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JButton;

public class MostLeastSoldPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MostLeastSoldPanel() {
		setLayout(new MigLayout("", "[145px,grow]", "[10px][25px,grow]"));
		
		JPanel displayPanel = new JPanel();
		add(displayPanel, "flowx,cell 0 1");
		
		JPanel selectPanel = new JPanel();
		add(selectPanel, "flowx,cell 0 0,alignx left,aligny top");
		selectPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSelectMostleastSold = new JLabel("Select most/least sold movie: ");
		lblSelectMostleastSold.setFont(new Font("Calibri", Font.BOLD, 12));
		selectPanel.add(lblSelectMostleastSold);
		lblSelectMostleastSold.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectMostleastSold.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnMostSold = new JButton("Most Sold");
		add(btnMostSold, "cell 0 0");
		
		JButton btnLeastSold = new JButton("Least Sold");
		add(btnLeastSold, "cell 0 0");

	}

}
