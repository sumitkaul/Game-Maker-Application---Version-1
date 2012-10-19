package repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

public class DBConnector {
	
	private transient static Logger log = Logger.getLogger(DBConnector.class);

	public void connect() {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 
			connection = DriverManager.getConnection(
					"jdbc:mysql://tintin.cs.indiana.edu:8099/a8team7db","team7", "password01");
			
			PreparedStatement select = connection.prepareStatement("select * from user");
			ResultSet rs = select.executeQuery();
			while(rs.next()) {
				String username = rs.getString("uname");
				log.info(username);
			}
		
		} catch(Exception e) {
			
		}
	}
}
