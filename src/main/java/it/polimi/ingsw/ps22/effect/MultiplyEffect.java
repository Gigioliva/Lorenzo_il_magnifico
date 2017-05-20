package it.polimi.ingsw.ps22.effect;

import java.util.ArrayList;

import it.polimi.ingsw.ps22.board.Board;
import it.polimi.ingsw.ps22.player.Player;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

//guadagna multiplicand per ogni multiplier
public class MultiplyEffect implements ActionEffect, ImmediateEffect {
	private String multiplier;
	private ResourceAbstract multiplicand;
	private String multiplicandType;
	
	public MultiplyEffect(String multiplier, ResourceAbstract multiplicand, String multiplicandType){
		this.multiplicand=multiplicand;
		this.multiplier=multiplier;
	}

	@Override
	public void performEffect(Player player, Board board) {
		ArrayList<String> resources = new ArrayList<String>();
		resources.add(multiplicandType);
		if (player.isCard(multiplier)){
			int numeroCarte = player.getDevelopmentCard(multiplier).size();
			multiplicand.addResource(new ResourceAbstract(numeroCarte*multiplicand.getQuantity()));
		}
		ResourceAbstract playerResource = player.getSpecificResource(multiplier);
		playerResource.addResource(new ResourceAbstract(playerResource.getQuantity() * multiplicand.getQuantity()));
		player.applyMalusResource(resources);
	}

}
