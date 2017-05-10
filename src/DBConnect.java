
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DBConnect {

	private Connection con;
	private Statement stat;
	private ResultSet set;
	private Vector columnNamesVector = new Vector();
	private Vector dataVector = new Vector();

	public DBConnect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/checkInDB", "root", "");
			stat = con.createStatement();

		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}
	}

	public void getData(){
		try{
			String query = "select * from Employes";
			set = stat.executeQuery(query);

			System.out.println("Records from db");
			while(set.next()){
				String id = set.getString("ID");
				String name = set.getString("name");
				String rank = set.getString("rank");
				System.out.println(id + " " + name + " " + rank);
			}
		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}
	}

	public boolean logIn(int id){
		try{
			String query = "select id from Employes";
			set = stat.executeQuery(query);
			while(set.next()){
				int dbId = Integer.parseInt(set.getString("ID"));
				if(id == dbId){
					return true;
				}
			}
		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}

		return false;
	}

	public void addUser(String id, String name, String rank){
		try{

			PreparedStatement statement = con.prepareStatement("INSERT INTO Employes (ID, name, Rank) VALUES ( ?, ?, ?)");
			statement.setString(1, id);
			statement.setString(2, name);
			statement.setString(3, rank);
			statement.execute();

			//String query = "INSERT INTO Employes (ID, name, Rank) VALUES (" + id + ", " + name + ", " + rank + ")";
			//set = stat.executeQuery(query);
		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}
	}

	public String getRankFromId(int id){
		try{
			String query = "select * from Employes";
			set = stat.executeQuery(query);
			while(set.next()){
				int dbId = Integer.parseInt(set.getString("ID"));
				if(dbId == id){
					return set.getString("rank");
				}
			}
		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}

		return "";
	}

	public void deleteUser(String id){
		try{
			PreparedStatement statement = con.prepareStatement("DELETE FROM Employes WHERE ID='" + id + "'");
			statement.execute();

		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}
	}

	public void checkIn(String id){
		try{
			PreparedStatement statement = con.prepareStatement("INSERT INTO Time (E_ID, TIME_IN) VALUES (?, NOW())");
			statement.setString(1, id);
			statement.execute();
		}catch(Exception ex){
			System.out.println("ERROR: " + ex);
		}
	}

	public String getNameFromId(String id){
		String query = "SELECT * FROM Employes";
		try {
			set = stat.executeQuery(query);
			while(set.next()){
				if(id.equals(set.getString("ID"))){
					return set.getString("name");
				}
			}
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex);
		}
		return "";

	}

	@SuppressWarnings("unchecked")
	public void createTable(){
		ArrayList columnNames = new ArrayList();
		ArrayList data = new ArrayList();
		try {
			System.out.println("test");
			ResultSetMetaData md = set.getMetaData();
			System.out.println("test 2");
			int columns = md.getColumnCount();

			for(int i = 1; i <= columns; i++){
				columnNames.add(md.getColumnName(i));
			}

			while(set.next()){
				ArrayList row = new ArrayList(columns);
				for(int i = 1; i <= columns; i++){
					row.add(set.getObject(i));
				}
				data.add(row);
			}

		} catch (Exception ex) {

			System.out.println("ERROR:1 " + ex);

		}

		

		for (int i = 0; i < data.size(); i++)
		{
			ArrayList subArray = (ArrayList)data.get(i);
			Vector subVector = new Vector();
			for (int j = 0; j < subArray.size(); j++){
				subVector.add(subArray.get(j));
			}
			dataVector.add(subVector);
		}

		for (int i = 0; i < columnNames.size(); i++ ){
			columnNamesVector.add(columnNames.get(i));
		}



	}
	
	public Vector getColumnNamesVector(){
		return columnNamesVector;
	}
	
	public Vector getDataVector(){
		return dataVector;
	}


}
