package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.player.Player;

public class CardLeader extends Card {

	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<HashMap<String, Integer>> requisite;
	private ArrayList<PermanentEffect> permanentEffect;
	private ArrayList<ImmediateEffect> immediateEffect;
	private boolean play;
	private boolean copy=false;

	public CardLeader(String name) {
		this.name = name;
		requisite = new ArrayList<HashMap<String, Integer>>();
		permanentEffect=new ArrayList<PermanentEffect>();
		immediateEffect=new ArrayList<ImmediateEffect>();
		this.play = false;
	}
	
	public void setCopy(){
		copy=true;
	}

	public ArrayList<PermanentEffect> getPermanentEffect() {
		return permanentEffect;
	}

	public void addPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffect.add(permanentEffect);
	}

	public ArrayList<ImmediateEffect> getImmediateEffect() {
		return immediateEffect;
	}

	public void addImmediateEffect(ImmediateEffect immediateEffect) {
		this.immediateEffect.add(immediateEffect);
	}

	public String getName() {
		return name;
	}

	private void applyPermanentEffect(Player player) {
		for(PermanentEffect el: permanentEffect){
			el.performEffect(player);
		}
	}

	private void applyActionEffect(Player player) {
		for(ImmediateEffect el: immediateEffect){
			el.performEffect(player);
		}
	}

	public void addRequisite(HashMap<String, Integer> req) {
		this.requisite.add(req);
	}

	public ArrayList<HashMap<String, Integer>> addRequisite() {
		return requisite;
	}

	public ArrayList<HashMap<String, Integer>> getRequisite() {
		return requisite;
	}

	public void playLeader(Player player) {
		if (play == false) {
			applyPermanentEffect(player);
			applyActionEffect(player);
			play = true;
		}
	}

	public void resetLeader() {
		if (permanentEffect.size() != 0) {
			play = false;
		}
	}
	
	public boolean getCopy(){
		return copy;
	}
	
	public void setCopy(boolean copy){
		this.copy=copy;
	}
	
	public boolean isPlay(){
		return play;
	}
}
