package repos;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ImageRepo {

    private Map<String, String> gameImages;
    private static ImageRepo instance;

    public Map<String, String> getGameImages() {
        return gameImages;
    }

    private ImageRepo() {
        gameImages = new HashMap<String, String>(5);
        gameImages.put("Aliens", "/images/Aliens.png");
        gameImages.put("Bullet", "/images/Bullet.png");
        gameImages.put("Car1", "/images/Car_1.png");
        gameImages.put("Car2", "/images/Car_2.png");
        gameImages.put("Car3", "/images/Car_3.png");
        gameImages.put("Car4", "/images/Car_4.png");
        gameImages.put("Fly", "/images/Fly.png");
        gameImages.put("frog", "/images/Frog.png");
        gameImages.put("Frogger_Background", "/images/Frogger_Background.png");
        gameImages.put("Ghost", "/images/Ghost.png");
        gameImages.put("GhostBlue", "/images/Ghost_blue2.gif");
        gameImages.put("Pacman", "/images/Pacman.png");
        gameImages.put("Tanker", "/images/Tanker.png");
        gameImages.put("Tree", "/images/Tree.png");
        gameImages.put("Truck", "/images/Truck.png");
        gameImages.put("Turtles", "/images/Turtles.png");
        gameImages.put("verticalBar", "/images/VerticalBar.png");
        gameImages.put("weapon", "/images/Weapon.png");
        gameImages.put("Arrow", "/images/arrow.png");
        gameImages.put("balloonfighter", "/images/ballon-fighter.jpg");
        gameImages.put("brick", "/images/brick.png");
        gameImages.put("circleBlack", "/images/circleBlack.png");
        gameImages.put("paddle", "/images/paddle.png");
        gameImages.put("squareBlack", "/images/squareBlack.png");
        gameImages.put("triangleBlack", "/images/triangleBlack.png");



    }

    public static ImageRepo getInstance() {
        if (instance == null) {
            instance = new ImageRepo();
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
