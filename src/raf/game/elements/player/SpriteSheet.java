package raf.game.elements.player;

import rafgfxlib.Util;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;
    private int frameW, frameH;
    private int sheetW, sheetH;
    private int offsetX = 0, offsetY = 0;

    public SpriteSheet(String img, int cols, int rows) {

        sheet = Util.loadImage(img);
        if(sheet == null)
        {
            sheet = null;
            System.out.println("Error loading sprite sheet!");
            return;
        }

        sheetW = cols;
        sheetH = rows;
        frameW = sheet.getWidth() / sheetW;
        frameH = sheet.getHeight() / sheetH;

    }

    public void drawTo(Graphics g, int posX, int posY, int frameX, int frameY)
    {
        if(sheet == null) return;

        if(frameX < 0 || frameY < 0 || frameX >= sheetW || frameY >= sheetH) return;

        g.drawImage(sheet,
                posX - offsetX, posY - offsetY,
                posX - offsetX + frameW, posY - offsetY + frameH,
                frameX * frameW, frameY * frameH,
                frameX * frameW + frameW, frameY * frameH + frameH,
                null);
    }

    public void setOffsets(int x, int y) {

        offsetX = x;
        offsetY = y;

    }

    public void setOffsetX(int x) { offsetX = x; }

    public int getOffsetX() { return offsetX; }

    public void setOffsetY(int y) { offsetY = y; }

    public int getOffsetY() { return offsetY; }

    public int getColumnCount() { return sheetW; }

    public int getRowCount() { return sheetH; }

    public int getFrameWidth() { return frameW; }

    public int getFrameHeight() { return frameH; }

}
