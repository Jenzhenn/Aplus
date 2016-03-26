package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import core.Movie;
import core.Ticket;

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
			System.out.println("Connection failed ");
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
		
			List<Movie> movieList = new ArrayList<Movie>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("select * from movie natural left join performed_by natural left join directed_by");
			while(rs.next()){
				Movie tempMovie = convertRowToMovie(rs);
				if(movieList.contains(tempMovie)){
					int index = movieList.indexOf(tempMovie);
					Movie sameMovie = movieList.get(index);
					tempMovie.mergeDirector(sameMovie);
					tempMovie.mergeActor(sameMovie);
					movieList.remove(index);
				}
				movieList.add(tempMovie);
			}
			
			return movieList;
		}
		finally{
			close(stmt,rs);
		}
	}

	public List<Movie> displayByGenre(String genre) throws Exception{
		List<Movie> movieList = new ArrayList<Movie>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("SELECT * FROM movie natural join performed_by, directed_by WHERE genre like ?");
			stmt.setString(1, genre);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Movie tempMovie = convertRowToMovie(rs);
				if(movieList.contains(tempMovie)){				
					int index = movieList.indexOf(tempMovie);
					Movie sameMovie = movieList.get(index);
					tempMovie.mergeDirector(sameMovie);
					tempMovie.mergeActor(sameMovie);
					movieList.remove(index);
				}
				
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
		Set<String> directorList = new HashSet<String>();
		directorList.add(rs.getString("dname"));
		Set<String> actorList = new HashSet<String>();
		actorList.add(rs.getString("aname"));
		
		Movie tempMovie = new Movie(genre, language, length, movieID, rating, directorList, actorList);
		
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
	
	
	public int availableSeats(int auditorium, String date, String time, String mID)throws Exception{
		int avail_seat = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("SELECT * FROM ticket WHERE show_date = ? AND show_time = ? AND movie_ID = ? AND audi_num = ?");
			stmt.setString(1, date);
			stmt.setString(2, time);
			stmt.setString(3, mID);
			stmt.setInt(4, auditorium);
			rs = stmt.executeQuery();
			
			while(rs.next()){

				
				int isSold = rs.getInt("isSold");
				rs.getString("show_time");
				rs.getString("show_date");
				rs.getString("movie_ID");
				rs.getInt("audi_num");
				
				if (isSold == 0) {
					avail_seat = avail_seat+1;
				}
			}
			return avail_seat;
		}
		finally{
			close(stmt,rs);
		}	
		
	}


	private void close(Statement stmt, ResultSet rs) throws SQLException {
		close(null, stmt, rs);		
	}
	
	public static void main(String[] args) throws Exception{

		DBManager dao = new DBManager();

		//MovieDAO.printMovies(dao.displayByGenre("family"));
		DBManager.printMovies(dao.getAllMovie());	
//		System.out.println("Available seats: " + dao.availableSeats(2, "3/22/2016", "15:45", "104112"));
//		System.out.println("Available seats: " + dao.availableSeats(8, "1/23/2016", "9:20", "098344"));
//		System.out.println("Available seats: " + dao.availableSeats(1, "1/23/2016", "9:20", "098344"));
		
	}


}
