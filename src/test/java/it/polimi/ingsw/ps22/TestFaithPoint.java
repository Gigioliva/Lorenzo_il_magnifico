package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.FaithPoint;

public class TestFaithPoint {

	private FaithPoint s = new FaithPoint(9);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "FaithPoint" && s.clone().getQuantity() == 9);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "FaithPoint");
	}

}
