
package repos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.model.Constants;
import main.view.GameBoard;

import org.apache.log4j.Logger;

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
	
	public List<String> getSavedGameNames() {
		List<String> gameNames = new ArrayList<String>();
		try {			
			PreparedStatement userSelect = connection.prepareStatement("select gamename from game where uname=?");
			userSelect.setString(1, Constants.USERNAME);
			ResultSet rs = userSelect.executeQuery();
			log.info("Retreiving "+Constants.USERNAME+"'s saved game names");
			while(rs.next()) {
		        String gamename = rs.getString("gamename");
		        gameNames.add(gamename);
			}
		} catch(SQLException e) {
			log.error(e);
		}
		return gameNames;
	}
	
	public String getSavedGame(String gamename) {
		String xml = null;
		try {			
			PreparedStatement userSelect = connection.prepareStatement("select * from game where uname=? and gamename=?");
			userSelect.setString(1, Constants.USERNAME);
			userSelect.setString(2, gamename);
			ResultSet rs = userSelect.executeQuery();
			log.info("Retreiving "+Constants.USERNAME+"'s saved game names");
			while(rs.next()) {
				 xml = rs.getString("game");
				 GameBoard.getGameBoard().setScore(rs.getInt("score"));
			}
		} catch(SQLException e) {
			log.error(e);
		}
		return xml;
	}
	
	public void saveGame(int score,String gamename, String xml) {
		try {			
			PreparedStatement insert = connection.prepareStatement("insert into game values(?,?,?,?)");
			insert.setString(1, Constants.USERNAME);
			insert.setInt(2, score);
			insert.setString(3, gamename);
			insert.setString(4, xml);
			insert.executeUpdate();
			log.info("Game saved to DB !");
		} catch(SQLException e) {
			log.error(e);
		}
	}
	
	public static void main(String args[]) {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 
			connection = DriverManager.getConnection(
					"jdbc:mysql://tintin.cs.indiana.edu:8099/a8team7db","team7", "password01");
			
			PreparedStatement userSelect = connection.prepareStatement("select * from game");
			//PreparedStatement delete = connection.prepareStatement("delete from game");
			//delete.executeUpdate();
			ResultSet rs = userSelect.executeQuery();
			while(rs.next()) {
				String username = rs.getString("uname");
				String gamename = rs.getString("gamename");
				String game = rs.getString("game");
				log.info("Username is "+username);
				log.info("gamename is "+gamename);
				log.info("game is "+game);
			}
		} catch(Exception e) {
			log.error(e);
		}
	}
}