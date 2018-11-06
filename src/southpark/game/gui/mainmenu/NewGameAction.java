package southpark.game.gui.mainmenu;

import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NewGameAction extends Action {

    public NewGameAction(Button button) { super(button); }

    @Override
    public void perform() {

        button.getParent().getParent().openCharacterSelection();

    }
}
