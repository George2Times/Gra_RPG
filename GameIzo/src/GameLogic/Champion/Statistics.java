package GameLogic.Champion;

import GameLogic.Equipment.Item;

public class Statistics {

    private int hitPoints;
    private int maxHitPoints;
    private int manaPoints;
    private int maxManaPoints;
    private int minDamage;
    private int maxDamage;
    private int armor;
    private int abilityPower;
    private int gold;

    private int hpRegen;
    private int manaRegen;

    private int pointsAvailable;

    public Statistics() {
        this.hpRegen = 1;
        this.manaRegen = 3;
        this.maxHitPoints = 100;
        this.hitPoints = this.maxHitPoints;
        this.maxManaPoints = 100;
        this.manaPoints = this.maxManaPoints;
        this.minDamage = 8;
        this.maxDamage = 11;
        this.armor = 3;
        this.abilityPower = 1;
        this.gold = 0;

        this.pointsAvailable = 5;

        Thread thread = new Thread(() -> {
            while(this.hitPoints > 0) {
                this.hitPoints = this.hitPoints + this.hpRegen;
                if(this.hitPoints > this.maxHitPoints) {
                    this.hitPoints = this.maxHitPoints;
                }

                this.manaPoints = this.manaPoints + this.manaRegen;
                if(this.manaPoints > this.maxManaPoints) {
                    this.manaPoints = this.maxManaPoints;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public int getMaxManaPoints() {
        return maxManaPoints;
    }

    public void setMaxManaPoints(int maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public void setAbilityPower(int abilityPower) {
        this.abilityPower = abilityPower;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHpRegen() {
        return hpRegen;
    }

    public void setHpRegen(int hpRegen) {
        this.hpRegen = hpRegen;
    }

    public int getManaRegen() {
        return manaRegen;
    }

    public void setManaRegen(int manaRegen) {
        this.manaRegen = manaRegen;
    }

    public void addItem(Item item) {
        this.minDamage += item.getMinDamage();
        this.maxDamage += item.getMaxDamage();
        this.abilityPower += item.getAbilityPower();
        this.armor += item.getArmor();
        this.maxHitPoints += item.getHitPoints();
        this.maxManaPoints += item.getManaPoints();
    }

    public void dealDamage(int damage) {
        this.hitPoints -= damage;
        if(this.hitPoints < 0) {
            this.hitPoints = 0;
        }
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public int getPointsAvailable() {
        return pointsAvailable;
    }

    public void addStats(int hp, int mana, int dmg, int ability) {
        this.hitPoints += hp*10;
        this.maxHitPoints += hp*10;

        this.manaPoints += mana*10;
        this.maxManaPoints += mana*10;

        this.minDamage += dmg;
        this.maxDamage += dmg;

        this.abilityPower += ability;
    }

    public void setPointsAvailable(int points) {
        this.pointsAvailable += points;
    }
}
