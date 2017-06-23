package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Coin;

public class TestCoin {

	private Coin s = new Coin(22);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "Coin" && s.clone().getQuantity() == 22);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "Coin");
	}
}
