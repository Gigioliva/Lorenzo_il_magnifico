package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerUsername extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	
	public AnswerUsername(String answer, String pass){
		super(0);
		this.username=answer;
		this.pass=pass;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return pass;
	}

	@Override
	public void applyAnswer(Model model) {

	}

}
