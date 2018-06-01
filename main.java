import javax.swing.*;

public class main extends JFrame implements Runnable{
	public int gamestate=1;//0:menu,1:playing
	public int turn=0;//the game turn
	field f;
	projectile p;
	tank t1,t2;
	graphical g;
	keyInput ki;
	menus ms;
	
	private boolean running = false;//whether the thread is running
	private Thread thread;// second thread updates graphics
	private int t;
	
	public void setstuff(field f, projectile p, tank t1, tank t2,graphical g, menus ms) {// passes in the necessary class instances
		this.f=f;
		this.p=p;
		this.t1=t1;
		this.t2=t2;
		this.g=g;
		this.ms=ms;
	}
	
	private synchronized void start() {// starts the thread if not already running
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() throws InterruptedException {// stops the thread if running
		if(!running)
			return;
		running = false;
		thread.join();
		System.exit(1);
		
	}
	
	public void run() {// runs repeatedly in the background, ticks once every 1/60 of a second, and refreshes the graphics as often as possible
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime) /ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
		}
		
	}
	
	private void tick() {// happens once every 1/60 of a second
		if(t1.backx+t1.xvel>0&&t1.frontx+t1.xvel<1200&&t1.fuel>0&&turn%2==0&&((t1.placeFront((int)(t1.backx+t1.xvel))<t2.backx-t1.bheight/2)||t1.backx+t1.xvel>t2.frontx+t1.bheight/2)) {// moves the tank forward as long as the tank has fuel, stays inbounds, and doesn't collide with the other tank
		t1.backx+=t1.xvel;
		t1.frontx=t1.placeFront(t1.backx);
		t1.fuel-=Math.abs(t1.xvel);
		}
		if(t2.backx+t2.xvel>0&&t2.frontx+t2.xvel<1200&&t2.fuel>0&&turn%2==1&&((t2.placeFront((int)(t2.backx+t2.xvel))<t1.backx-t1.bheight/2)||t2.backx+t2.xvel>t1.frontx+t1.bheight/2)) {// see above
		t2.backx+=t2.xvel;
		t2.frontx=t2.placeFront(t2.backx);
		t2.fuel-=Math.abs(t2.xvel);
		}
		if(t1.health<=0||t2.health<=0) {// if either player ran out of health, remove them
			gamestate=0;
			if(t==0) {
			ms.setwinner();
			ms.winscreen.setBounds(0,0,1200,675);
			g.lp.add(ms.winscreen, new Integer(3));
			if(t1.health<=0)
				g.lp.remove(g.tank1);
			if(t2.health<=0)
				g.lp.remove(g.tank2);
			t++;
			}
			if(ms.red>0&&ms.blue==0) {
			ms.red-=5;
			ms.green+=5;
			}
			else if (ms.green>0) {
				ms.green-=5;
				ms.blue+=5;
			}
			else if(ms.blue>0) {
				ms.blue-=5;
				ms.red+=5;
			}
			
		}
			
	}
	
	private void render() {// refreshes the screen
		g.repaint();
		
	}

	  public static void main(String[] args){// its main... you know what it does... why am I commenting this?
		  System.out.println("Use the left and right arrow keys to move. Click to fire. Have fun!");
		  main m = new main();
		  graphical g = new graphical();
		  field f = new field();
		  projectile p = new projectile();
		  keyInput ki = new keyInput();
		  mouseInput mi = new mouseInput();
		  tank t1 = new tank(f, p, g,ki,100);
		  tank t2 = new tank(f, p, g,ki,1100);
		  menus ms = new menus(t1,t2,g);
		  m.setstuff(f, p, t1, t2, g,ms);
		  g.setstuff(f, p, t1, t2,ki,mi);
		  p.setstuff(f, g, t1, t2, m,mi);
		  ki.setstuff(m,t1,t2);
		  mi.setstuff(m,f, p, t1, t2);
		  g.gstart();
		  m.start();		  	  
		  }
	}

