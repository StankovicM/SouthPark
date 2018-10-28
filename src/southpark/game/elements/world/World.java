package southpark.game.elements.world;

import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

import static southpark.game.utils.Constants.*;

public class World {

    public BufferedImage image;

    public int width;
    public int height;

    public int sections;
    public int section = 0;

    public double groundLevel = 660.0;

    public double gravity = 0.95;

    public World() {  }

    public boolean load() {

        image = Util.loadImage(WORLD_PATH);
        if (image == null) return false;

        width = image.getWidth();
        height = image.getHeight();

        sections = width / SCR_W;

        return true;

    }

    public Image getCurrentImage() {

        return image.getSubimage(section * SCR_W, 0, SCR_W, SCR_H);

    }

}
