package southpark.game.elements.particle;

import java.awt.*;

import static southpark.game.utils.Constants.*;

public class Particle {

    public double xPos;
    public double yPos;
    public float dX;
    public float dY;
    public int life;

    public Particle() {  }

    public void update() {

        life--;
        xPos += dX;
        yPos += dY;
        dX *= 0.99f;
        dY *= 0.99f;
        dY += 0.1f;

        if(xPos < 0) {
            xPos = 0.01f;
            dX = Math.abs(dX) * (float)Math.random() * 0.6f;
        }

        if(yPos < 0) {
            yPos = 0.01f;
            dY = Math.abs(dY) * (float)Math.random() * 0.6f;
        }

        if(xPos > APP_W) {
            xPos = APP_W - 0.01f;
            dX = Math.abs(dX) * (float)Math.random() * -0.6f;
        }

        if(yPos > APP_H) {
            yPos = APP_H - 0.01f;
            dY = Math.abs(dY) * (float)Math.random() * -0.6f;
        }

    }

    public void render(Graphics2D g) {

        g.drawLine((int)(xPos - dX), (int)(yPos - dY), (int)xPos, (int)yPos);

    }

}
