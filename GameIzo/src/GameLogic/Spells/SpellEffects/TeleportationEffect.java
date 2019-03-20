package GameLogic.Spells.SpellEffects;

import GameLogic.Champion.Champion;
import GameLogic.Champion.Statistics;
import GameLogic.Spells.Spell;
import GameLogic.Spells.SpellEffects.SpellEffect;
import GameLogic.Units.Unit;

import java.util.ArrayList;

public class TeleportationEffect implements SpellEffect {
    private int oldX;
    private int oldY;
    private int newX;
    private int newY;

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public int getNewX() {
        return newX;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    @Override
    public void useSpell(Champion champion, Spell spell, ArrayList<Unit> unitSet) {
        oldX = champion.getX();
        oldY = champion.getY();

        // teleportation distance
        int delta = 200;

        switch(champion.getDirection()) {
            case UP:
                newX = oldX;
                newY = oldY - delta;
                champion.setY(newY);
                break;
            case DOWN:
                newX = oldX;
                newY = oldY + delta;
                champion.setY(newY);
                break;
            case RIGHT:
                newX = oldX + delta;
                newY = oldY;
                champion.setX(newX);
                break;
            case LEFT:
                newX = oldX - delta;
                newY = oldY;
                champion.setX(newX);
                break;
        }
    }



}
