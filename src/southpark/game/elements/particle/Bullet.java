package southpark.game.elements.particle;

import rafgfxlib.Util;
import southpark.game.Game;
import southpark.game.elements.player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import static southpark.game.utils.Constants.*;

public class Bullet {

    private Game game;

    public double xPos;
    public double yPos;

    public double speed = 8.0;

    public boolean isAlive = false;

    private static int[][] lookupTableX = new int[256][256];
    private static int[][] lookupTableY = new int[256][256];

    private int distX;
    private int distY;

    public Player.Facing dir;

    static {

        BufferedImage imgLookup = Util.loadImage("src/southpark/game/assets/whirl.png");
        WritableRaster lookupRaster = imgLookup.getRaster();
        int[] rgb = new int[3];
        for(int y = 0; y < 256; y++) {
            for(int x = 0; x < 256; ++x) {
                lookupRaster.getPixel(x, y, rgb);
                lookupTableX[y][x] = rgb[0];
                lookupTableY[y][x] = rgb[1];
            }
        }

    }

    public Bullet(Game game) { this.game = game; }

    public void render(Graphics2D g) {

        drawBlur(g);
        g.setColor(Color.YELLOW);
        g.fillOval((int)xPos, (int)yPos, 10, 10);

    }

    public void update() {

        if (dir == Player.Facing.LEFT)
            xPos -= speed;
        else if (dir == Player.Facing.RIGHT)
            xPos += speed;
        else
            isAlive = false;

        if (xPos > APP_W - 1)
            isAlive = false;

        if (xPos < 0)
            isAlive = false;

        distX = (int)xPos - 128;
        distY = (int)yPos - 128;

    }

    private void drawBlur(Graphics2D g) {

        WritableRaster raster = ((BufferedImage)game.world.getCurrentImage()).getRaster();
        BufferedImage imgDistort = new BufferedImage(256, 256, BufferedImage.TYPE_3BYTE_BGR);
        int rgba[] = new int[4];
        for(int y = 0; y < 256; y++) {
            if(distY + y < 0) continue;
            if(distY + y > 719) continue;

            for(int x = 0; x < 256; ++x) {
                if(distX + x < 0) continue;
                if(distX + x > 1279) continue;

                int sx = distX + lookupTableX[y][x];
                int sy = distY + lookupTableY[y][x];
                if(sx < 0) sx = 0;
                if(sy < 0) sy = 0;
                if(sx > 1279) sx = 1279;
                if(sy > 719) sy = 719;

                raster.getPixel(sx, sy, rgba);
                imgDistort.setRGB(x, y, (rgba[0] << 16) | (rgba[1] << 8) | (rgba[2]));
            }
        }

        g.drawImage(imgDistort, distX, distY, null);

    }

}
