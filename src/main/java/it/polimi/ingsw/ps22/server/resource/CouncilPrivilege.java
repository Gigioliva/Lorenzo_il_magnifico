package it.polimi.ingsw.ps22.server.resource;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Extension of the class {@link ResourceAbstract}, it implements a
 * representation of the Council Privilege.
 */
public class CouncilPrivilege extends ResourceAbstract {
	
	private static final long serialVersionUID = 1L;
	
	private transient static HashMap<Integer, HashMap<String,ResourceAbstract>> possibleExchanges;
	
	/**
	 * Create a new {@link CouncilPrivilege}
	 * @param councilprivilege is the quantity to which the resource is set
	 */
	public CouncilPrivilege(int councilprivilege){
		super(councilprivilege);
		
		possibleExchanges = new HashMap<>();
		
		HashMap<String, ResourceAbstract> scelta1 = new HashMap<>();
		scelta1.put("Stone", new Stone(1));
		scelta1.put("Wood", new Wood(1));
		possibleExchanges.put(new Integer(1), scelta1);
		
		HashMap<String, ResourceAbstract> scelta2 = new HashMap<>();
		scelta2.put("Servant", new Servant(2));
		possibleExchanges.put(new Integer(2), scelta2);
		
		HashMap<String, ResourceAbstract> scelta3 = new HashMap<>();
		scelta3.put("Coin", new Coin(2));
		possibleExchanges.put(new Integer(3), scelta3);
		
		HashMap<String, ResourceAbstract> scelta4 = new HashMap<>();
		scelta4.put("MilitaryPoint", new MilitaryPoint(2));
		possibleExchanges.put(new Integer(4), scelta4);
		
		HashMap<String, ResourceAbstract> scelta5 = new HashMap<>();
		scelta5.put("FaithPoint", new FaithPoint(1));
		possibleExchanges.put(new Integer(5), scelta5);
		
		
	}
	
	/**
	 * The method exchange the council privileges with the corresponding resources. 
	 * @param chosenPossibilities is an ArrayList containing the Integer representing the chosen resources. 
	 * It requires the Integer to be in the range [1,5].
	 * @return an {@link HashMap} with a String as key representing the Resource, and the chosen {@link ResourceAbstract} as value.
	 */
	public static HashMap<String, ResourceAbstract> exchangeWithResources(ArrayList<Integer> chosenPossibilities){
		HashMap<String, ResourceAbstract> resourcesForPlayer = new HashMap<>();
		for(Integer index: chosenPossibilities){
			for(String resource: possibleExchanges.get(index).keySet())
				resourcesForPlayer.put(resource, possibleExchanges.get(index).get(resource));
		}
		return resourcesForPlayer;
	}
		
	@Override
	public String getName() {
		return "CouncilPrivilege";
	}
	
	@Override
	public CouncilPrivilege clone() {
		return new CouncilPrivilege(this.getQuantity());
	}
}
