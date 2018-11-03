package southpark.game.elements.player;

import rafgfxlib.Util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.HashMap;

public class Character {

    private static final String CHAR_FRONT = "src/southpark/game/assets/character_generic_front.png";
    private static final String CHAR_LEFT  = "src/southpark/game/assets/character_generic_left.png";
    private static final String CHAR_RIGHT  = "src/southpark/game/assets/character_generic_right.png";

    private static final String MOUTH_01_FRONT = "src/southpark/game/assets/mouths/mouth_01_front.png";
    private static final String MOUTH_01_LEFT = "src/southpark/game/assets/mouths/mouth_01_left.png";
    private static final String MOUTH_01_RIGHT = "src/southpark/game/assets/mouths/mouth_01_right.png";

    private static final String HAIR_01_FRONT = "src/southpark/game/assets/hairs/hair_01_front.png";
    private static final String HAIR_01_LEFT = "src/southpark/game/assets/hairs/hair_01_left.png";
    private static final String HAIR_01_RIGHT = "src/southpark/game/assets/hairs/hair_01_right.png";

    private static final String SHIRT_01_FRONT = "src/southpark/game/assets/shirts/shirt_01_front.png";
    private static final String SHIRT_01_LEFT = "src/southpark/game/assets/shirts/shirt_01_left.png";
    private static final String SHIRT_01_RIGHT = "src/southpark/game/assets/shirts/shirt_01_right.png";

    private static final String PANTS_01_FRONT = "src/southpark/game/assets/pants/pants_01_front.png";
    private static final String PANTS_01_LEFT = "src/southpark/game/assets/pants/pants_01_left.png";
    private static final String PANTS_01_RIGHT = "src/southpark/game/assets/pants/pants_01_right.png";

    private static final String SHOES_FRONT  = "src/southpark/game/assets/shoes_front.png";
    private static final String SHOES_LEFT  = "src/southpark/game/assets/shoes_left.png";
    private static final String SHOES_RIGHT  = "src/southpark/game/assets/shoes_right.png";

    private static HashMap<String, BufferedImage> assets = new HashMap<>();

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

    private boolean drawHair = true;
    public Color hair_color = new Color(110, 50, 0);
    private static BufferedImage hair_front;
    private static BufferedImage hair_left;
    private static BufferedImage hair_right;
    private BufferedImage hair;
    private AffineTransform hairTransform = new AffineTransform();

    private boolean drawShirt = true;
    public Color shirt_color = Color.GREEN;
    private static BufferedImage shirt_front;
    private static BufferedImage shirt_left;
    private static BufferedImage shirt_right;
    private BufferedImage shirt;
    private AffineTransform shirtTransform = new AffineTransform();

    private boolean drawPants = true;
    public Color pants_color = new Color(32, 154, 247);
    private static BufferedImage pants_front;
    private static BufferedImage pants_left;
    private static BufferedImage pants_right;
    private BufferedImage pants;
    private AffineTransform pantsTransform = new AffineTransform();

    public Color shoe_color = Color.BLACK;
    private int shoe_width;
    private int shoe_height;
    private static BufferedImage shoes_front;
    private static BufferedImage shoes_left;
    private static BufferedImage shoes_right;
    private BufferedImage shoes;
    private AffineTransform shoeTransform = new AffineTransform();

    private static final int def_col[] = {160, 160, 160};
    private static final int def_shadow[] = {128, 128, 128};

    public Character(Player player) {

        this.player = player;

    }

    public void render(Graphics2D g) {

        if (!player.heroAnim) {
            g.drawImage(image, player.playerTransform, null);
            if (drawMouth) g.drawImage(mouth, mouthTransform, null);
            if (drawHair) g.drawImage(hair, hairTransform, null);
            if (drawShirt) g.drawImage(shirt, shirtTransform, null);
            if (drawPants) g.drawImage(pants, pantsTransform, null);
            g.drawImage(shoes, shoeTransform, null);
        } else {

        }

    }

    public void update() {

        mouthTransform.setTransform(player.playerTransform);

        hairTransform.setTransform(player.playerTransform);

        shirtTransform.setTransform(player.playerTransform);

        pantsTransform.setTransform(player.playerTransform);

        shoeTransform.setTransform(player.playerTransform);
        shoeTransform.translate(0, height - shoe_height);

    }

    public void setFront() {

        if (!player.heroMode) {
            image = image_front;
            mouth = mouth_front;
            hair = hair_front;
            shirt = shirt_front;
            pants = pants_front;
            shoes = shoes_front;
        } else {

        }

    }

    public void setLeft() {

        if (!player.heroMode) {
            image = image_left;
            mouth = mouth_left;
            hair = hair_left;
            shirt = shirt_left;
            pants = pants_left;
            shoes = shoes_left;
        } else {

        }

    }

    public void setRight() {

        if (!player.heroMode) {
            image = image_right;
            mouth = mouth_right;
            hair = hair_right;
            shirt = shirt_right;
            pants = pants_right;
            shoes = shoes_right;
        } else {

        }

    }

    public boolean load() {

        image_front = Util.loadImage(CHAR_FRONT);
        if (image_front == null) return false;

        image_left = Util.loadImage(CHAR_LEFT);
        if (image_left == null) return false;

        image_right = Util.loadImage(CHAR_RIGHT);
        if (image_right == null) return false;

        mouth_front = Util.loadImage(MOUTH_01_FRONT);
        if (mouth_front == null) return false;

        mouth_left = Util.loadImage(MOUTH_01_LEFT);
        if (mouth_left == null) return false;

        mouth_right = Util.loadImage(MOUTH_01_RIGHT);
        if (mouth_right == null) return false;

        hair_front = Util.loadImage(HAIR_01_FRONT);
        if (hair_front == null) return false;

        hair_left = Util.loadImage(HAIR_01_LEFT);
        if (hair_left == null) return false;

        hair_right = Util.loadImage(HAIR_01_RIGHT);
        if (hair_right == null) return false;

        shirt_front = Util.loadImage(SHIRT_01_FRONT);
        if (shirt_front == null) return false;

        shirt_left = Util.loadImage(SHIRT_01_LEFT);
        if (shirt_left == null) return false;

        shirt_right = Util.loadImage(SHIRT_01_RIGHT);
        if (shirt_right == null) return false;

        pants_front = Util.loadImage(PANTS_01_FRONT);
        if (pants_front == null) return false;

        pants_left = Util.loadImage(PANTS_01_LEFT);
        if (pants_left == null) return false;

        pants_right = Util.loadImage(PANTS_01_RIGHT);
        if (pants_right == null) return false;

        shoes_front = Util.loadImage(SHOES_FRONT);
        if (shoes_front == null) return false;

        shoes_left = Util.loadImage(SHOES_LEFT);
        if (shoes_left == null) return false;

        shoes_right = Util.loadImage(SHOES_RIGHT);
        if (shoes_right == null) return false;

        width = image_front.getWidth();
        height = image_front.getHeight();

        shoe_width = shoes_front.getWidth();
        shoe_height = shoes_front.getHeight();

        setShirtColor();
        setHairColor();
        setPantsColor();
        setShoeColor();

        return true;

    }

    //TODO hat, glasses, eyebrows

    public void setHair(String style, Color color) {



    }

    public void setMouth() {



    }

    public void setShirt() {



    }

    public void setPants() {



    }

    //TODO ZAMENITI SIVU  BOJU PROSLEDJENOM, 160 160 160 255 je siva, obavezno koristiti rgba
    private void setShoeColor() {

        int rgb[] = new int[4];
        int[] shoe_rgb = {shoe_color.getRed(), shoe_color.getGreen(), shoe_color.getBlue(), 255};

        WritableRaster raster_f = shoes_front.getRaster();
        WritableRaster raster_l = shoes_left.getRaster();
        WritableRaster raster_r = shoes_right.getRaster();

        for (int y = 0; y < shoe_height; ++y) {
            for (int x = 0; x < shoe_width; ++x) {
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
        int[] shoe_rgb = {hair_color.getRed(), hair_color.getGreen(), hair_color.getBlue(), 255};

        WritableRaster raster_f = hair_front.getRaster();
        WritableRaster raster_l = hair_left.getRaster();
        WritableRaster raster_r = hair_right.getRaster();

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
