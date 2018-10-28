package southpark.game.elements.player;

import rafgfxlib.Util;
import southpark.game.Game;
import southpark.game.elements.world.World;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static southpark.game.utils.Constants.*;

public class Player {

    public String PLAYER_FRONT_PATH = "src/southpark/game/assets/cartman.png";
    public String PLAYER_LEFT_PATH = "src/southpark/game/assets/cartman_left.png";
    public String PLAYER_RIGHT_PATH = "src/southpark/game/assets/cartman_right.png";

    public static BufferedImage image_front;
    public static BufferedImage image_left;
    public static BufferedImage image_right;

    public AffineTransform playerTransform;

    public int width;
    public int height;

    private Game game;

    public double xPos;
    public double yPos;

    public double speed = 3.0;
    public double angle = -Math.PI / 2.0;
    private double rotatitionSpeed = 0.013;
    private double rotationDist = 0.20;

    public Facing facing = Facing.NONE;

    private boolean up = true;

    public Player(Game game) { this(game,50, 620.0); }

    public Player(Game game, double xPos, double yPos) {

        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void moveLeft() {

        xPos -= speed;
        if (facing == Facing.RIGHT) {
            angle = -Math.PI / 2.0;
            up = true;
        }

        if (up) {
            angle = angle + rotatitionSpeed;
            if (angle >= -Math.PI / 2.0 + rotationDist) {
                up = false;
            }
        } else {
            angle = angle - rotatitionSpeed;
            if (angle <= -Math.PI / 2.0) {
                up = true;
            }
        }
        facing = Facing.LEFT;

        if (game.world.section == 0) {
            if (xPos - width / 2 < 0) {
                xPos = width / 2;
            }
        } else {
            if (xPos < 0) {
                --game.world.section;
                xPos = SCR_W - width / 2;
            }
        }

    }

    public void moveRight() {

        xPos += speed;
        if (facing == Facing.LEFT) {
            angle = -Math.PI / 2.0;
            up = true;
        }

        if (up) {
            angle -= rotatitionSpeed;
            if (angle <= -Math.PI / 2.0 - rotationDist) {
                up = false;
            }
        } else {
            angle += rotatitionSpeed;
            if (angle >= -Math.PI / 2.0) {
                up = true;
            }
        }

        facing = Facing.RIGHT;

        if (game.world.section == game.world.sections - 1) {
            if (xPos + width / 2 > SCR_W) {
                xPos = SCR_W - width / 2;
            }
        } else {
            if (xPos > SCR_W) {
                ++game.world.section;
                xPos = width / 2;
            }
        }

    }

    public boolean load() {

        image_front = Util.loadImage(PLAYER_FRONT_PATH);
        if (image_front == null) return false;

        image_left = Util.loadImage(PLAYER_LEFT_PATH);
        if (image_left == null) return false;

        image_right = Util.loadImage(PLAYER_RIGHT_PATH);
        if (image_right == null) return false;

        playerTransform = new AffineTransform();

        width = image_front.getWidth();
        height = image_front.getHeight();

        return true;

    }

    public enum Facing {LEFT, RIGHT, NONE}

}
