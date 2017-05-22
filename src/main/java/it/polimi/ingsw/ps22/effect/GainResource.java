package it.polimi.ingsw.ps22.effect;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.controller.Ask;
import it.polimi.ingsw.ps22.model.Model;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class GainResource implements ActionEffect,ImmediateEffect {
	private HashMap<String,ResourceAbstract> gain;
	
	public GainResource(){
		gain=new HashMap<String, ResourceAbstract>();
	}
	
	public void addGain(String type, ResourceAbstract resource){
		gain.put(type, resource);
	}
	
	public HashMap<String,ResourceAbstract> getGain(){
		return gain;
	}
	
	@Override
	public void performEffect(Player player, Board board) {
		int cont = 0;
		for(String type: gain.keySet()){
			if(type.equals("CouncilPrivilege")){
				cont = gain.get(type).getQuantity();
			}
			else{
				player.getSpecificResource(type).addResource(gain.get(type));
			}
		}
		player.applyMalusResource(new ArrayList<String>(gain.keySet()));
		if(cont>0){
			Ask ask = Model.getAsk();
			ask.askPrivilegeChange(cont);
		}
	}

	@Override
	public boolean canAffordCost(Player player) {
		return true;
	}
	
}
