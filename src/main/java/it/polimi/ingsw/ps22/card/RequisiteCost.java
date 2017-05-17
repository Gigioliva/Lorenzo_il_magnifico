package it.polimi.ingsw.ps22.card;

import java.util.HashMap;

import it.polimi.ingsw.ps22.resource.Resource;

public class RequisiteCost{
	private HashMap<String, Resource> cost;
	private HashMap<String, Resource> requisite;
	
	public RequisiteCost(){
		this.cost=new HashMap<String, Resource>();
		this.requisite=new HashMap<String, Resource>();
	}
	
	public void addCost(HashMap<String, Resource> cost){
		this.cost=cost;
	}
	public void addRequisite(HashMap<String, Resource> requisite){
		this.requisite=requisite;
	}
	public HashMap<String, Resource> getCost(){
		return this.cost;
	}
	public HashMap<String, Resource> getRequire(){
		return this.requisite;
	}
}