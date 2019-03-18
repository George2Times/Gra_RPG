package GameLogic.Spells.SpellEffects;

import GameLogic.Champion.Champion;
import GameLogic.Champion.Statistics;
import GameLogic.InfoSet.Info;
import GameLogic.Spells.Spell;
import GameLogic.Units.Unit;

import java.awt.*;
import java.util.ArrayList;

public class HealEffect implements SpellEffect {

    @Override
    public void useSpell(Champion champion, Spell spell, ArrayList<Unit> unitSet) {
        int prevHp = champion.getStatistics().getHitPoints();
        Statistics statistics = champion.getStatistics();
        statistics.setHitPoints(statistics.getHitPoints() + (statistics.getAbilityPower() * 6 / 10) + 5);
        if (statistics.getHitPoints() > statistics.getMaxHitPoints()) {
            statistics.setHitPoints(statistics.getMaxHitPoints());
        }
        int afterHp = champion.getStatistics().getHitPoints();

        if(prevHp != afterHp) {
            champion.addInfo(new Info(champion.getX() + champion.getWidth()/2, champion.getY(), "+" + (afterHp-prevHp), Color.GREEN));
        }
    }
}
