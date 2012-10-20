
package repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.model.MakerState;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class DBConnector {
	
	private transient static Logger log = Logger.getLogger(DBConnector.class);

	public boolean userExists(String username, String password) {
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 
			connection = DriverManager.getConnection(
					"jdbc:mysql://tintin.cs.indiana.edu:8099/a8team7db","team7", "password01");
			
			PreparedStatement userSelect = connection.prepareStatement("select * from user where uname=? and password=?");
			userSelect.setString(1, username);
			userSelect.setString(2, password);
			ResultSet rs = userSelect.executeQuery();
			//HashMap<String, String> userMap = new HashMap<String, String>();
			if(rs.next()) {
				return true;
			}
/*			while(rs.next()) {
				username = rs.getString("uname");
				password = rs.getString("password");
				//userMap.pu
				log.info(username);
			}*/
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<MakerState> getSavedGames(String username, String password) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 
			connection = DriverManager.getConnection(
					"jdbc:mysql://tintin.cs.indiana.edu:8099/a8team7db","team7", "password01");
			
			PreparedStatement userSelect = connection.prepareStatement("select * from game where uname=? and password=?");
			userSelect.setString(1, username);
			userSelect.setString(2, password);
			ResultSet rs = userSelect.executeQuery();
			List<MakerState> savedGames = new ArrayList<MakerState>();
			while(rs.next()) {
				XStream reader = new XStream(new StaxDriver());
		        MakerState makerState = (MakerState) reader.fromXML(rs.getString("game"));
		        savedGames.add(makerState);
				//password = rs.getString("password");
				//userMap.pu
				log.info(username);
			}
			return savedGames;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}