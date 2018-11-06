package southpark.game.gui.exceptions;

public class ButtonExistsException extends Exception {

    private String text;

    public ButtonExistsException(String text) {

        this.text = text;

    }

    @Override
    public void printStackTrace() {

        System.err.println(text);
        super.printStackTrace();

    }
}
