package GameLogic.Units;

import Panels.Animations.Blood;

public class Golem extends Unit {

    public Golem(int x, int y) {
        super(x, y);
        this.maxHitPoints = 80;
        this.hitPoints = this.maxHitPoints;
        this.armor = 8;
        this.damageMin = 20;
        this.damageMax = 27;
        this.experience = 120;
        this.width = 83;
        this.height = 57;
        this.gold = 60;
        this.frames = 5;
        this.unitImages = new UnitImages("golem", frames, width, height);
        this.collisionWidth = 54;
        this.collisionHeight = 47;
        this.collisionX = 14;
        this.collisionXLeft = 14;
        this.collisionY = 10;
        this.blood = new Blood();
        this.attackSpeed = 160;
        this.side = true;
        this.movementSpeed = 4;
    }
}
