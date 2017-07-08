package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.AnswerExcomm;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

/**
 * 
 * During the game a player may be asked to choose whether
 * being excommunicated or not. This message implements this kind of question
 *
 */
public class AskExcomm extends MessageAsk{

	private static final long serialVersionUID = 1L;
	private Player player;

	/**
	 * it creates a new {@link AskExcomm} message
	 * @param player the target {@link Player}
	 */
	public AskExcomm(Player player) {
		super(player.getUsername());
		this.player=player;
		setString("Appoggi la chiesa?");
	}
	
	/**
	 * 
	 * @param str the text of the message
	 * @param id the id of the message
	 */
	public AskExcomm(String str,int id) {
		super(str,id);
	}

	/**
	 * 
	 * @return the target {@link Player}
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * This method will be used to apply default answers to
	 * messages which dont't have answer by the player in a given time clock.
	 * In this case the {@link Player} won't be excommunicated
	 */
	@Override
	public void applyDefault(Model model){
		AnswerExcomm ans=new AnswerExcomm(this.getId(), "NO");
		ans.applyAnswer(model);
	}
	
	public AskExcomm accept(VisitorA visitor){
		return visitor.visit(this);
	}
	
	public void accept(VisitorB visitor){
		visitor.visit(this);
	}

}
