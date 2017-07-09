package it.polimi.ingsw.ps22;

import java.util.ArrayList;
import org.junit.Test;
import it.polimi.ingsw.ps22.server.resource.CouncilPrivilege;

/**
 * 
 * This class checks whether the {@link CouncilPrivilege} class works properly or not
 *
 */

public class TestCouncilPrivilege {

	private CouncilPrivilege s = new CouncilPrivilege(22);

	/**
	 * this method tests the Clone method in {@link CouncilPrivilege}
	 */
	
	@Test
	public void testClone() {
		assert(s.clone().getName() == "CouncilPrivilege" && s.clone().getQuantity() == 22);
	}
	
	/**
	 * this method tests the getName method in {@link CouncilPrivilege}
	 */
	
	@Test
	public void testGetName() {
		assert(s.getName() == "CouncilPrivilege");
	}
	
	/**
	 * this method tests the ExchangeWithResources method in {@link CouncilPrivilege}
	 */
	
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
