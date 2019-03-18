package Panels.Animations;

import javax.swing.*;

public class Blood {

    private int counter;
    private ImageIcon[] images;
    private int frames = 6;
    private Thread thread;

    public Blood() {
        this.images = new ImageIcon[frames];
        for(int i = 0; i < frames; i++) {
            this.images[i] = new ImageIcon("resources/animations/blood/blood"+ i + ".png");
        }
        this.counter = -1;
    }

    private void incCounter() {
        this.counter++;
        this.counter %= 6;
    }

    public ImageIcon getImage() {
        return this.images[this.counter];
    }

    public int getCounter() {
        return this.counter;
    }

    public void start() {
        this.thread = new Thread(() -> {
            for(int i = 0; i < frames; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                incCounter();

            }
            this.counter = -1;
        });
        this.thread.start();
    }
}
