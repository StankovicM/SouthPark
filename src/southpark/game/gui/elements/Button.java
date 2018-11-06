package southpark.game.gui.elements;

import southpark.game.gui.exceptions.ButtonExistsException;

import java.awt.*;
import java.awt.font.FontRenderContext;

import static southpark.game.utils.Constants.*;

public class Button {

    private Font font;

    protected Menu parent;

    protected double xPos;
    protected double yPos;

    protected double width;
    protected double height;

    protected String text;

    protected String id;

    protected Action action;

    public Button(String text, String id, Menu parent) throws ButtonExistsException {

        if (parent.contains(id)) throw new ButtonExistsException("Button with the same id already exists!");

        this.text = text;
        this.id = id;
        this.parent = parent;

    }

    public Button(String text, String id, double xPos, double yPos, Menu parent, int size) throws ButtonExistsException {

        if (parent.contains(id)) throw new ButtonExistsException("Button with the same id already exists!");

        this.text = text;
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.parent = parent;

        font = new Font("Times New Roman", Font.BOLD, size);

        FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
        this.width = font.getStringBounds(text, frc).getWidth();
        this.height = font.getStringBounds(text, frc).getHeight();

    }

    public Button(String text, String id, double yPos, Menu parent) throws ButtonExistsException {

        if (parent.contains(id)) throw new ButtonExistsException("Button with the same id already exists!");

        this.text = text;
        this.id = id;
        this.yPos = yPos;
        this.parent = parent;

        font = new Font("Times New Roman", Font.BOLD, 76);

        FontRenderContext frc = new FontRenderContext(font.getTransform(), true, true);
        this.width = font.getStringBounds(text, frc).getWidth();
        this.height = font.getStringBounds(text, frc).getHeight();

        this.xPos = APP_W / 2 - width / 2;

    }

    public void render(Graphics2D g) {

        g.setFont(font);

        if (isMouseOver())
            g.setColor(Color.ORANGE);
        else
            g.setColor(Color.WHITE);

        g.drawString(text, (int)xPos, (int)yPos);

    }

    public boolean isMouseOver() {

        if (parent.game.crosshairX >= xPos && parent.game.crosshairX <= xPos + width) {
            if (parent.game.crosshairY >= yPos - height && parent.game.crosshairY <= yPos) {
                return true;
            }
        }

        return false;

    }

    public void notifyButton() {

        if (action == null) return;

        if (isMouseOver()) {
            action.perform();
        }

    }

    public void setAction(Action action) { this.action = action; }

    public void setPosition(double xPos, double yPos) {

        this.xPos = xPos;
        this.yPos = yPos;

    }

    public double getXPos() { return xPos; }

    public double getYPos() { return yPos; }

    public void setWidth(double width) { this.width = width; }

    public double getWidth() { return width; }

    public void setHeight(double height) { this.height = height; }

    public double getHeight() { return  height; }

    public void setText(String text) { this.text = text; }

    public String getText() { return text; }

    public Menu getParent() { return parent; }

}
