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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		

		
		JComboBox<String> genreDropDown = new JComboBox<String>();

		
		genreDropDown.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "Action", "Comedy", "Family", "Romance", "Violence"}));
		displayMoviePanel.add(genreDropDown);
		
		JScrollPane tablePanel = new JScrollPane();
		add(tablePanel, "cell 0 1 1 2,grow");

		
		table = new JTable();
		tablePanel.setViewportView(table);
	    table.setRowSelectionAllowed(false);
	    table.setColumnSelectionAllowed(false);
	    table.setCellSelectionEnabled(true);
        try {
            List<Movie> movies = null;
            movies = dbManager.getAllMovie();
            MovieTableModel movieModel = new MovieTableModel(movies);
            table.setModel(movieModel);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }	    
	    
        genreDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //select by genre
                
                String genre = (String) genreDropDown.getSelectedItem();
                List<Movie> movies = null;
                
                if(genre != "All") {
                    try {
                        System.out.println(genre);
                        movies = dbManager.displayByGenre(genre.toLowerCase());
                        DBManager.printMovies(movies);
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
		
		JButton btnAllMovie = new JButton("All Movie");
		btnAllMovie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                List<Movie> movies = null;
                try {
                    movies = dbManager.getAllMovie();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                MovieTableModel movieModel = new MovieTableModel(movies);
                table.setModel(movieModel);
				
			}
		});
		add(btnAllMovie, "cell 0 0");
	    
		JButton btnUpcoming = new JButton("Upcoming");
		add(btnUpcoming, "cell 0 0");
		
		
	}

}
