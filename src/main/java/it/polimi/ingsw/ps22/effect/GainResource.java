package it.polimi.ingsw.ps22.effect;
import java.util.HashMap;

import it.polimi.ingsw.ps22.board.Board;
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
		for(String type: gain.keySet()){
			player.getSpecificResource(type).addResource(gain.get(type));
		}
	}
	
	

}
