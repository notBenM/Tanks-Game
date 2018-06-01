import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.Map;

import javax.swing.*;

public class menus extends JPanel {
tank t1,t2;
graphical g;
	public menus(tank t1, tank t2, graphical g) {// imports the necessary class instances
	this.t1=t1;
	this.t2=t2;
	this.g=g;
}
public int red=255,green=0,blue=0;// the background color of the win screen
	JPanel winscreen = new JPanel() {// appears when one player wins
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			setBackground(new Color(red,green,blue));
			}
	};
	JLabel winner;// displays the name of the winner
	public void setwinner(){// determines who won and sets the label to their player number
	if(t1.health<=0&&t2.health>0)
		winner = new JLabel("Player 2  Wins!");
	else if(t2.health<=0&&t1.health>0)
		winner = new JLabel("Player 1 Wins!");
	else
		winner = new JLabel("It's A  Tie!");
	winscreen.setLayout(new GridBagLayout());
	winscreen.add(winner);
	winner.setFont(new Font("Times new Roman", Font.BOLD, 60));
	winner.setForeground(new Color(255,255,255));
	}
}
