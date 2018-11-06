package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NextHairColAction extends Action {

    public NextHairColAction(Button button) {
        super(button);
    }

    @Override
    public void perform() {

        CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.hair_col_ind = (cs.hair_col_ind + 1) % cs.colors.size();
        cs.updateHairCol();

    }

}
