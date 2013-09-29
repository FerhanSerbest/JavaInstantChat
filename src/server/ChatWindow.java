package server;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;


/**
 * Main chat window on the server side.
 * 
 * @author Ferhan Serbest
 * 
 */
public class ChatWindow extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JButton jButton1;
	private JTextPane jTextPane1;
	private JTextField jTextField1;
	private JPanel jPanel2;
	private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String login;
    private String message, loginSender;
    private Calendar cal;
    private SimpleDateFormat sdf;

	
	
	
	public ChatWindow() {
		super();
		initGUI();
		
	}
	
	public ChatWindow(Socket s, String l) {
		socket = s;
		login = l;
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setTitle("server");
		initGUI();
	}

	/**
	 * Method used to display and personalize the JFrame
	 */
	private void initGUI() {
		try {
			System.out.println("initgui1");
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	sdf = new SimpleDateFormat("HH:mm");
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				jPanel1.setPreferredSize(new java.awt.Dimension(484, 474));
				{
					jTextPane1 = new JTextPane();
					jPanel1.add(jTextPane1);
					jTextPane1.setPreferredSize(new java.awt.Dimension(483, 473));
					jTextPane1.setEditable(false);
				}
			}
			{
				jPanel2 = new JPanel();
				GridBagLayout jPanel2Layout = new GridBagLayout();
				jPanel2Layout.columnWidths = new int[] {48, 250, 48, 7};
				jPanel2Layout.rowHeights = new int[] {7};
				jPanel2Layout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				jPanel2Layout.rowWeights = new double[] {0.1};
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				jPanel2.setLayout(jPanel2Layout);
				jPanel2.setPreferredSize(new java.awt.Dimension(484, 54));
				{
					jTextField1 = new JTextField();
					jPanel2.add(jTextField1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				}
				{
					jButton1 = new JButton();
					jPanel2.add(jButton1, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					jButton1.setText("Send");
					System.out.println("initgui7");
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
			}
			pack();
			setSize(500, 600);
			while (true){
				message = in.readLine();
				loginSender = in.readLine();
				cal = Calendar.getInstance();
				jTextPane1.setText(jTextPane1.getText()+"("+sdf.format(cal.getTime())+") "+loginSender+" says: "+message+"\n");
				System.out.println("caca");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Method called upon click on the JButton. This method handles the input of text.
	 * @param evt - event of clicking on the JButton.
	 */
	private void jButton1ActionPerformed(ActionEvent evt) {
		if (jTextField1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please type in a message.");
		}
		else {

			out.println(jTextField1.getText());
			out.flush();
			out.println(login);
			out.flush();
			cal = Calendar.getInstance();
	        jTextPane1.setText(jTextPane1.getText()+"("+sdf.format(cal.getTime())+") "+login+" says: "+jTextField1.getText()+"\n");
	        jTextField1.setText("");
		}
	}

}
