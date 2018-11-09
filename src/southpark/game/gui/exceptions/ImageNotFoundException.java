package southpark.game.gui.exceptions;

public class ImageNotFoundException extends Exception {

    private String text;

    public ImageNotFoundException(String text) {

        this.text = text;

    }

    @Override
    public void printStackTrace() {

        System.err.println(text);
        super.printStackTrace();

    }

}
