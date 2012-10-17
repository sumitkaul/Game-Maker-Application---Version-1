package repos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class SoundRepo {
	
	private Map<String, String> gameSounds;
    private static SoundRepo instance;

    public Map<String, String> getGameSounds() {
        return gameSounds;
    }

    private SoundRepo() {
        gameSounds = new HashMap<String, String>(4);
        gameSounds.put("Applause", "/sounds/applause.wav");
        gameSounds.put("Bounce", "/sounds/bounce.au");
        gameSounds.put("Brick", "/sounds/Brick.au");
        gameSounds.put("Gameover", "/sounds/smb_gameover.wav");
           }

    public static SoundRepo getInstance() {
        if (instance == null) {
            instance = new SoundRepo();
        }
        return instance;
    }

    public List<String> getSoundNameList() {
        return new LinkedList<String>(gameSounds.keySet());
    }

    public String getSoundPath(String clipName) {
        return gameSounds.get(clipName);
    }

}
