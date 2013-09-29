package client;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;



/**
 * Starting point for the client. A JFrame asks user input for login and password then handles them.
 * 
 * @author Ferhan Serbest
 * 
 */
public class Welcome extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JLabel jLabel3;
	private JButton jButton1;
	private JButton jButton2;
	private JTextField jTextField2;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jPanel2;
	private String login, password;
	private boolean IDcheck = false;
	public static Socket socket = null;
    public static Thread t2;

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Welcome inst = new Welcome();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public Welcome() {
		super();
		initGUI();
	}
	
	/**
	 * Method used to display and personalize the JFrame.
	 */
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				jPanel1.setPreferredSize(new java.awt.Dimension(384, 42));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("Welcome to JavaInstantChat");
				}
			}
			{
				jPanel2 = new JPanel();
				GridBagLayout jPanel2Layout = new GridBagLayout();
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				jPanel2.setPreferredSize(new java.awt.Dimension(384, 212));
				jPanel2Layout.rowWeights = new double[] {0.1, 0.1, 0.1};
				jPanel2Layout.rowHeights = new int[] {7, 7, 7};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.1};
				jPanel2Layout.columnWidths = new int[] {181, 151, 20};
				jPanel2.setLayout(jPanel2Layout);
				{
					jLabel2 = new JLabel();
					jPanel2.add(jLabel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel2.setText("Login:");
				}
				{
					jLabel3 = new JLabel();
					jPanel2.add(jLabel3, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jLabel3.setText("Password:");
				}
				{
					jTextField1 = new JTextField();
					jPanel2.add(jTextField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jTextField2 = new JTextField();
					jPanel2.add(jTextField2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jButton1 = new JButton();
					jPanel2.add(jButton1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("Connect");
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
				{
					jButton2 = new JButton();
					jPanel2.add(jButton2, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton2.setText("Exit");
					jButton2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton2ActionPerformed(evt);
						}
					});
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method called upon click on the JButton. This method tries to connect to the server then checks login/password if successful.
	 * @param evt - event of clicking on the JButton.
	 */
	private void jButton1ActionPerformed(ActionEvent evt) {
		try {
	         
	        socket = new Socket("127.0.0.1",2009);
	        login = jTextField1.getText();
	        password = jTextField2.getText();	        
	        LoginCheck lc = new LoginCheck(socket);
	        IDcheck = lc.Check(login, password);
	        if (!IDcheck){
	        	JOptionPane.showMessageDialog(this, "Bad login and/or password.");
	        }
	        else{
	        	t2 = new Thread(new ChatWindow(socket,login));
                t2.start();
                this.dispose();
	        }
       
	         
	         
	         
	    } catch (UnknownHostException e) {
	      System.err.println("Can't connect to address "+socket.getLocalAddress());
	    } catch (IOException e) {
	      System.err.println("No server listening on port "+socket.getLocalPort());
	    }
	}
	
	/**
	 * Method called upon click on the JButton. This method exits the JFrame.
	 * @param evt - event of clicking on the JButton.
	 */
	private void jButton2ActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}
