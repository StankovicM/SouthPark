package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class NextShoesAction extends Action {

	public NextShoesAction(Button button) { super(button); }

	@Override
    public void perform() {

        CharacterSelection cs = (CharacterSelection)button.getParent();
        cs.shoes_ind = cs.shoes_ind == 1 ? 0 : 1;
        cs.updateShoes();

    }
	
}
