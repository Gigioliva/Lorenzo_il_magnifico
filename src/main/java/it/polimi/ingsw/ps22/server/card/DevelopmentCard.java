package it.polimi.ingsw.ps22.server.card;

import java.util.ArrayList;
import java.util.HashMap;
import it.polimi.ingsw.ps22.server.effect.ActionEffect;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.effect.PermanentEffect;
import it.polimi.ingsw.ps22.server.model.Model;
import it.polimi.ingsw.ps22.server.player.Player;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.Resource;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public abstract class DevelopmentCard extends Card {
	
	private static final long serialVersionUID = 1L;
	private int era;
	private String name;

	public void setEra(int era) {
		this.era = era;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEra() {
		return this.era;
	}

	public String getName() {
		return this.name;
	}
	
	public abstract DevelopmentCard clone();

	public boolean takeCardControl(Player player) {
		return false;
	}
	
	
	public ArrayList<RequisiteCost> getAffordableCosts(Player player){
		return null;
	}

	
	public void addRequisiteCost(HashMap<String, ResourceAbstract> cost, HashMap<String, ResourceAbstract> requisite) {

	}

	public void addImmediateEffect(ImmediateEffect effect) {
		
	}

	public ArrayList<RequisiteCost> getRequisiteCost(){
		return null;
	}
	
	public void addActionEffect(ActionEffect effect) {

	}

	public void addEndEffect(EndEffect effect) {

	}

	public void addPermanentEffect(PermanentEffect effect) {

	}

	public void applyImmediateEffects(Player player, Model model) {

	}

	public void loadEndEffects(Player player) {
		
	}

	public void applyActionEffect(Player player, int number, Model model) {

	}
	
	public void applyPermanentEffects(Player player, Model model){
		
	}

	public ArrayList<ActionEffect> getActionEffects() {
		return new ArrayList<ActionEffect>();
	}
	
	public ArrayList<EndEffect> getEndEffect(){
		return new ArrayList<EndEffect>();
	}

	public int getActionValue() {
		return 0;
	}
	
	public void addCost(Coin coin){
		
	}
	
	public void addCost(String type, Resource resource) {
		
	}
	
	public HashMap<String, Resource> getCost() {
		return null;
	}
	
	public void applyCostToPlayer(Player player){
		
	}
	
	public void applyCostToPlayer(Player player, RequisiteCost chosenCost){
		
	}

}
