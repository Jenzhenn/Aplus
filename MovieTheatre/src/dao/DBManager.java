package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import core.Auditorium;
import core.Employee;
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
		dbConnector();
	}
////// Customer

	private void dbConnector() {
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
    
    public Integer getPoint(String cphone) throws SQLException{
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	Integer point = null;
    	
    	try{
    		stmt = con.prepareStatement("SELECT cphone,reward_point from member where cphone like ?");
    		stmt.setString(1,cphone);
    		rs = stmt.executeQuery();
    		while(rs.next()){
    			point = rs.getInt("reward_point");

    		}
    	} finally {
			close(stmt,rs);
    	}
    	if(point == null){
    		SQLException e = new SQLException();
    		throw e;
    		}
    	return point;
    }

////// Employee	
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
	
	public Boolean checkManager(String eid) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Boolean result = false;
		
		try {
			stmt = con.prepareStatement("select count(*) from manager where eid like ?");
			stmt.setString(1, eid);
			rs = stmt.executeQuery();
			while(rs.next()){
				result = rs.getBoolean(1);
				System.out.println(result);
			}
		} finally{
			close(stmt,rs);
		}

		
		return result;
	}
	
    public Boolean checkCashier(String eid) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Boolean result = false;
        
        try {
            stmt = con.prepareStatement("select count(*) from cashier where eid like ?");
            stmt.setString(1, eid);
            rs = stmt.executeQuery();
            while(rs.next()){
                result = rs.getBoolean(1);
                System.out.println(result);
            }
        } finally{
            close(stmt,rs);
        }

        
        return result;
    }

    public Integer cashierAddHours(Integer hours, String eid) throws SQLException{
        PreparedStatement current = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer currentHours = 0;
        
        try{
            current = con.prepareStatement("select hours from cashier where eid like ?");
            current.setString(1,eid);
            rs = current.executeQuery();
            while(rs.next()){
                currentHours = rs.getInt(1);
                System.out.println(currentHours);
            }
        } finally{
            close(current,rs);
        }
        
        hours = hours+currentHours;
        
        try{
            stmt = con.prepareStatement("update cashier set hours=? where eid like ?");
            stmt.setInt(1, hours);
            stmt.setString(2, eid);
            rs = stmt.executeQuery();
            return hours;
        } finally{
            close(stmt,rs);
        }
        
    }
    public String[] cashierLogin(String eid) throws SQLException{
        if(checkCashier(eid)){
            String[] eidEnamePair =getEIDEname(eid);
            return eidEnamePair;
        } else {
            throw new SQLException();
        }
        
    }    
	
	public String[] managerLogin(String eid) throws SQLException{
		if(checkManager(eid)){
			String[] eidEnamePair =getEIDEname(eid);
			return eidEnamePair;
		} else {
			throw new SQLException();
		}
		
	}
	
	public void deleteEmployee(String eid) throws SQLException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("delete from employee where eid like ?");
			stmt.setString(1, eid);
			rs = stmt.executeQuery();
		}finally{
			close(stmt,rs);
		}
	}
	
	public List<Employee> getAllEmployee() throws SQLException{
		List<Employee> employeeList = new ArrayList<Employee>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from employee");
			while(rs.next()){
				Employee employee = convertRowToEmployee(rs);
				employeeList.add(employee);
			}
			
			return employeeList;
		} finally{
			close(stmt,rs);
		}
	}
	
	public Employee convertRowToEmployee(ResultSet rs) throws SQLException{

		String eid = rs.getString("eid");
		String name = rs.getString("ename");
		String phone = rs.getString("ephone");
		Integer salary = rs.getInt("salary");
		Employee employee = new Employee(eid,name,phone,salary);
		
		return employee;
	}

	
//////Movie
	public List<Movie> getAllMovie() throws Exception{
		
			List<Movie> movieList = new ArrayList<Movie>();
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = con.createStatement();
				rs = stmt.executeQuery("select movie_ID, genre, title, language, length, rating, dname, aname from movie natural left join performed_by natural left join directed_by");
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
	
	public List<Movie> getUpcomingMovies() throws Exception{
		
		List<Movie> movieList = new ArrayList<Movie>();
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from (select movie_ID from movie minus (select movie_ID from isPlaying)) natural left join movie natural left join directed_by natural left join performed_by");
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
	
	public List<Movie> displayUpcomingByGenre(String genre) throws Exception{
		List<Movie> movieList = new ArrayList<Movie>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("select * from (select movie_ID from movie minus (select movie_ID from isPlaying)) natural left join movie natural left join directed_by natural left join performed_by WHERE genre like ?");
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

	public List<Movie> displayByGenre(String genre) throws Exception{
		List<Movie> movieList = new ArrayList<Movie>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("select * from movie natural left join performed_by natural join directed_by WHERE genre like ?");
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

		String title = rs.getString("title");
		String genre = rs.getString("genre");
		String language = rs.getString("language");
		int length = rs.getInt("length");
		String movieID = rs.getString("movie_ID");
		String rating = rs.getString("rating");
		Set<String> directorList = new HashSet<String>();
		directorList.add(rs.getString("dname"));
		Set<String> actorList = new HashSet<String>();
		actorList.add(rs.getString("aname"));
		
		Movie tempMovie = new Movie(title, genre, language, length, movieID, rating, directorList, actorList);
		
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
		if(movies.isEmpty()) System.out.println("empty movie");
	    
	    for (Movie m:movies){
			System.out.println(m.printMovie());
		}
	}
	
//////Ticket
	public List<Movie> seeMostSoldMovie() throws Exception{
		// sql query find the max:
		// get all tickets group by movieID
		// compute avg for each group: sumofisSold/countOfTicket
		//return the movieID of the most sold movie
	    List<Movie> movieList = new ArrayList<Movie>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
            rs = stmt.executeQuery("Select movie_ID, title, avg(isSold) from ticket natural left join movie group by movie_ID, title having avg(isSold) = (select MAX(avg(isSold)) from ticket natural left join movie group by movie_ID, title)");
		while(rs.next()){
			String movieID = rs.getString("movie_ID");
			String title = rs.getString("title");
			Double avgSold = rs.getDouble(3);
			
			Movie tempMovie = new Movie(movieID,title,avgSold);
			movieList.add(tempMovie);
		}
		
		return movieList;
		}
		finally{
			close(stmt,rs);
		}
	}
	
	public List<Movie> seeLeastSoldMovie() throws Exception{
		//return the movieID of the least sold movie
	    List<Movie> movieList = new ArrayList<Movie>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
            rs = stmt.executeQuery("Select movie_ID, title, avg(isSold) from ticket natural left join movie group by movie_ID, title having avg(isSold) = (select MIN(avg(isSold)) from ticket natural left join movie group by movie_ID, title)");
		while(rs.next()){
            String movieID = rs.getString("movie_ID");
            String title = rs.getString("title");
            Double avgSold = rs.getDouble(3);
            
            Movie tempMovie = new Movie(movieID,title,avgSold);
            movieList.add(tempMovie);
		}
		
		return movieList;
		}
		finally{
			close(stmt,rs);
		}
	}
	
	public List<Auditorium> availableSeats(String title, String date, String time)throws Exception{

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Auditorium> audi_seat_list = new ArrayList<Auditorium>();
		
		try{
			stmt = con.prepareStatement("SELECT audi_num,COUNT(isSold) as count FROM ticket natural left join movie WHERE title = ? AND show_date = ? AND show_time = ? AND isSold = 0 GROUP BY audi_num,isSold");
			stmt.setString(1, title);
			stmt.setString(2, date);
			stmt.setString(3, time);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Auditorium audi_seat = convertRowToAuditorium(rs);
				audi_seat_list.add(audi_seat);
			}
			return audi_seat_list;
		}
		finally{
			close(stmt,rs);
		}			
	}
	
	public List<Ticket> seeReservedTicket(String cphone) throws Exception{
		List<Ticket> tickets = new ArrayList<Ticket>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("select online_sold.conf_code, ticket.ticket_num, movie.title, ticket.show_time, ticket.show_date, ticket.audi_num, ticket.seat_num from ticket, online_sold, movie where ticket.cphone like ? and ticket.ticket_num = online_sold.ticket_num and ticket.movie_ID = movie.movie_ID");
			stmt.setString(1, cphone);
			rs = stmt.executeQuery();
		while(rs.next()){
			String confCode = rs.getString("conf_code");
            String title = rs.getString("title");
            String date = rs.getString("show_date");
            String time = rs.getString("show_time");
            int audiNum = rs.getInt("audi_num");
            String seatNum = rs.getString("seat_num");

            float num = 0;
            int i =0;
            
            Ticket tempTicket = new Ticket(null,null,null,num,i,time,date,seatNum,audiNum,null,title,confCode);
            tickets.add(tempTicket);
		}
		
		return tickets;
		}
		finally{
			close(stmt,rs);
		}
		
	}

	
	
    private Auditorium convertRowToAuditorium(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		int a = rs.getInt("audi_num");
		int s = rs.getInt("count");
		Auditorium audi_seat = new Auditorium(a, s);
    	return audi_seat;
	}

	//////For purchasing tickets
	public List<String> showAvailableTime(String movie, String date) throws Exception{
		// show available dates for the selected movie
	    List<String> timeList = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("SELECT show_time FROM ticket natural left join movie WHERE title = ? AND show_date = ? AND isSold = 0 GROUP BY show_time");
			stmt.setString(1, movie);
			stmt.setString(2, date);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				String time = rs.getString("show_time");
				timeList.add(time);
	
			}
			return timeList;
		} finally{
			close(stmt,rs);
		}	
		
	}

	public List<String> showAvailableDate(String movie) throws Exception{
		// show available dates for the selected movie
	    List<String> dateList = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			stmt = con.prepareStatement("SELECT show_date FROM ticket natural left join movie WHERE title = ? AND isSold = 0 GROUP BY show_date");
			stmt.setString(1, movie);
			rs = stmt.executeQuery();
			
			while(rs.next()){
				
				String date = rs.getString("show_date");
				dateList.add(date);
	
			}
			return dateList;
		} finally{
			close(stmt,rs);
		}	
		
	}

	public List<String> showPlayingMovie() throws Exception{
		// get all the movie titles from database
	    List<String> titleList = new ArrayList<String>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT title, movie_ID FROM ticket natural left join movie WHERE isSold = 0 GROUP BY movie_ID, title");
		while(rs.next()){
            String title = rs.getString("title");
            titleList.add(title);
		}
		return titleList;
		}
		finally{
			close(stmt,rs);
		}
	}
/*	
public void buyTicket(String title, String date, String time, int audi_num){
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try{
		stmt = con.prepareStatement("SELECT * FROM ticket natural left join movie WHERE title = ? AND show_date = ? AND show_time = ? AND audi_num = ? AND isSold = 0 AND ROWNUM = 1");
		stmt.setString(1, title);
		stmt.setString(2, date);
		stmt.setString(3, time);
		stmt.setInt(4, audi_num);
		rs = stmt.executeQuery();
		
		while(rs.next()){
			Auditorium audi_seat = convertRowToAuditorium(rs);
			audi_seat_list.add(audi_seat);
		}
		return audi_seat_list;
	}
	finally{
		close(stmt,rs);
	}			
}
*/
  

	public void quit(){
	    try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

	private void close(Statement stmt, ResultSet rs) throws SQLException {
		close(null, stmt, rs);		
	}
	
	public static void main(String[] args) throws Exception{

		DBManager dao = new DBManager();

		//DBManager.printMovies(dao.displayByGenre("family"));
		//DBManager.printMovies(dao.getAllMovie());	
		
		System.out.println("//show Available seats");
//		System.out.println("Auditorium: "+ (dao.availableSeats("Mamamia","3/22/2016", "15:45")));
//		System.out.println("Auditorium: " + dao.availableSeats("Titanic", "1/23/2016", "9:20"));
//		System.out.println("Auditorium: " + dao.availableSeats("Titanic", "1/23/2016", "9:20"));

		List<Auditorium> seats = dao.availableSeats("Mamamia","3/22/2016", "15:45");
		for(Auditorium seat:seats){
			System.out.println(seat);
		}
		
		
		System.out.println("//show Playing Movie");
		List<String> movies = dao.showPlayingMovie();
		for(String title:movies){
			System.out.println(title);
		}
		
		System.out.println("//show Available Time");
		List<String> times = dao.showAvailableTime("Mamamia", "1/23/2016");
		for(String time:times){
			System.out.println(time);
		}
		
	}

	

	

}
