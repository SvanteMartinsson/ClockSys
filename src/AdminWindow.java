import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AdminWindow {

	private JFrame frame;
	private Window logInFrame;
	private DBConnect dbCon;
	private String currId;
	private JLabel nameLbl;
	private ListUsersWindow lUsersWindow;
	private DateFormat dateFormat;
	private Date date;
	//private Window logInWindow = new Window();
	



	/**
	 * Create the application.
	 */
	public AdminWindow(Window logInFrame) {
		date = new Date();
		dateFormat = new SimpleDateFormat("yyyy/mm/dd HH:mm:ss");
		initialize();
		this.logInFrame = logInFrame;
		dbCon = new DBConnect();
		lUsersWindow = new ListUsersWindow(this);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton logOutBtn = new JButton("Log out");
		logOutBtn.setBounds(300, 202, 144, 70);
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				logInFrame.getFrame().setVisible(true);
			}
		});
		frame.getContentPane().setLayout(null);
		
		JButton checkInBtn = new JButton("Check in");
		checkInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbCon.checkIn(currId);
				JOptionPane.showMessageDialog(null, "You just checked in!");
			}
		});
		checkInBtn.setBounds(300, 120, 144, 70);
		frame.getContentPane().add(checkInBtn);
		
		JLabel timeLbl = new JLabel(dateFormat.format(date));
		timeLbl.setBounds(306, 6, 138, 16);
		frame.getContentPane().add(timeLbl);
		
		JLabel lblAdminWindow = new JLabel("Admin window");
		lblAdminWindow.setBounds(170, 6, 93, 16);
		frame.getContentPane().add(lblAdminWindow);
		
		JButton regBtn = new JButton("Register new user");
		regBtn.setBounds(6, 202, 144, 70);
		regBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Input id");
				String name = JOptionPane.showInputDialog("Input name");
				String rank = JOptionPane.showInputDialog("Input rank");
				dbCon.addUser(id, name, rank);
			}
		});
		frame.getContentPane().add(regBtn);
		frame.getContentPane().add(logOutBtn);
		
		JButton btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.setBounds(154, 202, 144, 70);
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = JOptionPane.showInputDialog("Input ID");
				dbCon.deleteUser(id);
			}
		});
		frame.getContentPane().add(btnDeleteUser);
		
		JButton checkOutBtn = new JButton("Check out");
		checkOutBtn.setBounds(154, 120, 144, 70);
		frame.getContentPane().add(checkOutBtn);
		
		nameLbl = new JLabel("");
		nameLbl.setBounds(6, 6, 152, 16);
		frame.getContentPane().add(nameLbl);
		
		JButton btnListUsers = new JButton("List users");
		btnListUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				lUsersWindow.getFrame().setVisible(true);
			}
		});
		btnListUsers.setBounds(6, 120, 144, 70);
		frame.getContentPane().add(btnListUsers);
		
		
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public JLabel getNameLabel(){
		return nameLbl;
	}
	
	public void setCurrID(String id){
		currId = id;
	}

}
