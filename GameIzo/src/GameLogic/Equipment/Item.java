package GameLogic.Equipment;

import javax.swing.*;

public class Item {

    private String name;
    private int minDamage;
    private int maxDamage;
    private int hitPoints;
    private int manaPoints;
    private int armor;
    private int abilityPower;
    private int cost;
    private ImageIcon icon;

    public Item(String name, int minDamage, int maxDamage, int hitPoints, int manaPoints, int armor, int abilityPower, int cost, ImageIcon icon) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.hitPoints = hitPoints;
        this.manaPoints = manaPoints;
        this.armor = armor;
        this.abilityPower = abilityPower;
        this.cost = cost;
        this.icon = icon;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public int getArmor() {
        return armor;
    }

    public int getAbilityPower() {
        return abilityPower;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
