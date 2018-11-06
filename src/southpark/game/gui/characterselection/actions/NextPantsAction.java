package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NextPantsAction extends Action {

	public NextPantsAction(Button button) { super(button); }

	@Override
	public void perform() {
		
		CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.pants_ind = cs.pants_ind == cs.getGame().player.character.pantsCount ? 0 : cs.pants_ind + 1;
        cs.updatePants();
		
	}
	
}
