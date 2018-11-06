package southpark.game.gui.pausemenu;

import southpark.game.Game;
import southpark.game.gui.GUI;
import southpark.game.gui.elements.Button;
import southpark.game.gui.elements.Menu;
import southpark.game.gui.exceptions.ButtonExistsException;
import southpark.game.gui.mainmenu.QuitGameAction;

import java.awt.*;

public class PauseMenu extends Menu {

    public PauseMenu(Game game, GUI parent) {

        super(game, parent);

    }

    public void render(Graphics2D g) {

        for (Button b : buttons)
            b.render(g);

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
