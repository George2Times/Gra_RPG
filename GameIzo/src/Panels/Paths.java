package Panels;

import GameLogic.Sqaure;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Paths {


    public Paths(Sqaure[][] mapPixels, ImageIcon[] textures) {
        int y = 0;

        File f = new File("resources/path.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] number = line.split(",");
           for(int x = 0; x < number.length; x++) {
                   for (int i = 0; i < 4; i++) {
                       for (int j = 0; j < 4; j++) {
                           mapPixels[x * 4 + i][y * 4 + j].setTexture(textures[Integer.parseInt(number[x])]);
                           mapPixels[x*4+i][y*4+j].setType(Integer.parseInt(number[x]));
                       }
                   }
           }
           y++;
        }
    }
}
