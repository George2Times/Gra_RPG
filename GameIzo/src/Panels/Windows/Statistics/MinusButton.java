package Panels.Windows.Statistics;

import javax.swing.*;
import java.awt.*;

public class MinusButton extends JButton {


    public MinusButton(int x, int y, int buttonWidth, int buttonHeight) {
        this.setVisible(true);
        this.setOpaque(false);
        this.setBounds(x, y, buttonWidth, buttonHeight);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setFocusable(false);
    }
}