package southpark.game.elements.player;

import southpark.game.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static southpark.game.utils.Constants.*;

public class Player {

    public Character character;
    public AffineTransform playerTransform;

    public Game game;

    public double xPos;
    public double yPos;

    public double speed = 3.0;
    public double jumpSpeed = 4.0;
    public double curSpeed = jumpSpeed;
    public double angle = -Math.PI / 2.0;
    private double rotationSpeed = 0.013;
    private double rotationDist = 0.20;

    public Facing facing = Facing.NONE;

    public double jumpHeight = 550.0;
    public Facing jumpDir = Facing.NONE;
    public boolean inJump = false;

    private boolean up = true;

    public boolean heroMode = false;
    public boolean heroAnim = false;

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

        character.render(g);

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
        character.setLeft();

        if (game.world.section == 0) {
            if (xPos - character.width / 2 < 0) {
                xPos = character.width / 2;
            }
        } else {
            if (xPos < 0) {
                --game.world.section;
                xPos = APP_W - character.width / 2;
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
        character.setRight();

        if (game.world.section == game.world.currentMap.sections - 1) {
            if (xPos + character.width / 2 > APP_W) {
                xPos = APP_W - character.width / 2;
            }
        } else {
            if (xPos > APP_W) {
                ++game.world.section;
                xPos = character.width / 2;
            }
        }

    }

    public void idle() {

        if (inJump) {
            jump();
            return;
        }

        character.setFront();

    }

    public void jump() {

        facing = jumpDir;
        if (jumpDir == Facing.RIGHT) {
            xPos += curSpeed;

            if (game.world.section == game.world.currentMap.sections - 1) {
                if (xPos + character.width / 2 > APP_W) {
                    xPos = APP_W - character.width / 2;
                }
            } else {
                if (xPos > APP_W) {
                    ++game.world.section;
                    xPos = character.width / 2;
                }
            }
        } else if (jumpDir == Facing.LEFT){
            xPos -= curSpeed;

            if (game.world.section == 0) {
                if (xPos - character.width / 2 < 0) {
                    xPos = character.width / 2;
                }
            } else {
                if (xPos < 0) {
                    --game.world.section;
                    xPos = APP_W - character.width / 2;
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

    }

    public void pullDown() {

        if (yPos < game.world.currentMap.groundLevel - character.height / 2) {
            yPos += curSpeed;
            curSpeed *= game.world.gravity;
            if (yPos >= game.world.currentMap.groundLevel - character.height / 2) {
                curSpeed = jumpSpeed;
                falling = false;
                yPos = game.world.currentMap.groundLevel - character.height / 2;
            }
        }

    }

    public boolean load() {
        character = new Character(this);
        if (!character.load()) return false;

        playerTransform = new AffineTransform();

        yPos = game.world.currentMap.groundLevel - character.height / 2;

        return true;

    }

    public void toggleHeroMode() {

        if (!heroAnim) {
            heroAnim = true;
            heroMode = heroMode ? false : true;
            character.lastTime = System.nanoTime();
        }

    }

    public enum Facing {LEFT, RIGHT, NONE}

}
