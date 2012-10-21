
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
	
	public List<String> getPreLoadedGameNames() {
		List<String> gameNames = new ArrayList<String>();
		try {			
			PreparedStatement gameSelect = connection.prepareStatement("select gamename from game where uname=?");
			gameSelect.setString(1, Constants.DEFAULT_USER);
			ResultSet rs = gameSelect.executeQuery();
			log.info("Retreiving "+Constants.DEFAULT_USER+"'s saved game names");
			while(rs.next()) {
		        String gamename = rs.getString("gamename");
		        gameNames.add(gamename);
			}
		} catch(SQLException e) {
			log.error(e);
		}
		return gameNames;
	}
	
	public String getPreLoadedGame(String gamename) {
		String xml = null;
		try {			
			PreparedStatement gameSelect = connection.prepareStatement("select * from game where uname=? and gamename=?");
			gameSelect.setString(1, Constants.DEFAULT_USER);
			gameSelect.setString(2, gamename);
			ResultSet rs = gameSelect.executeQuery();
			log.info("Retreiving "+Constants.DEFAULT_USER+"'s saved game "+gamename);
			while(rs.next()) {
				 xml = rs.getString("game");
				 GameBoard.getGameBoard().setScore(rs.getInt("score"));
			}
		} catch(SQLException e) {
			log.error(e);
		}
		return xml;
	}
	
	public List<String> getSavedGameNames() {
		List<String> gameNames = new ArrayList<String>();
		try {			
			PreparedStatement gameSelect = connection.prepareStatement("select gamename from game where uname=?");
			gameSelect.setString(1, Constants.USERNAME);
			ResultSet rs = gameSelect.executeQuery();
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
			PreparedStatement gameSelect = connection.prepareStatement("select * from game where uname=? and gamename=?");
			gameSelect.setString(1, Constants.USERNAME);
			gameSelect.setString(2, gamename);
			ResultSet rs = gameSelect.executeQuery();
			log.info("Retreiving "+Constants.USERNAME+"'s saved game "+gamename);
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
			
			/*PreparedStatement insert1 = connection.prepareStatement("insert into user values(?,?,?,?)");
			insert1.setString(1, "default");
			insert1.setString(2, "default");
			insert1.setString(3, "default@example.com");
			insert1.setString(4, "default");
			insert1.executeUpdate();
			*/
			
/*			PreparedStatement insert = connection.prepareStatement("insert into game values(?,?,?,?)");
			insert.setString(1, Constants.DEFAULT_USER);
			insert.setInt(2, 0);
			insert.setString(3, "Frogger");
			insert.setString(4, "<?xml version=\"1.0\" ?><main.controller.MakerState><windowSize><width>850</width><height>750</height></windowSize><compositeClass><childObjects class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>10</int><main.model.Drawable><id>1</id><name>Frog</name><x>175</x><y>667</y><vx>0</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>43</width><height>40</height><imageFilePath>images/Frog.png</imageFilePath><events><entry><main.utilities.Event>COLLISION</main.utilities.Event><list><main.utilities.ActionObjectPair><action>STICK</action><gameObjectName>Log 1</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>STICK</action><gameObjectName>Log 2</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>STICK</action><gameObjectName>Log 3</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>STICK</action><gameObjectName>Log 4</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>GAME_OVER</action><gameObjectName>Car 1</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>GAME_OVER</action><gameObjectName>Car 2</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>GAME_OVER</action><gameObjectName>Car 3</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>GAME_OVER</action><gameObjectName>Car 4</gameObjectName></main.utilities.ActionObjectPair><main.utilities.ActionObjectPair><action>GAME_WIN</action><gameObjectName>Fly</gameObjectName></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.NullMoveAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions><main.actions.MoveUpKeyAction><moveAmount>-1</moveAmount><keyCode>38</keyCode></main.actions.MoveUpKeyAction><main.actions.MoveDownKeyAction><moveAmount>1</moveAmount><keyCode>40</keyCode></main.actions.MoveDownKeyAction><main.actions.MoveLeftKeyAction><moveAmount>-1</moveAmount><keyCode>37</keyCode></main.actions.MoveLeftKeyAction><main.actions.MoveRightKeyAction><moveAmount>1</moveAmount><keyCode>39</keyCode></main.actions.MoveRightKeyAction></keyActions><childrenList></childrenList><keycode>0</keycode><type>Frog</type></main.model.Drawable><main.model.Drawable><id>2</id><name>Log 1</name><x>275</x><y>204</y><vx>1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>127</width><height>40</height><imageFilePath>images/Tree.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Log</type></main.model.Drawable><main.model.Drawable><id>3</id><name>Log 2</name><x>77</x><y>206</y><vx>1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>127</width><height>40</height><imageFilePath>images/Tree.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Log</type></main.model.Drawable><main.model.Drawable><id>4</id><name>Log 3</name><x>245</x><y>298</y><vx>1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>127</width><height>40</height><imageFilePath>images/Tree.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Log</type></main.model.Drawable><main.model.Drawable><id>5</id><name>Log 4</name><x>35</x><y>299</y><vx>1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>127</width><height>40</height><imageFilePath>images/Tree.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Log</type></main.model.Drawable><main.model.Drawable><id>6</id><name>Car 1</name><x>271</x><y>485</y><vx>-1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>39</width><height>39</height><imageFilePath>images/Car_1.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Car</type></main.model.Drawable><main.model.Drawable><id>7</id><name>Car 2</name><x>18</x><y>484</y><vx>-1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>39</width><height>39</height><imageFilePath>images/Car_1.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Car</type></main.model.Drawable><main.model.Drawable><id>8</id><name>Car 3</name><x>334</x><y>581</y><vx>1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>39</width><height>39</height><imageFilePath>images/Car_2.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Car</type></main.model.Drawable><main.model.Drawable><id>9</id><name>Car 4</name><x>86</x><y>581</y><vx>1</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>39</width><height>39</height><imageFilePath>images/Car_2.png</imageFilePath><events><entry><main.utilities.Event>MOVE</main.utilities.Event><list><main.utilities.ActionObjectPair><action>MOVE_AROUND</action></main.utilities.ActionObjectPair></list></entry></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.MoveAroundAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Car</type></main.model.Drawable><main.model.Drawable><id>10</id><name>Fly</name><x>193</x><y>97</y><vx>0</vx><vy>0</vy><ax>0</ax><ay>0</ay><heading>0</heading><aHeading>0</aHeading><vHeading>0</vHeading><width>38</width><height>40</height><imageFilePath>images/Fly.png</imageFilePath><events></events><actions></actions><actionsMap></actionsMap><moveAction class=\"main.actions.NullMoveAction\"></moveAction><pressedKey class=\"java.util.concurrent.CopyOnWriteArrayList\" serialization=\"custom\"><java.util.concurrent.CopyOnWriteArrayList><default></default><int>0</int></java.util.concurrent.CopyOnWriteArrayList></pressedKey><keyActions></keyActions><childrenList></childrenList><keycode>0</keycode><type>Fly</type></main.model.Drawable></java.util.concurrent.CopyOnWriteArrayList></childObjects><selectedObject class=\"main.model.Drawable\" reference=\"../childObjects/java.util.concurrent.CopyOnWriteArrayList/main.model.Drawable\"></selectedObject></compositeClass><backgroundImagePath>images/Frogger_Background.png</backgroundImagePath><filename>Frogger.xml</filename></main.controller.MakerState>");
			insert.executeUpdate();*/
			
			
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