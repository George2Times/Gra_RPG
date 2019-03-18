package GameLogic.Champion;

import GameLogic.Direction;
import GameLogic.Equipment.Item;
import GameLogic.InfoSet.Info;
import GameLogic.InfoSet.InfoSet;
import GameLogic.ScreenPosition;
import GameLogic.Spells.Spell;
import GameLogic.Spells.SpellInstance;
import GameLogic.Units.Unit;
import Panels.Animations.Blood;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Champion {

    private Statistics statistics;
    private Level level;
    private int x;
    private int y;
    private int width;
    private int height;
    private int speedX;
    private int speedY;
    private Direction direction;
    private int frameCounter;
    private ScreenPosition screenPosition;
    private ChampionImages championImages;

    private ArrayList<Item> itemList;

    private ArrayList<Spell> spellList;

    private SpellInstance spellInstance;

    private InfoSet infoSet;

    private int range = 50;
    private boolean attackFlag;

    private Blood blood;

    public Champion(ScreenPosition screenPosition, InfoSet infoSet) {
        this.statistics = new Statistics();
        this.level = new Level(this.statistics);
        this.x = 800;
        this.y = 200;
        this.width = 49;
        this.height = 77;
        this.speedX = 4;
        this.speedY = 4;

        this.screenPosition = screenPosition;
        this.direction = Direction.DOWN;
        this.frameCounter = 0;
        this.championImages = new ChampionImages(this.width, this.height);

        this.itemList = new ArrayList<>();
        this.spellList = new ArrayList<>();

        this.spellInstance = null;

        this.infoSet = infoSet;
        this.attackFlag = true;

        this.blood = new Blood();
    }

    public void move(int x, int y) {
        this.x = this.x + this.speedX * x;
        this.y = this.y + this.speedY * y;

        if (this.x < 0) {
            this.x = 0;
        } else if (this.x > screenPosition.getMaxX() - this.width) {
            this.x = screenPosition.getMaxX() - this.width;
        }

        if (this.y < 0) {
            this.y = 0;
        } else if (this.y > screenPosition.getMaxY() - this.height) {
            this.y = screenPosition.getMaxY() - this.height;
        }


        this.frameCounter++;
        if (this.frameCounter == 6) {
            this.frameCounter = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void switchDirection(Direction direction) {
        this.direction = direction;
    }

    public ImageIcon getImage() {
        return this.championImages.getImage(this.direction, this.frameCounter);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public int getCollisionX() {
        return this.x + 8;
    }

    public int getCollisionWidth() {
        return 32;
    }

    public int getCollisionY() {
        return this.y + 29;
    }

    public int getCollisionHeight() {
        return 48;
    }

    public Direction getDirection() {
        return direction;
    }

    public void addItem(Item item) {
        if (this.itemList.size() < 14) {
            this.itemList.add(item);
            this.statistics.addItem(item);
        }
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public void addSpell(Spell spell) {
        this.spellList.add(spell);
    }

    public ArrayList<Spell> getSpellList() {
        return this.spellList;
    }

    public void useSpell(int spell, ArrayList<Unit> unitSet) {
        if (this.spellList.get(spell).isSpellReady() && this.statistics.getManaPoints() >= this.getSpellList().get(spell).getManaCost()) {
            this.spellList.get(spell).resetCooldown();
            this.statistics.setManaPoints(this.statistics.getManaPoints() - this.getSpellList().get(spell).getManaCost());
            this.spellList.get(spell).setActive(true);
            this.spellList.get(spell).resetThread();
            this.spellList.get(spell).getThread().start();

            this.spellList.get(spell).useSpell(this, unitSet);
        }
    }

    public void addSpellInstance(SpellInstance spellInstance) {
        this.spellInstance = spellInstance;
    }

    public SpellInstance getSpellInstance() {
        return spellInstance;
    }

    public Statistics getStatistics() {
        return this.statistics;
    }

    public Level getLevel() {
        return this.level;
    }

    public void addExperience(int experience) {
        this.level.addExperience(experience);
    }

    public void dealDamage(int damage) {
        this.statistics.dealDamage(damage);
    }

    public void addGold(int gold) {
        this.statistics.addGold(gold);
    }

    public void addInfo(Info info) {
        this.infoSet.addInfo(info);
    }

    public InfoSet getInfoSet() {
        return infoSet;
    }

    public void basicAttack(ArrayList<Unit> unitSet) {
        if(this.attackFlag) {
            this.attackFlag = false;
            for (int i = 0; i < unitSet.size(); i++) {
                if (isUnitInRange(unitSet.get(i))) {
                    this.dealDamage(unitSet.get(i), unitSet, i);
                    break;
                }
            }

            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.attackFlag = true;
            }).start();

        }
    }

    private void dealDamage(Unit unit, ArrayList<Unit> unitSet, int i) {
        int dmg = this.calculateDamage(unit);
        unit.dealDamage(dmg);
        this.addInfo(new Info(unit.getX(), unit.getY(), ""+dmg, Color.RED));
        if(unit.isUnitDead()) {
            this.addExperience(unit.getExperience());
            this.addGold(unit.getGold());
            this.addInfo(new Info(unit.getX()+unit.getWidth()/2, unit.getY() + unit.getHeight()/2, "+" + unit.getGold(), Color.YELLOW));
            unitSet.remove(i);
        }
    }

    private int calculateDamage(Unit unit) {
        Random r = new Random();
        int dmg = r.nextInt(this.statistics.getMaxDamage() - this.statistics.getMinDamage() + 1) + this.statistics.getMinDamage();
        return dmg * (100-unit.getArmor()) / 100;
    }

    private boolean isUnitInRange(Unit unit) {
        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        switch(this.direction) {
            case UP:
                x = this.x;
                y = this.y - range;
                width = this.width;
                height = range;
                break;
            case DOWN:
                x = this.x;
                y = this.y + this.height;
                width = this.width;
                height = range;
                break;
            case RIGHT:
                x = this.x + this.width;
                y = this.y;
                width = range;
                height = this.height;
                break;
            case LEFT:
                x = this.x - range;
                y = this.y;
                width = range;
                height = this.height;
                break;
        }
        Rectangle obj1 = new Rectangle(x, y, width, height);
        Rectangle obj2 = new Rectangle(unit.getCollisionX(), unit.getCollisionY(), unit.getCollisionWidth(), unit.getCollisionHeight());
        return obj1.intersects(obj2);
    }

    public Blood getBlood() {
        return blood;
    }
}
