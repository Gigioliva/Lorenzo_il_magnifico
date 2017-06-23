package it.polimi.ingsw.ps22;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;

public class TestCouncilPrivilege {

	private CouncilPrivilege s = new CouncilPrivilege(22);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "CouncilPrivilege" && s.clone().getQuantity() == 22);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "CouncilPrivilege");
	}
	
	@Test
	public void testExchangeWithResources() {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(3);
		assert(CouncilPrivilege.exchangeWithResources(arr).get("Wood").getQuantity() == 1 &&
				CouncilPrivilege.exchangeWithResources(arr).get("Stone").getQuantity() == 1 &&
				CouncilPrivilege.exchangeWithResources(arr).get("Coin").getQuantity() == 2);
	}

}
