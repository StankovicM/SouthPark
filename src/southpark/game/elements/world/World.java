package southpark.game.elements.world;

import java.awt.*;
import java.util.HashMap;

import static southpark.game.utils.Constants.*;

public class World {

    private static final String TOWN_01_PATH = "src/southpark/game/assets/town.png";
    private static final String SCHOOL_PATH = "src/southpark/game/assets/school.png";

    private Map town_01;
    private Map school;

    public Map currentMap;

    private HashMap<String, Map> maps = new HashMap<>();

    public int section = 0;

    public double gravity = 1.17;

    public World() {  }

    public boolean load() {

        town_01 = new Map(TOWN_01_PATH, 660.0);
        if (!town_01.load()) return false;
        maps.put("town_01", town_01);

        school = new Map(SCHOOL_PATH, 700.0);
        if(!school.load()) return false;
        maps.put("school", school);

        currentMap = town_01;

        return true;

    }

    public void switchMap(String mapId) {

        currentMap = maps.get(mapId);
        section = 0;

    }

    public Image getCurrentImage() {

        return currentMap.image.getSubimage(section * APP_W, 0, APP_W, APP_H);

    }

}
