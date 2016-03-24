package testing;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OracleJDBC {
	
	public static void main(String[] args){
		
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
		
		Connection connection = null;
		
		//connect to DB
		try {
			connection = DriverManager.getConnection(
					  "jdbc:oracle:thin:@localhost:1522:ug", "ora_n2v8", "a36847127");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			return;
		}
		
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//start
		
		System.out.println("Start Statement");
		
		try {
			Statement stmt = connection.createStatement();
			System.out.println("before query");
			ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");
			System.out.println("after query");
			System.out.println(rs.next());
			while (rs.next()) {
				System.out.println("in while loop");
	            String au_id = rs.getString("genre");
	            //String au_lname = rs.getString("AU_LNAME");
	            System.out.println(au_id);
	        }
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

