package southpark.game;

import rafgfxlib.GameFrame;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import rafgfxlib.Util;
import southpark.game.elements.GObject;
import southpark.game.elements.particle.Bullet;
import southpark.game.elements.particle.Particle;
import southpark.game.elements.player.Player;
import southpark.game.elements.vehicle.Bus;
import southpark.game.elements.world.World;
import southpark.game.gui.GUI;
import southpark.game.gui.characterselection.CharacterSelection;

import static southpark.game.utils.Constants.*;

public class Game extends GameFrame {

    public GUI gui;
    public World world;
    public Player player;
    public Bus bus;
    private Particle[] particles = new Particle[MAX_PARTICLES];

    public ArrayList<GObject> objects = new ArrayList<>();

    public CharacterSelection cs;

    public BufferedImage crosshair;
    public int crosshairX = APP_W / 2;
    public int crosshairY = APP_H / 2;
    public int crosshairSize;

    public boolean paused = true; //TODO postaviti na true na kraju, da bi se prvo otvorio meni
    public boolean ready = true;
    public boolean running = false;
    public boolean drawCrosshair = false;

    int distX;
    int distY;

    private boolean transitionAnim = false;
    private float position = 0.0f;
    private float transSpeed = 0.02f;

    private Bullet[] bullets = new Bullet[MAX_BULLETS];

    public Game(String title, int sizeX, int sizeY) {
        super(title, sizeX, sizeY);
    }

    public void init() {

        world = new World();
        ready = world.load();

        player = new Player(this);
        ready = player.load();

        gui = new GUI(this);
        ready = gui.load();

        bus = new Bus(this);
        ready = bus.load();

        crosshair = Util.loadImage("src/southpark/game/assets/crosshair.png");
        if (crosshair == null) ready = false;
        crosshairSize = crosshair.getWidth();

        for (int i = 0; i < MAX_PARTICLES; ++i)
            particles[i] = new Particle();

        for (int i = 0; i < MAX_BULLETS; ++i)
            bullets[i] = new Bullet(this);

        setUpdateRate(60);
        initGameWindow();

    }

    public void startGame() {

        if (ready) {
            player.character.mergeChar();
            running = true;
            paused = false;
            transitionAnim = true;
        } else {
            System.err.println("Game failed to start!");
        }

    }

    public void stopGame() {

        running = false;
        paused = false;

        getWindow().dispose();

    }

    @Override
    public void render(Graphics2D g, int sw, int sh) {

        if (!paused) {
            if (running) {
                if (!transitionAnim) {
                    g.drawImage(world.getCurrentImage(), 0, 0, null);

                    for (Bullet b : bullets) {
                        if (!b.isAlive) continue;

                        b.render(g);
                    }

                    player.render(g);

                    bus.render();

                    g.setColor(player.partCol);
                    for (Particle p : particles) {
                        if (p.life <= 0) continue;

                        p.render(g);
                    }

                    if (drawCrosshair) {
                        g.setColor(Color.YELLOW);
                        g.drawImage(crosshair, crosshairX - crosshairSize / 2, crosshairY - crosshairSize / 2, null);
                    }
                } else {
                    g.drawImage(gui.cs.getBackground(),
                            (int)(0 - position * (APP_W)),
                            (int)(position * APP_H * 0.25f),
                            (int)(APP_W * (1.0f - position * 0.5f)),
                            (int)(APP_H * (1.0f - position * 0.5f)),
                            null);

                    g.drawImage(world.getCurrentImage(),
                            (int)(APP_W - position * (APP_W)),
                            (int)((1.0f - position) * APP_H * 0.25f),
                            (int)(APP_W * (0.5f + position * 0.5f)),
                            (int)(APP_H * (0.5f + position * 0.5f)),
                            null);
                }
            }
        } else {
            gui.render(g);
        }

    }

    @Override
    public void update() {

        if (!paused) {
            if (running) {
                if (!transitionAnim) {
                    distX = getMouseX() - 128;
                    distY = getMouseY() - 128;

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

                    player.playerTransform.setToIdentity();
                    player.playerTransform.translate(player.xPos, player.yPos);
                    player.playerTransform.rotate(player.angle + Math.PI / 2.0);
                    player.playerTransform.translate(-player.character.width / 2, -player.character.height / 2);

                    player.character.update();

                    for (Particle p : particles) {
                        if (p.life <= 0) continue;

                        p.update();
                    }

                    for (Bullet b : bullets) {
                        if (!b.isAlive) continue;

                        b.update();
                    }
                } else {
                    position += transSpeed;

                    if(position >= 1.0f) {
                        position = 1.0f;
                        transitionAnim = false;
                    }
                }
            }
        } else {
            gui.update();
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
    public void handleWindowInit() {  }

    @Override
    public void handleWindowDestroy() {  }

    @Override
    public void handleMouseDown(int x, int y, GFMouseButton button) {

        if (button == GFMouseButton.Right)
            drawCrosshair = drawCrosshair ? false : true;

        if (button == GFMouseButton.Left) {
            if (!running || paused) {
                gui.notifyButtons();
            } else {
                if (player.heroMode && player.facing != Player.Facing.NONE) {
                    for (Bullet b : bullets) {
                        if (!b.isAlive) {
                            b.xPos = player.xPos + player.character.width / 2;
                            if (b.xPos > APP_W - 1) b.xPos = APP_W - 1;
                            b.yPos = player.yPos;
                            b.isAlive = true;
                            b.dir = player.facing;
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void handleMouseUp(int x, int y, GFMouseButton button) {  }

    @Override
    public void handleMouseMove(int x, int y) {

        crosshairX = x;
        crosshairY = y;

    }

    @Override
    public void handleKeyDown(int keyCode) {

        if (keyCode == KeyEvent.VK_ESCAPE && running)
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
    public void handleKeyUp(int keyCode) {  }

}
