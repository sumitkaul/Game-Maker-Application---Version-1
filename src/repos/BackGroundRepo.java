package repos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class BackGroundRepo {
	
	private Map<String, String> gameImages;
    private static BackGroundRepo instance;

    public Map<String, String> getGameImages() {
        return gameImages;
    }

    private BackGroundRepo() {
        gameImages = new HashMap<String, String>(5);
        gameImages.put("Boston", "/BackGround/Boston.jpg");
        gameImages.put("clouds", "/BackGround/clouds-land.png");
        gameImages.put("darkknight", "/BackGround/DarkNight.jpg");
        gameImages.put("Frogger", "/BackGround/Frogger Background.png");
        gameImages.put("Pp", "/BackGround/Pp.jpg");
        gameImages.put("Sadak", "/BackGround/sadak.jpg");
        }

    public static BackGroundRepo getInstance() {
        if (instance == null) {
            instance = new BackGroundRepo();
        }
        return instance;
    }

    public List<String> getImageNameList() {
        return new LinkedList<String>(gameImages.keySet());
    }

    public String getImagePath(String imgName) {
        return gameImages.get(imgName);
    }


}
