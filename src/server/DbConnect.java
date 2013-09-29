package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class used to connect to the SQL Database (PostGreSQL)
 * 
 * @author Ferhan Serbest
 * 
 */
public class DbConnect {
	private Statement state;
	
	/**
	 * Method to connect to the SQL Database
	 * 
	 * @return - returns a Statement to use for further SQL manipulation.
	 */
	public Statement Connect() {
		try {
		      Class.forName("org.postgresql.Driver");
		      String url = "jdbc:postgresql://localhost:5432/JavaInstantChat";
		      String user = "postgres";
		      String passwd = "Supinf0";
		      Connection conn = DriverManager.getConnection(url, user, passwd);
		      state = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		      return state;
		          
		    } catch (Exception e) {
		      e.printStackTrace();
		    }     
		return state;
	}
	}