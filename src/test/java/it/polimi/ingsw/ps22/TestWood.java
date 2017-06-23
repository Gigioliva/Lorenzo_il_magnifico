package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Wood;

public class TestWood {

	private Wood s = new Wood(9);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "Wood" && s.clone().getQuantity() == 9);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "Wood");
	}
}
