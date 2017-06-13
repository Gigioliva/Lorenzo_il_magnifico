package it.polimi.ingsw.ps22.server.effect;

import it.polimi.ingsw.ps22.server.message.AskFamily;
import it.polimi.ingsw.ps22.server.player.Player;

public class StrangeEffect implements PermanentEffect, ImmediateEffect{
	
	private static final long serialVersionUID = 1L;
	private String type;
	
	public StrangeEffect(String type) {
		this.type = type;
	}

	@Override
	public void performEffect(Player player) {
		if(type.equals("OneFamilyCol6")){
			AskFamily mex=new AskFamily(player);
			mex.applyAsk();
		}else{
			player.getSpecBonus().setSpecBonus(type);
		}
	}
	
	@Override
	public StrangeEffect clone(){
		StrangeEffect temp=new StrangeEffect(this.type);
		return temp;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(type);
		return str.toString();
	}
}
