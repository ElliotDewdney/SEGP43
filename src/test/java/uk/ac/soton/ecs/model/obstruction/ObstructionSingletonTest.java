package uk.ac.soton.ecs.model.obstruction;

import static org.junit.Assert.*;

import org.junit.Test;

public class ObstructionSingletonTest {

	@Test
	public void testGetInstance() {
		assertEquals("getInstance should return a instance of the class", ObstructionSingleton.getInstance(), ObstructionSingleton.class);
	}

}
