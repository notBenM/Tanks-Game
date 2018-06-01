import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class projectile extends JPanel{
	field f;
	graphical g;
	tank t1,t2;
	main m;
	mouseInput mi;
	void setstuff(field f,graphical g,tank t1,tank t2,main m,mouseInput mi) {//passes in all necessary class instances
		this.f=f;
		this.t1=t1;
		this.t2=t2;
		this.g=g;
		this.m=m;
	}
public int radius=35,size=0,colorr,colorg,colorb;//radius of explosion, size of the bullet, color of the bullet
public double x,y,xvel,yvel,gravity=.0581,pow = 7.5;//x position, y position, x velocity, y velocity of the bullet, force of gravity, power of the shot
boolean canbefired=true;// true if fire can be called

Timer moving = new Timer(8, new ActionListener(){//moves the bullet then starts the explosion when done
	@Override
	public void actionPerformed(ActionEvent e) {
		if(x<=0||x>=1200) {//if the bullet went out of bounds, end the turn and set the fuel of the next player
			moving.stop();
			m.turn++;
			if(m.turn%2==0)
			t1.fuel=t1.maxfuel;
			if(m.turn%2==1)
			t2.fuel=t2.maxfuel;
			canbefired = true;
		}
		else if(y<f.gheight[(int)x]) {// if hit the ground, stop moving, go to the height of the ground, and start exploding
			y=f.gheight[(int)x]+10;//10 is y fudge
			moving.stop();
			exploding.start();
		}
		y+=yvel;
		x+=xvel;
		yvel-=gravity;
	}
});
Timer exploding = new Timer(8, new ActionListener(){//explodes the bullet then implodes the bullet when done
	@Override
	public void actionPerformed(ActionEvent e) {
		if(size>=radius) {//explode until size is the radius of explosion,do damage, update the terrain, then start imploding
			exploding.stop();
			dammage();
			f.impact((int)x, (int)y, radius);
			imploding.start();
		}
		size++;
		if(size/5%2==0) {
			colorr=255;
			colorg=0;
			colorb=0;
		}
		if(size/5%2==1) {
			colorr=255;
			colorg=165;
			colorb=0;
		}
	}
});
Timer imploding = new Timer(8, new ActionListener(){//implodes the bullet, then ends the turn
	@Override
	public void actionPerformed(ActionEvent e) {
		if(size<=0) {// if the size is 0, start the next turn
			imploding.stop();
			m.turn++;
			if(m.turn%2==0)
			t1.fuel=t1.maxfuel;
			if(m.turn%2==1)
			t2.fuel=t2.maxfuel;
			canbefired=true;
		}
		size--;
		if(size/10%2==0) {
			colorr=255;
			colorg=0;
			colorb=0;
		}
		if(size/10%2==1) {
			colorr=255;
			colorg=165;
			colorb=0;
		}
	}
});
public void fire(double x0, double y0){// fires the bullet starting at x0,y0
	if(canbefired) {// stops multiple bullets from being fired at the same time
		if(m.turn%2==0) {// sets the trajectory relative to the angle
			xvel=pow*Math.cos(t1.tangle);
			yvel=pow*Math.sin(2*Math.PI-t1.tangle);
		}
		if(m.turn%2==1) {// see above
			xvel=pow*Math.cos(t2.tangle);
			yvel=pow*Math.sin(2*Math.PI-t2.tangle);
		}
	canbefired = false;
	x=x0;
	y=y0;
	colorr=0;
	colorg=0;
	colorb=0;
	size=2;
	moving.start();// starts moving
	}
}

public void dammage() {// does damage relative to the proximity of the tank to the explosion
	if(Math.sqrt(Math.pow(Math.abs(x-t1.frontx),2)+Math.pow(Math.abs(y-f.gheight[t1.frontx]), 2))<radius)
		t1.health-=3*(radius-Math.sqrt(Math.pow(Math.abs(x-t1.frontx),2)+Math.pow(Math.abs(y-f.gheight[t1.frontx]), 2)));
	else if(Math.sqrt(Math.pow(Math.abs(x-t1.backx),2)+Math.pow(Math.abs(y-f.gheight[t1.backx]), 2))<radius)
		t1.health-=3*(radius-Math.sqrt(Math.pow(Math.abs(x-t1.frontx),2)+Math.pow(Math.abs(y-f.gheight[t1.frontx]), 2)));
	if(Math.sqrt(Math.pow(Math.abs(x-t2.frontx),2)+Math.pow(Math.abs(y-f.gheight[t2.frontx]), 2))<radius)
		t2.health-=3*(radius-Math.sqrt(Math.pow(Math.abs(x-t2.frontx),2)+Math.pow(Math.abs(y-f.gheight[t2.frontx]), 2)));
	else if(Math.sqrt(Math.pow(Math.abs(x-t2.backx),2)+Math.pow(Math.abs(y-f.gheight[t2.backx]), 2))<radius)
		t2.health-=3*(radius-Math.sqrt(Math.pow(Math.abs(x-t2.frontx),2)+Math.pow(Math.abs(y-f.gheight[t2.frontx]), 2)));
}

JPanel bullet = new JPanel() {// bullet sprite
public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(new Color(colorr,colorg,colorb));
	g.fillOval((int)x-size,675-(int)y-size,size*2,size*2);
	}
};
}