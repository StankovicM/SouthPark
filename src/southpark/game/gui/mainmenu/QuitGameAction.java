package southpark.game.gui.mainmenu;

import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class QuitGameAction extends Action {

    public QuitGameAction(Button button) { super(button); }

    @Override
    public void perform() {

        button.getParent().getGame().stopGame();

    }

}

