package southpark.game;

import rafgfxlib.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

import southpark.game.elements.player.Player;
import southpark.game.elements.world.World;

import static southpark.game.utils.Constants.*;

public class Game extends GameFrame {

    public World world;
    private Player player;

    private boolean paused = false;

    public boolean ready = true;

    public Game(String title, int sizeX, int sizeY) {
        super(title, sizeX, sizeY);
    }

    public void init() {

        world = new World();
        ready = world.load();

        player = new Player(this);
        ready = player.load();

        setUpdateRate(60);
        initGameWindow();

    }

    public void startGame() {

        if (ready) {
            startThread();
        } else {
            System.err.println("Game failed to start!");
        }

    }

    @Override
    public void render(Graphics2D g, int sw, int sh) {

        if (!paused) {
            g.drawImage(world.getCurrentImage(), 0, 0, null);

            if (player.facing == Player.Facing.LEFT) {
                g.drawImage(player.image_left, player.playerTransform, null);
            } else if (player.facing == Player.Facing.RIGHT){
                g.drawImage(player.image_right, player.playerTransform, null);
            } else {
                g.drawImage(player.image_front, player.playerTransform, null);
            }
        } else {

        }

    }

    @Override
    public void update() {

        if (!paused) {
            player.facing = Player.Facing.NONE;
            if (isKeyDown(KeyEvent.VK_A)) {
                player.moveLeft();
            }
            if (isKeyDown(KeyEvent.VK_D)) {
                player.moveRight();
            }

            if (player.facing == Player.Facing.NONE) {
                player.angle = -Math.PI / 2.0;
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

    }

    @Override
    public void handleKeyUp(int keyCode) {

    }
}
