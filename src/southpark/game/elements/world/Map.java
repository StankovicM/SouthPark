package southpark.game.elements.world;

import rafgfxlib.Util;

import java.awt.image.BufferedImage;

import static southpark.game.utils.Constants.*;

public class Map {

    private String path;

    public BufferedImage image;

    public int width;
    public int height;

    public int sections;

    public double groundLevel;

    public Map(String path, double groundLevel) {

        this.path = path;
        this.groundLevel = groundLevel;

    }

    public boolean load() {

        image = Util.loadImage(path);
        if (image == null) return false;

        width = image.getWidth();
        height = image.getHeight();

        sections = width / APP_W;

        return true;

    }

}
