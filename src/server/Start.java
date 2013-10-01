package server;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
 * Starting point for the Server. A JFrame displays information and creates a Socket upon user input.
 * 
 * @author Ferhan Serbest
 * 
 */
public class Start extends javax.swing.JFrame {
	public static ServerSocket ss = null;
	public static Thread t;
	private JPanel jPanel1;
	private JButton jButton1;
	private JPanel jPanel3;
	private JLabel jLabel1;
	private JPanel jPanel2;

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Start inst = new Start();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
				inst.setTitle("Java Instant Chat Server");
			}
		});
	}
	
	public Start() {
		super();
		initGUI();
	}
	
	/**
	 * Method to display and personalize the JFrame
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				jPanel2.setPreferredSize(new java.awt.Dimension(384, 66));
				{
					jButton1 = new JButton();
					jPanel2.add(jButton1);
					jButton1.setText("Start Server");
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				jPanel1.setPreferredSize(new java.awt.Dimension(384, 130));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("Welcome to the server for JavaInstantChat");
				}
			}
			{
				jPanel3 = new JPanel();
				getContentPane().add(jPanel3, BorderLayout.NORTH);
				jPanel3.setPreferredSize(new java.awt.Dimension(384, 42));
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method called upon click on the JButton. This method creates the Socket.
	 * @param evt - event of clicking on the JButton.
	 */
	private void jButton1ActionPerformed(ActionEvent evt) {
		try {
            ss = new ServerSocket(2009);
            System.out.println("Server started on "+ss.getLocalPort()+". Waiting for clients to connect.");
             
            t = new Thread(new Accept(ss));
            t.start();
            this.dispose();
            
             
        } catch (IOException e) {
            System.err.println("The port "+ss.getLocalPort()+" is already in use.");
        }
		
	}

}
