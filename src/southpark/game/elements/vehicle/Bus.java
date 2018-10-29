package southpark.game.elements.vehicle;

import rafgfxlib.Util;
import southpark.game.Game;

import java.awt.image.BufferedImage;

public class Bus {

    private static final String BUS_PATH = "src/southpark/game/assets/bus.png";

    public BufferedImage image;

    public int width;
    public int height;

    private Game game;

    public Bus(Game game) { this.game = game; }

    public boolean load() {

        image = Util.loadImage(BUS_PATH);
        if (image == null) return false;

        width = image.getWidth();
        height = image.getHeight();

        return true;

    }

    public void update() {



    }

    public void render() {



    }

}
