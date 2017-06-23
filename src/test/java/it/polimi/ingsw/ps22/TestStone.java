package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Stone;

public class TestStone {

	private Stone s = new Stone(3);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "Stone" && s.clone().getQuantity() == 3);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "Stone");
	}

}
