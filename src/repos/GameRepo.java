package repos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameRepo {

    private Map<String, String> gameSaves;
    private static GameRepo instance;

    public Map<String, String> getGameSaves() {
        return gameSaves;
    }

    private GameRepo() {
        gameSaves = new HashMap<String, String>(5);
        gameSaves.put("Game1", "/games/game1.xml");
        gameSaves.put("Game2", "/games/game2.xml");
    }

    public static GameRepo getInstance() {
        if (instance == null) {
            instance = new GameRepo();
        }
        return instance;
    }

    public List<String> getGameNameList() {
        return new LinkedList<String>(gameSaves.keySet());
    }

    public String getGamePath(String gameName) {
        return gameSaves.get(gameName);
    }
}
