package GameLogic.Spells.SpellEffects;

import GameLogic.Champion.Champion;
import GameLogic.Champion.Statistics;
import GameLogic.Spells.Spell;
import GameLogic.Units.Unit;

import java.util.ArrayList;

public class ShieldEffect implements SpellEffect {

    @Override
    public void useSpell(Champion champion, Spell spell, ArrayList<Unit> unitSet) {
        Statistics statistics = champion.getStatistics();
        statistics.setArmor(statistics.getArmor() + 30);
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            statistics.setArmor(statistics.getArmor() - 30);
            spell.setActive(false);
        });
        thread.start();
    }

}
