package uk.ac.soton.ecs.model.obstruction;

import static org.junit.Assert.*;
import java.util.Set;

import org.junit.Test;

public class ObstructionSingletonTest {

	@Test
	public void testGetInstance() {
		assertEquals("getInstance should return a instance of the class", ObstructionSingleton.getInstance().getClass(), ObstructionSingleton.class);
	}

	@Test
	public void testGetNames() {
		assertFalse("Factories should not be Empty", ObstructionSingleton.getInstance().getFactoryNames().isEmpty());
	}
	
	@Test
	public void testGetNamesVehicle() {
		assertTrue("Factories should contain vehicle", ObstructionSingleton.getInstance().getFactoryNames().contains("Vehicle"));
	}
	
	@Test
	public void testGetFactoriesVehicle() {
		try {
			ObstructionFactory.class.cast( ObstructionSingleton.getInstance().getFactoryByName("Vehicle"));
		}catch(Exception  e) {
			fail("should be type ObstructionFactory");
		}
	}
}
