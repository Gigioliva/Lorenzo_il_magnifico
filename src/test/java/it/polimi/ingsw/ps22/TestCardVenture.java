package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.card.CardVenture;
import it.polimi.ingsw.ps22.server.card.RequisiteCost;
import it.polimi.ingsw.ps22.server.resource.Coin;
import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;
import it.polimi.ingsw.ps22.server.resource.Stone;

public class TestCardVenture {
	
	private CardVenture card;
	private HashMap<String, ResourceAbstract> cost;
	private HashMap<String, ResourceAbstract> requisite;
	private RequisiteCost reqCost;
	
	@Before
	public void init(){
		card = new CardVenture();
		cost = new HashMap<String, ResourceAbstract>();
		requisite = new HashMap<String, ResourceAbstract>();
		
		ResourceAbstract r1 = new Coin(3);
		cost.put("Coin", r1);
		ResourceAbstract r2 = new Stone(4);
		cost.put("Stone", r2);
		ResourceAbstract r3 = new MilitaryPoint(1);
		requisite.put("MilitaryPoint", r3);
		
		reqCost = new RequisiteCost();
		reqCost.addCost(cost);
		reqCost.addRequisite(requisite);
		
		ArrayList<RequisiteCost> arr = new ArrayList<RequisiteCost>();
		arr.add(reqCost);
	}

	@Test
	public void AddRequisiteCosttest() {
		
		card.addRequisiteCost(cost, requisite);
		assert(containsRequisiteCost(card.getRequisiteCost(), reqCost));
		
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
	
	
	private boolean containsRequisiteCost(ArrayList<RequisiteCost> arr, RequisiteCost r){
		for(RequisiteCost reqCost: arr){
			if(equalsRequisiteCost(reqCost, r))
				return true;
		}
		return false;
	}
	

}
