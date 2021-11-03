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

public class TestJLabel {
    
    public static void main(String[] args) throws InterruptedException {
    	JFrame f = new JFrame("Fuego Emblem");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel game = new MyPanel();
        f.add(game);
        f.pack();
        f.setVisible(true);
        int test=1;
        int total=0;
        game.update();
        while(test>-1) {
        	long a=System.currentTimeMillis();
        	Thread.sleep(15);
        	//15 gets about 60 FPS
        	game.update();
        	test+=1;
        	long b=System.currentTimeMillis();
//        	System.out.println("FPS: "+1000/(b-a));
        	MyPanel.fPS=1000/(b-a);
        	total+=1000/(b-a);
        }
//        System.out.println("Average: "+total/1000);
    	

    }
    


}

class MyPanel extends JPanel {
	Square selected;
	Square one = new Square();
	
	Square two = new Square();
	Square three = new Square();
	Square four = new Square();
	Square five = new Square();
	Square six = new Square();
	//columns is to the right
		public int rows=15;
		public int columns=25;
		public int xOff=0;
		public int yOff=00;
		public int xMax=200;
		public int yMax=100;
		public int mRows=5;
		public int mColumns=5;
		public int mxOff=50;
		public int myOff=100;
		public int[][] array = new int[rows][columns];
	public static long fPS;

	public static List<Square> all = new ArrayList<Square>();
	public static int X(int x) {
		//for columns
		return (x*40);
	}
	public static int Y(int y) {
		//for rows
		return(y*40);
	}
	



    public MyPanel() {
    	Random rand = new Random();
    	all.add(one);
    	all.add(two);
    	all.add(three);
    	all.add(four);
    	all.add(five);
    	all.add(six);
//    	
    	
    	for(Square s: all) {
//    		s.setX(rand.nextInt(500));
//    		s.setY(rand.nextInt(500));
//    		s.setXChange(rand.nextInt(5));
//    		s.setYChange(rand.nextInt(5));
    		s.setX(2);
    		s.setY(1);
    	}
    	

        setBorder(BorderFactory.createLineBorder(Color.black));

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	for(Square s: all) {
            		int x=s.getX()*40;
            		int x2=(s.getX()*40+s.getWidth());
            		int y=s.getY()*40;
            		int y2=(s.getY()*40+s.getHeight());
            		
            		//fix this from going out of range!!
//            		System.out.println("BX: "+x+" Y: "+y+" X2: "+x2+" Y2: "+y2);
//            		System.out.println("MX: "+e.getX()+" Y: "+e.getY());
            		
            		if(x<e.getX()&&x2>e.getX()&&y<e.getY()&&y2>e.getY()) {
//            			moveSquare(e.getX(),e.getY(),s);
//            			System.out.println("working here");
            			selected=s;
            			break;
            			
            		}
            	}
//            	System.out.println(selected);
                
            }
            public void mouseReleased(MouseEvent e) {
            	selected=null;
//            	System.out.println(selected);
                
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
    	try {
    		int x= getMousePosition().x/40;
    		int y = getMousePosition().y/40;
    		for(Square s: all) {
    			if(s.getX()==x && s.getY()==y) {
    				System.out.println("Add code here for display");
    			}
    		}
    	}catch(Exception e) {
    		
    	}
    	
//    	for(Square s: all) {
//    		s.setX(s.getX()+s.getXChange());
//    		s.setY(s.getY()+s.getYChange());
//    		if(s.getX()>500 || s.getX()<1) {
//    			s.setXChange(s.getXChange()*-1);
//    		}
//    		if(s.getY()>500 || s.getY()<1) {
//    			s.setYChange(s.getYChange()*-1);
//    		}
//    	}
    	if(selected!=null) {
//    		System.out.println(selected);
    		try {
    			if(getMousePosition().y>=0 && getMousePosition().x>=0) {
        			
        			if(getMousePosition().y/40<rows && getMousePosition().y/40>=0) {
            			if(getMousePosition().x/40<columns && getMousePosition().x>=0) {
//            				for(int i=0;i<array.length;i++) {
//            					for(int j=0;j<array[i].length;j++) {
//            						
//            					}
//            				}
            				int x= getMousePosition().x/40;
            	    		int y = getMousePosition().y/40;
    						if(array[y][x]==0) {
    							selected.setY(getMousePosition().y/40);
                	        	selected.setX(getMousePosition().x/40);
    						}
            				
            			}
            		}
        		}
    		} catch(Exception e) {
//    			System.out.println("anger");
    		}
    		
    		
    		
    	}
    	for(int i=0;i<array.length;i++) {
    		for(int j=0; j<array[i].length;j++) {
    			array[i][j]=0;
    		}
    	}
    	for(Square a: all) {
    		array[a.getY()][a.getX()]=1;
    	}
    	repaint();
    	for(int[] a: array) {
    		for(int b:a) {
    			System.out.print(b);
    		}
    		System.out.println();
    	}
    	System.out.println();
    	System.out.println();
    }

    public Dimension getPreferredSize() {
        return new Dimension(X(columns+1)-25+xOff-7+xMax+240,Y(rows+1)-1+yOff-31+yMax);
    }
    
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	for(Square s: all) {
    		s.paintSquare(g);
    	}
    	//stuff for text, use for stats
//    	Font myFont = new Font("SansSerif",0,20);
//    	g.setFont(myFont);
//    	g.setColor(Color.blue);
//    	String text = "FPS: "+String.valueOf(fPS);
//    	g.drawString(text, 200,40);
    	for(int i=0;i<columns+1;i++) {

			g.setColor(Color.black);
			g.drawLine(X(i)+xOff,0+yOff, X(i)+xOff, Y(rows)+yOff);
//			System.out.println(i);
		}
		
		for(int i=0;i<rows+1;i++) {
			g.setColor(Color.black);
			g.drawLine(0+xOff,Y(i)+yOff,X(columns)+xOff,Y(i)+yOff);
		}
		
		for(int i=0;i<mColumns+1;i++) {

			g.setColor(Color.black);
			g.drawLine(X(i)+xOff+X(columns)+mxOff,0+yOff+myOff, X(i)+xOff+X(columns)+mxOff, Y(mRows)+yOff+myOff);
//			System.out.println(i);
		}
		
		for(int i=0;i<mRows+1;i++) {
			g.setColor(Color.black);
			g.drawLine(xOff+X(columns)+mxOff, yOff+Y(i)+myOff, X(mRows)+xOff+X(columns)+mxOff, yOff+Y(i)+myOff);
		}

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
		g.fillRect(xPos*40, yPos*40, width, height);
		g.setColor(Color.black);
		g.drawRect(xPos*40, yPos*40, width, height);
	}
}

