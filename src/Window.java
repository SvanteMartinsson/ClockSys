import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Window {

	private JFrame frame;
	private JTextField eIdField;
	private AdminWindow aWindow = new AdminWindow(this);
	private String currId;
	private DateFormat dateFormat;
	private Date date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
		date = new Date();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel timeLbl = new JLabel(dateFormat.format(date));
		timeLbl.setBounds(6, 6, 147, 16);
		frame.getContentPane().add(timeLbl);
		
		JLabel lblEmployeId = new JLabel("Employe ID");
		lblEmployeId.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblEmployeId.setBounds(6, 76, 85, 16);
		frame.getContentPane().add(lblEmployeId);
		
		eIdField = new JTextField();
		eIdField.setBounds(103, 72, 130, 26);
		frame.getContentPane().add(eIdField);
		eIdField.setColumns(10);
		
		JButton checkInBtn = new JButton("Log in");
		checkInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(eIdField.getText());
				DBConnect connect = new DBConnect();
				if(connect.logIn(id)){
					JOptionPane.showMessageDialog(null, "You were succefully logged in!");
					currId = String.valueOf(id);
					aWindow.getNameLabel().setText(connect.getNameFromId(String.valueOf(id)));
					aWindow.setCurrID(eIdField.getText());
					
					if(connect.getRankFromId(id).equals("2")){
						frame.setVisible(false);
						aWindow.getFrame().setVisible(true);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "That id does not exist!");
				}
				
			}
		});
		checkInBtn.setBounds(284, 204, 160, 68);
		frame.getContentPane().add(checkInBtn);
		
		
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public String getCurrId(){
		return currId;
	}
	
	public Date getDate(){
		return date;
	}
	
	public DateFormat getDateFormat(){
		return dateFormat;
	}
}
