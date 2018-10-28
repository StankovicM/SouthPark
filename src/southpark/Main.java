package southpark;

import southpark.game.Game;
import static southpark.game.utils.Constants.*;

public class Main {

    public static void main(String[] args) {

        Game game = new Game("South Park", SCR_W, SCR_H);
        game.init();
        game.startGame();

    }

}
