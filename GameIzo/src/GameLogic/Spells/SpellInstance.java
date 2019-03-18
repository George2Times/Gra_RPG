package GameLogic.Spells;

import GameLogic.Direction;

import javax.swing.*;

public class SpellInstance {
    private int x;
    private int y;
    private Direction direction;
    private int width;
    private int height;
    private ImageIcon image;

    public SpellInstance(int x, int y, Direction direction, int width, int height, ImageIcon image) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

}
