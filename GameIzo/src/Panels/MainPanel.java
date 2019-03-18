package Panels;

import GameLogic.GameEngine;
import GameLogic.InfoSet.Info;
import GameLogic.Units.Unit;
import Panels.Windows.StatisticsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MainPanel extends JPanel {

    private GameEngine gameEngine;
    private int screenX;
    private int screenY;

    private TopPanel topPanel;
    private GameScreen gameScreen;

    private StatisticsWindow statisticsWindow;

    int mapSizeX = 1000;
    int mapSizeY = 1000;
    int mapSquareSize = 16;

    int topPanelHeight;

    public MainPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenX = (int) screenSize.getWidth();
        this.screenY = (int) screenSize.getHeight();

        int marginLeft = 0;
        this.topPanelHeight = this.screenY / 8;
        int marginTop = topPanelHeight + marginLeft;
        this.setLayout(null);


        this.setBounds(0, 0, this.screenX, this.screenY);


        this.initGameScreen();

        this.gameEngine = new GameEngine(marginLeft, marginTop, mapSizeX, mapSizeY, mapSquareSize, this.gameScreen.getGameMap(), this.topPanelHeight * 9 / 20, this.topPanelHeight * 6 / 10);
        this.topPanel = new TopPanel(topPanelHeight, this.gameEngine.getChampion(), this.topPanelHeight * 9 / 20);
        this.gameScreen.setScreenPosition(this.gameEngine.getScreenPosition());

        this.statisticsWindow = new StatisticsWindow(topPanelHeight, screenX, screenY);

        Thread thread = new Thread(() -> {
            while (true) {
                repaint();
                this.gameEngine.updateScreenPosition(this.gameScreen.getWidth(), this.gameScreen.getHeight(), topPanelHeight);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(20, 60, 90));
        g.fillRect(0, 0, this.screenX, this.screenY);

        int positionX = this.gameEngine.getChampion().getX() - this.gameEngine.getScreenPosition().getX() + this.gameEngine.getScreenPosition().getGameScreenX();
        int positionY = this.gameEngine.getChampion().getY() - this.gameEngine.getScreenPosition().getY() + this.gameEngine.getScreenPosition().getGameScreenY();

        this.gameScreen.paintComponent(g);

        this.gameEngine.getChampion().getImage().paintIcon(this, g, positionX, positionY);
        this.paintChampionBlood(g, positionX, positionY);
        g.drawRect(positionX, positionY, gameEngine.getChampion().getWidth(), gameEngine.getChampion().getHeight());

        this.paintUnits(g);

        this.topPanel.paintComponent(g);

        this.gameScreen.paintAnimations(g, this.gameEngine.getChampion(), positionX, positionY);

        this.paintInfo(g);

        this.statisticsWindow.paintWindow(g, gameEngine.getChampion());

    }

    private void paintChampionBlood(Graphics g, int positionX, int positionY) {
        if(this.gameEngine.getChampion().getBlood().getCounter() >= 0) {
            this.gameEngine.getChampion().getBlood().getImage().paintIcon(this, g, positionX, positionY);
        }
    }

    public void control(KeyEvent e) {
        this.gameEngine.control(e);

        if(e.getKeyCode() == KeyEvent.VK_F1) {
            this.statisticsWindow.openClose();
        }
    }

    private void initGameScreen() {
        int x = 0;
        int y = this.topPanelHeight;
        int width = this.screenX;
        int height = this.screenY - y;
        this.gameScreen = new GameScreen(x, y, width, height, mapSizeX, mapSizeY, mapSquareSize);
    }

    private void paintUnits(Graphics g) {
        for (int i = 0; i < this.gameEngine.getUnitSet().size(); i++) {
            int positionX = this.gameEngine.getUnitSet().get(i).getX() - this.gameEngine.getScreenPosition().getX() + this.gameEngine.getScreenPosition().getGameScreenX();
            int positionY = this.gameEngine.getUnitSet().get(i).getY() - this.gameEngine.getScreenPosition().getY() + this.gameEngine.getScreenPosition().getGameScreenY();
            this.gameEngine.getUnitSet().get(i).getImage().paintIcon(this, g, positionX, positionY);

            //g.setColor(Color.MAGENTA);
            //g.drawRect(positionX, positionY, this.gameEngine.getUnitSet().get(i).getWidth(), this.gameEngine.getUnitSet().get(i).getHeight());

            int width = this.gameEngine.getUnitSet().get(i).getCollisionWidth() + 20;
            int height = 12;
            int currentWidth = this.gameEngine.getUnitSet().get(i).getHitPoints() * 100 / this.gameEngine.getUnitSet().get(i).getMaxHitPoints() * width / 100;
            int y = positionY - height - 10;
            positionX = this.gameEngine.getUnitSet().get(i).getCollisionX() - this.gameEngine.getScreenPosition().getX() + this.gameEngine.getScreenPosition().getGameScreenX() - 10;
            g.setColor(new Color(220, 220, 220));
            g.fillRect(positionX, y, width, height);

            g.setColor(new Color(255, 20, 20));
            g.fillRect(positionX, y, currentWidth, height);
            g.setColor(new Color(155, 0, 0));
            g.drawRect(positionX, y, width, height);

            positionX = this.gameEngine.getUnitSet().get(i).getX() - this.gameEngine.getScreenPosition().getX() + this.gameEngine.getScreenPosition().getGameScreenX();
            this.paintBlood(g, this.gameEngine.getUnitSet().get(i), positionX, positionY);

            //collision unit
            /*positionX = this.gameEngine.getUnitSet().get(i).getCollisionX() - this.gameEngine.getScreenPosition().getX() + this.gameEngine.getScreenPosition().getGameScreenX();
            positionY = this.gameEngine.getUnitSet().get(i).getCollisionY() - this.gameEngine.getScreenPosition().getY() + this.gameEngine.getScreenPosition().getGameScreenY();
            g.setColor(Color.GREEN);
            g.drawRect(positionX, positionY, this.gameEngine.getUnitSet().get(i).getCollisionWidth(), this.gameEngine.getUnitSet().get(i).getCollisionHeight());*/
        }
    }

    private void paintBlood(Graphics g, Unit unit, int unitX, int unitY) {
        if(unit.getBlood().getCounter() >= 0) {
            unit.getBlood().getImage().paintIcon(this, g, unitX + unit.getCollisionX() - unit.getX(), unitY);
        }
    }

    private void paintInfo(Graphics g) {
        ArrayList<Info> infoList = this.gameEngine.getChampion().getInfoSet().getInfoList();
        for (int i = 0; i < infoList.size(); i++) {
            int positionX = infoList.get(i).getX() - this.gameEngine.getScreenPosition().getX() + this.gameEngine.getScreenPosition().getGameScreenX();
            int positionY = infoList.get(i).getY() - this.gameEngine.getScreenPosition().getY() + this.gameEngine.getScreenPosition().getGameScreenY();


            g.setColor(Color.BLACK);
            g.drawString(infoList.get(i).getMessage(), positionX - 1, positionY - 1);
            g.drawString(infoList.get(i).getMessage(), positionX + 1, positionY - 1);
            g.drawString(infoList.get(i).getMessage(), positionX - 1, positionY + 1);
            g.drawString(infoList.get(i).getMessage(), positionX + 1, positionY + 1);
            g.setColor(infoList.get(i).getColor());
            g.drawString(infoList.get(i).getMessage(), positionX, positionY);

        }

    }
}
