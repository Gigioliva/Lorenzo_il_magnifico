package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CheatButton extends JButton {

	private static final long serialVersionUID = 1L;

	public CheatButton() {
		super("Cheat");
		this.setVisible(false);
		this.addActionListener(new ButtonListener());
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame temp = new JFrame("Cheats");
			ImageIcon img = new ImageIcon("src/main/java/it/polimi/ingsw/ps22/client/gui/image/zeb.jpg");
			JLabel sfondo = new JLabel(img);
			sfondo.setMinimumSize(temp.getSize());
			temp.add(sfondo);
			temp.setSize(img.getIconWidth(), img.getIconHeight());
			temp.setResizable(false);
			temp.setLocationRelativeTo(null);
			temp.setVisible(true);
		}
	}

}
