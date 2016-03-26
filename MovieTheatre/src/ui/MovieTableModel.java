package ui;

import java.util.List;
import core.Movie;

import javax.swing.table.AbstractTableModel;

public class MovieTableModel extends AbstractTableModel{
	
	private static final int TITLE_COL = 0;
	private static final int LANGUAGE_COL = 1;
	private static final int LENGTH_COL = 2;
	private static final int RATING_COL = 3;
	private static final int DIRECTOR_COL = 4;
	private static final int ACTOR_COL = 5;
	
	private String[] columnNames = {"Title", "Language", "Length", "Rating", "Director(s)", "Actor(s)"};
	private List<Movie> movies;
	
	public MovieTableModel(List<Movie> theMovies) {
		movies = theMovies;
	}

	@Override
	public int getRowCount() {
		return movies.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Movie tempMovie = movies.get(rowIndex);
		
		switch(columnIndex){
		case TITLE_COL:
			return tempMovie.getTitle();
		case LANGUAGE_COL:
			return tempMovie.getLanguage();
		case LENGTH_COL:
			return tempMovie.getLength();
		case RATING_COL:
			return tempMovie.getRating();
		case DIRECTOR_COL:
			return tempMovie.getDirector();
		case ACTOR_COL:
			return tempMovie.getActor();
		default:
			return tempMovie.getMovieID();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
