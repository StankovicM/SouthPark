package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class PrevShirtColAction extends Action {

	public PrevShirtColAction(Button button) { super(button); }

	@Override
	public void perform() {

		CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.shirt_col_ind = cs.shirt_col_ind == 0 ? cs.colors.size() - 1 : cs.shirt_col_ind - 1;
        cs.updateShirtCol();
		
	}	
	
}
