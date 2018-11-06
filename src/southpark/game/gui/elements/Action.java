package southpark.game.gui.elements;

public abstract class Action {

    protected Button button;

    public Action(Button button) { this.button = button; }

    public abstract void perform();

}
