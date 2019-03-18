package GameLogic.Spells.SpellEffects;

import GameLogic.Champion.Champion;
import GameLogic.Champion.ChampionImages;
import GameLogic.InfoSet.Info;
import GameLogic.Spells.SpellInstance;
import GameLogic.Spells.Spell;
import GameLogic.Units.Unit;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FireballEffect implements SpellEffect {

    private int range = 300;
    private int framesNumber = 20;
    private int i;
    private int width = 40;
    private int height = 40;
    private int damage = 25;

    @Override
    public void useSpell(Champion champion, Spell spell, ArrayList<Unit> unitSet) {
        ImageIcon image = ChampionImages.scaleImage("resources/animations/fireball/fireball.png", width, height);
        SpellInstance spellInstance = new SpellInstance(champion.getX() + champion.getWidth()/2 - width/2, champion.getY() + champion.getHeight()/3, champion.getDirection(), width, height, image);
        champion.addSpellInstance(spellInstance);
        i = 0;
        Thread thread = new Thread(() -> {
            while ( i  < framesNumber) {
                if(champion.getSpellInstance() != null) {
                    this.moveSpellInstance(spellInstance);
                    this.checkCollision(champion, unitSet, spellInstance);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            i = 0;
            champion.getSpellList().get(0).setActive(false);
        });

        thread.start();
    }

    public int getRange() {
        return this.range;
    }

    private void moveSpellInstance(SpellInstance spellInstance) {
        switch(spellInstance.getDirection()) {
            case UP:
                spellInstance.setY(spellInstance.getY() - range/framesNumber);
                break;
            case DOWN:
                spellInstance.setY(spellInstance.getY() + range/framesNumber);
                break;
            case RIGHT:
                spellInstance.setX(spellInstance.getX() + range/framesNumber);
                break;
            case LEFT:
                spellInstance.setX(spellInstance.getX() - range/framesNumber);
                break;
        }
    }

    private void checkCollision(Champion champion, ArrayList<Unit> unitSet, SpellInstance spellInstance) {
        for(int i = 0; i < unitSet.size(); i++) {
            Unit u = unitSet.get(i);
            if(areObjectsColliding(u.getCollisionX(), u.getCollisionY(), u.getCollisionWidth(), u.getCollisionHeight(),
                    spellInstance.getX(), spellInstance.getY(), spellInstance.getWidth(), spellInstance.getHeight())) {
                u.dealDamage(damage);
                champion.addInfo(new Info(u.getX(), u.getY(), ""+damage, Color.RED));
                spellInstance.setY(10000);
                if(u.isUnitDead()) {
                    champion.addExperience(u.getExperience());
                    champion.addGold(u.getGold());
                    champion.addInfo(new Info(u.getX()+u.getWidth()/2, u.getY() + u.getHeight()/2, "+" + u.getGold(), Color.YELLOW));
                    unitSet.remove(i);
                    return;
                }
            }
        }
    }

    static boolean areObjectsColliding(int obj1X, int obj1Y, int obj1W, int obj1H, int obj2X, int obj2Y, int obj2W, int obj2H) {
        Rectangle obj1 = new Rectangle(obj1X, obj1Y, obj1W, obj1H);
        Rectangle obj2 = new Rectangle(obj2X, obj2Y, obj2W, obj2H);
        return obj1.intersects(obj2);
    }
}
