package Panels.Animations;

import GameLogic.Champion.Champion;
import GameLogic.Champion.ChampionImages;

import javax.swing.*;
import java.awt.*;

public class Heal extends Component{

    private int counter;
    private ImageIcon[] images;

    public Heal() {
        this.counter = 0;
        this.images = new ImageIcon[7];

        for(int i = 0; i < this.images.length; i++) {
            this.images[i] = ChampionImages.scaleImage("resources/animations/heal/heal"+ i +".png", 49, 77);
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
}
