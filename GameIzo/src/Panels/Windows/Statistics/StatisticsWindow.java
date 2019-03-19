package Panels.Windows.Statistics;


import GameLogic.Champion.Champion;
import GameLogic.Champion.ChampionImages;

import javax.swing.*;
import java.awt.*;

public class StatisticsWindow extends JComponent {


    private Champion champion;
    private int points;

    private int size;
    private int screenX;
    private int screenY;
    private boolean active;

    private int hpPoints;
    private int manaPoints;
    private int damagePoints;
    private int abilityPoints;

    private int buttonWidth;
    private int buttonHeight;

    private int iconX;
    private int nameX;
    private int valueX;
    private int minusX;
    private int pointsX;
    private int plusX;

    private PlusButton hitPointsPlusButton;
    private MinusButton hitPointsMinusButton;

    private PlusButton manaPointsPlusButton;
    private MinusButton manaPointsMinusButton;

    private PlusButton damagePointsPlusButton;
    private MinusButton damagePointsMinusButton;

    private PlusButton abilityPointsPlusButton;
    private MinusButton abilityPointsMinusButton;

    private PlusButton confirmButton;

    public StatisticsWindow(int size, int screenX, int screenY, Champion champion) {
        this.champion = champion;
        this.size = size;
        this.screenX = screenX;
        this.screenY = screenY;
        this.active = false;

        this.hpPoints = 0;
        this.manaPoints = 0;
        this.damagePoints = 0;
        this.abilityPoints = 0;

        this.buttonWidth = size/4;
        this.buttonHeight = buttonWidth * 4 / 5;

        this.iconX = (int) (5.3 * size);
        this.nameX = (int) (5.7 * size);
        this.valueX = (int) (7.5 * size);
        this.minusX = (int) (8.1 * size);
        this.pointsX = (int) (8.48 * size);
        this.plusX = (int) (8.7 * size);

        this.hitPointsPlusButton = new PlusButton(plusX,(int) (3.2 * size), buttonWidth, buttonHeight);
        this.hitPointsPlusButton.addActionListener(e -> {
            if(this.points < this.champion.getStatistics().getPointsAvailable()) {
                this.hpPoints++;
                this.points++;
            }
        } );

        this.hitPointsMinusButton = new MinusButton(minusX,(int) (3.2 * size), buttonWidth, buttonHeight);
        this.hitPointsMinusButton.addActionListener(e -> {
            if(this.hpPoints > 0) {
                this.hpPoints--;
                this.points--;
            }
        });

        this.manaPointsPlusButton = new PlusButton(plusX,(int) (3.2 * size) + 40, buttonWidth, buttonHeight);
        this.manaPointsPlusButton.addActionListener(e -> {
            if(this.points < this.champion.getStatistics().getPointsAvailable()) {
                this.manaPoints++;
                this.points++;
            }
        } );

        this.manaPointsMinusButton = new MinusButton(minusX,(int) (3.2 * size) + 40, buttonWidth, buttonHeight);
        this.manaPointsMinusButton.addActionListener(e -> {
            if(this.manaPoints > 0) {
                this.manaPoints--;
                this.points--;
            }
        } );

        this.damagePointsPlusButton = new PlusButton(plusX,(int) (3.2 * size) + 80, buttonWidth, buttonHeight);
        this.damagePointsPlusButton.addActionListener(e -> {
            if(this.points < this.champion.getStatistics().getPointsAvailable()) {
                this.damagePoints++;
                this.points++;
            }
        } );

        this.damagePointsMinusButton = new MinusButton(minusX,(int) (3.2 * size) + 80, buttonWidth, buttonHeight);
        this.damagePointsMinusButton.addActionListener(e -> {
            if(this.damagePoints > 0) {
                this.damagePoints--;
                this.points--;
            }
        });

        this.abilityPointsPlusButton = new PlusButton(plusX,(int) (3.2 * size) + 120, buttonWidth, buttonHeight);
        this.abilityPointsPlusButton.addActionListener(e -> {
            if(this.points < this.champion.getStatistics().getPointsAvailable()) {
                this.abilityPoints++;
                this.points++;
            }
        } );

        this.abilityPointsMinusButton = new MinusButton(minusX,(int) (3.2 * size) + 120, buttonWidth, buttonHeight);
        this.abilityPointsMinusButton.addActionListener(e -> {
            if(this.abilityPoints > 0) {
                this.abilityPoints--;
                this.points--;
            }
        });

        int width = size;
        int height = (int) (size*0.4);
        int x = screenX - (int) (7.5)*size - width/2;
        int y = screenY - (int) (2.65*size);
        this.confirmButton = new PlusButton(x,y,width,height);
        this.confirmButton.addActionListener(e -> {
            this.active = false;
            this.champion.getStatistics().addStats(this.hpPoints, this.manaPoints, this.damagePoints, this.abilityPoints);
            this.hpPoints = 0;
            this.manaPoints = 0;
            this.damagePoints = 0;
            this.abilityPoints = 0;
        });
    }

    public void paintComponent(Graphics g) {
        if(active) {

            g.setFont(new Font("Times New Roman", Font.BOLD, (int) (size * 0.3)));
            int space = (int) (3.2 * size);

            ChampionImages.scaleImage("resources/statistics.png",screenX - 10 * size ,screenY - 4 * size).paintIcon(this, g, 5 * size, 2 * size);

            g.setColor(Color.WHITE);
            g.drawString("Statistics", this.iconX, space - 80);


            //all points
            g.setFont(new Font("Times New Roman", Font.BOLD, size/6));
            g.drawString("Points left: " + (this.champion.getStatistics().getPointsAvailable() - this.points), this.iconX, space - 30);


            //hitPoints
            g.drawString("Hit points", nameX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/hpIcon.png",buttonHeight, buttonHeight).paintIcon(this, g, iconX, space);
            g.drawString(champion.getStatistics().getMaxHitPoints()+"", valueX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/minus.png",buttonWidth, buttonHeight).paintIcon(this, g, minusX, space);
            g.drawString(this.hpPoints+"", pointsX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/plus.png",buttonWidth, buttonHeight).paintIcon(this, g, plusX, space);


            //mana
            space = space + 40;
            g.drawString("Mana", nameX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/manaIcon.png",buttonHeight, buttonHeight).paintIcon(this, g, iconX, space);
            g.drawString(champion.getStatistics().getMaxManaPoints()+"", valueX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/minus.png",buttonWidth, buttonHeight).paintIcon(this, g, minusX, space);
            g.drawString(this.manaPoints+"", pointsX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/plus.png",buttonWidth, buttonHeight).paintIcon(this, g, plusX, space);

            //attack
            space = space + 40;
            g.drawString("Damage", nameX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/attackIcon.png",buttonHeight, buttonHeight).paintIcon(this, g, iconX, space);
            g.drawString(champion.getStatistics().getMinDamage()+"-"+champion.getStatistics().getMaxDamage(), valueX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/minus.png",buttonWidth, buttonHeight).paintIcon(this, g, minusX, space);
            g.drawString(this.damagePoints+"", pointsX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/plus.png",buttonWidth, buttonHeight).paintIcon(this, g, plusX, space);

            //abilityPower
            space = space + 40;
            g.drawString("Ability power", nameX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/magicIcon.png",buttonHeight, buttonHeight).paintIcon(this, g, iconX, space);
            g.drawString(champion.getStatistics().getAbilityPower()+"", valueX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/minus.png",buttonWidth, buttonHeight).paintIcon(this, g, minusX, space);
            g.drawString(this.abilityPoints+"", pointsX, space + g.getFont().getSize());
            ChampionImages.scaleImage("resources/plus.png",buttonWidth, buttonHeight).paintIcon(this, g, plusX, space);

            //confirm
            int width = size;
            int height = (int) (size*0.4);
            int x = screenX - (int) (7.5)*size - width/2;
            int y = screenY - (int) (2.65*size);
            ChampionImages.scaleImage("resources/confirm.png",width ,height).paintIcon(this, g, x, y);
        }
    }

    public void openClose() {
        this.active = !this.active;
    }


    public Champion getChampion() {
        return champion;
    }

    public PlusButton getHitPointsPlusButton() {
        return hitPointsPlusButton;
    }

    public MinusButton getHitPointsMinusButton() {
        return hitPointsMinusButton;
    }

    public PlusButton getManaPointsPlusButton() {
        return manaPointsPlusButton;
    }

    public MinusButton getManaPointsMinusButton() {
        return manaPointsMinusButton;
    }

    public PlusButton getDamagePointsPlusButton() {
        return damagePointsPlusButton;
    }

    public MinusButton getDamagePointsMinusButton() {
        return damagePointsMinusButton;
    }

    public PlusButton getAbilityPointsPlusButton() {
        return abilityPointsPlusButton;
    }

    public MinusButton getAbilityPointsMinusButton() {
        return abilityPointsMinusButton;
    }

    public PlusButton getConfirmButton() {
        return confirmButton;
    }
}
