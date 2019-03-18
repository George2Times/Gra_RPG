package Panels;

import GameLogic.Champion.Champion;
import GameLogic.Spells.Spell;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class TopPanel extends JComponent {

    private int screenX;
    private int screenY;
    private int width;
    private int height;
    private int itemSize;
    private ImageIcon avatar;
    private ImageIcon hpIcon;
    private ImageIcon manaIcon;
    private ImageIcon attackIcon;
    private ImageIcon armorIcon;
    private ImageIcon magicIcon;
    private ImageIcon goldIcon;
    private Champion champion;

    public TopPanel(int topPanelHeight, Champion champion, int itemSize) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenX = (int) screenSize.getWidth();
        this.screenY = (int) screenSize.getHeight();

        this.itemSize = itemSize;

        this.width = this.screenX;
        this.height = topPanelHeight;

        this.champion = champion;

        this.avatar = scaleImage("resources/avatar.png", (int)(height*0.8), (int)(height*0.8));
        this.hpIcon = scaleImage("resources/hpIcon.png",getHpManaBarHeight(),getHpManaBarHeight());
        this.manaIcon = scaleImage("resources/manaIcon.png", getHpManaBarHeight(),getHpManaBarHeight());
        this.attackIcon = scaleImage("resources/attackIcon.png", getHpManaBarHeight(),getHpManaBarHeight());
        this.armorIcon = scaleImage("resources/armorIcon.png", getHpManaBarHeight(),getHpManaBarHeight());
        this.magicIcon = scaleImage("resources/magicIcon.png", getHpManaBarHeight(),getHpManaBarHeight());
        this.goldIcon = scaleImage("resources/goldIcon.png", getHpManaBarHeight(),getHpManaBarHeight());
    }

    @Override
    public void paintComponent(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setColor(new Color(95,78,148));
        g.fillRect(0,0, this.width, this.height);

        g.setColor(Color.BLACK);
        g.drawRect(0,0,width,height);
        g.drawRect(1,1,width-2,height-2);

        this.paintExperience(g, champion);

        this.avatar.paintIcon(this, g, (int)(this.height*0.2),(int)(this.height*0.15));
        this.paintLevel(g, champion);

        this.drawHpBar(g);
        this.drawManaBar(g);

        this.drawItemSlots(g);
        this.drawSpellSlots(g);

        this.drawStatistics(g);

    }

    public void paintExperience(Graphics g, Champion champion) {
        int width = this.height/10;
        int x = (int)(this.height*0.2);
        int y = (int)(this.height*0.15);
        int a = (int)(height*0.8);
        g.setColor(Color.BLACK);
        g.fillArc(x-width-1,y-width-1, a+2*width+2, a+2*width+2,319,262);
        g.setColor(new Color(220,220,220));
        g.fillArc(x-width,y-width, a+2*width, a+2*width,320,260);

        g.setColor(new Color(75,58,180));

        int angle = (champion.getLevel().getNextLevelExperience() - champion.getLevel().getExperience())*100/champion.getLevel().getNextLevelExperience()*260/100;
        g.fillArc(x-width,y-width, a+2*width, a+2*width,320,angle);
    }

    public int getHeight() {
        return this.height;
    }

    private static ImageIcon scaleImage(String path, int x, int y) {
        Image temporary =  new ImageIcon(path).getImage();
        temporary = temporary.getScaledInstance(x, y,  Image.SCALE_SMOOTH );
        return new ImageIcon(temporary);
    }

    private void drawHpBar(Graphics g) {
        this.hpIcon.paintIcon(this, g, this.getHpManaMarginLeft() - this.getHpManaBarHeight()-5,getHpMarginTop());
        g.setColor(new Color(220,220,220));
        g.fillRect(this.getHpManaMarginLeft(), getHpMarginTop(), this.getHpManaBarWidth(), this.getHpManaBarHeight());

        g.setColor(new Color(255,20,20));
        g.fillRect(this.getHpManaMarginLeft(), getHpMarginTop(), this.getCurrentHpWidth(), this.getHpManaBarHeight());
        g.setColor(new Color(155,0,0));
        g.drawRect(this.getHpManaMarginLeft(), getHpMarginTop(), this.getHpManaBarWidth(), this.getHpManaBarHeight());

        g.setFont(new Font("Times New Roman", Font.BOLD, this.getHeight()/6));
        g.setColor(new Color(220,220, 220));
        String s = this.champion.getStatistics().getHitPoints() + "/" + this.champion.getStatistics().getMaxHitPoints();
        g.drawString(s, this.getHpManaMarginLeft()+this.getHpManaBarWidth()+ 6, this.getHpMarginTop() + this.getHpManaBarHeight() - 2);
    }

    private void drawManaBar(Graphics g) {
        this.manaIcon.paintIcon(this, g, this.getHpManaMarginLeft() - this.getHpManaBarHeight()-5,getManaMarginTop());
        g.setColor(new Color(220,220,220));
        g.fillRect(this.getHpManaMarginLeft(), getManaMarginTop(), this.getHpManaBarWidth(), this.getHpManaBarHeight());

        g.setColor(new Color(20,20,255));
        g.fillRect(this.getHpManaMarginLeft(), getManaMarginTop(), this.getCurrentManaWidth(), this.getHpManaBarHeight());
        g.setColor(new Color(27,0,142));
        g.drawRect(this.getHpManaMarginLeft(), getManaMarginTop(), this.getHpManaBarWidth(), this.getHpManaBarHeight());

        g.setFont(new Font("Times New Roman", Font.BOLD, this.getHeight()/6));
        g.setColor(new Color(220,220, 220));
        String s = this.champion.getStatistics().getManaPoints() + "/" + this.champion.getStatistics().getMaxManaPoints();
        g.drawString(s, this.getHpManaMarginLeft()+this.getHpManaBarWidth()+ 6, this.getManaMarginTop() + this.getHpManaBarHeight() - 2);
    }

    private int getHpManaMarginLeft() {
        return this.width/8;
    }

    private int getHpManaBarWidth() {
        return this.width/8;
    }

    private int getHpMarginTop() {
        return this.height/6;
    }

    private int getHpManaBarHeight() {
        return this.height/5;
    }

    private int getManaMarginTop() {
        return (this.height*3)/5;
    }

    private int getCurrentHpWidth() {
        return (this.champion.getStatistics().getHitPoints()*100)/this.champion.getStatistics().getMaxHitPoints()*this.getHpManaBarWidth()/100;
    }

    private int getCurrentManaWidth() {
        return (this.champion.getStatistics().getManaPoints()*100)/this.champion.getStatistics().getMaxManaPoints()*this.getHpManaBarWidth()/100;
    }

    private void drawItemSlots(Graphics g) {
        ImageIcon empty = scaleImage("resources/items/empty.png", this.itemSize,this.itemSize);
        int y = this.height/20;
        for(int i = 0; i < 14; i++) {
            g.setColor(new Color(20,20,20));
            g.fillRect(this.width*15/20 + (this.itemSize*(i%7)) + i%7, y, this.itemSize,this.itemSize);
            if(i >= this.champion.getItemList().size()) {
                empty.paintIcon(this, g, this.width * 15 / 20 + (this.itemSize * (i % 7)) + i % 7, y);
            }
            else {
                this.champion.getItemList().get(i).getIcon().paintIcon(this, g, this.width * 15 / 20 + (this.itemSize * (i % 7)) + i % 7, y);
            }
            if(i == 6) {
                y = this.height/2;
            }
        }
    }

    private void drawSpellSlots(Graphics g) {
        int spellSquareSize = this.height*6/10;
        int spellSquareSpace = this.height*3/20;
        int spellSquareMarginTop = this.height/10;
        int x = this.width/2;
        ImageIcon empty = scaleImage("resources/spells/empty.png", spellSquareSize,spellSquareSize);

        for(int i = 0; i <4; i++) {
            if(i >= this.champion.getSpellList().size()) {
                empty.paintIcon(this, g, x + i*(spellSquareSize + spellSquareSpace), spellSquareMarginTop);
            }
            else {
                this.champion.getSpellList().get(i).getImage().paintIcon(this, g, x + i*(spellSquareSize + spellSquareSpace), spellSquareMarginTop);
                if(!this.champion.getSpellList().get(i).isSpellReady()) {
                    g.setColor(new Color(0,0,0,128));
                    g.fillRect(x + i*(spellSquareSize + spellSquareSpace)+2, spellSquareMarginTop+2, spellSquareSize-4, spellSquareSize-4);
                    g.setColor(new Color(0,0,100,128));
                    int percent = this.champion.getSpellList().get(i).getCooldownLast()*100/this.champion.getSpellList().get(i).getCooldown()*spellSquareSize/100;
                    g.fillRect(x + i*(spellSquareSize + spellSquareSpace) + 2, spellSquareMarginTop + percent + 2, spellSquareSize- 4, spellSquareSize - percent - 4);
                    this.drawCooldown(g,x + i*(spellSquareSize + spellSquareSpace), spellSquareMarginTop, spellSquareSize, this.champion.getSpellList().get(i));
                }
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, this.getHeight()/6));

        //q spell
        g.drawString("1", x + spellSquareSize/2 - g.getFont().getSize()/3, spellSquareMarginTop + spellSquareSize + g.getFont().getSize());
        //w
        g.drawString("2", x + spellSquareSize + spellSquareSpace + spellSquareSize/2 - g.getFont().getSize()/3, spellSquareMarginTop + spellSquareSize + g.getFont().getSize());
        //e
        g.drawString("3", x + 2*(spellSquareSize + spellSquareSpace) + spellSquareSize/2 - g.getFont().getSize()/3, spellSquareMarginTop + spellSquareSize + g.getFont().getSize());
        //r
        g.drawString("4", x + 3*(spellSquareSize + spellSquareSpace) +  spellSquareSize/2 - g.getFont().getSize()/3, spellSquareMarginTop + spellSquareSize + g.getFont().getSize());
    }

    private void drawStatistics(Graphics g) {
        g.setColor(Color.BLACK);
        int firstRowX = this.width*7/20;
        int firstRowY = this.height*3/20;
        this.attackIcon.paintIcon(this, g, firstRowX, firstRowY);
        g.drawString(this.champion.getStatistics().getMinDamage()+"-"+this.champion.getStatistics().getMaxDamage(), firstRowX + g.getFont().getSize()/2 + this.getHpManaBarHeight(), firstRowY + g.getFont().getSize());

        int secondRowY = firstRowY + this.getHpManaBarHeight() + this.height/10;
        this.armorIcon.paintIcon(this, g, firstRowX, secondRowY);
        g.drawString(this.champion.getStatistics().getArmor()+"", firstRowX + g.getFont().getSize()/2 + this.getHpManaBarHeight(), secondRowY + g.getFont().getSize());

        int thirdRowY = secondRowY + this.getHpManaBarHeight() + this.height/10;
        this.magicIcon.paintIcon(this, g, firstRowX, thirdRowY);
        g.drawString(this.champion.getStatistics().getAbilityPower()+"", firstRowX + g.getFont().getSize()/2 + this.getHpManaBarHeight(), thirdRowY + g.getFont().getSize());

        int secondRowX = firstRowX + this.height*8/10;
        this.goldIcon.paintIcon(this, g, secondRowX, firstRowY);
        g.drawString(this.champion.getStatistics().getGold()+"", secondRowX + g.getFont().getSize()/2 + this.getHpManaBarHeight(), firstRowY + g.getFont().getSize());
    }

    private void drawCooldown(Graphics g, int x, int y, int size, Spell spell) {
        g.setFont(new Font("Times New Roman", Font.BOLD, this.getHeight()/6));
        g.setColor(new Color(255,255,255));
        int secLeft = (spell.getCooldown() - spell.getCooldownLast())/10 + 1;
        g.drawString(secLeft +"", x + size/10, y + size/4 + 4);
        g.setFont(new Font("Times New Roman", Font.BOLD, this.getHeight()/6 - 1));
        g.setColor(new Color(31,23,14));
    }

    private void paintLevel(Graphics g, Champion champion) {
        g.setColor(Color.BLACK);
        int x = this.height*13/20;
        int y = this.height*27/40;
        int width =  this.height*3/10;
        int height =  this.height*3/10;
        g.fillOval(x, y, width, height);

        g.setColor(new Color(180,180,180));
        g.fillOval(x+2, y+2, width-4, height-4);


        g.setFont(new Font("Times New Roman", Font.BOLD, this.getHeight()/6));
        g.setColor(Color.BLACK);
        g.drawString(champion.getLevel().getLevel()+"", x+2 + g.getFont().getSize()/2, y+2 + g.getFont().getSize() + height/10);
    }
}
