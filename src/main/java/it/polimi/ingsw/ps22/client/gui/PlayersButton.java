package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PlayersButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4395364097597264389L;
	PersonalBoardPanel personalBoard;
	
	public PlayersButton(double widthScreen, double heightScreen, String username, int pos){
		this.setText(username);
		
		this.setBounds((int)widthScreen/2 + 300 + 100*pos, (int)heightScreen - 100, 100, 50); 
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username);
		
		this.addActionListener(new ShowPersonalBoard(username));
		
	}
	
	private class ShowPersonalBoard implements ActionListener{
		
		
		private  String username;
		
		public ShowPersonalBoard(String username) {
			this.username = username;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFrame frame = new JFrame(username);
			frame.add(personalBoard);
			
			frame.setBounds(0,0,personalBoard.getWidth(), personalBoard.getHeight());
			
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
		}
		
	}
}
