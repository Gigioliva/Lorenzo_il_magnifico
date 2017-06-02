package it.polimi.ingsw.ps22.answer;

import it.polimi.ingsw.ps22.model.Model;

public abstract class GenericAnswer {
	
	protected int id;
	
	public GenericAnswer(int id){
		this.id=id;
	}
	
	public abstract void applyAnswer(Model model);

}
