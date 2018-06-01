import javax.swing.*;

public class graphical extends JFrame{
	field f;
	projectile p;
	tank t1,t2;
	keyInput ki;
	mouseInput mi;
	JLayeredPane lp = new JLayeredPane();// Creates a new layered pane
	void setstuff(field f,projectile p,tank t1,tank t2,keyInput ki,mouseInput mi) {// passes in all necessary class instances
		this.f=f;
		this.p=p;
		this.t1=t1;
		this.t2=t2;
		this.ki=ki;
		this.mi=mi;
	}
	
	public  graphical() {//makes the jFrame
		super("Tanks a Lot");
	    setSize(1200, 675);
	    setResizable(false);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

JLayeredPane tank1 = new JLayeredPane();
JLayeredPane tank2 = new JLayeredPane();


public void gstart() {// not in the constructor because some things need to happen before others, adds all elements to the layered pane, then adds the layered pane to the frame
	requestFocus();
	addKeyListener(ki);
	addMouseListener(mi);
	addMouseMotionListener(mi);
	f.background.setBounds(0,0,1200,675);
    p.bullet.setBounds(0,0,1200,675);
    p.bullet.setOpaque(false);
	t1.tankrecto.setBounds(0,0,1200,675);
	t1.tankrecto.setOpaque(false);
	t1.tankrecti.setBounds(0,0,1200,675);
	t1.tankrecti.setOpaque(false);
	t1.tankdome.setBounds(0,0,1200,675);
	t1.tankdome.setOpaque(false);
	t1.turret.setBounds(0,0,1200,675);
	t1.turret.setOpaque(false);
	t1.wheelso.setBounds(0,0,1200,675);
	t1.wheelso.setOpaque(false);
	t1.wheelsi.setBounds(0,0,1200,675);
	t1.wheelsi.setOpaque(false);
	t1.healthbar.setBounds(0,0,1200,675);
	t1.healthbar.setOpaque(false);
	t1.fuelbar.setBounds(0,0,1200,675);
	t1.fuelbar.setOpaque(false);
	t2.tankrecto.setBounds(0,0,1200,675);
	t2.tankrecto.setOpaque(false);
	t2.tankrecti.setBounds(0,0,1200,675);
	t2.tankrecti.setOpaque(false);
	t2.tankdome.setBounds(0,0,1200,675);
	t2.tankdome.setOpaque(false);
	t2.turret.setBounds(0,0,1200,675);
	t2.turret.setOpaque(false);
	t2.wheelso.setBounds(0,0,1200,675);
	t2.wheelso.setOpaque(false);
	t2.wheelsi.setBounds(0,0,1200,675);
	t2.wheelsi.setOpaque(false);
	t2.healthbar.setBounds(0,0,1200,675);
	t2.healthbar.setOpaque(false);
	t2.fuelbar.setBounds(0,0,1200,675);
	t2.fuelbar.setOpaque(false);
    lp.add(f.background, new Integer(0));
    lp.add(p.bullet, new Integer(2));

    
    tank1.add(t1.turret, new Integer(0));
    tank1.add(t1.tankdome, new Integer(1));
    tank1.add(t1.tankrecto, new Integer(2));
    tank1.add(t1.wheelso, new Integer(2));
    tank1.add(t1.tankrecti, new Integer(3));
    tank1.add(t1.wheelsi, new Integer(3));
    tank1.add(t1.healthbar, new Integer(4));
    tank1.add(t1.fuelbar, new Integer(4));
    tank1.setBounds(0,0,1200,675);

    tank2.add(t2.turret, new Integer(0));
    tank2.add(t2.tankdome, new Integer(1));
    tank2.add(t2.tankrecto, new Integer(2));
    tank2.add(t2.wheelso, new Integer(2));
    tank2.add(t2.tankrecti, new Integer(3));
    tank2.add(t2.wheelsi, new Integer(3));
    tank2.add(t2.healthbar, new Integer(4));
    tank2.add(t2.fuelbar, new Integer(4));
    tank2.setBounds(0,0,1200,675);
    
    lp.add(tank1, new Integer(1));
    lp.add(tank2, new Integer(1));
	add(lp);
	setVisible(true);
}
}

