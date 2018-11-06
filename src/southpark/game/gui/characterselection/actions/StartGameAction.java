package southpark.game.gui.characterselection.actions;

import southpark.game.gui.characterselection.CharacterSelection;
import southpark.game.gui.elements.Action;
import southpark.game.gui.elements.Button;

public class StartGameAction extends Action {

    public StartGameAction(Button button) { super(button);  }

    @Override
    public void perform() {

        CharacterSelection cs = (CharacterSelection)button.getParent();
        if (cs.hair_ind != 0) cs.getGame().player.character.setHair(cs.hair_ind, cs.colors.get(cs.hair_col_ind));
        if (cs.mouth_ind != 0) cs.getGame().player.character.setMouth(cs.mouth_ind);
        if (cs.shirt_ind != 0) cs.getGame().player.character.setShirt(cs.shirt_ind, cs.colors.get(cs.shirt_col_ind));
        if (cs.pants_ind != 0) cs.getGame().player.character.setPants(cs.pants_ind, cs.colors.get(cs.pants_col_ind));
        if (cs.shoes_ind != 0) cs.getGame().player.character.setShoes(cs.colors.get(cs.shoes_col_ind));
        cs.getGame().startGame();

    }

}
