package server;

import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
 

/**
 * Class used to authenticate the user trying to connect.
 * 
 * @author Ferhan Serbest
 *
 */
public class Auth implements Runnable {
 
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "zero", password =  null;
    private Statement state = null;
    private int IDcheck;
    public Thread t2;
     
    public Auth(Socket s){
         socket = s;
        }
    
    public void run() {
     
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            login = in.readLine();
            password = in.readLine();
            DbConnect DBC = new DbConnect();
            state = DBC.Connect();
            ResultSet result = state.executeQuery("SELECT login FROM users WHERE login = '"+login+"' AND password= '"+password+"'");
            if (!result.next()){
            	IDcheck = 0;
            	out.print(IDcheck);
            	out.flush();
            }
            else {
            	IDcheck = 1;
            	out.println(IDcheck);
            	out.flush();
            	String s = "Server";
            	ChatWindow cw = new ChatWindow(socket,s);
            }
                        
            
             
        } catch (IOException e) {
             
        	System.out.println("Error connecting to the Server.");
            
		} catch (SQLException e) {
			
			System.out.println("Error connecting to the SQL Server.");
		}
    }
     
    
 
}