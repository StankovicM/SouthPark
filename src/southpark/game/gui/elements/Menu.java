package southpark.game.gui.elements;

import southpark.game.Game;
import southpark.game.gui.GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Menu {

    protected GUI parent;

    protected BufferedImage background;

    protected ArrayList<Button> buttons = new ArrayList<>();

    protected Game game;



    public Menu(Game game, GUI parent) {

        this.game = game;
        this.parent = parent;

    }

    public abstract void render(Graphics2D g);

    public abstract void update();

    public abstract boolean load();

    public boolean contains(Button btn) {

        for (Button b : buttons)
            if (b.id.equalsIgnoreCase(btn.id))
                return true;

        return false;

    }

    public boolean contains(String btnId) {

        for (Button b : buttons)
            if (b.id.equalsIgnoreCase(btnId))
                return true;

        return false;

    }

    public void notifyButtons() {

        for (Button b : buttons)
            b.notifyButton();

    }

    public Game getGame() { return game; }

    public GUI getParent() { return parent; }

    public BufferedImage getBackground() {
        return background;
    }
}
