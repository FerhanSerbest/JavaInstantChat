package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class to check the login and password of a user.
 * 
 * @author Ferhan Serbest
 * 
 */
public class LoginCheck {
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private int j;
	
	public LoginCheck(Socket s){
		socket = s;
	}
	
	/**
	 * Method that sends the user information to the server to check against the SQL Database.
	 * @param login - login of the user.
	 * @param password - password of the user.
	 * @return - returns true if the login and password are correct.
	 */
	public boolean Check(String login, String password) {
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(login);
			out.flush();
			out.println(password);
			out.flush();
			j = Integer.parseInt(in.readLine());
			switch(j){
				case 0 : return false;
				case 1 : return true; 
			}
		} catch (IOException e) {
			System.out.println("Error connecting to the Server.");
		}
         
		return false;
	}
	
	
}