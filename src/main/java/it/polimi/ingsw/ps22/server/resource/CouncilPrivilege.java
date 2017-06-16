package it.polimi.ingsw.ps22.server.resource;

import java.util.ArrayList;
import java.util.HashMap;

public class CouncilPrivilege extends ResourceAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private transient static HashMap<Integer, HashMap<String,ResourceAbstract>> possibleExchanges;
	
	public CouncilPrivilege(int councilprivilege){
		super(councilprivilege);
		
		possibleExchanges = new HashMap<Integer, HashMap<String,ResourceAbstract>>();
		
		HashMap<String, ResourceAbstract> scelta1 = new HashMap<String, ResourceAbstract>();
		scelta1.put("Stone", new Stone(1));
		scelta1.put("Wood", new Wood(1));
		possibleExchanges.put(new Integer(1), scelta1);
		
		HashMap<String, ResourceAbstract> scelta2 = new HashMap<String, ResourceAbstract>();
		scelta2.put("Servant", new Servant(2));
		possibleExchanges.put(new Integer(2), scelta2);
		
		HashMap<String, ResourceAbstract> scelta3 = new HashMap<String, ResourceAbstract>();
		scelta3.put("Coin", new Coin(2));
		possibleExchanges.put(new Integer(3), scelta3);
		
		HashMap<String, ResourceAbstract> scelta4 = new HashMap<String, ResourceAbstract>();
		scelta4.put("MilitaryPoint", new MilitaryPoint(2));
		possibleExchanges.put(new Integer(4), scelta4);
		
		HashMap<String, ResourceAbstract> scelta5 = new HashMap<String, ResourceAbstract>();
		scelta5.put("FaithPoint", new FaithPoint(1));
		possibleExchanges.put(new Integer(5), scelta5);
		
		
	}
	
	public static HashMap<String, ResourceAbstract> exchangeWithResources(ArrayList<Integer> chosenPossibilities){
		HashMap<String, ResourceAbstract> resourcesForPlayer = new HashMap<String, ResourceAbstract>();
		for(Integer index: chosenPossibilities){
			for(String resource: possibleExchanges.get(index).keySet())
				resourcesForPlayer.put(resource, possibleExchanges.get(index).get(resource));
		}
		return resourcesForPlayer;
	}
		
	public String getName() {
		return "CouncilPrivilege";
	}
	
	@Override
	public CouncilPrivilege clone() {
		return new CouncilPrivilege(this.getQuantity());
	}
}
