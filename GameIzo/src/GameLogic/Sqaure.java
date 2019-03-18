package GameLogic;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Sqaure {

    private int width;
    private ImageIcon texture;
    private int type;

    public Sqaure(int width, ImageIcon texture, int type) {
        this.width = width;
        this.texture =  texture;
        this.type = type;
    }

    public ImageIcon getTexture() {
        return texture;
    }

    public void setTexture(ImageIcon texture) {
        this.texture = texture;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
