import java.awt.event.*;

public class keyInput extends KeyAdapter {
	main m;
	tank t1;
	tank t2;
	public void setstuff(main m, tank t1, tank t2) {// passes in all necessary class instances
		this.m=m;
		this.t1=t1;
		this.t2=t2;
	}

	public void  keyPressed(KeyEvent e) {
		if(m.gamestate==1) {// if playing
		int key = e.getKeyCode();
		if(m.turn%2==0) {// when press an arrow, move in that direction
		if(key == KeyEvent.VK_RIGHT)
			t1.xvel=2;
		 if(key == KeyEvent.VK_LEFT)
			t1.xvel=-2;
		}
		if(m.turn%2==1) {// see above
			if(key == KeyEvent.VK_RIGHT)
				t2.xvel=2;
			 if(key == KeyEvent.VK_LEFT)
				t2.xvel=-2;
			}
		}
	}
	public void keyReleased(KeyEvent e) {// when the arrow is released, stop moving
		int key = e.getKeyCode();
		if(m.gamestate==1) {// if playing
		if(key == KeyEvent.VK_RIGHT||key== KeyEvent.VK_LEFT)
			t1.xvel=0;
			t2.xvel=0;
		}
	}
		
		
}
