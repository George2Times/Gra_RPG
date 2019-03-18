package Panels.Windows;

import GameLogic.Champion.Champion;

import java.awt.*;

public class StatisticsWindow {

    private int size;
    private int screenX;
    private int screenY;
    private boolean active;

    public StatisticsWindow(int size, int screenX, int screenY) {
        this.size = size;
        this.screenX = screenX;
        this.screenY = screenY;
        this.active = false;
    }

    public void paintWindow(Graphics g, Champion champion) {
        if(active) {
            int space = (int) (2.3 * size);
            int nameX = (int) (5.2 * size);
            int iconX = (int) (6.6 * size);
            int valueX = (int) (7.8 * size);
            int minusX = (int) (8.8 * size);
            int plusX = (int) (9 * size);

            g.setColor(Color.WHITE);
            g.fillRect(5 * size, 2 * size, screenX - 10 * size, screenY - 4 * size);

            g.setColor(Color.BLACK);
            //hitPoints
            g.drawString("Hit points", nameX, space);
            g.drawString("Icon", iconX, space);
            g.drawString(champion.getStatistics().getMaxHitPoints()+"", valueX, space);
            g.drawString("-", minusX, space);
            g.drawString("+", plusX, space);

            //mana
            space = space + 40;
            g.drawString("Mana", nameX, space);
            g.drawString("Icon", iconX, space);
            g.drawString(champion.getStatistics().getMaxManaPoints()+"", valueX, space);
            g.drawString("-", minusX, space);
            g.drawString("+", plusX, space);

            //attack
            space = space + 40;
            g.drawString("Damage", nameX, space);
            g.drawString("Icon", iconX, space);
            g.drawString(champion.getStatistics().getMinDamage()+"-"+champion.getStatistics().getMaxDamage(), valueX, space);
            g.drawString("-", minusX, space);
            g.drawString("+", plusX, space);

            //abilityPower
            space = space + 40;
            g.drawString("Ability power", nameX, space);
            g.drawString("Icon", iconX, space);
            g.drawString(champion.getStatistics().getAbilityPower()+"", valueX, space);
            g.drawString("-", minusX, space);
            g.drawString("+", plusX, space);

            //armor
            space = space + 40;
            g.drawString("Armor", nameX, space);
            g.drawString("Icon", iconX, space);
            g.drawString(champion.getStatistics().getArmor()+"", valueX, space);
            g.drawString("-", minusX, space);
            g.drawString("+", plusX, space);
        }
    }

    public void openClose() {
        this.active = !this.active;
    }

}
