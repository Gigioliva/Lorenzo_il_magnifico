package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.model.Model;
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
	
	@Override
	public CardLeader clone() {
		CardLeader temp=new CardLeader(this.name);
		temp.play=this.play;
		temp.copy=this.copy;
		for(HashMap<String, Integer> el: requisite){
			HashMap<String, Integer> x=new HashMap<String, Integer>();
			for(String el2: el.keySet()){
				x.put(el2, el.get(el2));
			}
			temp.addRequisite(x);
		}
		for(PermanentEffect el: permanentEffect){
			temp.addPermanentEffect(el.clone());
		}
		for(ImmediateEffect el: immediateEffect){
			temp.addImmediateEffect(el.clone());
		}
		return temp;
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

	private void applyPermanentEffect(Player player, Model model) {
		for(PermanentEffect el: permanentEffect){
			el.performEffect(player, model);
		}
	}

	private void applyActionEffect(Player player, Model model) {
		for(ImmediateEffect el: immediateEffect){
			el.performEffect(player, model);
		}
	}

	public void addRequisite(HashMap<String, Integer> req) {
		this.requisite.add(req);
	}

	public ArrayList<HashMap<String, Integer>> getRequisite() {
		return requisite;
	}

	public void playLeader(Player player, Model model) {
		if (play == false) {
			applyPermanentEffect(player, model);
			applyActionEffect(player, model);
			play = true;
		}
	}
	
	public boolean takeCardControl(Player player){
		boolean playable = false;
		for (HashMap<String, Integer> el : requisite) {
			playable = true;
			for (String type : el.keySet()) {
				if (el.get(type) > player.getGenericValue(type)) {
					playable = false;
				}
			}
			if (playable == true) {
				break;
			}
		}
		return playable;
	}

	public void resetLeader() {
		if (permanentEffect.size() == 0) {
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
	
	public String toString(){
		StringBuilder str=new StringBuilder("Card Leader: "+ name);
		for(ImmediateEffect el: immediateEffect){
			str.append("\nimmediate "+ el.toString());
		}
		for(PermanentEffect el: permanentEffect){
			str.append("Permanent " +el.toString());
		}
		return str.toString();
	}
}
