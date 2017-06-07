package it.polimi.ingsw.ps22.server.answer;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.model.Model;

public abstract class GenericAnswer implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	
	public GenericAnswer(int id){
		this.id=id;
	}
	
	public abstract void applyAnswer(Model model);

}