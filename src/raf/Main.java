package raf;

import raf.game.Game;

public class Main {

    public static void main(String[] args) {

        Game game = Game.getInstance("RAF Theft Auto", 800, 600);
        game.initGame();
        game.startGame();

    }

}
