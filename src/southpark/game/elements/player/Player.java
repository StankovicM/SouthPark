package southpark.game.elements.player;

import rafgfxlib.Util;
import southpark.game.Game;
import southpark.game.elements.GObject;

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

    public boolean heroMode = false;
    public boolean heroAnim = false;

    private static final long ANIM_TIME = 5500000000l;
    private long lastTime = System.nanoTime();
    private long now;
    private long time = 0;

    public double flightHeight = 370.0;
    public double flightSpeed = 4.0;
    public double flightAngle = 0.8;
    public double flightRotation = 0.02;
    public boolean inFlight = false;

    public boolean falling = false;

    public Color partCol = Color.YELLOW;

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
                game.genParticles(xPos, yPos, 10.0, 60, 100);
                if (heroMode)
                    partCol = new Color(130, 2, 104);
                else
                    partCol = Color.YELLOW;

                heroAnim = false;
                time = 0;
            }

            if (time >= 1500000000l && time <= 2000000000l)
                g.drawImage(heroMode ? hero_front : image_front, playerTransform, null);

            if (time >= 2500000000l && time <= 3000000000l)
                g.drawImage(heroMode ? image_front : hero_front, playerTransform, null);

            if (time >= 3500000000l && time <= 4000000000l)
                g.drawImage(heroMode ? hero_front : image_front, playerTransform, null);

            if (time >= 4250000000l && time <= 4500000000l)
                g.drawImage(heroMode ? image_front : hero_front, playerTransform, null);

            if (time >= 4500000000l && time <= 4750000000l)
                g.drawImage(heroMode ? hero_front : image_front, playerTransform, null);

            if (time >= 4750000000l && time <= 5000000000l)
                g.drawImage(heroMode ? image_front : hero_front, playerTransform, null);

            if (time >= 5000000000l && time <= 5100000000l)
                g.drawImage(heroMode ? hero_front : image_front, playerTransform, null);

            if (time >= 5100000000l && time <= 5200000000l)
                g.drawImage(heroMode ? image_front : hero_front, playerTransform, null);

            if (time >= 5200000000l && time <= 5300000000l)
                g.drawImage(heroMode ? hero_front : image_front, playerTransform, null);

            if (time >= 5300000000l && time <= 5400000000l)
                g.drawImage(heroMode ? image_front : hero_front, playerTransform, null);

            if (time >= 5400000000l)
                g.drawImage(heroMode ? hero_front : image_front, playerTransform, null);

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

        if (!inFlight) {
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
        } else {
            angle = angle - flightRotation;
            if (angle <= -Math.PI / 2.0 - flightAngle)
                angle = -Math.PI / 2.0 - flightAngle;
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
                xPos = APP_W - width / 2;
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

        if (!inFlight) {
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
        } else {
            angle += flightRotation;
            if (angle >= -Math.PI / 2.0 + flightAngle)
                angle = -Math.PI / 2.0 + flightAngle;
        }

        facing = Facing.RIGHT;
        if (!heroMode)
            image = image_right;
        else
            image = hero_right;

        if (game.world.section == game.world.currentMap.sections - 1) {
            if (xPos + width / 2 > APP_W) {
                xPos = APP_W - width / 2;
            }
        } else {
            if (xPos > APP_W) {
                ++game.world.section;
                xPos = width / 2;
            }
        }

    }

    public void idle() {

        if (inJump) {
            jump();
            return;
        }

        if (!heroMode)
            image = image_front;
        else
            image = hero_front;

    }

    public void jump() {

        facing = jumpDir;
        if (jumpDir == Facing.RIGHT) {
            xPos += curSpeed;

            if (game.world.section == game.world.currentMap.sections - 1) {
                if (xPos + width / 2 > APP_W) {
                    xPos = APP_W - width / 2;
                }
            } else {
                if (xPos > APP_W) {
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
                    xPos = APP_W - width / 2;
                }
            }
        }

        yPos -= curSpeed;
        curSpeed *= 0.97;
        if (yPos <= jumpHeight) {
            inJump = false;
            falling = true;
            curSpeed = 1.0;
        }
    }

    public void fly() {

        if (inJump) return;

        yPos -= flightSpeed;
        if (yPos <= flightHeight) {
            yPos = flightHeight;
        }

        curSpeed = 1.0;

        //TODO fly

    }

    public void pullDown() {

        if (yPos < game.world.groundLevel - height / 2) {
            yPos += curSpeed;
            curSpeed *= game.world.gravity;
            if (yPos >= game.world.groundLevel - height / 2) {
                curSpeed = jumpSpeed;
                falling = false;
                yPos = game.world.groundLevel - height / 2;
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
