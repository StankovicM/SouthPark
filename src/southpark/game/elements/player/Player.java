package southpark.game.elements.player;

import rafgfxlib.Util;
import southpark.game.Game;
import southpark.game.elements.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import static southpark.game.utils.Constants.*;

public class Player {

    public String PLAYER_FRONT_PATH = "src/southpark/game/assets/cartman_front.png";
    public String PLAYER_LEFT_PATH = "src/southpark/game/assets/cartman_left.png";
    public String PLAYER_RIGHT_PATH = "src/southpark/game/assets/cartman_right.png";
    public String PLAYER_HERO_FRONT = "src/southpark/game/assets/coon_front.png";
    public String PLAYER_HERO_LEFT = "src/southpark/game/assets/coon_left.png";
    public String PLAYER_HERO_RIGHT = "src/southpark/game/assets/coon_right.png";

    public static BufferedImage image_front;
    public static BufferedImage image_left;
    public static BufferedImage image_right;
    public static BufferedImage hero_front;
    public static BufferedImage hero_left;
    public static BufferedImage hero_right;
    public BufferedImage image;

    public AffineTransform playerTransform;

    public int width;
    public int height;

    private Game game;

    public double xPos;
    public double yPos;

    public double speed = 3.0;
    public double jumpSpeed = 4.0;
    public double curSpeed = jumpSpeed;
    public double angle = -Math.PI / 2.0;
    private double rotationSpeed = 0.013;
    private double rotationDist = 0.20;

    public Facing facing = Facing.NONE;

    public double jumpHeight = 570.0;
    public Facing jumpDir = Facing.NONE;
    public boolean inJump = false;

    private boolean up = true;
    private boolean upJump = true;

    public boolean heroMode = false;
    public boolean heroAnim = false;

    private static final long ANIM_TIME = 5000000000l;
    private long lastTime = System.nanoTime();
    private long now;
    private long time = 0;

    public Player(Game game) { this(game,50); }

    public Player(Game game, double xPos) {

        this.game = game;
        this.xPos = xPos;

    }

    public void render(Graphics2D g) {

        if (!heroAnim) {
            if (facing == Player.Facing.LEFT) {
                g.drawImage(image, playerTransform, null);
            } else if (facing == Player.Facing.RIGHT) {
                g.drawImage(image, playerTransform, null);
            } else {
                g.drawImage(image, playerTransform, null);
            }
        } else {
            now = System.nanoTime();
            time += now - lastTime;
            if (time >= ANIM_TIME) {
                heroAnim = false;
                time = 0;
            }

            //TODO hero animacija

            lastTime = now;
        }

    }

    public void moveLeft() {

        if (inJump) {
            jump();
            return;
        }

        xPos -= speed;
        if (facing == Facing.RIGHT) {
            angle = -Math.PI / 2.0;
            up = true;
        }

        if (up) {
            angle = angle + rotationSpeed;
            if (angle >= -Math.PI / 2.0 + rotationDist) {
                up = false;
            }
        } else {
            angle = angle - rotationSpeed;
            if (angle <= -Math.PI / 2.0) {
                up = true;
            }
        }

        facing = Facing.LEFT;

        if (!heroMode)
            image = image_left;
        else
            image = hero_left;

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

        if (inJump) {
            jump();
            return;
        }

        xPos += speed;
        if (facing == Facing.LEFT) {
            angle = -Math.PI / 2.0;
            up = true;
        }

        if (up) {
            angle -= rotationSpeed;
            if (angle <= -Math.PI / 2.0 - rotationDist) {
                up = false;
            }
        } else {
            angle += rotationSpeed;
            if (angle >= -Math.PI / 2.0) {
                up = true;
            }
        }

        facing = Facing.RIGHT;
        if (!heroMode)
            image = image_right;
        else
            image = hero_right;

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

    public void idle() {

        if (!heroMode)
            image = image_front;
        else
            image = hero_front;

        if (inJump)
            jump();

    }

    public void jump() {

        facing = jumpDir;
        if (jumpDir == Facing.RIGHT) {
            xPos += curSpeed;

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
        } else if (jumpDir == Facing.LEFT){
            xPos -= curSpeed;

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

        if (upJump) {
            yPos -= curSpeed;
            curSpeed *= 0.97;
            if (yPos <= jumpHeight)
                upJump = false;
        } else {
            yPos += curSpeed;
            curSpeed *= 1.04;
            if (yPos >= game.world.groundLevel - height / 2) {
                upJump = true;
                inJump = false;
                curSpeed = jumpSpeed;
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

        hero_front = Util.loadImage(PLAYER_HERO_FRONT);
        if (hero_front == null) return false;

        hero_left = Util.loadImage(PLAYER_HERO_LEFT);
        if (hero_left == null) return false;

        hero_right = Util.loadImage(PLAYER_HERO_RIGHT);
        if (hero_right == null) return false;

        playerTransform = new AffineTransform();

        width = image_front.getWidth();
        height = image_front.getHeight();
        yPos = game.world.groundLevel - height / 2;

        return true;

    }

    public void toggleHeroMode() {

        if (!heroAnim) {
            heroAnim = true;
            heroMode = heroMode ? false : true;
            lastTime = System.nanoTime();
        }

    }

    public enum Facing {LEFT, RIGHT, NONE}

}
