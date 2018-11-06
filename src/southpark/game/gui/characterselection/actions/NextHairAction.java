package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NextHairAction extends Action {

    public NextHairAction(Button button) { super(button); }

    @Override
    public void perform() {

        CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.hair_ind = cs.hair_ind == cs.getGame().player.character.hairCount ? 0 : cs.hair_ind + 1;
        cs.updateHair();

    }

}
