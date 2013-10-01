package server;

import java.io.*;
import java.net.*;
 
 
/**
 * Class with an infinite loop waiting for a client to connect to the ServerSocket.
 * 
 * @author Ferhan Serbest
 *
 */
public class Accept implements Runnable{
 
    private ServerSocket socketserver = null;
    private Socket socket = null;
    public Thread t1;
    
    
    public Accept(ServerSocket ss){
     socketserver = ss;
    }
     
    public void run() {
         
        try {
            while(true){                 
            socket = socketserver.accept();             
            t1 = new Thread(new Auth(socket));
            t1.start();
            }
        } catch (IOException e) {
             
            System.err.println("Erreur serveur");
        }
         
    }
}