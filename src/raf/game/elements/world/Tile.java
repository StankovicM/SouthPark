package raf.game.elements.world;

import rafgfxlib.Util;

import java.awt.image.BufferedImage;

public class Tile {

    public static final int TILE_W = 300;
    public static final int TILE_H = 300;

    public BufferedImage image;

    public int offsetX = 0;
    public int offsetY = 0;

    public Tile(String img) {

        image = Util.loadImage(img);

        if(image != null) {
            offsetX = 0;
            offsetY = -(image.getHeight() - TILE_H);
        } else  {
            System.out.println("Fail at \"" + img + "\"");
        }

    }

}
