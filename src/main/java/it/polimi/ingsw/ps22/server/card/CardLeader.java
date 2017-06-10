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
	private PermanentEffect permanentEffect;
	private ImmediateEffect immediateEffect;
	private boolean play;
	private boolean copy=false;

	public CardLeader(String name) {
		this.name = name;
		requisite = new ArrayList<HashMap<String, Integer>>();
		this.play = false;
	}
	
	public void setCopy(){
		copy=true;
	}

	public PermanentEffect getPermanentEffect() {
		return permanentEffect;
	}

	public void setPermanentEffect(PermanentEffect permanentEffect) {
		this.permanentEffect = permanentEffect;
	}

	public ImmediateEffect getActionEffect() {
		return immediateEffect;
	}

	public void setActionEffect(ImmediateEffect immediateEffect) {
		this.immediateEffect = immediateEffect;
	}

	public String getName() {
		return name;
	}

	private void applyPermanentEffect(Player player) {
		if (permanentEffect != null) {
			permanentEffect.performEffect(player);
		}
	}

	private void applyActionEffect(Player player) {
		if (immediateEffect != null) {
			immediateEffect.performEffect(player);
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
		if (permanentEffect != null) {
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
