package GameLogic.Units;

import GameLogic.Champion.Champion;
import GameLogic.InfoSet.Info;
import Panels.Animations.Blood;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Unit {
    int hitPoints;
    int maxHitPoints;
    protected int x;
    protected int y;
    int armor;
    int damageMin;
    int damageMax;
    int experience;
    protected int width;
    protected int height;
    int gold;
    int frames;
    UnitImages unitImages;
    int collisionX;
    int collisionXLeft;
    int collisionY;
    int collisionWidth;
    int collisionHeight;
    Blood blood;
    int attackSpeed;
    boolean side;
    int movementSpeed;

    public Unit(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getArmor() {
        return armor;
    }

    public int getExperience() {
        return experience;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGold() {
        return gold;
    }

    public ImageIcon getImage() {
       return this.unitImages.getNextFrame();
    }

    public void dealDamage(int damage) {
        this.hitPoints -= damage;
        this.blood.start();
        if(this.hitPoints < 0) {
            this.hitPoints = 0;
        }
    }

    public boolean isUnitDead() {
        return this.hitPoints == 0;
    }

    public void move(Champion champion) {
        Thread thread = new Thread (() -> {
            while(this.hitPoints > 0) {
                if(calcDistance(champion) >= 300) {
                    this.unitImages.setIdle();
                }

                if(calcDistance(champion) < 300 && calcDistance(champion)  >= 60) {
                    this.approach(champion);
                    this.switchDirection(champion.getX() + champion.getWidth()/2);
                    this.unitImages.setWalk();
                }

                if(calcDistance(champion) < 60) {
                    this.switchDirection(champion.getX() + champion.getWidth()/2);
                    this.unitImages.setAttack();
                    for(int i = 0; i < frames; i++) {
                        try {
                            Thread.sleep(this.attackSpeed);
                            this.unitImages.incAttackCounter();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(this.hitPoints > 0 && i == 2) {
                            this.dealDamage(champion);
                        }
                    }
                    this.unitImages.setIdle();
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private int calcDistance(Champion champion) {
        int unitX = this.getCollisionX() + this.collisionWidth / 2;
        int unitY = this.getCollisionY() + this.collisionHeight / 2;

        int champX = champion.getX() + champion.getWidth()/2;
        int champY = champion.getY() + champion.getHeight()/2;

        int distance = (int) Math.pow(unitX - champX, 2) + (int) Math.pow(unitY - champY, 2);
        return (int) Math.sqrt(distance);
    }

    private void approach(Champion champion) {
        int unitXMiddle = this.getCollisionX() + this.collisionWidth/2;
        int unitYMiddle = this.getCollisionY() + this.collisionHeight/2;


        if(unitXMiddle > champion.getX() + champion.getWidth()/2 + 5) {
            this.x -= movementSpeed;
        }
        else if(unitXMiddle < champion.getX() + champion.getWidth()/2 - 5) {
            this.x += movementSpeed;
        }

        if(unitYMiddle > champion.getY() + champion.getHeight()/2 + 5) {
            this.y -= movementSpeed;
        }
        else if(unitYMiddle < champion.getY() + champion.getHeight()/2 - 5) {
            this.y += movementSpeed;
        }
    }

    private void dealDamage(Champion champion) {
        champion.getBlood().start();
        int damage = this.calculateDamage(champion.getStatistics().getArmor());
        champion.dealDamage(damage);
        champion.addInfo(new Info(champion.getX()+champion.getWidth()/2, champion.getY() + champion.getHeight()/2, ""+damage, Color.RED));
    }

    private int calculateDamage(int armor) {
        Random r = new Random();
        int dmg = r.nextInt(damageMax - damageMin + 1) + damageMin;
        return dmg * (100-armor) / 100;
    }

    public int getCollisionX() {
        if(this.side) {
            return x + collisionX;
        }
        else {
            return x + collisionXLeft;
        }
    }

    public int getCollisionY() {
        return this.y + collisionY;
    }

    public int getCollisionWidth() {
        return collisionWidth;
    }

    public int getCollisionHeight() {
        return collisionHeight;
    }

    private void switchDirection(int champX) {
        if(this.getCollisionX() + this.getCollisionWidth()/2 < champX) {
            if(!this.side) {
                //right
                this.unitImages.setSide(true);
                this.switchSide(true);
                this.x = this.x + collisionXLeft;
            }
        }
        else {
            if(this.side) {
                //left
                this.unitImages.setSide(false);
                this.switchSide(false);
                this.x = this.x - collisionXLeft;
            }
        }
    }

    public Blood getBlood() {
        return blood;
    }

    private void switchSide(boolean side) {
        this.side = side;
    }
}
