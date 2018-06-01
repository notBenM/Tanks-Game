import javax.swing.*;
import java.awt.*;
import java.lang.Math;
public class tank extends JPanel{
	field f;
	graphical g;
	projectile p;
	keyInput ki;
	public tank(field f,projectile p,graphical g,keyInput ki,int xpos) {
		this.f=f;
		this.p=p;
		this.g=g;
		this.ki=ki;
		backx=xpos;
		if(tanknum%2==0) {//sets player ones color to blue and fills their fuel
			colorr=0;
			colorg=0;
			colorb=255;
			fuel=maxfuel;
		}
		else {//sets player twos color to red
			colorr=255;
			colorg=0;
			colorb=0;
		}
		tanknum++;
		
	}
	public int backx, frontx,maxhealth = 100,health=maxhealth,maxfuel = 200,fuel,blength=15,bheight=12,twidth=2,tlength=15,colorr,colorg,colorb;//xpos of rear of tank, xpos of front of tank, maximum health,current health,maximum fuel, current fuel, length of tank body, height of tank body, width of turret, length of turret, r value of tank color, g value of tank color, blue value of tank body 
	public static int tanknum=0;//the number tank generated
	public double tangle=Math.PI,xvel=0;// the angle of the turret, the velocity that the tank is moving left or right
	public int placeFront(int backx) {// places the front of the tank relative to the back of the tank and the length of the tank
		int temp=backx;
		while(Math.pow(Math.abs(temp-backx),2)+Math.pow(Math.abs(f.gheight[backx]-f.gheight[temp]),2)<Math.pow(blength, 2)) {
	temp++;
	}
	return temp-1;
}
	JPanel tankrecti = new JPanel() {// the inner rectangle of the tank sprite
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(new Color(colorr,colorg,colorb));
			frontx=placeFront(backx);
		    int x[]={(int)((.25)*((backx+(f.gheight[backx]-f.gheight[frontx])*bheight/blength)+3*backx))
		    		,(int)(frontx+(.25)*((frontx+(f.gheight[backx]-f.gheight[frontx])*bheight/blength)-frontx))
		    		,(int)(frontx+(.75)*((frontx+(f.gheight[backx]-f.gheight[frontx])*bheight/blength)-frontx))
		    		,(int)(backx+(.75)*((backx+(f.gheight[backx]-f.gheight[frontx])*bheight/blength)-backx))
		    		};
		    int y[]= {675-(int)(f.gheight[backx]+(.25)*((f.gheight[backx]+(frontx-backx)*bheight/blength)-f.gheight[backx]))
		    		,675-(int)(f.gheight[frontx]+(.25)*((f.gheight[frontx]+(frontx-backx)*bheight/blength)-f.gheight[frontx]))
		    		,675-(int)(f.gheight[frontx]+(.75)*((f.gheight[frontx]+(frontx-backx)*bheight/blength)-f.gheight[frontx]))
		    		,675-(int)(f.gheight[backx]+(.75)*((f.gheight[backx]+(frontx-backx)*bheight/blength)-f.gheight[backx]))
		    		};
		    g.fillPolygon(x,y,4);
		}
		};
	JPanel wheelsi = new JPanel() {//the inner circles of the tank sprite
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(colorr,colorg,colorb));
			    g.fillOval(
			    		(int)(backx+(f.gheight[backx]-f.gheight[frontx])*bheight/(2*blength))-bheight/4
			    		,675-(int)(f.gheight[backx]+(frontx-backx)*bheight/(2*blength))-bheight/4
			    		,bheight/2-1/2
			    		,bheight/2-1/2);//1 is aprox fudge
			    g.fillOval(
			    		(int)(frontx+(f.gheight[backx]-f.gheight[frontx])*bheight/(2*blength))-bheight/4
			    		,675-(int)(f.gheight[frontx]+(frontx-backx)*bheight/(2*blength))-bheight/4
			    		,bheight/2-1/2
			    		,bheight/2-1/2);//1 is aprox fudge
			}
			
			
			
		};
	JPanel tankrecto = new JPanel() {// the outer rectangle of the tank sprite
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(new Color(45,45,45));
			frontx=placeFront(backx);
		    int x[]={backx
		    		,frontx
		    		,(int)(frontx+(f.gheight[backx]-f.gheight[frontx])*bheight/blength)
		    		,(int)(backx+(f.gheight[backx]-f.gheight[frontx])*bheight/blength)
		    		//,(int)((frontx-(f.gheight[frontx]-f.gheight[backx])*bheight/blength)-(frontx-backx))
		    		};
		    int y[]= {675-(int)f.gheight[backx]
		    		,675-(int)f.gheight[frontx]
		    		,675-(int)(f.gheight[frontx]+(frontx-backx)*bheight/blength)
		    		,675-(int)(f.gheight[backx]+(frontx-backx)*bheight/blength)
		    		//675-(int)((f.gheight[frontx]+((frontx-backx)*bheight/blength))-(f.gheight[frontx]-f.gheight[backx])) unsimplified
		    		};
		    g.fillPolygon(x,y,4);
		}
		};
	JPanel wheelso = new JPanel() {// the outer circles of the tank sprite
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(new Color(45,45,45));
		    g.fillOval(
		    		(int)(backx+(f.gheight[backx]-f.gheight[frontx])*bheight/(2*blength))-bheight/2
		    		,675-(int)(f.gheight[backx]+(frontx-backx)*bheight/(2*blength))-bheight/2
		    		,bheight-1
		    		,bheight-1);//1 is aprox fudge
		    g.fillOval(
		    		(int)(frontx+(f.gheight[backx]-f.gheight[frontx])*bheight/(2*blength))-bheight/2
		    		,675-(int)(f.gheight[frontx]+(frontx-backx)*bheight/(2*blength))-bheight/2
		    		,bheight-1
		    		,bheight-1);//1 is aprox fudge
		}
		
		
		
	};
	JPanel turret = new JPanel() {// the turret of the tank sprite
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			 int x[]={
					 	 (int)((f.gheight[backx]-f.gheight[frontx])*bheight/blength+(frontx+backx)/2+twidth/2*Math.sin(tangle))
			    		,(int)((f.gheight[backx]-f.gheight[frontx])*bheight/blength+(frontx+backx)/2-twidth/2*Math.sin(tangle))
			    		,(int)((f.gheight[backx]-f.gheight[frontx])*bheight/blength+(frontx+backx)/2-twidth/2*Math.sin(tangle)+tlength*Math.cos(tangle))
			    		,(int)((f.gheight[backx]-f.gheight[frontx])*bheight/blength+(frontx+backx)/2+twidth/2*Math.sin(tangle)+tlength*Math.cos(tangle))
			    		 };
			    int y[]={675-(int)((f.gheight[frontx]+f.gheight[backx])/2+(frontx-backx)*bheight/blength+twidth/2*Math.cos(tangle))
			    		,675-(int)((f.gheight[frontx]+f.gheight[backx])/2+(frontx-backx)*bheight/blength-twidth/2*Math.cos(tangle))
			    		,675-(int)((f.gheight[frontx]+f.gheight[backx])/2+(frontx-backx)*bheight/blength-twidth/2*Math.cos(tangle)-tlength*Math.sin(tangle))
			    		,675-(int)((f.gheight[frontx]+f.gheight[backx])/2+(frontx-backx)*bheight/blength+twidth/2*Math.cos(tangle)-tlength*Math.sin(tangle))
			    		 };
			    g.fillPolygon(x,y,4);
		}
		};
	JPanel tankdome = new JPanel() {// the dome of the tank sprite
		public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				g.setColor(new Color(colorr,colorg,colorb));
			    g.fillOval(
			    		(int)((f.gheight[backx]-f.gheight[frontx])*bheight/blength+(backx+frontx)/2-bheight/2)
			    		//(int)(frontx-(f.gheight[frontx]-f.gheight[backx])*bheight/blength-(frontx-backx)+(frontx-(f.gheight[frontx]-f.gheight[backx])*bheight/blength-(frontx-(f.gheight[frontx]-f.gheight[backx])*bheight/blength-(frontx-backx)))/2-bheight/2)
			    		,675-(int)((frontx-backx)*bheight/blength+(f.gheight[frontx]+f.gheight[backx])/2)-bheight/2
			    		//,675-(int)((f.gheight[frontx]+((frontx-backx)*bheight/blength)-(f.gheight[frontx]+((frontx-backx)*bheight/blength)-(f.gheight[frontx]-f.gheight[backx])))/2+(f.gheight[frontx]+((frontx-backx)*bheight/blength))-(f.gheight[frontx]-f.gheight[backx])+bheight/2)
			    		,bheight
			    		,bheight);
			}
			};
	JPanel healthbar = new JPanel() {// the health bar of the tank
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(0,255,0));
			g.fillRect((frontx+backx)/2-maxhealth/10,675-(int)((f.gheight[frontx]+f.gheight[backx])/2+30), health/5, 3);
			g.setColor(new Color(255,0,0));
			g.fillRect((frontx+backx)/2-maxhealth/10+health/5,675-(int)((f.gheight[frontx]+f.gheight[backx])/2+30), (maxhealth-health)/5, 3);
			
		}
	};
	JPanel fuelbar = new JPanel() {// the fuel bar of the tank
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(new Color(0,0,0));
			g.fillRect((frontx+backx)/2-fuel/10,675-(int)((f.gheight[frontx]+f.gheight[backx])/2-30),fuel/5, 3);
		}
	};
}
