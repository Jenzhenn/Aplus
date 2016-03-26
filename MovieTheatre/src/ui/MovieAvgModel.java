package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Movie;

public class MovieAvgModel extends AbstractTableModel {
    private static final int MID_COL = 0;
    private static final int TITLE_COL = 1;
    private static final int TICKETS_COL = 2;
    
    private String[] columnNames = {"MID", "Title", "Tickets Sold %"};
    
    private List<Movie> movies;
    
    public MovieAvgModel(List<Movie> theMovies) {
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
        case MID_COL:
            return tempMovie.getMovieID();
        case TICKETS_COL:
            return tempMovie.getAvgSold();
        default:
            return tempMovie.getMovieID();
        }
    }


    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

}
