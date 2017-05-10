import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

public class ListUsersWindow {

	private JFrame frame;
	private JTable userTable;
	private AdminWindow aWindow;
	private DBConnect dbCon;
	private Vector columnNames;
	private Vector dataVector;

	/**
	 * Create the application.
	 */
	public ListUsersWindow(AdminWindow aWindow) {
		initialize();
		this.aWindow = aWindow;
		dbCon = new DBConnect();
		dbCon.createTable();
		columnNames = dbCon.getColumnNamesVector();
		dataVector = dbCon.getDataVector();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		userTable = new JTable(dataVector, columnNames){
			public Class getColumnClass(int column){
				for(int row = 0; row<getRowCount();row++){
					Object o = getValueAt(row, column);

                    if (o != null){
                        return o.getClass();
                    }
				}
				return Object.class;
			}
		};
		userTable.setBounds(6, 85, 438, 187);
		frame.getContentPane().add(userTable);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				aWindow.getFrame().setVisible(true);
			}
		});
		backBtn.setBounds(0, 6, 117, 67);
		frame.getContentPane().add(backBtn);
	}
	
	public JFrame getFrame(){
		return frame;
	}
}
