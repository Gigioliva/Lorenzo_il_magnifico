package it.polimi.ingsw.ps22.server.card;

import java.io.Serializable;
import java.util.HashMap;

import it.polimi.ingsw.ps22.server.effect.EndEffect;
import it.polimi.ingsw.ps22.server.effect.ImmediateEffect;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class RequisiteCost implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, ResourceAbstract> cost;
	private HashMap<String, ResourceAbstract> requisite;
	
	public RequisiteCost(){
		this.cost=new HashMap<String, ResourceAbstract>();
		this.requisite=new HashMap<String, ResourceAbstract>();
	}
	
	@Override
	public RequisiteCost clone() {
		RequisiteCost temp=new RequisiteCost();
		for(String el: cost.keySet()){
			temp.cost.put(el, cost.get(el).clone());
		}
		for(String el: requisite.keySet()){
			temp.requisite.put(el, requisite.get(el).clone());
		}
		return temp;
	}
	
	public void addCost(HashMap<String, ResourceAbstract> cost){
		this.cost=cost;
	}
	public void addRequisite(HashMap<String, ResourceAbstract> requisite){
		this.requisite=requisite;
	}
	public HashMap<String, ResourceAbstract> getCost(){
		return this.cost;
	}
	
	public boolean hasRequisite(String costType){
		return(requisite.containsKey(costType));
	}
	public int getSpecificCost(String type){
		return this.cost.get(type).getQuantity();
	}
	
	public int getSpecificRequisite(String type){
		return this.requisite.get(type).getQuantity();
	}
	
	public HashMap<String, ResourceAbstract> getRequisite(){
		return this.requisite;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		if (requisite.size() > 0){
			str.append("requisite: \n");
			for(String type: requisite.keySet()){
				str.append("  " + requisite.get(type).getQuantity()+ " "+ type+"\n");
			}
		}
		
		str.append("cost: \n");
		for(String type: cost.keySet()){
			str.append("  " + cost.get(type).getQuantity() + " " + type + "\n");
		}
		return str.toString();
	}
	
	
}