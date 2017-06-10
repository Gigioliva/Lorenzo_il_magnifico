package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.model.Model;

public class AnswerUsername extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private String answer;
	
	public AnswerUsername(String answer){
		super(0);
		this.answer=answer;
	}
	
	public String getAnswer(){
		return answer;
	}

	@Override
	public void applyAnswer(Model model) {

	}

}
