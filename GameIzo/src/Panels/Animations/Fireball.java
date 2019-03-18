package Panels.Animations;

import GameLogic.Champion.Champion;
import java.awt.*;

public class Fireball extends Component{
    private int counter;

    public Fireball() {
        this.counter = 0;
    }

    public void paintAnimation(Graphics g, Champion champion, int positionX, int positionY) {
        if (this.counter >= 0 && champion.getSpellInstance() != null) {

            int x = positionX + (champion.getSpellInstance().getX() - champion.getX());
            int y = positionY + (champion.getSpellInstance().getY() - champion.getY());

            champion.getSpellInstance().getImage().paintIcon(this, g, x, y);
            g.drawRect(x,y, champion.getSpellInstance().getWidth(), champion.getSpellInstance().getHeight());

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
