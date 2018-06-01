import java.awt.*;
import javax.swing.*;
import java.lang.Math;
public class field  extends JPanel{
	public double[] gheight = new double[2400];// longer so overflows don't happen
	public int[] ghieght;
	public field() {// creates the playing field
	for(int i = 0;i<2400;i++) {
		gheight[i]=(50*Math.cos((double)i/100)+200);
	}
	}
	public void impact(int x, int y, int radius) {// Destroys the terrain when hit by an explosion
		for(int i=x-radius;i<x+radius;i++) {
			if(i>=0) {
			if(gheight[i]>y+Math.sqrt(Math.pow(radius, 2)-Math.pow(Math.abs(x-i), 2)))
				gheight[i]=gheight[i]-2*(Math.sqrt(Math.pow(radius, 2)-Math.pow(Math.abs(x-i), 2)));
			else if(gheight[i]>y-Math.sqrt(Math.pow(radius, 2)-Math.pow(Math.abs(x-i), 2)))
				gheight[i]=y-Math.sqrt(Math.pow(radius, 2)-Math.pow(Math.abs(x-i), 2));
			if(gheight[i]<30)
				gheight[i]=30;
			}
		}
		
		
	}
	JPanel background = new JPanel() {// background graphics
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(0, 181, 255));
			g.setColor(new Color(124, 204, 25));
			for(int i=0;i<1200;i++) {
				g.drawLine(i, 675, i, 675-(int)gheight[i]);
				}
			
			}
		};
	
	}

