package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class PrevHairAction extends Action {

    public PrevHairAction(Button button) { super(button); }

    @Override
    public void perform() {

        CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.hair_ind = cs.hair_ind == 0 ? cs.getGame().player.character.hairCount : cs.hair_ind - 1;
        cs.updateHair();

    }

}
