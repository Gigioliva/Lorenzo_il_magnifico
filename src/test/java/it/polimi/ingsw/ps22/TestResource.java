package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Resource;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

public class TestResource {
	
	ResourceAbstract r = new Resource(5);
	ResourceAbstract other = new Resource(4);

	@Test
	public void testClone() {
		assert(r.clone().getQuantity() == r.getQuantity());
	}
	
	@Test
	public void testGetQuantity(){
		assert(r.getQuantity() == 5);
	}
	
	@Test
	public void testAddResource(){
		r.addResource(other);
		assert(r.getQuantity() == 9);
	}
	
	@Test
	public void testAddResurce(){
		r.subResource(other);
		assert(r.getQuantity() == 1);
	}

}
