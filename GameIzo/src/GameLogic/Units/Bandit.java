package GameLogic.Units;

import Panels.Animations.Blood;

public class Bandit extends Unit{

    public Bandit(int x, int y) {
        super(x, y);
        this.maxHitPoints = 50;
        this.hitPoints = this.maxHitPoints;
        this.armor = 12;
        this.damageMin = 12;
        this.damageMax = 15;
        this.experience = 40;
        this.width = 71;
        this.height = 55;
        this.gold = 30;
        this.frames = 5;
        this.unitImages = new UnitImages("bandit", frames, width, height);
        this.collisionWidth = 30;
        this.collisionHeight = 55;
        this.collisionX = 0;
        this.collisionXLeft = 41;
        this.collisionY = 0;
        this.blood = new Blood();
        this.attackSpeed = 120;
        this.side = true;
        this.movementSpeed = 5;
    }
}
