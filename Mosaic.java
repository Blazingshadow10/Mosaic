import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

class ABCTiles extends JPanel implements MouseListener {
    private int r, g, b;
    private String letter;
    int objectDraw;
    private Face face;

    public String getLetter() {
        return letter;
    }
    public void setLetter(String s) {
        letter = s;
    }
    public int getR() {
        return r;
    }
    public int getG() {
        return g;
    }
    public int getB() {
        return b;
    }
    public void setRGB(int x, int y, int z) {
        r=x;
        g=y;
        b=z;
    }
    public int getObjectDraw() {
        return objectDraw;
    }
    public void setObjectDraw(int n) {
        objectDraw = n;
    }

    // Basic constructor
    ABCTiles() {
        super();
        RandomVariable();
    }

    // 2rd Constructor to set all values you want
    ABCTiles(int r, int g, int b, String letter, int objectDraw) {
        super();
        face = new Face(getHeight(),getWidth());
        setRGB(r,g,b);
        setLetter(letter);
        setObjectDraw(objectDraw);
        this.addMouseListener(this);
    }

    // set all the random Values for the tile
    final public void RandomVariable() {
        r = RandomNum(0,255);
        g = RandomNum(0,255);
        b = RandomNum(0,255);

        objectDraw = RandomNum(1, 2);

        face = new Face(getHeight(),getWidth());

        int randletter = RandomNum(65, 90);
        letter = Character.toString((char)randletter);
        
        this.addMouseListener(this);
    }

    private static int RandomNum(int min, int max) {
        Random ran = new Random();
        return min + ran.nextInt(max-min+1);
    }   

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }

    // Prints data for the object
    public String toString() {
        String shape = " ";
        String s;
        if (objectDraw == 1) {
            shape = "Rectangle";
        } 
        if (objectDraw == 2) {
            shape = "Circle";   
        }
        
        s = "Shape: " + shape + " Color: " + r + " "  + g + " "  + b + " Letter: " + letter;
        return s;
    }

    public void paintComponent(Graphics e) {
        super.paintComponent(e); 

        int tileWidth = getWidth();
        int tileHeight = getHeight();

        face.setheight(getHeight());
        face.setwidth(getWidth());
        
        e.setColor(new Color(r,g,b));

        if (objectDraw == 1) {
            e.fillRect(0, 0, tileWidth, tileHeight);
        } 
        if (objectDraw == 2) {
            e.fillOval(0, 0, tileWidth, tileHeight);    
        }
        
        e.setColor(new Color(GetContrastingColor(r),GetContrastingColor(g),GetContrastingColor(b)));

        final int fontSize=50;
        e.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int positionX = (tileWidth/2)-20;
        int positionY = (tileHeight/2)+20;
        e.drawString(letter,positionX,positionY);

        if (objectDraw == 3) {
            face.paintComponent(e);
        }
    }

    // mouse click override (It complained when I did not have all of them)
    @Override
    public void mouseClicked(MouseEvent e) {
        objectDraw = 3;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

class MosaicFrame extends JFrame implements ActionListener {
    private ArrayList<ABCTiles> tileList = new ArrayList<ABCTiles>();

    public MosaicFrame() {
        setBounds(100,50,1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Panel to hold the button
        JPanel reRollPanel = new JPanel();
        contentPane.add(reRollPanel, BorderLayout.SOUTH);

        // Makes a ReRoll button
        JButton reRoll = new JButton("Reroll");
        reRollPanel.add(reRoll);
        reRoll.addActionListener(this);

        // Makes a new JPanel to hold all the tiles in a 12x12 grid
        JPanel ABCTiles = new JPanel();
        contentPane.add(ABCTiles, BorderLayout.CENTER);
        ABCTiles.setLayout(new GridLayout(12,12));
        
        // Makes all the tiles and adds them to the grid.
        for(int i=0; i<144; i++) {
            ABCTiles tile = new ABCTiles(255,100,100,"A",1);
            tileList.add(tile);
            ABCTiles.add(tile);
            System.out.println((i+1) + ": "+ tile);
        }
    }

    // Logic for the reroll button
    public void actionPerformed(ActionEvent e) {
        int count = 1;
        for(ABCTiles tile : tileList) {
            tile.RandomVariable();
            System.out.println(count + ": "+ tile);
            count++;
        }    
        repaint();
    }
}

public class Mosaic {
    public static void main(String[] args) {
        System.out.println("Mosaic Starting...");

        MosaicFrame MosaicFrame = new MosaicFrame();
        MosaicFrame.setVisible(true);
    }
}
