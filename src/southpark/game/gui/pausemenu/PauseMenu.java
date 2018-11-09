package southpark.game.gui.pausemenu;

import southpark.game.Game;
import southpark.game.gui.GUI;
import southpark.game.gui.elements.Button;
import southpark.game.gui.elements.Menu;
import southpark.game.gui.exceptions.ButtonExistsException;
import southpark.game.gui.exceptions.ImageNotFoundException;
import southpark.game.gui.mainmenu.QuitGameAction;

import java.awt.*;

public class PauseMenu extends Menu {

    private static final String PATH = "src/southpark/game/assets/";

    private Head[] heads = new Head[4];

    public PauseMenu(Game game, GUI parent) {

        super(game, parent);
        try {
            heads[0] = new Head(PATH + "cartman_head.png", 50, 50);
            heads[1] = new Head(PATH + "kenny_head.png", 1230, 50);
            heads[2] = new Head(PATH + "kyle_head.png", 50, 670);
            heads[3] = new Head(PATH + "stan_head.png", 1230, 670);
        } catch (ImageNotFoundException infe) {
            infe.printStackTrace();
        }

    }

    public void render(Graphics2D g) {

        for (Button b : buttons)
            b.render(g);

        for (Head h : heads)
            h.render(g);

    }

    public void update() {

        for (Head h : heads)
            h.update();

    }

    public boolean load() {

        Button b;
        try {
            b = new Button("Resume Game", "resume_game_btn", 300, this);
            b.setAction(new ResumeGameAction(b));
            buttons.add(b);

            b = new Button("Quit Game", "quit_game_btn", 400, this);
            b.setAction(new QuitGameAction(b));
            buttons.add(b);
        } catch (ButtonExistsException bee) {
            bee.printStackTrace();
            return false;
        }

        return true;

    }

}
