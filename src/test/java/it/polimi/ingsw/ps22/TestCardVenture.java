package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.NoPointsCard;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Stone;

public class TestCardVenture {
	
	private CardVenture card;
	private HashMap<String, ResourceAbstract> cost;
	private HashMap<String, ResourceAbstract> requisite;
	private ArrayList<EndEffect> endEffects = new ArrayList<>();
	private NoPointsCard eff = new NoPointsCard("Territory");
	private RequisiteCost reqCost = new RequisiteCost();
	private ArrayList<RequisiteCost> arr = new ArrayList<>();
	
	@Before
	public void init(){
		card = new CardVenture();
		cost = new HashMap<>();
		requisite = new HashMap<>();
		
		endEffects.add(new NoPointsCard("Territory"));
		
		ResourceAbstract r1 = new Coin(3);
		cost.put("Coin", r1);
		ResourceAbstract r2 = new Stone(4);
		cost.put("Stone", r2);
		ResourceAbstract r3 = new MilitaryPoint(1);
		requisite.put("MilitaryPoint", r3);
		
		reqCost.addCost(cost);
		reqCost.addRequisite(requisite);
		
		arr.add(reqCost);
	}
	
	@Test
	public void getEndEffectTest(){
		card.addEndEffect(eff);
		for(EndEffect effect: card.getEndEffect())
			assert(effect == eff);
	}

	@Test
	public void addRequisiteCostTest() {
		
		card.addRequisiteCost(cost, requisite);
		assert(containsRequisiteCost(card.getRequisiteCost(), reqCost));
		
	}
	
	@Test
	public void getRequisiteCostTest(){
		card.addRequisiteCost(cost, requisite);
		ArrayList<RequisiteCost> reqCosts = card.getRequisiteCost();
		assert(equalsArrRequisiteCost(reqCosts, arr));
	}
	
	
	
	private boolean equalsResource(ResourceAbstract r1, ResourceAbstract r2){
		
		return (r1.getName().equals(r2.getName()) && r1.getQuantity() == r2.getQuantity() );
	}
	
	private boolean equalsCostOrRequisite(HashMap<String, ResourceAbstract> r1, HashMap<String, ResourceAbstract> r2){
		
		for(String type: r1.keySet()){
			if(!r2.containsKey(type))
				return false;
			if(!equalsResource(r1.get(type), r2.get(type)))
				return false;
		}
		
		for(String type: r2.keySet()){
			if(!r1.containsKey(type))
				return false;
		}
		
		return true;
		
		
	}
	private boolean equalsRequisiteCost(RequisiteCost r1, RequisiteCost r2){
		return( equalsCostOrRequisite(r1.getCost(), r2.getCost()) && equalsCostOrRequisite(r1.getRequisite(), r2.getRequisite()));
	}
	
	private boolean equalsArrRequisiteCost(List<RequisiteCost> arr1, List<RequisiteCost> arr2){
		for(RequisiteCost r: arr1){
			if(!containsRequisiteCost(arr2, r))
				return false;
		}
		for(RequisiteCost r:arr2){
			if(!containsRequisiteCost(arr1, r))
				return false;
		}
		return true;
	}
	
	
	private boolean containsRequisiteCost(List<RequisiteCost> arr, RequisiteCost r){
		for(RequisiteCost reqCost: arr){
			if(equalsRequisiteCost(reqCost, r))
				return true;
		}
		return false;
	}
	

}
