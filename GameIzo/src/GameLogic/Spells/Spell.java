package GameLogic.Spells;

import GameLogic.Champion.Champion;
import GameLogic.Spells.SpellEffects.SpellEffect;
import GameLogic.Units.Unit;

import javax.swing.*;
import java.util.ArrayList;

public class Spell {

    private SpellType name;
    private String description;
    private ImageIcon image;
    private int cooldown;
    private int cooldownLast;
    private int manaCost;
    private boolean active;
    private Thread thread;
    private SpellEffect spellEffect;

    public Spell(SpellType name, String description, ImageIcon image, int cooldown, int manaCost, SpellEffect spellEffect) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.cooldown = cooldown;
        this.cooldownLast = cooldown;
        this.manaCost = manaCost;
        this.active = false;
        this.spellEffect = spellEffect;

        this.resetThread();
    }

    public SpellType getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCooldownLast() {
        return cooldownLast;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void resetCooldown() {
        this.cooldownLast = 0;
    }

    public boolean isSpellReady() {
        return this.cooldown == this.cooldownLast;
    }

    public Thread getThread() {
        return this.thread;
    }

    public int getManaCost() {
        return this.manaCost;
    }

    public void resetThread() {
        this.thread = new Thread(() -> {
            while (this.cooldownLast < this.cooldown) {
                this.cooldownLast++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void useSpell(Champion champion, ArrayList<Unit> unitSet) {
            this.spellEffect.useSpell(champion, this, unitSet);
    }

    public SpellEffect getSpellEffect() {
        return this.spellEffect;
    }
}
