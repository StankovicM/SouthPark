package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class PrevPantsAction extends Action {

	public PrevPantsAction(Button button) { super(button); }

	@Override
	public void perform() {

		CharacterSelection cs = (CharacterSelection)button.getParent();
		cs.pants_ind = cs.pants_ind == 0 ? cs.getGame().player.character.pantsCount : cs.pants_ind - 1;
        cs.updatePants();
		
	}	
	
}
