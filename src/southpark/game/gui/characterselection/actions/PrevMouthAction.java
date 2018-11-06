package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class PrevMouthAction extends Action {

	public PrevMouthAction(Button button) { super(button); }

	@Override
	public void perform() {
		
		CharacterSelection cs = (CharacterSelection)button.getParent();
		cs.mouth_ind = cs.mouth_ind == 0 ? cs.getGame().player.character.mouthCount : cs.mouth_ind - 1;
        cs.updateMouth();
		
	}
	
}
