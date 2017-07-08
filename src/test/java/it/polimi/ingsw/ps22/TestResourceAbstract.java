package it.polimi.ingsw.ps22;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.ps22.server.resource.ResourceAbstract;

/**
 * 
 * this class tests that the {@link ResourceAbstract} class works correctly
 *
 */
public class TestResourceAbstract {
	
	private ResourceAbstract res;
	
	/**
	 * it initializes a new resource abstract
	 */
	@Before
	public void init(){
		res = new ResourceAbstract(5);
	}

	/**
	 * this method checks whether the getName method works
	 * correctly
	 */
	@Test
	public void testGetName() {
		assert(res.getName() == null);
	}
	
	/**
	 * this method checks whether the getQuantity method works
	 * correctly
	 */
	@Test
	public void testGet() {
		assert(res.getQuantity() == 5);
	}
	
    /**
     * this method checks whether the clone method works
	 * correctly
     */
	@Test
	public void testClone() {
		assert(res.getQuantity() == res.clone().getQuantity() && res.getName() == res.clone().getName());
	}

}
