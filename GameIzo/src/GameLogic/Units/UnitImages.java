package GameLogic.Units;

import GameLogic.Champion.ChampionImages;

import javax.swing.*;

public class UnitImages {

    private ImageIcon[] idle;
    private ImageIcon[] idleLeft;
    private ImageIcon[] walk;
    private ImageIcon[] attack;
    private ImageIcon[] attackLeft;
    private ImageIcon[] walkLeft;
    private int counter;
    private int frames;
    private Types type;
    private boolean isRightSide;

    UnitImages(String name, int frames, int width, int height) {
        this.frames = frames;

        this.idle = new ImageIcon[frames];
        for(int i = 0; i < frames; i++) {
            this.idle[i] = ChampionImages.scaleImage("resources/units/"+ name +"/idle/idle"+ i +".png", width, height);
        }

        this.walk = new ImageIcon[frames];
        for(int i = 0; i < frames; i++) {
            this.walk[i] = ChampionImages.scaleImage("resources/units/"+ name +"/walk/walk"+ i +".png", width, height);
        }

        this.attack = new ImageIcon[frames+1];
        for(int i = 0; i < frames; i++) {
            this.attack[i] = ChampionImages.scaleImage("resources/units/"+ name +"/attack/attack"+ i +".png", width, height);
        }

        this.idleLeft = new ImageIcon[frames+1];
        for(int i = 0; i < frames; i++) {
            this.idleLeft[i] = ChampionImages.scaleImage("resources/units/"+ name +"/idle/idle"+ i + "left" +".png", width, height);
        }

        this.attackLeft = new ImageIcon[frames+1];
        for(int i = 0; i < frames; i++) {
            this.attackLeft[i] = ChampionImages.scaleImage("resources/units/"+ name +"/attack/attack"+ i + "left" +".png", width, height);
        }

        this.walkLeft = new ImageIcon[frames+1];
        for(int i = 0; i < frames; i++) {
            this.walkLeft[i] = ChampionImages.scaleImage("resources/units/"+ name +"/walk/walk"+ i + "left" +".png", width, height);
        }

        this.isRightSide = true;
        this.type = Types.IDLE;
    }

    private void incCounter() {
        this.counter++;
        if(counter >= frames*3) {
            this.counter = 0;
        }
    }

    ImageIcon getNextFrame() {
        if (this.type != Types.ATTACK && this.type != Types.ATTACK_LEFT) {
            this.incCounter();
        }
        switch (this.type) {
            case IDLE:
                return this.idle[counter/3];
            case WALK:
                return this.walk[counter/3];
            case ATTACK:
                return this.attack[counter/3];
            case WALK_LEFT:
                return this.walkLeft[counter/3];
            case ATTACK_LEFT:
                return this.attackLeft[counter/3];
            case IDLE_LEFT:
                return this.idleLeft[counter/3];
        }
        return null;
    }


    private void setType(Types type) {
        if (this.type != type) {
            this.type = type;
            this.counter = 0;
        }
    }

    void incAttackCounter() {
        this.counter += 3;
    }

    void setIdle() {
        if(isRightSide) {
            this.setType(Types.IDLE);
        }
        else {
            this.setType(Types.IDLE_LEFT);
        }
    }

    void setAttack() {
        if(isRightSide) {
            this.setType(Types.ATTACK);
        }
        else {
            this.setType(Types.ATTACK_LEFT);
        }
    }

    void setWalk() {
        if(isRightSide) {
            this.setType(Types.WALK);
        }
        else {
            this.setType(Types.WALK_LEFT);
        }
    }

    void setSide(boolean isRight) {
        this.isRightSide = isRight;
    }
}
