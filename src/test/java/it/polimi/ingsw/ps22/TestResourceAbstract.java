package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TestResourceAbstract {
	
	private ResourceAbstract res;
	
	@Before
	public void init(){
		res = new ResourceAbstract(5);
	}

	@Test
	public void testGetName() {
		assert(res.getName() == null);
	}
	
	@Test
	public void testGet() {
		assert(res.getQuantity() == 5);
	}
	
	@Test
	public void testClone() {
		assert(res.getQuantity() == res.clone().getQuantity() && res.getName() == res.clone().getName());
	}

}
