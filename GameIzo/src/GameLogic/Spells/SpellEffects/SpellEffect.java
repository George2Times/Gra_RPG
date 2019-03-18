package GameLogic.Spells.SpellEffects;

import GameLogic.Champion.Champion;
import GameLogic.Spells.Spell;
import GameLogic.Units.Unit;

import java.util.ArrayList;

public interface SpellEffect {
    void useSpell(Champion champion, Spell spell, ArrayList<Unit> unitSet);
}
