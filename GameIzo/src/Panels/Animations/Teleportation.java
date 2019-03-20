package Panels.Animations;

import GameLogic.Champion.Champion;
import GameLogic.Champion.ChampionImages;

import javax.swing.*;
import java.awt.*;

public class Teleportation extends Component{
    private int counter;
    private ImageIcon[] images;

    public Teleportation() {
        this.counter = 0;
        this.images = new ImageIcon[4];

        for(int i = 0; i < this.images.length; i++) {
            this.images[i] = ChampionImages.scaleImage("resources/animations/teleportation/teleport"+ i +".png", 49, 77);
        }
    }

    public void paintAnimation(Graphics g, Champion champion, int positionX, int positionY) {
        if(this.counter >= 0) {
            this.images[this.counter].paintIcon(this, g, positionX, positionY);
        }
    }

    public int getCounter() {
        return this.counter;
    }

    public void incCounter() {
        this.counter++;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public int getFramesNumber() {
        return 20;
    }
}
