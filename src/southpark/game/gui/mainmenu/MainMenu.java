package southpark.game.gui.mainmenu;

import rafgfxlib.Util;
import southpark.game.Game;
import southpark.game.gui.GUI;
import southpark.game.gui.elements.Button;
import southpark.game.gui.elements.Menu;
import southpark.game.gui.exceptions.ButtonExistsException;

import java.awt.*;

public class MainMenu extends Menu {

    private static final String MMBG_PATH = "src/southpark/game/assets/gui/mmbg.png";

    public MainMenu(Game game, GUI parent) {

        super(game, parent);

    }

    public void render(Graphics2D g) {

        g.drawImage(background, 0, 0, null);
        for (Button b : buttons)
            b.render(g);



    }

    public boolean load() {

        background = Util.loadImage(MMBG_PATH);
        if (background == null) return false;

        Button b;
        try {
            b = new Button("New Game", "new_game_btn", 300, this);
            b.setAction(new NewGameAction(b));
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
