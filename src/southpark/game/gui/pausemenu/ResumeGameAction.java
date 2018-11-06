package southpark.game.gui.pausemenu;

import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class ResumeGameAction extends Action {

    public ResumeGameAction(Button button) { super(button); }

    @Override
    public void perform() {

        button.getParent().getGame().paused = false;

    }
}
