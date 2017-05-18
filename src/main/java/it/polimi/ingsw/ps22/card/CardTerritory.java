package it.polimi.ingsw.ps22.card;

import java.util.ArrayList;
import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.effect.ActionEffect;
import it.polimi.ingsw.ps22.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.player.Player;

public class CardTerritory extends DevelopmentCard {
	private final int actionValue;
	private ArrayList<ImmediateEffect> immediateEffects;
	private ArrayList<ActionEffect> actionEffects;

	public CardTerritory(int other) {
		this.immediateEffects = new ArrayList<ImmediateEffect>();
		this.actionEffects = new ArrayList<ActionEffect>();
		this.actionValue = other;
	}

	public void addImmediateEffect(ImmediateEffect effect) {
		this.immediateEffects.add(effect);
	}

	public void addActionEffect(ActionEffect effect) {
		this.actionEffects.add(effect);
	}

	public void applyImmediateEffects(Player player, Board board) {
		for (ImmediateEffect el : immediateEffects) {
			el.performEffect(player, board);
		}
	}

	public ArrayList<ActionEffect> getActionEffects() {
		return this.actionEffects;
	}

	public void applyActionEffect(Player player, Board board, int number) {
		this.actionEffects.get(number).performEffect(player, board);
	}

	public int getActionValue() {
		return this.actionValue;
	}
	
	@Override
	public boolean takeCardControl(Player player){
		int size = player.getSizeCard("Territory");
		//se il giocatore ha già raggiunto il limite di carte territorio non può prendere la carta
		if (size >= player.getPersonalBoard().getRequirementHarvest().size())
			return false;
		else{
			int requirementHarvest = player.getPersonalBoard().getRequirementHarvest().get(size+1).getQuantity();
			int harvestPoint = player.getPoints().get("Territory").getQuantity();
			return requirementHarvest <= harvestPoint;
		}
	}

}
