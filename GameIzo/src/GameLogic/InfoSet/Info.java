package GameLogic.InfoSet;

import javax.swing.*;
import java.awt.*;

public class Info {

    private int frames = 10;
    private int x;
    private int y;
    private String message;
    private Color color;

    public Info(int x, int y, String message, Color color) {
        this.x = x;
        this.y = y;
        this.message = message;
        this.color = color;
        this.move();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    private void move() {
        Thread thread = new Thread(() -> {
            int i = 0;
            while(i < this.frames) {
                this.y -= 5;
                i++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.y = 9999;
        });
        thread.start();
    }
}
