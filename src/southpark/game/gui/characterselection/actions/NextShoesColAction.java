package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NextShoesColAction extends Action {

	public NextShoesColAction(Button button) { super(button); }
	
	 @Override
	 public void perform() {

		 CharacterSelection cs = (CharacterSelection)button.getParent();
		 cs.shoes_col_ind = (cs.shoes_col_ind + 1) % cs.colors.size();
		 cs.updateShoeCol();

	 }

}
