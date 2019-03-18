package GameLogic.Champion;

import GameLogic.Direction;

import javax.swing.*;
import java.awt.*;

public class ChampionImages {

    private ImageIcon[] upImages;
    private ImageIcon[] downImages;
    private ImageIcon[] rightImages;
    private ImageIcon[] leftImages;

    public ChampionImages(int width, int height) {
        this.upImages = new ImageIcon[6];
        this.downImages = new ImageIcon[6];
        this.rightImages = new ImageIcon[6];
        this.leftImages = new ImageIcon[6];

        for(int i = 1; i < 7; i++) {
            this.upImages[i-1] = scaleImage("resources/up"+ i +".png", width, height);
            this.downImages[i-1] = scaleImage("resources/down"+ i +".png", width, height);
            this.rightImages[i-1] = scaleImage("resources/right"+ i +".png", width, height);
            this.leftImages[i-1] = scaleImage("resources/left"+ i +".png", width, height);
        }
    }

    public static ImageIcon scaleImage(String path, int x, int y) {
        Image temporary =  new ImageIcon(path).getImage();
        temporary = temporary.getScaledInstance(x, y,  Image.SCALE_SMOOTH );
        return new ImageIcon(temporary);
    }

    ImageIcon getImage(Direction direction, int counter) {
        if(counter > 5) {
            counter = 5;
        }
        switch(direction) {
            case UP:
                return this.upImages[counter];
            case DOWN:
                return this.downImages[counter];
            case RIGHT:
                return this.rightImages[counter];
            case LEFT:
                return this.leftImages[counter];
        }
        return null;
    }
}
