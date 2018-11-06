package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NextPantsColAction extends Action {

	public NextPantsColAction(Button button) { super(button); }
	
	@Override
    public void perform() {

        CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.pants_col_ind = (cs.pants_col_ind + 1) % cs.colors.size();
        cs.updatePantsCol();

    }
	
}
