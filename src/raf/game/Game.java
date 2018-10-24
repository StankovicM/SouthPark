package raf.game;

import raf.game.elements.player.Player;
import raf.game.elements.world.Tile;
import raf.game.elements.world.World;
import rafgfxlib.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class Game extends GameFrame {

    private static Game instance = null;

    private static World world;
    private static Player player;

    private int camX = 0;
    private int camY = 0;

    private int camSpeed = 10;

    private double playerX;
    private double playerY;
    private double playerAngle = 0.0;
    private double playerSpeed = 2.0;

    private AffineTransform playerTransform = new AffineTransform();

    private Game(String title, int sizeX, int sizeY) { super(title, sizeX, sizeY);  }

    public static Game getInstance() { return instance; }

    public static Game getInstance(String title, int sizeX, int sizeY) {

        if (instance == null)
            instance = new Game(title, sizeX, sizeY);

        return instance;

    }

    public void initGame() {

        world = new World();
        world.load();

        player = new Player();
        playerX = player.width;
        playerY = player.height;

        setUpdateRate(60);
        initGameWindow();

    }

    public void startGame() {

        startThread();

    }

    @Override
    public void render(Graphics2D g, int sw, int sh) {

        g.drawImage(player.image, playerTransform, null);

        /*int x0 = camX / Tile.TILE_W;
        int x1 = x0 + (getWidth() / Tile.TILE_W) + 1;
        int y0 = camY / Tile.TILE_H;
        int y1 = y0 + (getHeight() / Tile.TILE_H) + 1;

        if(x0 < 0) x0 = 0;
        if(y0 < 0) y0 = 0;
        if(x1 < 0) x1 = 0;
        if(y1 < 0) y1 = 0;

        if(x0 >= world.width) x0 = world.width - 1;
        if(y0 >= world.height) y0 = world.height - 1;
        if(x1 >= world.width) x1 = world.width - 1;
        if(y1 >= world.height) y1 = world.height - 1;

        for(int y = y0; y <= y1; ++y) {
            for(int x = x0; x <= x1; ++x) {
                System.out.println(world.tilemap[x][y]);
                g.drawImage(world.tileSet.get(world.tilemap[x][y]).image,
                        x * Tile.TILE_W + world.tileSet.get(world.tilemap[x][y]).offsetX - camX,
                        y * Tile.TILE_H + world.tileSet.get(world.tilemap[x][y]).offsetY - camY,
                        null);
            }
        }*/

    }

    @Override
    public void update() {

        if(isKeyDown(KeyEvent.VK_A)) {
            camX -= camSpeed;
            playerAngle = Math.PI;
            player.posX += Math.cos(playerAngle) * playerSpeed;
        }

        if(isKeyDown(KeyEvent.VK_D)) {
            camX += camSpeed;
            playerAngle = 0.0;
            player.posX += Math.cos(playerAngle) * playerSpeed;
        }

        if(isKeyDown(KeyEvent.VK_W)) {
            camY -= camSpeed;
            if (isKeyDown(KeyEvent.VK_A)) {
                playerAngle = Math.PI * 5 / 4;
            } else if (isKeyDown(KeyEvent.VK_D)) {
                playerAngle = Math.PI * 7 / 4;
            } else {
                playerAngle = Math.PI * 3 / 2;
            }
            player.posY += Math.sin(playerAngle) * playerSpeed;
        }

        if(isKeyDown(KeyEvent.VK_S)) {
            camY += camSpeed;
            if (isKeyDown(KeyEvent.VK_A)) {
                playerAngle = Math.PI * 3 / 4;
            } else if (isKeyDown(KeyEvent.VK_D)) {
                playerAngle = Math.PI / 4;
            } else {
                playerAngle = Math.PI / 2;
            }
            player.posY += Math.sin(playerAngle) * playerSpeed;
        }

        playerTransform.setToIdentity();
        playerTransform.translate(player.posX, player.posY);
        playerTransform.rotate(playerAngle + Math.PI / 2.0);
        playerTransform.translate(-player.width / 2, -player.height / 2);

        //camX = camX < 0 ? 0 : (camX > world.width - Tile.TILE_W - 1 ? world.width - Tile.TILE_W - 1 : camX);
        //camY = camY < 0 ? 0 : (camY > world.height - Tile.TILE_H - 1 ? world.height - Tile.TILE_H - 1 : camY);

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

/*int x0 = camX / Tile.TILE_W;
        int x1 = x0 + (getWidth() / Tile.TILE_W) + 1;
        int y0 = camY / Tile.TILE_H;
        int y1 = y0 + (getHeight() / Tile.TILE_H) + 1;

        if(x0 < 0) x0 = 0;
        if(y0 < 0) y0 = 0;
        if(x1 < 0) x1 = 0;
        if(y1 < 0) y1 = 0;

        if(x0 >= world.width) x0 = world.width - 1;
        if(y0 >= world.height) y0 = world.height - 1;
        if(x1 >= world.width) x1 = world.width - 1;
        if(y1 >= world.height) y1 = world.height - 1;

        for(int y = y0; y <= y1; ++y) {
            for(int x = x0; x <= x1; ++x) {
                System.out.println(world.tilemap[x][y]);
                g.drawImage(world.tileSet.get(world.tilemap[x][y]).image,
                        x * Tile.TILE_W + world.tileSet.get(world.tilemap[x][y]).offsetX - camX,
                        y * Tile.TILE_H + world.tileSet.get(world.tilemap[x][y]).offsetY - camY,
                        null);
            }
        }*/