package raf.game.elements.player;

import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private static final String PLAYER_PATH = "src/raf/game/assets/player.png";
    private static final String SHEET_PATH = "src/raf/game/assets/playersheet.png";

    public BufferedImage image;
    private SpriteSheet sheet;

    public int width;
    public int height;

    public int posX, posY;

    private int animationID = 0;
    private int animFrame = 0;
    private boolean animPlaying = false;
    private int frameInterval = 2;
    private int frameCountdown = 0;

    public Player() {

        image = Util.loadImage(PLAYER_PATH);
        sheet = new SpriteSheet(SHEET_PATH, 4, 1);
        width = image.getWidth();
        height = image.getHeight();
        posX = width;
        posY = height;

    }

    public void draw(Graphics g)
    {
        sheet.drawTo(g, posX, posY, animFrame, animationID);
    }

}
