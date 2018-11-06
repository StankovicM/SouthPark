package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class PrevShirtAction extends Action {

	public PrevShirtAction(Button button) { super(button); }

	@Override
	public void perform() {

		CharacterSelection cs = (CharacterSelection)button.getParent();
		cs.shirt_ind = cs.shirt_ind == 0 ? cs.getGame().player.character.shirtCount : cs.shirt_ind - 1;
        cs.updateShirt();
		
	}
	
}
