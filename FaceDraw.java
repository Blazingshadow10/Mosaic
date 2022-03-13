import java.util.Random;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.util.ArrayList;

// Randomize everything
interface anyRandom {
    public static int ran(int min, int max) {
        if (min == max) {
            return min;
        } else {
        Random ran = new Random();
        return min + ran.nextInt(max-min+1);
        }
    }
}

// Draws a Random Face at a Random Piont
class Face extends EyeDraw implements anyRandom {
    private int xPosition;
    private int yPosition;
    private int height;
    private int width;
    private int smileStatus;
    private EyeDraw eyesL;
    private EyeDraw eyesR;
    

    // Getters and Setters
    public int getXPosition() {
        return xPosition;
    }
    public void setXPostion(int x) {
        xPosition = x;
    }
    public int getYPosition() {
        return yPosition;
    }
    public void setYPostion(int x) {
        yPosition = x;
    }
    public int getheight() {
        return height;
    }
    public void setheight(int x) {
        height = x;
    }
    public int getwidth() {
        return width;
    }
    public void setwidth(int x) {
        width = x;
    }
    public int getsmileStatus() {
        return smileStatus;
    }
    public void setsmileStatus(int x) {
        smileStatus = x;
    }
    
    public Face(int height, int width) {
        
        xPosition = anyRandom.ran(0, 0);
        yPosition = anyRandom.ran(0, 0);
        height = anyRandom.ran((int)(height/2),height);
        width = anyRandom.ran((int)(width/2),width);
        smileStatus = anyRandom.ran(1,3);

        // Eye Math and set up
        int eyeHeight = height / 5;
        int eyeWidth = eyeHeight / 2;
        int eyePositionX = xPosition + (width / 2) - (eyeWidth / 2);
        int eyePoisitonY = yPosition + (height / 3) - (eyeHeight / 2);

        eyesL = new EyeDraw(eyePositionX - 25, eyePoisitonY, eyeWidth, eyeHeight);
        eyesR = new EyeDraw(eyePositionX + 25, eyePoisitonY, eyeWidth, eyeHeight);
    }

    // Rewritten ToString to give information on the Face
    public String toString() {
        String s = "X = " + getXPosition() + " Y = " + getYPosition() + " Width = " + getwidth() + " Height = " + getheight();
        return s;
    }

    // Paints the face
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.yellow);                        // Skin color
        g.fillOval(xPosition, yPosition, width, height); // Face its self
        g.setColor(Color.blue);                          // Eye color
        eyesR.paintComponent(g);
        g.setColor(Color.blue);                          // Resets Eye color
        eyesL.paintComponent(g);
        g.drawOval(xPosition, yPosition, width, height); // Face Border
    
        // Random smile Status
        switch(smileStatus) {
            case 1:
                g.drawArc(xPosition+5, yPosition-((height/2)-(height/3)), width-10, height-10, -45, -90);
                break;
            case 2:
                g.drawArc(xPosition, yPosition+(height/2), width-10, height-10, 45, 90);
                break;
            case 3:    
                g.drawLine(xPosition+(width/2)-(width/4), yPosition+(height/2)+(height/6), xPosition+(width/2)+(width/4), yPosition+(height/2)+(height/6));       
                break;
        }
    }

}

// Draws Eyes
class EyeDraw extends Oval {
    public EyeDraw() {
        super(0, 0, 0, 0);
    }
    public EyeDraw(int xPosition, int yPosition, int width, int height) {
        super(xPosition, yPosition, width, height);
    }
    public void paintComponent(Graphics g) {
        g.fillOval(getPositionX(), getPositionY(), getWidth(), getHeight()); 
        g.setColor(Color.black); // Sets Eye Border color
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
        
    }
}

// Panel to Make 3 to 10 faces
class FacePanel extends JPanel implements anyRandom {
    ArrayList<Face> FaceList = new ArrayList<Face>();
    public FacePanel() {
        int numFaces = anyRandom.ran(3,10);
        for (int i = 1; i <= numFaces; i++) {
            FaceList.add(new Face(getHeight(),getWidth()));
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int count = 0;
        for (Face f : FaceList){            // Runs through the Faces
            count += 1;                     // Counts the Face
            f.paintComponent(g);
            System.out.print(count + ": ");
            System.out.println(f);
        }
    }
}

public class FaceDraw {
    public static void main(String[] args) {
        System.out.println("FaceDraw...");

        JFrame myFrame = new JFrame("FaceDraw");
        myFrame.setBounds(0,0,900,800);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FacePanel myFacePanel = new FacePanel();
        Color color = new Color(0,255,0);
        myFrame.setBackground(color);
        myFrame.add(myFacePanel);
        myFrame.setVisible(true);
    }
}