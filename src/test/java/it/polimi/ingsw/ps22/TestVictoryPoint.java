package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.VictoryPoint;

public class TestVictoryPoint {

	private VictoryPoint s = new VictoryPoint(9);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "VictoryPoint" && s.clone().getQuantity() == 9);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "VictoryPoint");
	}
}
