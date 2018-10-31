package southpark.game;

import rafgfxlib.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rafgfxlib.Util;
import southpark.game.elements.GObject;
import southpark.game.elements.particle.Particle;
import southpark.game.elements.player.Player;
import southpark.game.elements.vehicle.Bus;
import southpark.game.elements.world.World;
import southpark.game.gui.GUI;

import static southpark.game.utils.Constants.*;

public class Game extends GameFrame {

    public GUI gui;
    public World world;
    private Player player;
    public Bus bus;
    private Particle[] particles = new Particle[MAX_PARTICLES];

    public ArrayList<GObject> objects = new ArrayList<>();

    public BufferedImage crosshair;
    public int crosshairX = APP_W / 2;
    public int crosshairY = APP_H / 2;
    public int crosshairSize;

    public boolean paused = false;
    public boolean ready = true;
    public boolean running = false;
    public boolean drawCrosshair = false;

    public Game(String title, int sizeX, int sizeY) {
        super(title, sizeX, sizeY);
    }

    public void init() {

        gui = new GUI(this);

        world = new World();
        ready = world.load();

        player = new Player(this);
        ready = player.load();

        bus = new Bus(this);
        ready = bus.load();

        crosshair = Util.loadImage("src/southpark/game/assets/crosshair.png");
        if (crosshair == null) ready = false;
        crosshairSize = crosshair.getWidth();

        for (int i = 0; i < MAX_PARTICLES; ++i)
            particles[i] = new Particle();

        setUpdateRate(60);
        initGameWindow();
        //setLocation(SCR_W / 2 - APP_W / 2, SCR_H / 2 - APP_H / 2);

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

            bus.render();

            g.setColor(player.partCol);
            for(Particle p : particles) {
                if(p.life <= 0) continue;

                p.render(g);
            }

            if (drawCrosshair) {
                g.setColor(Color.YELLOW);
                g.drawImage(crosshair, crosshairX - crosshairSize / 2, crosshairY - crosshairSize / 2, null);
            }
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

            player.inFlight = false;
            if (player.heroMode && !player.heroAnim && isKeyDown(KeyEvent.VK_F)) {
                player.inFlight = true;
                player.fly();
            }

            if (!player.inJump && !player.inFlight)
               player.pullDown();

            if (player.facing == Player.Facing.NONE) {
                player.angle = -Math.PI / 2.0;
                player.idle();
            }

            //for (GObject o : objects)
            //    if (o.isFalling())
            //        o.pullDown();

            player.playerTransform.setToIdentity();
            player.playerTransform.translate(player.xPos , player.yPos);
            player.playerTransform.rotate(player.angle + Math.PI / 2.0);
            player.playerTransform.translate(-player.width / 2, -player.height / 2);

            for (Particle p : particles) {
                if (p.life <= 0) continue;

                p.update();
            }
        } else {

        }

    }

    public void genParticles(double xPos, double yPos, double radius, int life, int count) {

        for(Particle p : particles) {
            if(p.life <= 0) {
                p.life = (int)(Math.random() * life * 0.5) + life / 2;
                p.xPos = xPos;
                p.yPos = yPos;
                double angle = Math.random() * Math.PI * 2.0;
                double speed = Math.random() * radius;
                p.dX = (float)(Math.cos(angle) * speed);
                p.dY = (float)(Math.sin(angle) * speed);

                count--;
                if(count <= 0) return;
            }
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

        if (button == GFMouseButton.Right)
            drawCrosshair = drawCrosshair ? false : true;

    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {

    }

    @Override
    public void handleMouseMove(int x, int y) {

        crosshairX = x;
        crosshairY = y;

    }

    @Override
    public void handleKeyDown(int keyCode) {

        if (keyCode == KeyEvent.VK_ESCAPE)
            paused = paused == true ? false : true;

        if (!paused && !player.heroAnim && !player.inJump && !player.inFlight && !player.falling) {
            if (keyCode == KeyEvent.VK_SPACE) {
                player.jumpDir = player.facing;
                player.inJump = true;
                player.angle = -Math.PI / 2;
            }

            if (keyCode == KeyEvent.VK_H) {
                player.toggleHeroMode();
                genParticles(player.xPos, player.yPos, 10.0, 60, 100);
                if (!player.heroMode)
                    player.partCol = new Color(130, 2, 104);
                else
                    player.partCol = Color.YELLOW;
            }
        }

    }

    @Override
    public void handleKeyUp(int keyCode) {

    }
}
