package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.effect.PermanentEffect;
import it.polimi.ingsw.ps22.player.Player;

public class CardLeader extends Card{
	
	private String name;
	private ArrayList<HashMap<String, Integer>> requisite;
	private PermanentEffect permanentEffect;
	private ActionEffect actionEffect;
	private boolean play;
	
	public CardLeader(String name){
		this.name=name;
		requisite=new ArrayList<HashMap<String, Integer>>();
		this.play=false;
	}

	public PermanentEffect getPermanentEffect() {
		return permanentEffect;
	}

	public void setPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffect = permanentEffect;
	}

	public ActionEffect getActionEffect() {
		return actionEffect;
	}

	public void setActionEffect(ActionEffect actionEffect) {
		this.actionEffect = actionEffect;
	}

	public String getName() {
		return name;
	}
	
	private void applyPermanentEffect(Player player) {
			if(permanentEffect!=null){
				permanentEffect.performEffect(player);
			}
	}
	
	private void applyActionEffect(Player player) {
		if(actionEffect!=null){
			actionEffect.performEffect(player);
		}
	}
	
	public void addRequisite(HashMap<String, Integer> req){
		this.requisite.add(req);
	}
	
	public ArrayList<HashMap<String, Integer>> addRequisite(){
		return requisite;
	}
	public ArrayList<HashMap<String, Integer>> getRequisite(){
		return requisite;
	}
	
	public void playLeader(Player player){
		if(play==false){
			applyPermanentEffect(player);
			applyActionEffect(player);
			play=true;
		}
	}
	
	public void resetLeader(){
		if(permanentEffect!=null){
			play=false;
		}
	}
	
	public boolean isPlay(){
		return play;
	}
}
