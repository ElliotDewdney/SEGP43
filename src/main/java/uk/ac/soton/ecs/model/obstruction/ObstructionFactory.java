package uk.ac.soton.ecs.model.obstruction;

import java.util.List;
import java.util.function.Predicate;

public interface ObstructionFactory {
	
	public ObstructionType createObstruction();
	
	public List<Prerequisite<? extends Object>> getPrerequisites();
	
}
