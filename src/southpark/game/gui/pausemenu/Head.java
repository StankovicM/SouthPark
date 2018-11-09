package southpark.game.gui.pausemenu;

import rafgfxlib.Util;
import southpark.game.gui.exceptions.ImageNotFoundException;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Head {

    public BufferedImage image;
    private AffineTransform transform = new AffineTransform();

    private double width;
    private double height;

    public double xPos;
    public double yPos;

    private double rotSpeed = 0.05;
    private double angle = Math.PI / 2;

    public Head(String path, double xPos, double yPos) throws ImageNotFoundException {

        image = Util.loadImage(path);
        if (image == null) throw new ImageNotFoundException("Slika nije pronadjena: " + path);

        width = image.getWidth();
        height = image.getHeight();

        this.xPos = xPos;
        this.yPos = yPos;

    }

    public void render(Graphics2D g) {

        g.drawImage(image, transform, null);

    }

    public void update() {

        angle += rotSpeed;

        transform.setToIdentity();
        transform.translate(xPos, yPos);
        transform.rotate(angle + Math.PI / 2.0);
        transform.translate(-width / 2, -height / 2);

    }

}
