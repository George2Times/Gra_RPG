import Panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        MainPanel mainPanel = new MainPanel();
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0);
                }
                else {
                    mainPanel.control(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        frame.validate();
    }


}
