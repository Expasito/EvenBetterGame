package base;
import java.util.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Scanner;
import java.util.Random;

public class GUI {
    
    public static void main(String[] args) throws InterruptedException {
    	JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel game = new MyPanel();
        f.add(game);
        f.pack();
        f.setVisible(true);
        int test=1;
        game.update();
        while(test<1000) {
        	long a=System.currentTimeMillis();
        	Thread.sleep(15);
        	//15 gets about 60 FPS
        	game.update();
        	test+=1;
        	long b=System.currentTimeMillis();
        	System.out.println("FPS: "+1000/(b-a));
        	MyPanel.fPS=1000/(b-a);
        }
    	

    }
    


}

class MyPanel extends JPanel {
	Square one = new Square();
	Square two = new Square();
	Square three = new Square();
	Square four = new Square();
	Square five = new Square();
	Square six = new Square();
	public static long fPS;

	public static List<Square> all = new ArrayList<Square>();
	



    public MyPanel() {
    	Random rand = new Random(1);
    	all.add(one);
    	all.add(two);
    	all.add(three);
    	all.add(four);
    	all.add(five);
    	all.add(six);
    	
    	
    	for(Square s: all) {
    		s.setX(rand.nextInt(500));
    		s.setY(rand.nextInt(500));
    		s.setXChange(rand.nextInt(5));
    		s.setYChange(rand.nextInt(5));
    	}
    	

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	for(Square s: all) {
            		int x=s.getX();
            		int x2=s.getX()+s.getWidth();
            		int y=s.getY();
            		int y2=s.getY()+s.getHeight();
            		if(x<e.getX()&&x2>e.getX()&&y<e.getY()&&y2>e.getY()) {
            			moveSquare(e.getX(),e.getY(),s);
            		}
            	}
                
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
//                moveSquare(e.getX(),e.getY());
            }
        });
    
        
    }
    
    private void moveSquare(int x, int y,Square s) {
    	s.setX(x-10);
    	s.setY(y-10);
    	repaint();
    	

    }
    public void update() {
    	for(Square s: all) {
    		s.setX(s.getX()+s.getXChange());
    		s.setY(s.getY()+s.getYChange());
    		if(s.getX()>500 || s.getX()<1) {
    			s.setXChange(s.getXChange()*-1);
    		}
    		if(s.getY()>500 || s.getY()<1) {
    			s.setYChange(s.getYChange()*-1);
    		}
    	}
    	repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(540,540);
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	for(Square s: all) {
    		s.paintSquare(g);
    	}
    	Font myFont = new Font("SansSerif",0,20);
    	g.setFont(myFont);
    	g.setColor(Color.blue);
    	String text = "FPS: "+String.valueOf(fPS);
    	g.drawString(text, 200,40);

    }  
}

class Square{
	private int xPos=0;
	private int yPos=0;
	private int width=40;
	private int height=40;
	private int xChange=0;
	private int yChange=0;
	
	public void setX(int xPos) {
		this.xPos=xPos;
	}
	public int getX() {
		return xPos;
	}
	public void setY(int yPos) {
		this.yPos=yPos;
	}
	public int getY() {
		return yPos;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setXChange(int xChange) {
		this.xChange=xChange;
	}
	public int getXChange() {
		return xChange;
	}
	public void setYChange(int yChange) {
		this.yChange=yChange;
	}
	public int getYChange() {
		return yChange;
	}
	public void paintSquare(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(xPos, yPos, width, height);
		g.setColor(Color.black);
		g.drawRect(xPos, yPos, width, height);
	}
}

