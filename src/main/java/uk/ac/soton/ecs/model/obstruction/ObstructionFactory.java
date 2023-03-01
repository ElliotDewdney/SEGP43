package uk.ac.soton.ecs.model.obstruction;

import java.util.function.Predicate;

public interface ObstructionFactory {
	
	public ObstructionType createObstruction();
	
	public Predicate<Object>[] getPrerequisites();
	
}
