package it.polimi.ingsw.ps22;

import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.Resource;
import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 * This class tests the class {@link Resource}
 *
 */
public class TestResource {
	
	ResourceAbstract r = new Resource(5);
	ResourceAbstract other = new Resource(4);

	/**
	 * this method checks whether the clone method works
	 * correctly
	 */
	@Test
	public void testClone() {
		assert(r.clone().getQuantity() == r.getQuantity());
	}
	
	/**
	 * this method checks whether the getQuantity method works
	 * correctly
	 */
	@Test
	public void testGetQuantity(){
		assert(r.getQuantity() == 5);
	}
	
	/**
	 * this method tests the addResource method
	 */
	@Test
	public void testAddResource(){
		r.addResource(other);
		assert(r.getQuantity() == 9);
	}
	
	/**
	 * this method checks whether the subResource method works
	 * correctly
	 */
	@Test
	public void testSubResurce(){
		r.subResource(other);
		assert(r.getQuantity() == 1);
	}

}
