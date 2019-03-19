package GameLogic;

import GameLogic.Champion.Champion;
import GameLogic.Champion.ChampionImages;
import GameLogic.Equipment.Item;
import GameLogic.InfoSet.InfoSet;
import GameLogic.Spells.*;
import GameLogic.Spells.SpellEffects.FireballEffect;
import GameLogic.Spells.SpellEffects.HealEffect;
import GameLogic.Spells.SpellEffects.ShieldEffect;
import GameLogic.Spells.SpellEffects.TeleportationEffect;
import GameLogic.Units.Bandit;
import GameLogic.Units.Golem;
import GameLogic.Units.Unit;
import Panels.Map;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameEngine extends Thread {

    private Champion champion;
    private ScreenPosition screenPosition;
    private Map gameMap;
    private int squareSize;
    private int itemSize;
    private int spellSize;
    private ArrayList<Item> itemSet;
    private ArrayList<Spell> spellSet;
    private InfoSet infoSet;

    private ArrayList<Unit> unitSet;

    public GameEngine(int gameScreenX, int gameScreenY, int mapSizeX, int mapSizeY, int mapSquareSize, Map gameMap, int itemSize, int spellSize) {
        this.screenPosition = new ScreenPosition(0,0, gameScreenX, gameScreenY, mapSizeX*mapSquareSize, mapSizeY*mapSquareSize);
        this.infoSet = new InfoSet();
        this.champion = new Champion(screenPosition, infoSet);
        this.gameMap = gameMap;
        this.squareSize = mapSquareSize;
        this.itemSize = itemSize;
        this.spellSize = spellSize;
        this.initItemSet();
        this.initSpellSet();
        this.initUnitSet();

        this.addItemToChampion(this.itemSet.get(0));
        this.addItemToChampion(this.itemSet.get(1));
        this.addItemToChampion(this.itemSet.get(2));
        this.addItemToChampion(this.itemSet.get(3));
        this.addItemToChampion(this.itemSet.get(4));
        this.addItemToChampion(this.itemSet.get(5));

        this.addSpellToChampion(this.spellSet.get(0));
        this.addSpellToChampion(this.spellSet.get(1));
        this.addSpellToChampion(this.spellSet.get(2));
        this.addSpellToChampion(this.spellSet.get(3));
    }

    public void control(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.champion.move(0, -1);
                this.champion.switchDirection(Direction.UP);
                this.checkUpSquares();
                break;
            case KeyEvent.VK_S:
                this.champion.move(0, 1);
                this.champion.switchDirection(Direction.DOWN);
                this.checkDownSquares();
                break;
            case KeyEvent.VK_A:
                this.champion.move(-1, 0);
                this.champion.switchDirection(Direction.LEFT);
                this.checkLeftSquares();
                break;
            case KeyEvent.VK_D:
                this.champion.move(1, 0);
                this.champion.switchDirection(Direction.RIGHT);
                this.checkRightSquares();
                break;
            case KeyEvent.VK_1:
                this.champion.useSpell(0, unitSet);
                break;
            case KeyEvent.VK_2:
                this.champion.useSpell(1, unitSet);
                break;
            case KeyEvent.VK_3:
                this.champion.useSpell(2, unitSet);
                break;
            case KeyEvent.VK_4:
                this.champion.useSpell(3, unitSet);
                break;
            case KeyEvent.VK_SPACE:
                this.champion.basicAttack(unitSet);
                break;

        }
    }

    public Champion getChampion() {
        return this.champion;
    }

    public void updateScreenPosition(int width, int height, int topPanelHeight) {
        int positionX = this.champion.getX() - width/2 + this.champion.getWidth()/2;
        if(positionX < 0) {
            positionX = 0;
        }

        else if(positionX > (this.screenPosition.getMaxX() - width)) {
            positionX = (this.screenPosition.getMaxX() - width);
        }

        this.screenPosition.setX(positionX);

        int positionY = this.champion.getY() - height/2 + this.champion.getHeight()/2 + topPanelHeight/2;
        if(positionY < 0) {
            positionY = 0;
        }
        else if(positionY > (this.screenPosition.getMaxY() - height)) {
            positionY = (this.screenPosition.getMaxY() - height);
        }
        this.screenPosition.setY(positionY);
    }

    public ScreenPosition getScreenPosition() {
        return this.screenPosition;
    }

    private void checkUpSquares() {
        if(this.champion.getCollisionY() >= this.squareSize) {
            for(int i = 0; i < (this.champion.getCollisionWidth()/this.squareSize)+1; i++) {
                if(this.gameMap.getMapPixels()[this.champion.getCollisionX()/this.squareSize + i][this.champion.getCollisionY()/this.squareSize].getType() > 1) {
                    this.champion.setY(this.champion.getY() + this.champion.getSpeedY());
                }
            }
        }
    }

    private void checkDownSquares() {
        if(this.champion.getCollisionY() <= this.screenPosition.getMaxY() - this.champion.getCollisionHeight()) {
            for(int i = 0; i < (this.champion.getCollisionWidth()/this.squareSize)+1; i++) {
                if(this.gameMap.getMapPixels()[this.champion.getCollisionX()/this.squareSize + i][(this.champion.getCollisionY()+this.champion.getCollisionHeight())/this.squareSize].getType() > 1) {
                    this.champion.setY(this.champion.getY() - this.champion.getSpeedY());
                }
            }
        }
    }

    private void checkLeftSquares() {
        if(this.champion.getCollisionX() >= this.squareSize) {
            for(int i = 0; i < (this.champion.getCollisionHeight()/this.squareSize)+1; i++) {
                if(this.gameMap.getMapPixels()[this.champion.getCollisionX()/this.squareSize][(this.champion.getCollisionY())/this.squareSize+i].getType() > 1) {
                    this.champion.setX(this.champion.getX() + this.champion.getSpeedX());
                }
            }
        }
    }

    private void checkRightSquares() {
        if(this.champion.getCollisionX() <= this.screenPosition.getMaxX() - this.champion.getWidth()) {
            for(int i = 0; i < (this.champion.getCollisionHeight()/this.squareSize)+1; i++) {
                if(this.gameMap.getMapPixels()[(this.champion.getCollisionX()+this.champion.getCollisionWidth())/this.squareSize][(this.champion.getCollisionY())/this.squareSize+i].getType() > 1) {
                    this.champion.setX(this.champion.getX() - this.champion.getSpeedX());
                }
            }
        }
    }

    private void initItemSet() {
        this.itemSet = new ArrayList<>();

        Item i1 = new Item("Dagger", 2,4,0,0,0,0,0, ChampionImages.scaleImage("resources/items/dagger.png",this.itemSize,this.itemSize));
        Item i2 = new Item("Armor", 0,0,51,0,3,0,0,ChampionImages.scaleImage("resources/items/armor.png",this.itemSize,this.itemSize));
        Item i3 = new Item("Helmet", 0,0,0,0,2,0,0,ChampionImages.scaleImage("resources/items/helmet.png",this.itemSize,this.itemSize));
        Item i4 = new Item("Ring", 0,0,0,20,0,4,0,ChampionImages.scaleImage("resources/items/ring.png",this.itemSize,this.itemSize));
        Item i5 = new Item("Book", 0,0,0,30,0,9,0,ChampionImages.scaleImage("resources/items/book.png",this.itemSize,this.itemSize));
        Item i6 = new Item("Book", 0,0,0,30,0,15,0,ChampionImages.scaleImage("resources/items/ruby.png",this.itemSize,this.itemSize));
        this.itemSet.add(i1);
        this.itemSet.add(i2);
        this.itemSet.add(i3);
        this.itemSet.add(i4);
        this.itemSet.add(i5);
        this.itemSet.add(i6);
    }

    private void addItemToChampion(Item item) {
        this.champion.addItem(item);
    }

    private void initSpellSet() {
        this.spellSet = new ArrayList<>();

        Spell s1 = new Spell(SpellType.Fireball, "Deals 100 dmg", ChampionImages.scaleImage("resources/spells/fire.png",spellSize ,spellSize), 20, 20, new FireballEffect());
        Spell s2 = new Spell(SpellType.Heal, "Heals for 50 hp", ChampionImages.scaleImage("resources/spells/heal.png",spellSize ,spellSize), 50, 20, new HealEffect());
        Spell s3 = new Spell(SpellType.Shield, "Armor increased for 5 seconds", ChampionImages.scaleImage("resources/spells/shield.png",spellSize ,spellSize), 80, 20, new ShieldEffect());
        Spell s4 = new Spell(SpellType.Teleportation, "Teleport champion 32 forward", ChampionImages.scaleImage("resources/spells/teleportation.png",spellSize ,spellSize), 10, 10, new TeleportationEffect());

        this.spellSet.add(s1);
        this.spellSet.add(s2);
        this.spellSet.add(s3);
        this.spellSet.add(s4);
    }

    private void addSpellToChampion(Spell spell) {
        this.champion.addSpell(spell);
    }

    private void initUnitSet() {
        this.unitSet = new ArrayList<>();


        addUnit(new Bandit(100,100));
        addUnit(new Bandit(100,200));
        addUnit(new Bandit(100,300));
        addUnit(new Bandit(100,400));
        addUnit(new Golem(1200,400));
        addUnit(new Golem(1200,500));
        addUnit(new Golem(1200,600));
        addUnit(new Golem(1300,600));
        addUnit(new Golem(1100,600));

    }

    public ArrayList<Unit> getUnitSet() {
        return unitSet;
    }

    private void addUnit(Unit u) {
        u.move(champion);
        this.unitSet.add(u);
    }
}
