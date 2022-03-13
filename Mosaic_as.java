<<<<<<< HEAD
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


    ABCTiles() {
        super();
        RandomVariable();
    }

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
        int positionX = (tileWidth/3);
        int positionY = (tileHeight/2)+20;
        e.drawString(letter,positionX,positionY);

        if (objectDraw == 3) {
            face.paintComponent(e);
        }
    }

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
        setBounds(100,50,1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton reRoll = new JButton("Reroll");
        buttonPanel.add(reRoll);
        reRoll.addActionListener(this);

        JPanel ABCTiles = new JPanel();
        contentPane.add(ABCTiles, BorderLayout.CENTER);
        ABCTiles.setLayout(new GridLayout(12,12));
        
        for(int i=0; i<144; i++) {
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
        System.out.println("Mosaic Starting...");

        MosaicFrame MosaicFrame = new MosaicFrame();
        MosaicFrame.setVisible(true);
    }
=======
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container; 
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

class ABCTiles extends JPanel {
    private int r, g, b;
    private String letter;
    int objectDraw;
    
    private Face face;

    ABCTiles() {
        super();
        face = new Face();
        RandomVariable();
    }

    final public void RandomVariable() {
        r = RandomNum(0,255);
        g = RandomNum(0,255);
        b = RandomNum(0,255);

        objectDraw = RandomNum(1, 2);

        int randletter = RandomNum(65, 90);
        letter = Character.toString((char)randletter);    
    }

    private static int RandomNum(int min, int max) {
        Random ran = new Random();
        return min + ran.nextInt(max-min+1);
    }   

    private static int GetContrastingColor(int colorIn) {
        return ((colorIn+128)%256);
    }


    public void mouseClicked(MouseEvent e) {
        objectDraw = 3;
    }

    public void paintComponent(Graphics e) {
        super.paintComponent(e); 

        int tileWidth = getWidth();
        int tileHeight = getHeight();

        face.setPositionX(0);
        face.setPositionY(0);

        face.setheight(getHeight());
        face.setwidth(getWidth());
        
        e.setColor(new Color(r,g,b));

        if (objectDraw == 1) {
            e.fillRect(0, 0, tileWidth, tileHeight);
        } 
        if (objectDraw == 2) {
            e.fillOval(0, 0, tileWidth, tileHeight);    
        }
        if (objectDraw == 3) {
            face.paintComponent(e);
        }

        e.setColor(new Color(GetContrastingColor(r),GetContrastingColor(g),GetContrastingColor(b)));

        final int fontSize=50;
        e.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        int positionX = (tileWidth/3);
        int positionY = (tileHeight/2)+20;
        e.drawString(letter,positionX,positionY);
    }
}

class MosaicFrame extends JFrame implements ActionListener {
    private ArrayList<ABCTiles> tileList = new ArrayList<ABCTiles>();
    //private ArrayList<Face> faceList = new ArrayList<Face>();

    public MosaicFrame() {
        setBounds(100,50,1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton reRoll = new JButton("Reroll");
        buttonPanel.add(reRoll);
        reRoll.addActionListener(this);

        JPanel ABCTiles = new JPanel();
        contentPane.add(ABCTiles, BorderLayout.CENTER);
        ABCTiles.setLayout(new GridLayout(12,12));
        
        for(int i=0; i<144; i++) {
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
        System.out.println("Mosaic Starting...");

        MosaicFrame MosaicFrame = new MosaicFrame();
        MosaicFrame.setVisible(true);
    }
>>>>>>> cade606cfe20725f76911e7639387242e554b425
}