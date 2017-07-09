package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheatButton extends JButton {

	private static final long serialVersionUID = 1L;
	
	public CheatButton(){
		super("Cheat");
		this.addActionListener(new ButtonListener());
	}
	
	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame temp=new JFrame("Cheats");
			ImageIcon img=new ImageIcon("./image/zeb.png");
			JLabel sfondo=new JLabel(img);
			sfondo.setMinimumSize(temp.getSize());
			temp.add(sfondo);
			temp.setSize(img.getIconWidth(), img.getIconHeight());
			temp.setVisible(true);
		}
	}

}
