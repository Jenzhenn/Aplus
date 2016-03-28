package ui;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import static javax.swing.ScrollPaneConstants.*;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import core.Movie;
import dao.DBManager;

import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MovieInfoPanel extends JPanel {
	private DBManager dbManager;
	private ScrollableListContainer listScroller;
	private JList listView;
	private DefaultListModel movieListModel;
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
		
		JButton btnAllMovie = new JButton("All Movie");
		displayMoviePanel.add(btnAllMovie);
		
		JButton btnUpcoming = new JButton("Upcoming");
		displayMoviePanel.add(btnUpcoming);
		
		//display all movies when enter the panel
		movieListModel = new DefaultListModel();
		listView = new JList();
		listView.setBackground(SystemColor.menu);
		
		try {
            List<Movie> movies = null;
            movies = dbManager.getAllMovie();
    		if (movies != null) {
    			for (Movie movie : movies) {
    				movieListModel.addElement(movie);
    			}
    		}
            MovieTableModel movieModel = new MovieTableModel(movies);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		listView.setModel(movieListModel);
		MovieListCellRenderer renderer = new MovieListCellRenderer();
		listView.setCellRenderer(renderer);

		listScroller = new ScrollableListContainer(listView);
		listScroller.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		add(listScroller, "cell 0 1 1 2, alignx left,aligny center");
		
		// display all movie when all movie button clicked
		btnAllMovie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                List<Movie> movies = null;
        		movieListModel = new DefaultListModel();
        		listView.setModel(movieListModel);
        		
                try {
                    movies = dbManager.getAllMovie();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
        		for (Movie movie : movies) {
        			movieListModel.addElement(movie);
        		}   
        		listView.repaint();

				
			}
		});

		// display movie by genre
        genreDropDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //select by genre
        		movieListModel = new DefaultListModel();
        		listView.setModel(movieListModel);
        		
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
        		for (Movie movie : movies) {
        			movieListModel.addElement(movie);
        		}

        		listView.repaint();
            }
        });	    
		
		
	}

	
	class ScrollableListContainer extends JScrollPane {

		private static final long serialVersionUID = -103572977104124066L;

		public ScrollableListContainer(Component component) {
			super(component);
			final JScrollBar verticalScrollBar = getVerticalScrollBar();
			setWheelScrollingEnabled(false);

			addMouseWheelListener(new MouseAdapter() {
				public void mouseWheelMoved(MouseWheelEvent evt) {
					if (evt.getWheelRotation() == 1) {
						int scrollAmount = evt.getScrollAmount();
						int newScrollPosition = verticalScrollBar.getValue()
								+ verticalScrollBar.getBlockIncrement()
								* scrollAmount;
						if (newScrollPosition <= verticalScrollBar.getMaximum()) {
							verticalScrollBar.setValue(newScrollPosition);
						}
					} else if (evt.getWheelRotation() == -1) {
						int scrollAmount = evt.getScrollAmount();
						int newScrollPosition = verticalScrollBar.getValue()
								- verticalScrollBar.getBlockIncrement()
								* scrollAmount;
						if (newScrollPosition >= 0) {
							verticalScrollBar.setValue(newScrollPosition);
						}
					}
				}
			});

		}
	}
	
	/*
	 * Handles the rendering/format of a movie item in the list.
	 */
	class MovieListCellRenderer extends DefaultListCellRenderer {

		private static final long serialVersionUID = -7799441088157759804L;

		private JPanel listItemParent;
		private JPanel listItemContainer;

		private JLabel titleLabel;
		private JLabel genreLabel;
		private JLabel languageLabel;
		private JLabel ratingLabel;
		private JLabel lengthLabel;
		private JLabel directorLabel;
		private JLabel actorLabel;
		private JLabel lblMin;

		public MovieListCellRenderer() {

			// ------------------------------------------------------------------
			// Initializes movie item components
			listItemParent = new JPanel();
			listItemParent.setBackground(SystemColor.menu);
			listItemContainer = new JPanel();
			listItemContainer.setBackground(SystemColor.menu);
			listItemContainer.setSize(400, 100);
			listItemContainer.setLayout(new MigLayout("", "[400px][][][][][][][][][][][][][]", "[][][][][][][][][][]"));

			
			titleLabel = new JLabel();
			genreLabel = new JLabel();
			languageLabel = new JLabel();
			ratingLabel = new JLabel();
			lengthLabel = new JLabel();
			directorLabel = new JLabel();
			actorLabel = new JLabel();
			lblMin = new JLabel("min");
			
			JLabel directorHeading = new JLabel("Director(s): ");
			listItemContainer.add(directorHeading, "flowx,cell 0 2");
			
			JLabel actorHeading = new JLabel("Actor(s): ");
			listItemContainer.add(actorHeading, "flowx,cell 0 3");
			
			directorLabel.setFont(new Font("Cordia New", Font.PLAIN, 20));
			listItemContainer.add(directorLabel, "cell 0 2");

			actorLabel.setFont(new Font("Cordia New", Font.PLAIN, 20));
			listItemContainer.add(actorLabel, "cell 0 3");

			titleLabel.setFont(new Font("Cordia New", Font.BOLD, 50));
			listItemContainer.add(titleLabel, "cell 0 0");
		
			
			listItemContainer.add(ratingLabel, "flowx,cell 0 1");
			ratingLabel.setFont(new Font("Cordia New", Font.PLAIN, 18));
			
			JLabel seperator1 = new JLabel("    |    ");
			listItemContainer.add(seperator1, "cell 0 1");
			
			listItemContainer.add(genreLabel, "cell 0 1");
			genreLabel.setFont(new Font("Cordia New", Font.PLAIN, 18));
			
			JLabel label = new JLabel("     |    ");
			listItemContainer.add(label, "cell 0 1");
			
			listItemContainer.add(lengthLabel, "cell 0 1");
			lengthLabel.setFont(new Font("Cordia New", Font.PLAIN, 15));
			
			JLabel lblMin = new JLabel("min");
			listItemContainer.add(lblMin, "cell 0 1");
			lblMin.setFont(new Font("Cordia New", Font.PLAIN, 15));
			
			

			// ------------------------------------------------------------------
			

			Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10,
					10);
			Border listItemSeparator = BorderFactory
					.createLineBorder(new Color(233, 233, 233));
			listItemParent.setBorder(new CompoundBorder(listItemSeparator,
					paddingBorder));
			listItemParent.add(listItemContainer, "grow,alignx left,aligny center");

		}

		@Override
		// Renders a movie item in list
		public Component getListCellRendererComponent(JList list,
				Object listItem, int index, boolean selected, boolean expanded) {

			Movie item = (Movie) listItem;
			String title = item.getTitle();
			String genre = item.getGenre();
			String language = item.getLanguage();
			String rating = item.getRating();
			String length = String.valueOf(item.getLength());
			Set<String> directorSet = item.getDirector();
			Set<String> actorSet = item.getActor();
			List<String> directorList = new ArrayList();
			directorList.addAll(directorSet);
			List<String> actorList = new ArrayList();
			actorList.addAll(actorSet);
			String directors = "";
			String actors = "";
			
			//convert directorList to string
			for(String d:directorList){
				directors += d + "   ";
			}
			//convert actorList to string
			for(String a:actorList){
				actors += a + "   ";
			}			

			titleLabel.setText(title);
			genreLabel.setText(genre);
			languageLabel.setText(language);
			ratingLabel.setText(rating);
			lengthLabel.setText(length);
			directorLabel.setText(directors);
			actorLabel.setText(actors);

			return listItemParent;
		}
	}

}
