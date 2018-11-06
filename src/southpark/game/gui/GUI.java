package southpark.game.gui;

import southpark.game.Game;
import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.mainmenu.MainMenu;
import southpark.game.gui.pausemenu.PauseMenu;

import java.awt.*;

public class GUI {

    private Game game;

    private MainMenu mainMenu;

    private PauseMenu pauseMenu;

    private CharacterSelection cs;
    private boolean inCS = false;

    public GUI(Game game) {

        this.game = game;
        mainMenu = new MainMenu(game, this);
        pauseMenu = new PauseMenu(game, this);
        cs = new CharacterSelection(game, this);

    }

    public void render(Graphics2D g) {

        if (!game.running) {
            if (!inCS)
                mainMenu.render(g);
            else
                cs.render(g);
        } else {
            pauseMenu.render(g);
        }

    }

    public boolean load() {

        boolean ready;

        ready = mainMenu.load();
        ready = pauseMenu.load();
        ready = cs.load();

        return ready;

    }

    public void openCharacterSelection() {

        inCS = true;

    }

    public void notifyButtons() {

        if (!game.running) {
            if (!inCS)
                mainMenu.notifyButtons();
            else
                cs.notifyButtons();
        } else {
            pauseMenu.notifyButtons();
        }

    }

}
