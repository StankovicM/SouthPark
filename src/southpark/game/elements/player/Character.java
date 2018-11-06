package southpark.game.elements.player;

import rafgfxlib.Util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;

public class Character {

    private static final String HAT_PATH = "src/southpark/game/assets/hats/";
    private static final String HAIR_PATH = "src/southpark/game/assets/hairs/";
    private static final String EYEBROWS_PATH = "src/southpark/game/assets/eyebrows/";
    private static final String MOUTH_PATH = "src/southpark/game/assets/mouths/";
    private static final String ACC_PATH = "src/southpark/game/assets/accessories/";
    private static final String SHIRT_PATH = "src/southpark/game/assets/shirts/";
    private static final String PANTS_PATH = "src/southpark/game/assets/pants/";

    public static final int hatCount = 0;
    public static final int hairCount = 2;
    public static final int eyebrowCount = 0;
    public static final int mouthCount = 1;
    public static final int accCount = 1;
    public static final int shirtCount = 3;
    public static final int pantsCount = 2;

    private static final String CHAR_FRONT = "src/southpark/game/assets/character_generic_front.png";
    private static final String CHAR_LEFT  = "src/southpark/game/assets/character_generic_left.png";
    private static final String CHAR_RIGHT  = "src/southpark/game/assets/character_generic_right.png";

    private static final String SHOES_FRONT  = "src/southpark/game/assets/shoes_front.png";
    private static final String SHOES_LEFT  = "src/southpark/game/assets/shoes_left.png";
    private static final String SHOES_RIGHT  = "src/southpark/game/assets/shoes_right.png";

    private static final String HERO_FRONT = "src/southpark/game/assets/hero/hero_1_front.png";
    private static final String HERO_LEFT = "src/southpark/game/assets/hero/hero_1_left.png";
    private static final String HERO_RIGHT = "src/southpark/game/assets/hero/hero_1_right.png";

    public static HashMap<String, BufferedImage> assets = new HashMap<>();

    private Player player;

    public int width;
    public int height;

    private static BufferedImage image_front;
    private static BufferedImage image_left;
    private static BufferedImage image_right;
    private BufferedImage image;

    private boolean drawMouth = false;
    private static BufferedImage mouth_front;
    private static BufferedImage mouth_left;
    private static BufferedImage mouth_right;
    private BufferedImage mouth;
    private AffineTransform mouthTransform = new AffineTransform();

    private boolean drawHat = false;
    public Color hat_color = Color.BLACK;
    private static BufferedImage hat_front;
    private static BufferedImage hat_left;
    private static BufferedImage hat_right;
    private BufferedImage hat;
    private AffineTransform hatTransform = new AffineTransform();

    private boolean drawHair = false;
    public Color hair_color = Color.BLACK;//new Color(110, 50, 0);
    private static BufferedImage hair_front;
    private static BufferedImage hair_left;
    private static BufferedImage hair_right;
    private BufferedImage hair;
    private AffineTransform hairTransform = new AffineTransform();

    private boolean drawEyebrows = false;
    public Color eyebrows_color = Color.BLACK;
    private static BufferedImage eyebrows_front;
    private static BufferedImage eyebrows_left;
    private static BufferedImage eyebrows_right;
    private BufferedImage eyebrows;
    private AffineTransform eyebrowsTransform = new AffineTransform();

    private boolean drawAcc = false;
    private static BufferedImage acc_front;
    private static BufferedImage acc_left;
    private static BufferedImage acc_right;
    private BufferedImage acc;
    private AffineTransform accTransform = new AffineTransform();

    private boolean drawShirt = false;
    public Color shirt_color = Color.BLACK;
    private static BufferedImage shirt_front;
    private static BufferedImage shirt_left;
    private static BufferedImage shirt_right;
    private BufferedImage shirt;
    private AffineTransform shirtTransform = new AffineTransform();

    private boolean drawPants = false;
    public Color pants_color = new Color(32, 154, 247);
    private static BufferedImage pants_front;
    private static BufferedImage pants_left;
    private static BufferedImage pants_right;
    private BufferedImage pants;
    private AffineTransform pantsTransform = new AffineTransform();

    private boolean drawShoes = false;
    public Color shoe_color = Color.BLACK;
    private static BufferedImage shoes_front;
    private static BufferedImage shoes_left;
    private static BufferedImage shoes_right;
    private BufferedImage shoes;
    private AffineTransform shoeTransform = new AffineTransform();

    private static final int def_col[] = {160, 160, 160};
    private static final int def_shadow[] = {128, 128, 128};

    private boolean drawHero = false;
    private static BufferedImage hero_front;
    private static BufferedImage hero_left;
    private static BufferedImage hero_right;
    private BufferedImage hero;
    private AffineTransform heroTransform = new AffineTransform();

    private static final long ANIM_TIME = 5500000000l;
    public long lastTime = System.nanoTime();
    private long now;
    private long time = 0;

    public Color partCol = Color.YELLOW;

    public Character(Player player) {

        this.player = player;

    }

    public void render(Graphics2D g) {

        if (!player.heroAnim) {
            drawCharacter(g);
        } else {
            now = System.nanoTime();
            time += now - lastTime;
            if (time >= ANIM_TIME) {
                player.game.genParticles(player.xPos, player.yPos, 10.0, 60, 100);
                if (player.heroMode)
                    partCol = new Color(130, 2, 104);
                else
                    partCol = Color.YELLOW;

                player.heroAnim = false;
                time = 0;
            }

            if (time >= 1500000000l && time <= 2000000000l) {
                drawHero = player.heroMode ? true : false;
                drawCharacter(g);
            }

            if (time >= 2500000000l && time <= 3000000000l) {
                drawHero = player.heroMode ? false : true;
                drawCharacter(g);
            }

            if (time >= 3500000000l && time <= 4000000000l) {
                drawHero = player.heroMode ? true : false;
                drawCharacter(g);
            }

            if (time >= 4250000000l && time <= 4500000000l) {
                drawHero = player.heroMode ? false : true;
                drawCharacter(g);
            }

            if (time >= 4500000000l && time <= 4750000000l) {
                drawHero = player.heroMode ? true : false;
                drawCharacter(g);
            }

            if (time >= 4750000000l && time <= 5000000000l) {
                drawHero = player.heroMode ? false : true;
                drawCharacter(g);
            }

            if (time >= 5000000000l && time <= 5100000000l) {
                drawHero = player.heroMode ? true : false;
                drawCharacter(g);
            }

            if (time >= 5100000000l && time <= 5200000000l) {
                drawHero = player.heroMode ? false : true;
                drawCharacter(g);
            }

            if (time >= 5200000000l && time <= 5300000000l) {
                drawHero = player.heroMode ? true : false;
                drawCharacter(g);
            }

            if (time >= 5300000000l && time <= 5400000000l) {
                drawHero = player.heroMode ? false : true;
                drawCharacter(g);
            }

            if (time >= 5400000000l) {
                drawHero = player.heroMode ? true : false;
                drawCharacter(g);
            }

            drawHero = player.heroMode ? true : false;

            lastTime = now;
        }

    }

    private void drawCharacter(Graphics2D g) {

        g.drawImage(image, player.playerTransform, null);
        if (drawMouth) g.drawImage(mouth, mouthTransform, null);
        if (drawAcc) g.drawImage(acc, accTransform, null);
        if (drawHair) g.drawImage(hair, hairTransform, null);
        if (drawEyebrows) g.drawImage(eyebrows, eyebrowsTransform, null);
        if (drawHat) g.drawImage(hat, hatTransform, null);
        if (drawShirt) g.drawImage(shirt, shirtTransform, null);
        if (drawPants) g.drawImage(pants, pantsTransform, null);
        if (drawShoes) g.drawImage(shoes, shoeTransform, null);
        if (drawHero) g.drawImage(hero, heroTransform, null);

    }

    public void update() {

        hatTransform.setTransform(player.playerTransform);

        mouthTransform.setTransform(player.playerTransform);

        hairTransform.setTransform(player.playerTransform);

        eyebrowsTransform.setTransform(player.playerTransform);

        accTransform.setTransform(player.playerTransform);

        shirtTransform.setTransform(player.playerTransform);

        pantsTransform.setTransform(player.playerTransform);

        shoeTransform.setTransform(player.playerTransform);

        heroTransform.setTransform(player.playerTransform);

    }

    public void setFront() {

        image = image_front;
        hat = hat_front;
        mouth = mouth_front;
        hair = hair_front;
        eyebrows = eyebrows_front;
        acc = acc_front;
        shirt = shirt_front;
        pants = pants_front;
        shoes = shoes_front;
        hero = hero_front;

    }

    public void setLeft() {

        image = image_left;
        hat = hat_left;
        mouth = mouth_left;
        hair = hair_left;
        eyebrows = eyebrows_left;
        acc = acc_left;
        shirt = shirt_left;
        pants = pants_left;
        shoes = shoes_left;
        hero = hero_left;

    }

    public void setRight() {

        image = image_right;
        hat = hat_right;
        mouth = mouth_right;
        hair = hair_right;
        eyebrows = eyebrows_right;
        acc = acc_right;
        shirt = shirt_right;
        pants = pants_right;
        shoes = shoes_right;
        hero = hero_right;

    }

    public boolean load() {

        image_front = Util.loadImage(CHAR_FRONT);
        if (image_front == null) return false;

        image_left = Util.loadImage(CHAR_LEFT);
        if (image_left == null) return false;

        image_right = Util.loadImage(CHAR_RIGHT);
        if (image_right == null) return false;

        assets.put("char_front", image_front);
        assets.put("char_left", image_left);
        assets.put("char_right", image_right);

        width = image_front.getWidth();
        height = image_front.getHeight();

        shoes_front = Util.loadImage(SHOES_FRONT);
        if (shoes_front == null) return false;

        shoes_left = Util.loadImage(SHOES_LEFT);
        if (shoes_left == null) return false;

        shoes_right = Util.loadImage(SHOES_RIGHT);
        if (shoes_right == null) return false;

        assets.put("shoes_front", shoes_front);
        assets.put("shoes_left", shoes_left);
        assets.put("shoes_right", shoes_right);

        hero_front = Util.loadImage(HERO_FRONT);
        if (hero_front == null) return false;

        hero_left = Util.loadImage(HERO_LEFT);
        if (hero_left == null) return false;

        hero_right = Util.loadImage(HERO_RIGHT);
        if (hero_right == null) return false;

        BufferedImage img;
        String imgName;
        for (int i = 1; i <= hatCount; ++i) {
            imgName = "hat_" + Integer.toString(i) + "_front";
            img = Util.loadImage(HAT_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "hat_" + Integer.toString(i) + "_left";
            img = Util.loadImage(HAT_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "hat_" + Integer.toString(i) + "_right";
            img = Util.loadImage(HAT_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        for (int i = 1; i <= hairCount; ++i) {
            imgName = "hair_" + Integer.toString(i) + "_front";
            img = Util.loadImage(HAIR_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "hair_" + Integer.toString(i) + "_left";
            img = Util.loadImage(HAIR_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "hair_" + Integer.toString(i) + "_right";
            img = Util.loadImage(HAIR_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        for (int i = 1; i <= eyebrowCount; ++i) {
            imgName = "eyebrows_" + Integer.toString(i) + "_front";
            img = Util.loadImage(EYEBROWS_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "eyebrows_" + Integer.toString(i) + "_left";
            img = Util.loadImage(EYEBROWS_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "eyebrows_" + Integer.toString(i) + "_right";
            img = Util.loadImage(EYEBROWS_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        for (int i = 1; i <= mouthCount; ++i) {
            imgName = "mouth_" + Integer.toString(i) + "_front";
            img = Util.loadImage(MOUTH_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "mouth_" + Integer.toString(i) + "_left";
            img = Util.loadImage(MOUTH_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "mouth_" + Integer.toString(i) + "_right";
            img = Util.loadImage(MOUTH_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        for (int i = 1; i <= accCount; ++i) {
            imgName = "acc_" + Integer.toString(i) + "_front";
            img = Util.loadImage(ACC_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "acc_" + Integer.toString(i) + "_left";
            img = Util.loadImage(ACC_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "acc_" + Integer.toString(i) + "_right";
            img = Util.loadImage(ACC_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        for (int i = 1; i <= shirtCount; ++i) {
            imgName = "shirt_" + Integer.toString(i) + "_front";
            img = Util.loadImage(SHIRT_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "shirt_" + Integer.toString(i) + "_left";
            img = Util.loadImage(SHIRT_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "shirt_" + Integer.toString(i) + "_right";
            img = Util.loadImage(SHIRT_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        for (int i = 1; i <= pantsCount; ++i) {
            imgName = "pants_" + Integer.toString(i) + "_front";
            img = Util.loadImage(PANTS_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "pants_" + Integer.toString(i) + "_left";
            img = Util.loadImage(PANTS_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);

            imgName = "pants_" + Integer.toString(i) + "_right";
            img = Util.loadImage(PANTS_PATH + imgName + ".png");
            if (img == null) return false;
            assets.put(imgName, img);
        }

        //setHair(1, null);
        //setShirt(1, null);
        //setPants(1, null);
        //setShoes(null);

        return true;

    }

    public void setHat(int style, Color color) {

        if (color != null) hat_color = color;

        String imgName = "hat_" + Integer.toString(style);
        hat_front = assets.get(imgName + "_front");
        hat_left = assets.get(imgName + "_left");
        hat_right = assets.get(imgName + "_right");

        //TODO setHatColor

        drawHat = true;

    }

    public void setHair(int style, Color color) {

        if (color != null) hair_color = color;

        String imgName = "hair_" + Integer.toString(style);
        hair_front = assets.get(imgName + "_front");
        hair_left = assets.get(imgName + "_left");
        hair_right = assets.get(imgName + "_right");

        setHairColor();

        drawHair = true;

    }

    public void setEyebros(int style, Color color) {

        if (color != null) eyebrows_color = color;

        String imgName = "eyebrows_" + Integer.toString(style);
        eyebrows_front = assets.get(imgName + "_front");
        eyebrows_left = assets.get(imgName + "_left");
        eyebrows_right = assets.get(imgName + "_right");

        drawEyebrows = true;

    }

    public void setAcc(int style) {

        String imgName = "acc_" + Integer.toString(style);
        acc_front = assets.get(imgName + "_front");
        acc_left = assets.get(imgName + "_left");
        acc_right = assets.get(imgName + "_right");

        drawAcc = true;

    }

    public void setMouth(int style) {

        String imgName = "mouth_" + Integer.toString(style);
        mouth_front = assets.get(imgName + "_front");
        mouth_left = assets.get(imgName + "_left");
        mouth_right = assets.get(imgName + "_right");

        drawMouth = true;

    }

    public void setShirt(int style, Color color) {

        if (color != null) shirt_color = color;

        String imgName = "shirt_" + Integer.toString(style);
        shirt_front = assets.get(imgName + "_front");
        shirt_left = assets.get(imgName + "_left");
        shirt_right = assets.get(imgName + "_right");

        setShirtColor();

        drawShirt = true;

    }

    public void setPants(int style, Color color) {

        if (color != null) pants_color = color;

        String imgName = "pants_" + Integer.toString(style);
        pants_front = assets.get(imgName + "_front");
        pants_left = assets.get(imgName + "_left");
        pants_right = assets.get(imgName + "_right");

        setPantsColor();

        drawPants = true;

    }

    public void setShoes(Color color) {

        if (color != null) shoe_color = color;

        setShoeColor();

        drawShoes = true;

    }

    private void setShoeColor() {

        int rgb[] = new int[4];
        int[] shoe_rgb = {shoe_color.getRed(), shoe_color.getGreen(), shoe_color.getBlue(), 255};

        WritableRaster raster_f = shoes_front.getRaster();
        WritableRaster raster_l = shoes_left.getRaster();
        WritableRaster raster_r = shoes_right.getRaster();

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                raster_f.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_f.setPixel(x, y, shoe_rgb);
                }

                raster_l.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_l.setPixel(x, y, shoe_rgb);
                }

                raster_r.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_r.setPixel(x, y, shoe_rgb);
                }
            }
        }

        shoes_front = Util.rasterToImage(raster_f);
        shoes_left = Util.rasterToImage(raster_l);
        shoes_right = Util.rasterToImage(raster_r);

    }

    private void setHairColor() {

        int rgb[] = new int[4];
        int[] hair_rgb = {hair_color.getRed(), hair_color.getGreen(), hair_color.getBlue(), 255};

        WritableRaster raster_f = hair_front.getRaster();
        WritableRaster raster_l = hair_left.getRaster();
        WritableRaster raster_r = hair_right.getRaster();

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                raster_f.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_f.setPixel(x, y, hair_rgb);
                }

                raster_l.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_l.setPixel(x, y, hair_rgb);
                }

                raster_r.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_r.setPixel(x, y, hair_rgb);
                }
            }
        }

        hair_front = Util.rasterToImage(raster_f);
        hair_left = Util.rasterToImage(raster_l);
        hair_right = Util.rasterToImage(raster_r);

    }

    private void setShirtColor() {

        int rgb[] = new int[4];
        int[] shirt_rgb = {shirt_color.getRed(), shirt_color.getGreen(), shirt_color.getBlue(), 255};
        float[] shirt_shadow = {(float)(shirt_color.getRed() * 0.85),
                                (float)(shirt_color.getGreen() * 0.85),
                                (float)(shirt_color.getBlue() * 0.85),
                                255};

        WritableRaster raster_f = shirt_front.getRaster();
        WritableRaster raster_l = shirt_left.getRaster();
        WritableRaster raster_r = shirt_right .getRaster();

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                raster_f.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_f.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster_f.setPixel(x, y, shirt_shadow);
                }

                raster_l.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_l.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster_l.setPixel(x, y, shirt_shadow);
                }


                raster_r.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_r.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster_r.setPixel(x, y, shirt_shadow);
                }
            }
        }

        shirt_front = Util.rasterToImage(raster_f);
        shirt_left = Util.rasterToImage(raster_l);
        shirt_right = Util.rasterToImage(raster_r);

    }

    private void setPantsColor() {

        int rgb[] = new int[4];
        int[] shirt_rgb = {pants_color.getRed(), pants_color.getGreen(), pants_color.getBlue(), 255};
        float[] shirt_shadow = {(float)(pants_color.getRed() * 0.85),
                (float)(pants_color.getGreen() * 0.85),
                (float)(pants_color.getBlue() * 0.85),
                255};

        WritableRaster raster_f = pants_front.getRaster();
        WritableRaster raster_l = pants_left.getRaster();
        WritableRaster raster_r = pants_right .getRaster();

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                raster_f.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_f.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster_f.setPixel(x, y, shirt_shadow);
                }

                raster_l.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_l.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster_l.setPixel(x, y, shirt_shadow);
                }


                raster_r.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster_r.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster_r.setPixel(x, y, shirt_shadow);
                }
            }
        }

        pants_front = Util.rasterToImage(raster_f);
        pants_left = Util.rasterToImage(raster_l);
        pants_right = Util.rasterToImage(raster_r);


    }

    private boolean areColorsEqual(int[] a1, int[] a2) {

        for (int i = 0; i < 3; ++i) {
            if (a1[i] != a2[i]) return false;
        }

        return true;

    }

}
