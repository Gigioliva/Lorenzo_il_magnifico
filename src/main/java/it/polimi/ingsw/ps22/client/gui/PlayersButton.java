package it.polimi.ingsw.ps22.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import it.polimi.ingsw.ps22.server.model.Model;

public class PlayersButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4395364097597264389L;
	PersonalBoardPanel personalBoard;
	
	public PlayersButton(double widthScreen, double heightScreen, String username, int pos, String pathPersonalBonus){
		this.setText(username);
		
		personalBoard = new PersonalBoardPanel(widthScreen, heightScreen, username, pathPersonalBonus);
		
		Rectangle dim = PersonalBoardAdaptive.getPlayerButtonSLot(personalBoard.resizeFactor, pos);
		this.setBounds(dim.getInitx() + personalBoard.getBounds().width, dim.getInity(), dim.getOffsetX(), dim.getOffsetY()); 
		
		this.addActionListener(new ShowPersonalBoard(username));
		
	}
	
	public void update(Model model){
		personalBoard.update(model);
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
