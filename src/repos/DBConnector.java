
package repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.MakerState;

import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class DBConnector {
	
	private transient static Logger log = Logger.getLogger(DBConnector.class);
	private Connection connection;
	
	public DBConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://tintin.cs.indiana.edu:8099/a8team7db","team7", "password01");
		} catch (Exception e) {
			log.error(e);
		}
	}

	public boolean isUserExists(String username, String password) {
		try {
			PreparedStatement userSelect = connection.prepareStatement("select * from user where uname=? and password=?");
			userSelect.setString(1, username);
			userSelect.setString(2, password);
			ResultSet rs = userSelect.executeQuery();
			if(rs.next()) {
				log.info("User exists in DB");
				return true;
			}
			log.info("User not found in DB !");
		} catch(SQLException e) {
			log.error(e);
		}
		return false;
	}
	
	public List<MakerState> getSavedGames(String username, String password) {
		try {			
			PreparedStatement userSelect = connection.prepareStatement("select * from game where uname=? and password=?");
			userSelect.setString(1, username);
			userSelect.setString(2, password);
			ResultSet rs = userSelect.executeQuery();
			List<MakerState> savedGames = new ArrayList<MakerState>();
			log.info("Retreiving "+username+"'s saved games");
			while(rs.next()) {
				XStream reader = new XStream(new StaxDriver());
		        MakerState makerState = (MakerState) reader.fromXML(rs.getString("game"));
		        savedGames.add(makerState);
			}
			return savedGames;
		} catch(SQLException e) {
			log.error(e);
		}
		return null;
	}
	
	public static void main(String args[]) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 
			connection = DriverManager.getConnection(
					"jdbc:mysql://tintin.cs.indiana.edu:8099/a8team7db","team7", "password01");
			
			PreparedStatement userSelect = connection.prepareStatement("select * from user");
			ResultSet rs = userSelect.executeQuery();
			while(rs.next()) {
				String username = rs.getString("uname");
				String password = rs.getString("password");
				log.info("Username is "+username);
				log.info("Pwd is "+password);
			}
		} catch(Exception e) {
			log.error(e);
		}
	}
}