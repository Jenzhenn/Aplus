package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import net.miginfocom.swing.MigLayout;

import java.awt.Font;

import javax.swing.JButton;

import core.Movie;
import dao.DBManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class MostLeastSoldPanel extends JPanel {
    private JTable table;
    private DBManager dbManager;
	/**
	 * Create the panel.
	 */
	public MostLeastSoldPanel(DBManager db) {
		setLayout(new MigLayout("", "[145px,grow]", "[10px][25px,grow]"));
		dbManager = db;
		JPanel selectPanel = new JPanel();
		add(selectPanel, "flowx,cell 0 0,alignx left,aligny top");
		selectPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblSelectMostleastSold = new JLabel("Select most/least sold movie: ");
		lblSelectMostleastSold.setFont(new Font("Calibri", Font.BOLD, 12));
		selectPanel.add(lblSelectMostleastSold);
		lblSelectMostleastSold.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectMostleastSold.setVerticalAlignment(SwingConstants.TOP);
		
		JButton btnMostSold = new JButton("Most Sold");
		selectPanel.add(btnMostSold);
		
		JButton btnLeastSold = new JButton("Least Sold");
		selectPanel.add(btnLeastSold);
		btnLeastSold.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            List<Movie> movies = null;
		            movies = dbManager.seeLeastSoldMovie();
		            MovieAvgModel movieModel = new MovieAvgModel(movies);
		            table.setModel(movieModel);
		        } catch (Exception e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }
		    }
		});
		btnMostSold.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent arg0) {
		        try {
		            List<Movie> movies = null;
		            movies = dbManager.seeMostSoldMovie();
		            MovieAvgModel movieModel = new MovieAvgModel(movies);
		            table.setModel(movieModel);
		        } catch (Exception e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		        }
		    }
		});

	      
		JScrollPane tablePanel = new JScrollPane();
        add(tablePanel, "cell 0 1 1 2,grow");
        
        table = new JTable();
        tablePanel.setViewportView(table);
        table.setRowSelectionAllowed(false);
        table.setColumnSelectionAllowed(false);
        table.setCellSelectionEnabled(true);
        try {
            List<Movie> movies = null;
            movies = dbManager.seeMostSoldMovie();
            MovieAvgModel movieModel = new MovieAvgModel(movies);
            table.setModel(movieModel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

}
