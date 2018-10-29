package southpark.game;

import rafgfxlib.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;

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

        bus = new Bus(this);
        ready = bus.load();

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

            g.setColor(Color.YELLOW);
            for(Particle p : particles) {
                if(p.life <= 0) continue;

                p.render(g);
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

            if (player.heroMode && isKeyDown(KeyEvent.VK_F)) {
                player.fly();
            }

            if (player.facing == Player.Facing.NONE) {
                player.angle = -Math.PI / 2.0;
                player.idle();
            }

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

        if (!paused && !player.heroAnim && !player.inJump && !player.inFlight) {
            if (keyCode == KeyEvent.VK_SPACE) {
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
