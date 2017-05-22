package it.polimi.ingsw.ps22.board;

import java.util.HashMap;

import it.polimi.ingsw.ps22.resource.Coin;
import it.polimi.ingsw.ps22.resource.CouncilPrivilege;
import it.polimi.ingsw.ps22.resource.ResourceAbstract;

public class CouncilPalaceSpace extends ActionSpace {
	private final static int ACTIONCOST=1;
	
	public CouncilPalaceSpace(){
		super(ACTIONCOST, true);
		HashMap<String,ResourceAbstract> bonus=new HashMap<String,ResourceAbstract>();
		bonus.put("Coin", new Coin(1));
		bonus.put("CouncilPrivilege", new CouncilPrivilege(1));
		super.addBonus(bonus);
	}
}
