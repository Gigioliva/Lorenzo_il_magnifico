package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerUsername extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	private int numPlayer;
	
	public AnswerUsername(String answer, String pass, int numPlayer){
		super(0);
		this.username=answer;
		this.pass=pass;
		this.numPlayer=numPlayer;
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

	@Override
	public void applyAnswer(Model model) {

	}

}
