package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.MilitaryPoint;

public class TestMilitaryPoint {
	
	private MilitaryPoint s = new MilitaryPoint(9);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "MilitaryPoint" && s.clone().getQuantity() == 9);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "MilitaryPoint");
	}

}
