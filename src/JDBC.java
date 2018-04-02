import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBC {
	Connection conn;
	private static final String selectUserName = "SELECT * FROM USERS WHERE USERNAME=?";
	
	public JDBC () {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connectionString = "jdbc:mysql://localhost:3306/CollageMaker?user=root&password=password&useSSL=true";
			conn = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public int checkUserCredentials(String userName, String password) {
		PreparedStatement ps;
		try {
			
			ps = conn.prepareStatement(selectUserName);
			ps.setString(1, userName);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				if(result.getString(3).equals(password)) {
					
					return 0;
				}
				else {
					System.out.println(result.getString(2));
					return 1;
				}
			}
			return 2;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;
		
	}
}
