package ui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import core.Movie;
import dao.DBManager;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MovieInfoPanel extends JPanel {
	private JTable table;
	private DBManager dbManager;
	/**
	 * Create the panel.
	 */
	public MovieInfoPanel(DBManager db) {
		

		dbManager = db;
		
		// create dbManager
		try {
			dbManager = new DBManager();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setLayout(new MigLayout("", "[145px,grow]", "[10px][][25px,grow]"));
		
		JPanel displayMoviePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) displayMoviePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(displayMoviePanel, "flowx,cell 0 0,alignx left,aligny center");
		
		JLabel lblDisplayByGenre = new JLabel("Display by genre:");
		displayMoviePanel.add(lblDisplayByGenre);
		

		
		JComboBox genreDropDown = new JComboBox();
		genreDropDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//select by genre
				
				String genre = (String) genreDropDown.getSelectedItem();
				List<Movie> movies = null;
				
				if(genre != "All") {
					try {
						movies = dbManager.displayByGenre(genre);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					try {
						movies = dbManager.getAllMovie();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				MovieTableModel movieModel = new MovieTableModel(movies);
				table.setModel(movieModel);
			}
		});
		
		genreDropDown.setModel(new DefaultComboBoxModel(new String[] {"All", "Action", "Comedy", "Family", "Romance", "Violence"}));
		displayMoviePanel.add(genreDropDown);
		
		JScrollPane tablePanel = new JScrollPane();
		add(tablePanel, "cell 0 1 1 2,grow");

		
		table = new JTable();
		tablePanel.setViewportView(table);
		
		JButton btnUpcoming = new JButton("Upcoming");
		add(btnUpcoming, "cell 0 0");
		
		
	}

}
