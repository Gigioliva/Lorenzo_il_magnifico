package it.polimi.ingsw.ps22.server.answer;

import java.io.Serializable;

import it.polimi.ingsw.ps22.server.model.Model;

/**
 * 
 * An answer is a message that the client sends back to the server, 
 * in order to reply to some request. Each answer has an id and contains 
 * all the information required by the model to perform the right action/
 * move/exchange/choice.
 * Each answer answers to a message contained in the message package
 *
 */
public abstract class GenericAnswer implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int id;
	
	public GenericAnswer(int id){
		this.id=id;
	}
	
	/**
	 * It applies the answer according to the information inserted
	 * @param model
	 */
	public abstract void applyAnswer(Model model);

}
