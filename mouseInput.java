import java.awt.event.MouseEvent;
import java.lang.Math;

import javax.swing.event.MouseInputAdapter;

public class mouseInput extends MouseInputAdapter{
	tank t1,t2;
	projectile p;
	main m;
	field f;
	public void setstuff(main m,field f,projectile p,tank t1,tank t2) {// passes in necessary class instances
		this.m=m;
		this.f=f;
		this.p=p;
		this.t1=t1;
		this.t2=t2;
	}
	public void mousePressed(MouseEvent e) {// when clicked
		if(m.gamestate==1) {// if playing
		if(m.turn%2==0) {// fire from tank 1
			p.fire(((f.gheight[t1.backx]-f.gheight[t1.frontx])*t1.bheight/t1.blength+(t1.frontx+t1.backx)/2+t1.tlength*Math.cos(t1.tangle)),((f.gheight[t1.frontx]+f.gheight[t1.backx])/2+(t1.frontx-t1.backx)*t1.bheight/t1.blength-t1.tlength*Math.sin(t1.tangle)));
		}
		if(m.turn%2==1) {// fire from tank 2
				p.fire(((f.gheight[t2.backx]-f.gheight[t2.frontx])*t2.bheight/t2.blength+(t2.frontx+t2.backx)/2+t2.tlength*Math.cos(t2.tangle)),((f.gheight[t2.frontx]+f.gheight[t2.backx])/2+(t2.frontx-t2.backx)*t2.bheight/t2.blength-t2.tlength*Math.sin(t2.tangle)));
			}
		}
	}


	public void mouseMoved(MouseEvent e) {// when the mouse moves
		if(m.gamestate==1) {// if playing
		if(m.turn%2==0) {// sets the angle of the turret to face the mouse
		if(e.getX()==(int)(f.gheight[(int)t1.backx]-f.gheight[(int)t1.frontx])*t1.bheight/t1.blength+(t1.backx+t1.frontx)/2)//arctan of infinity is 1
		t1.tangle=3*Math.PI/2;
		else if (e.getX()>(int)(f.gheight[(int)t1.backx]-f.gheight[(int)t1.frontx])*t1.bheight/t1.blength+(t1.backx+t1.frontx)/2)
		t1.tangle=2*Math.PI-Math.atan(((675-e.getY())-((t1.frontx-t1.backx)*t1.bheight/t1.blength+(f.gheight[t1.frontx]+f.gheight[t1.backx])/2))/(e.getX()-((f.gheight[t1.backx]-f.gheight[t1.frontx])*t1.bheight/t1.blength+(t1.backx+t1.frontx)/2)));
		else if (e.getX()<(int)(f.gheight[(int)t1.backx]-f.gheight[(int)t1.frontx])*t1.bheight/t1.blength+(t1.backx+t1.frontx)/2)
			t1.tangle=Math.PI-Math.atan(((675-e.getY())-((t1.frontx-t1.backx)*t1.bheight/t1.blength+(f.gheight[t1.frontx]+f.gheight[t1.backx])/2))/(e.getX()-((f.gheight[t1.backx]-f.gheight[t1.frontx])*t1.bheight/t1.blength+(t1.backx+t1.frontx)/2)));
		}
		if(m.turn%2==1) {// see above
			if(e.getX()==(int)(f.gheight[(int)t2.backx]-f.gheight[(int)t2.frontx])*t2.bheight/t2.blength+(t2.backx+t2.frontx)/2)
			t2.tangle=3*Math.PI/2;
			else if (e.getX()>(int)(f.gheight[(int)t2.backx]-f.gheight[(int)t2.frontx])*t2.bheight/t2.blength+(t2.backx+t2.frontx)/2)
			t2.tangle=2*Math.PI-Math.atan(((675-e.getY())-((t2.frontx-t2.backx)*t2.bheight/t2.blength+(f.gheight[t2.frontx]+f.gheight[t2.backx])/2))/(e.getX()-((f.gheight[t2.backx]-f.gheight[t2.frontx])*t2.bheight/t2.blength+(t2.backx+t2.frontx)/2)));
			else if (e.getX()<(int)(f.gheight[(int)t2.backx]-f.gheight[(int)t2.frontx])*t2.bheight/t2.blength+(t2.backx+t2.frontx)/2-1)// -1 to fix a rounding error
				t2.tangle=Math.PI-Math.atan(((675-e.getY())-((t2.frontx-t2.backx)*t2.bheight/t2.blength+(f.gheight[t2.frontx]+f.gheight[t2.backx])/2))/(e.getX()-((f.gheight[t2.backx]-f.gheight[t2.frontx])*t2.bheight/t2.blength+(t2.backx+t2.frontx)/2)));
			}
		}
	}

}
