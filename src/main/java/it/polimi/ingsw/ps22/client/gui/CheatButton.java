package it.polimi.ingsw.ps22.client.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
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
		this.addActionListener(new ButtonListener());
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame temp = new JFrame("Cheats");
			ImageIcon img = new ImageIcon("./image/zeb.jpg");
			JLabel sfondo = new JLabel(img);
			sfondo.setBounds(0, 0, 600, 400);
			sfondo.setMinimumSize(temp.getSize());
			temp.add(sfondo);
			sfondo.setVisible(true);
			temp.setMinimumSize(new Dimension(600, 400));
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			temp.setLocation(dim.width / 2 - temp.getSize().width / 2, dim.height / 2 - temp.getSize().height / 2);
			temp.setVisible(true);
		}
	}

}
