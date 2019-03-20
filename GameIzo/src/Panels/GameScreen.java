package Panels;

import GameLogic.Champion.Champion;
import GameLogic.ScreenPosition;
import GameLogic.Spells.SpellEffects.FireballEffect;
import Panels.Animations.Fireball;
import Panels.Animations.Heal;
import Panels.Animations.Shield;
import Panels.Animations.Teleportation;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JComponent {

    private ScreenPosition screenPosition;
    private int x;
    private int y;
    private int width;
    private int height;
    private Map gameMap;

    private Fireball fireball;
    private Heal heal;
    private Shield shield;
    private Teleportation teleportation;
    private FireballEffect fireballEffect;

    public GameScreen(int x, int y, int width, int height, int mapSizeX, int mapSizeY, int mapSquareSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameMap = new Map(mapSizeX, mapSizeY, mapSquareSize);

        this.heal = new Heal();
        this.shield = new Shield();
        this.fireball = new Fireball();
        this.teleportation = new Teleportation();
        this.fireballEffect = new FireballEffect();
    }

    @Override
    public void paintComponent(Graphics g) {
        int modX = this.screenPosition.getX()%gameMap.getSquareSize();
        int modY = this.screenPosition.getY()%gameMap.getSquareSize();


        for(int i = 0; i < width/gameMap.getSquareSize() + 2; i++) {
            for(int j = 0; j < height/gameMap.getSquareSize() + 2; j++) {
                this.gameMap.getMapPixels()[i+this.screenPosition.getX()/gameMap.getSquareSize()][j+this.screenPosition.getY()/gameMap.getSquareSize()].getTexture().paintIcon(this, g, x+(i*gameMap.getSquareSize())-modX, y+(j*gameMap.getSquareSize())-modY);
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Map getGameMap() {
        return this.gameMap;
    }

    public void setScreenPosition(ScreenPosition screenPosition) {
        this.screenPosition = screenPosition;
    }

    public void paintAnimations(Graphics g, Champion champion, int positionX, int positionY) {
        if(champion.getSpellList().get(1).getActive()) {
            this.heal.paintAnimation(g, champion, positionX, positionY);
            Thread thread = new Thread(() -> {
                while (this.heal.getCounter() < 6) {
                    this.heal.incCounter();
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.heal.resetCounter();
                champion.getSpellList().get(1).setActive(false);
            });

            if (this.heal.getCounter() == 0) {
                thread.start();
            }
        }

        if(champion.getSpellList().get(2).getActive()) {
            this.shield.paintAnimation(g, champion, positionX, positionY);
            Thread thread = new Thread(() -> {
                while (champion.getSpellList().get(2).getActive()) {
                    this.shield.incCounter();
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.shield.resetCounter();
            });

            if (this.shield.getCounter() == 0) {
                thread.start();
            }
        }

        if(champion.getSpellList().get(3).getActive()) {
            this.teleportation.paintAnimation(g, champion, positionX, positionY);
            Thread thread = new Thread(() -> {
                while (this.teleportation.getCounter() < 3) {
                    this.teleportation.incCounter();
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.teleportation.resetCounter();
                champion.getSpellList().get(3).setActive(false);
            });

            if (this.teleportation.getCounter() == 0) {
                thread.start();
            }
        }

        if(champion.getSpellList().get(0).getActive()) {
            this.fireball.paintAnimation(g, champion, positionX, positionY);
            Thread thread = new Thread(() -> {
                while (this.fireball.getCounter() < this.fireball.getFramesNumber()) {
                    this.fireball.incCounter();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                this.fireball.resetCounter();
                champion.getSpellList().get(0).setActive(false);
            });

            if (this.fireball.getCounter() == 0) {
                thread.start();
            }
        }
    }
}
