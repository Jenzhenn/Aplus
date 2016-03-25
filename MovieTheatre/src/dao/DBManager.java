package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import core.Movie;

public class DBManager {
	
	private Connection con;
	
	public DBManager() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e){
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}
		
		//connect to database
		try {
			con = DriverManager.getConnection(
					  "jdbc:oracle:thin:@localhost:1522:ug", "ora_n2v8", "a36847127");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			return;
		}
		
		if (con != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] getEIDEname(String eid) throws SQLException{
		String[] returnString = new String[2];
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("Select eid,ename from employee where eid like ?");
			stmt.setString(1, eid);
			rs = stmt.executeQuery();
			
			while (rs.next()){
				returnString[0] = rs.getString("eid");
				System.out.println(returnString[0]);
				returnString[1] = rs.getString("ename");
				System.out.println(returnString[1]);
			}
			
		}finally{
			close(stmt,rs);
		}
		return returnString;
	}
	
	public List<Movie> getAllMovie() throws Exception{
		List<Movie> movieList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Movie");
			
			while (rs.next()){
				Movie tempMovie = convertRowToMovie(rs);
				movieList.add(tempMovie);
			}
			
			return movieList;
		}
		finally{
			close(stmt,rs);
		}
		
		
	}

	public List<Movie> displayByGenre(String genre) throws Exception{
		List<Movie> movieList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("SELECT * FROM movie WHERE genre like ?");
			stmt.setString(1, genre);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Movie tempMovie = convertRowToMovie(rs);
				movieList.add(tempMovie);
			}
			
			return movieList;
		}
		finally{
			close(stmt,rs);
		}
	}
	
	private Movie convertRowToMovie(ResultSet rs) throws SQLException{

		String genre = rs.getString("genre");
		String language = rs.getString("language");
		int length = rs.getInt("length");
		String movieID = rs.getString("movie_ID");
		String rating = rs.getString("rating");
		
		Movie tempMovie = new Movie(genre, language, length, movieID, rating);
		
		return tempMovie;
	}
	
	private static void close(Connection con, Statement stmt, ResultSet rs)
			throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (stmt != null) {
			
		}
		
		if (con != null) {
			con.close();
		}
	}
	
	public static void printMovies(List<Movie> movies){
		for (Movie m:movies){
			System.out.println(m.printMovie());
		}
	}

	private void close(Statement stmt, ResultSet rs) throws SQLException {
		close(null, stmt, rs);		
	}
	


	

}
