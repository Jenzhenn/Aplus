import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class MovieDAO {
	
	private Connection con;
	
	public MovieDAO() throws Exception {
		//connect to database
		try {
			con = DriverManager.getConnection(
					  "jdbc:oracle:thin:@localhost:1522:ug", "ora_s2u9a", "a33425125");
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
	
	public List<Movie> getAllMovie() throws Exception{
		List<Movie> movieList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM movie");
			
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
			genre += "%";
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

	private void close(Statement stmt, ResultSet rs) throws SQLException {
		close(null, stmt, rs);		
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println("-------- Oracle JDBC Connection Testing ------");
		
		//register driver
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e){
			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Oracle JDBC Driver Registered");		
		
		MovieDAO dao = new MovieDAO();

		System.out.println(dao.displayByGenre("family"));
		System.out.println(dao.getAllMovie());		
		
	}

	

}
