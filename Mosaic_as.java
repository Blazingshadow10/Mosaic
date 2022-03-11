import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

class ABCTiles extends JPanel {
    private int red, green, blue;
    private String letter;
    int squareOrCircle;

    ABCTiles() {
        super();
        RandomVariable();
    }

    final public void RandomVariable() {
        red = RandomNum(0,255);
        green = RandomNum(0,255);
        blue = RandomNum(0,255);

        squareOrCircle = RandomNum(1, 2);

        int randletter = RandomNum(65, 90);
        letter = Character.toString((char)randletter);    
    }

    private static int RandomNum(int min, int max) {
        Random ran = new Random();
        return min + ran.nextInt(max-min+1);
    }   

     public void paintComponent(Graphics g) {
        super.paintComponent(g); 

        int tileWidth = getWidth();
        int tileHeight = getHeight();
        
        g.setColor(new Color(red,green,blue));

        if (squareOrCircle == 1) {
            g.fillRect(0, 0, tileWidth, tileHeight);
        } else {
            g.fillOval(0, 0, tileWidth, tileHeight);
        }
        
        g.setColor(new Color(GetContrastingColor(red),GetContrastingColor(green),GetContrastingColor(blue)));

        final int fontSize=50;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int positionX = (tileWidth/2)-20;
        int positionY = (tileHeight/2)+20;
        g.drawString(letter,positionX,positionY);
    }

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }
}

class MosaicLiteFrame extends JFrame implements ActionListener {
    private ArrayList<ABCTiles> tileList;

    public MosaicLiteFrame() {
        setBounds(200,200,1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton reroll = new JButton("Reroll");
        buttonPanel.add(reroll);
        reroll.addActionListener(this);

        JPanel ABCTiles = new JPanel();
        contentPane.add(ABCTiles, BorderLayout.CENTER);
        ABCTiles.setLayout(new GridLayout(12,12));

        tileList = new ArrayList<ABCTiles>();
        for(int i=1; i<145; i++) {
            ABCTiles tile = new ABCTiles();
            tileList.add(tile);
            ABCTiles.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for(ABCTiles tile : tileList) {
            tile.RandomVariable();
        }
        repaint();
    }
}

public class Mosaic_as {
    public static void main(String[] args) {
        System.out.println("MosaicLite Starting...");

        MosaicLiteFrame myMosaicLiteFrame = new MosaicLiteFrame();
        myMosaicLiteFrame.setVisible(true);
    }
}