package southpark.game.gui.characterselection;

import rafgfxlib.Util;
import southpark.game.Game;
import southpark.game.gui.GUI;
import southpark.game.gui.characterselection.actions.*;
import southpark.game.gui.elements.Button;
import southpark.game.gui.elements.Menu;
import southpark.game.gui.exceptions.ButtonExistsException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;

public class CharacterSelection extends Menu {

    public int hair_ind = 0;
    public int mouth_ind = 0;
    public int shirt_ind = 0;
    public int pants_ind = 0;
    public int shoes_ind = 0;

    private boolean drawHair = false;
    private boolean drawMouth = false;
    private boolean drawShirt = false;
    private boolean drawPants = false;
    private boolean drawShoes = false;

    public int hair_col_ind = 0;
    public int shirt_col_ind = 0;
    public int pants_col_ind = 0;
    public int shoes_col_ind = 0;

    private BufferedImage hat;
    private BufferedImage hair;
    private BufferedImage acc;
    private BufferedImage mouth;
    private BufferedImage shirt;
    private BufferedImage pants;
    private BufferedImage shoes;

    public ArrayList<Color> colors = new ArrayList<>();

    public CharacterSelection(Game game, GUI gui) {

        super(game, gui);

        colors.add(Color.GREEN);
        colors.add(Color.BLACK);
        colors.add(new Color(110, 50, 0));
        colors.add(new Color(32, 154, 247));
        colors.add(Color.RED);
        colors.add(Color.CYAN);

    }

    public void render(Graphics2D g) {

        g.drawImage(game.player.character.assets.get("char_front"), 600, 200, null);
        if (drawHair) g.drawImage(hair, 600, 200, null);
        if (drawMouth) g.drawImage(mouth, 600, 200, null);
        if (drawShirt) g.drawImage(shirt, 600, 200, null);
        if (drawPants) g.drawImage(pants, 600, 200, null);
        if (drawShoes) g.drawImage(shoes, 600, 200, null);
        
        g.setColor(Color.WHITE);
        g.drawRect(780, 215, 30, 16);
        
        g.drawRect(780, 275, 30, 16);
        
        g.drawRect(780, 295, 30, 16);
        
        g.drawRect(780, 315, 30, 16);
        
        g.setColor(colors.get(hair_col_ind));
        g.fillRect(781, 216, 29, 15);
        
        g.setColor(colors.get(shirt_col_ind));
        g.fillRect(781, 276, 29, 15);
        
        g.setColor(colors.get(pants_col_ind));
        g.fillRect(781, 296, 29, 15);
        
        g.setColor(colors.get(shoes_col_ind));
        g.fillRect(781, 316, 29, 15);

        for (Button b : buttons)
            b.render(g);

    }

    public boolean load() {

        Button b;
        try {
            //Hair
            b = new Button("<", "prev_hair_btn", 560, 230, this, 20);
            b.setAction(new PrevHairAction(b));
            buttons.add(b);

            b = new Button(">", "next_hair_btn", 720, 230, this, 20);
            b.setAction(new NextHairAction(b));
            buttons.add(b);

            b = new Button("<", "prev_hair_col_btn", 760, 230, this, 20);
            b.setAction(new PrevHairColAction(b));
            buttons.add(b);

            b = new Button(">", "next_hair_col_btn", 820, 230, this, 20);
            b.setAction(new NextHairColAction(b));
            buttons.add(b);

            //Mouth
            b = new Button("<", "prev_mouth_btn", 560, 270, this, 20);
            b.setAction(new PrevMouthAction(b));
            buttons.add(b);

            b = new Button(">", "next_mouth_btn", 720, 270, this, 20);
            b.setAction(new NextMouthAction(b));
            buttons.add(b);

            //Shirt
            b = new Button("<", "prev_shirt_btn", 560, 290, this, 20);
            b.setAction(new PrevShirtAction(b));
            buttons.add(b);

            b = new Button(">", "next_shirt_btn", 720, 290, this, 20);
            b.setAction(new NextShirtAction(b));
            buttons.add(b);

            b = new Button("<", "prev_shirt_col_btn", 760, 290, this, 20);
            b.setAction(new PrevShirtColAction(b));
            buttons.add(b);

            b = new Button(">", "next_shirt_col_btn", 820, 290, this, 20);
            b.setAction(new NextShirtColAction(b));
            buttons.add(b);

            //Pants
            b = new Button("<", "prev_pants_btn", 560, 310, this, 20);
            b.setAction(new PrevPantsAction(b));
            buttons.add(b);

            b = new Button(">", "next_pants_btn", 720, 310, this, 20);
            b.setAction(new NextPantsAction(b));
            buttons.add(b);

            b = new Button("<", "prev_pants_col_btn", 760, 310, this, 20);
            b.setAction(new PrevPantsColAction(b));
            buttons.add(b);

            b = new Button(">", "next_pants_col_btn", 820, 310, this, 20);
            b.setAction(new NextPantsColAction(b));
            buttons.add(b);

            //Shoes
            b = new Button("<", "prev_shoes_btn", 560, 330, this, 20);
            b.setAction(new PrevShoesAction(b));
            buttons.add(b);

            b = new Button(">", "next_shoes_btn", 720, 330, this, 20);
            b.setAction(new NextShoesAction(b));
            buttons.add(b);

            b = new Button("<", "prev_shoes_col_btn", 760, 330, this, 20);
            b.setAction(new PrevShoesColAction(b));
            buttons.add(b);

            b = new Button(">", "next_shoes_col_btn", 820, 330, this, 20);
            b.setAction(new NextShoesColAction(b));
            buttons.add(b);

            //Start Game
            b = new Button("Start Game", "start_game_btn", 420, this);
            b.setAction(new StartGameAction(b));
            buttons.add(b);

            //Init images
            WritableRaster raster = Util.createRaster(80, 117, true);
            hair = Util.rasterToImage(raster);

            mouth = Util.rasterToImage(raster);

            shirt = Util.rasterToImage(raster);

            pants = Util.rasterToImage(raster);

            shoes = Util.rasterToImage(raster);
        } catch (ButtonExistsException bee) {
            bee.printStackTrace();
            return false;
        }

        return true;

    }

    private static final int def_col[] = {160, 160, 160};
    private static final int def_shadow[] = {128, 128, 128};

    public void updateHair() {

        if (hair_ind == 0) {
            drawHair = false;
        } else {
            hair = Util.rasterToImage(game.player.character.assets.get("hair_" + hair_ind + "_front").getRaster());
            drawHair = true;
        }

    }

    public void updateHairCol() {

        updateHair();
        int rgb[] = new int[4];
        int[] hair_rgb = {colors.get(hair_col_ind).getRed(),
                          colors.get(hair_col_ind).getGreen(),
                          colors.get(hair_col_ind).getBlue(),
                          255};

        WritableRaster raster = hair.getRaster();
        for (int y = 0; y < raster.getHeight(); ++y) {
            for (int x = 0; x < raster.getWidth(); ++x) {
                raster.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster.setPixel(x, y, hair_rgb);
                }
            }
        }

        hair = Util.rasterToImage(raster);

    }

    public void updateShirt() {

        if (shirt_ind == 0) {
            drawShirt = false;
        } else {
            shirt = Util.rasterToImage(game.player.character.assets.get("shirt_" + shirt_ind + "_front").getRaster());
            drawShirt = true;
        }

    }

    public void updateShirtCol() {

        updateShirt();
        int rgb[] = new int[4];
        int[] shirt_rgb = {colors.get(shirt_col_ind).getRed(),
                colors.get(shirt_col_ind).getGreen(),
                colors.get(shirt_col_ind).getBlue(),
                255};

        float[] shirt_shadow = {(float)(shirt_rgb[0] * 0.85),
                                (float)(shirt_rgb[1] * 0.85),
                                (float)(shirt_rgb[2] * 0.85),
                                255};

        WritableRaster raster = shirt.getRaster();
        for (int y = 0; y < raster.getHeight(); ++y) {
            for (int x = 0; x < raster.getWidth(); ++x) {
                raster.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster.setPixel(x, y, shirt_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster.setPixel(x, y, shirt_shadow);
                }
            }
        }

        shirt = Util.rasterToImage(raster);

    }

    public void updateMouth() {

        if (mouth_ind == 0) {
            drawMouth = false;
        } else {
            mouth = Util.rasterToImage(game.player.character.assets.get("mouth_" + mouth_ind + "_front").getRaster());
            drawMouth = true;
        }

    }

    public void updatePants() {

        if (pants_ind == 0) {
            drawPants = false;
        } else {
            pants = Util.rasterToImage(game.player.character.assets.get("pants_" + pants_ind + "_front").getRaster());
            drawPants = true;
        }

    }

    public void updatePantsCol() {

        updatePants();
        int rgb[] = new int[4];
        int[] pants_rgb = {colors.get(pants_col_ind).getRed(),
                colors.get(pants_col_ind).getGreen(),
                colors.get(pants_col_ind).getBlue(),
                255};

        float[] pants_shadow = {(float)(pants_rgb[0] * 0.85),
                (float)(pants_rgb[1] * 0.85),
                (float)(pants_rgb[2] * 0.85),
                255};

        WritableRaster raster = pants.getRaster();
        for (int y = 0; y < raster.getHeight(); ++y) {
            for (int x = 0; x < raster.getWidth(); ++x) {
                raster.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster.setPixel(x, y, pants_rgb);
                } else if (areColorsEqual(def_shadow, rgb)) {
                    raster.setPixel(x, y, pants_shadow);
                }
            }
        }

        pants = Util.rasterToImage(raster);

    }

    public void updateShoes() {

        if (shoes_ind == 0) {
            drawShoes = false;
        } else {
            shoes = Util.rasterToImage(game.player.character.assets.get("shoes_front").getRaster());
            drawShoes = true;
        }

    }

    public void updateShoeCol() {

        updateShoes();
        int rgb[] = new int[4];
        int[] shoe_rgb = {colors.get(shoes_col_ind).getRed(),
                colors.get(shoes_col_ind).getGreen(),
                colors.get(shoes_col_ind).getBlue(),
                255};

        WritableRaster raster = shoes.getRaster();
        for (int y = 0; y < raster.getHeight(); ++y) {
            for (int x = 0; x < raster.getWidth(); ++x) {
                raster.getPixel(x, y, rgb);
                if (areColorsEqual(def_col, rgb)) {
                    raster.setPixel(x, y, shoe_rgb);
                }
            }
        }

        shoes = Util.rasterToImage(raster);

    }

    private boolean areColorsEqual(int[] a1, int[] a2) {

        for (int i = 0; i < 3; ++i) {
            if (a1[i] != a2[i]) return false;
        }

        return true;

    }

}
