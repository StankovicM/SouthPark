package southpark.game;

import rafgfxlib.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

import southpark.game.elements.player.Player;
import southpark.game.elements.world.World;
import southpark.game.gui.GUI;

import static southpark.game.utils.Constants.*;

public class Game extends GameFrame {

    public GUI gui;
    public World world;
    private Player player;

    private boolean paused = false;

    public boolean ready = true;

    public boolean running = false;

    public Game(String title, int sizeX, int sizeY) {
        super(title, sizeX, sizeY);
    }

    public void init() {

        gui = new GUI(this);

        world = new World();
        ready = world.load();

        player = new Player(this);
        ready = player.load();

        setUpdateRate(60);
        initGameWindow();

    }

    public void startGame() {

        if (ready) {
            running = true;
            startThread();
        } else {
            System.err.println("Game failed to start!");
        }

    }

    @Override
    public void render(Graphics2D g, int sw, int sh) {

        if (!paused) {
            g.drawImage(world.getCurrentImage(), 0, 0, null);

            player.render(g);
        } else {
            gui.draw(g);
        }

    }

    @Override
    public void update() {

        if (!paused) {
            player.facing = Player.Facing.NONE;
            if (isKeyDown(KeyEvent.VK_A) && !player.heroAnim) {
                player.moveLeft();
            }
            if (isKeyDown(KeyEvent.VK_D) && !player.heroAnim) {
                player.moveRight();
            }

            if (player.facing == Player.Facing.NONE) {
                player.angle = -Math.PI / 2.0;
                player.idle();
            }

            player.playerTransform.setToIdentity();
            player.playerTransform.translate(player.xPos , player.yPos);
            player.playerTransform.rotate(player.angle + Math.PI / 2.0);
            player.playerTransform.translate(-player.width / 2, -player.height / 2);
        } else {

        }

    }

    @Override
    public void handleWindowInit() {

    }

    @Override
    public void handleWindowDestroy() {

    }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseMove(int x, int y) {

    }

    @Override
    public void handleKeyDown(int keyCode) {

        if (keyCode == KeyEvent.VK_ESCAPE)
            paused = paused == true ? false : true;

        if (!paused && !player.heroAnim) {
            if (keyCode == KeyEvent.VK_SPACE && !player.inJump) {
                player.jumpDir = player.facing;
                player.inJump = true;
                player.angle = -Math.PI / 2;
            }

            if (keyCode == KeyEvent.VK_H) {
                player.toggleHeroMode();
            }
        }

    }

    @Override
    public void handleKeyUp(int keyCode) {

    }
}
