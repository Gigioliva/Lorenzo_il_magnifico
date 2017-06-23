package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Servant;

public class TestServant {
	
	private Servant s = new Servant(2);

	@Test
	public void testClone() {
		assert(s.clone().getName() == "Servant" && s.clone().getQuantity() == 2);
	}
	
	@Test
	public void testGetName() {
		assert(s.getName() == "Servant");
	}

}
