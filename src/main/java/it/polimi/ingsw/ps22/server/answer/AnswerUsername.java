package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerUsername extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	private int numPlayer;
	private boolean reg;
	
	public AnswerUsername(String answer, String pass, int numPlayer, boolean reg){
		super(0);
		this.username=answer;
		this.pass=pass;
		this.numPlayer=numPlayer;
		this.reg=reg;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return pass;
	}
	
	public int getNumPlayer(){
		return numPlayer;
	}
	
	public boolean getReg(){
		return reg;
	}

	@Override
	public void applyAnswer(Model model) {

	}

}
