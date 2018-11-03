package southpark.game.elements;

import southpark.game.Game;

public abstract class GObject {

    protected Game game;

    public double xPos;
    public double yPos;

    public int width;
    public int height;

    public double curSpeed;
    public final double fallSpeed = 4.0;

    protected boolean falling;

    public GObject(Game game) {

        this.game = game;
        game.objects.add(this);

    }

    public void pullDown() {

        if (yPos < game.world.currentMap.groundLevel - height / 2) {
            yPos += curSpeed;
            curSpeed *= game.world.gravity;
            if (yPos >= game.world.currentMap.groundLevel - height / 2) {
                curSpeed = fallSpeed;
                falling = false;
                yPos = game.world.currentMap.groundLevel - height / 2;
            }
        }

    }

    public boolean isFalling() { return falling; }

}
