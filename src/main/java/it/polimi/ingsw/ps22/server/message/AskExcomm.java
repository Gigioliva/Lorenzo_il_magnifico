package it.polimi.ingsw.ps22.server.message;

import it.polimi.ingsw.ps22.client.main.VisitorB;
import it.polimi.ingsw.ps22.server.answer.AnswerExcomm;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.view.VisitorA;

public class AskExcomm extends MessageAsk{

	private static final long serialVersionUID = 1L;
	private Player player;

	public AskExcomm(Player player) {
		super(player.getUsername());
		this.player=player;
		setString("Appoggi la chiesa?");
	}
	
	public AskExcomm(String str,int id) {
		super(str,id);
	}

	public Player getPlayer() {
		return player;
	}
	
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
