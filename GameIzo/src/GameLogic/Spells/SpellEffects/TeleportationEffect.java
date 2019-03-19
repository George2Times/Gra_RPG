package GameLogic.Spells.SpellEffects;

import GameLogic.Champion.Champion;
import GameLogic.Champion.Statistics;
import GameLogic.Spells.Spell;
import GameLogic.Spells.SpellEffects.SpellEffect;
import GameLogic.Units.Unit;

import java.util.ArrayList;

public class TeleportationEffect implements SpellEffect {

    @Override
    public void useSpell(Champion champion, Spell spell, ArrayList<Unit> unitSet) {
        int old_x = champion.getX();
        int old_y = champion.getY();
        // teleportation distance
        int delta = 100;

        switch(champion.getDirection()) {
            case UP:
                champion.setY(old_y - delta);
                break;
            case DOWN:
                champion.setY(old_y + delta);
                break;
            case RIGHT:
                champion.setX(old_x + delta);
                break;
            case LEFT:
                champion.setX(old_x - delta);
                break;
        }
    }

}
