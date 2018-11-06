package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class PrevShoesColAction extends Action {

	public PrevShoesColAction(Button button) { super(button); }

	@Override
	public void perform() {

		CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.shoes_col_ind = cs.shoes_col_ind == 0 ? cs.colors.size() - 1 : cs.shoes_col_ind - 1;
        cs.updateShoeCol();
		
	}
	
}
