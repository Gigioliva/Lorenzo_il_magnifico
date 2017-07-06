package it.polimi.ingsw.ps22.server.answer;

import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.message.GenericMessage;
import it.polimi.ingsw.ps22.server.message.MessageAsk;
import it.polimi.ingsw.ps22.server.model.Color;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Family;
import it.polimi.ingsw.ps22.server.player.Player;

/**
 * 
 * Some effects require the player to set the value 
 * of a {@link Family} member to 6. This class implements the answer of 
 * the player to this question
 *
 */
public class AnswerFamily extends GenericAnswer {

	private static final long serialVersionUID = 1L;
	private Color color;
	
	/**
	 * 
	 * @param id the id of the message
	 * @param color the color of the chosen familiar 
	 */
	public AnswerFamily(int id, Color color) {
		super(id);
		this.color=color;
	}
	
	/**
	 * 
	 * @return the {@link Color} of the chosen familiar
	 */
	public Color getColor(){
		return color;
	}

	/**
	 * it applies the answer by seetting the value 
	 * of the chosen {@link Family} member to 6
	 */
	@Override
	public void applyAnswer(Model model) {
		AskFamily ask=null;
		for(MessageAsk el: model.getWaitAnswer()){
			if(el.getId()==id){
				ask=(AskFamily)el;
			}
		}
		if(ask!=null){
			if(color!=Color.NEUTRAL){
				Player player=model.getCurrentPlayer();
				player.getAllFamily().get(color).setValue(6);
				model.getWaitAnswer().remove(ask);
				model.notifyModel();
				return;
			}
			model.notifyAsk(ask);
			return;
		}
		GenericMessage mex=new GenericMessage();
		mex.setString("risposta errata");
		model.notifyMessage(mex);
	}

}
