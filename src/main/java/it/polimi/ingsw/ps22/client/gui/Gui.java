package it.polimi.ingsw.ps22.client.gui;

import java.util.ArrayList;

import javax.swing.JFrame;

import it.polimi.ingsw.ps22.client.main.ViewClient;
import it.polimi.ingsw.ps22.server.model.Model;

public class Gui extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BoardPanel board;
	ViewClient view;
	//PersonalBoardPanel personalBoard;
	

	
	public void initGui(String username, String persBonusPath, ArrayList<String> avver, ArrayList<String> personBonusPaths, ViewClient view){
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		
		
		this.pack(); 
		this.setVisible(true);
		
	
		board = new BoardPanel(this.getWidth(), this.getHeight(), username, persBonusPath, avver, personBonusPaths, view); 
		
		//personalBoard = new PersonalBoardPanel(this.getWidth(), this.getHeight(), username);
		
		this.add(board);
		//this.add(personalBoard);
		this.view = view;
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	
	}
	
	public void updateGui(Model model){
		board.updateBoard(model);
	}
	
	/*public AnswerCost getPrivilege(int idMex){
		PrivilegeDialog p = new PrivilegeDialog(view);
		
	}
	*/


}
