package Panels;

import GameLogic.Sqaure;

import javax.swing.*;

public class Map {

    private ImageIcon[] textures;
    private ImageIcon[] treeTextures;
    private Sqaure[][] mapPixels;
    private int squareSize;

    public Map(int mapSizeX, int mapSizeY, int mapSquareSize) {
        this.textures = new ImageIcon[10];
        this.treeTextures = new ImageIcon[32];
        this.initTextures();
        this.squareSize = mapSquareSize;
        this.mapPixels = new Sqaure[mapSizeX][mapSizeY];
        //whole map with grass
        for(int i = 0; i < this.mapPixels.length; i++) {
            for(int j = 0; j < this.mapPixels[0].length; j++) {
                this.mapPixels[i][j] = new Sqaure(mapSquareSize, textures[0], 0);
            }
        }

        //paths
        Paths paths = new Paths(this.mapPixels, textures);


        this.addTree(12,8);
        this.addTree(12,16);
        this.addTree(16,8);
        this.addTree(16,16);
        this.addTree(20,8);
        this.addTree(20,16);
        this.addTree(24,8);
        this.addTree(24,16);


    }

    public Sqaure[][] getMapPixels() {
        return this.mapPixels;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public void initTextures() {
        this.textures[0] = new ImageIcon("resources/textures/grass.png");
        this.textures[1] = new ImageIcon("resources/textures/sand.png");
        this.textures[2] = new ImageIcon("resources/textures/water.png");


        for(int i = 0; i < this.treeTextures.length; i++) {
            this.treeTextures[i] = new ImageIcon("resources/textures/tree/tree" + (i+1) +".png");
        }

    }

    public ImageIcon[] getTextures() {
        return this.textures;
    }

    public void addTree(int x, int y) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 4; j++) {
                this.mapPixels[x+j][y+i] = new Sqaure(squareSize, treeTextures[i*4+j],3);
            }
        }
    }


}
