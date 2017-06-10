package it.polimi.ingsw.ps22.client.gui;

import javax.swing.JFrame;

import it.polimi.ingsw.ps22.server.model.Model;

public class Gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BoardPanel board;
	//PersonalBoardPanel personalBoard;
	

	
	public void initGui(String username){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		
		
		this.pack(); 
		this.setVisible(true);
		
	
		board = new BoardPanel(this.getWidth(), this.getHeight(), username); 
		
		//personalBoard = new PersonalBoardPanel(this.getWidth(), this.getHeight(), username);
		
		this.add(board);
		//this.add(personalBoard);
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
	}
	
	public void updateGui(Model model){
		//board.update(model.getBoard());
	}


}
