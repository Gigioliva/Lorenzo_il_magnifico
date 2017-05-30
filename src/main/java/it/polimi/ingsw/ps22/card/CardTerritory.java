package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.effect.GainResource;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.player.Player;

public class CardTerritory extends DevelopmentCard {
	private int actionValue;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<ActionEffect> actionEffects;

	public CardTerritory() {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.actionEffects = new ArrayList<ActionEffect>();
		this.actionValue = 0;
	}

	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}

	public void addActionEffect(ActionEffect effect) {
		this.actionEffects.add(effect);
	}

	public void applyImmediateEffects(Player player) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player);
			if (player.getSpecBonus().returnBool("DoubleGain") && el instanceof GainResource) {
				el.performEffect(player);
			}
		}
	}

	public ArrayList<ActionEffect> getActionEffects() {
		return this.actionEffects;
	}

	public void applyActionEffect(Player player, int number) {
		try{
			this.actionEffects.get(number).performEffect(player);
		}catch (IndexOutOfBoundsException e){
			return;
		}
	}

	public int getActionValue() {
		return this.actionValue;
	}
	
	public void setActionValue(int actionVal) {
		this.actionValue=actionVal;
	}
	
	@Override
	public boolean takeCardControl(Player player){
		int size = player.getSizeCard("Territory");
		//se il giocatore ha già raggiunto il limite di carte territorio non può prendere la carta
		if (size >= player.getPersonalBoard().getRequirementHarvest().size())
			return false;
		else{
			int requirementHarvest = player.getPersonalBoard().getRequirementHarvest().get(size+1).getQuantity();
			int harvestPoint = player.getResources().get("Territory").getQuantity();
			return requirementHarvest <= harvestPoint;
		}
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		
		str.append("nome carta = " + this.getName() + "\n");
		
		str.append("era: " + super.getEra() + "\n");
		
		str.append("valore azione = " + actionValue + "\n");
		
		if(immediateEffects.size() > 0){
			str.append("Immediate effects: \n");
			for(int i = 0; i < immediateEffects.size(); i++){
				str.append("  [" + (i+1) + "]" + immediateEffects.get(i).toString() + "\n");
			}
		}
		
		str.append("Action effects: \n");
		for(int i = 0; i < actionEffects.size(); i++){
			str.append("  [" + (i+1) + "]" + actionEffects.get(i).toString() + "\n");
		}
		return str.toString();
	}

}
