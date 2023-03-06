package uk.ac.soton.ecs.model.obstruction;

import java.util.List;
import java.util.function.Predicate;

/**
 * class for the factory programming parigym 
 * @author Elliot Dewdney
 *
 */
public interface ObstructionFactory {
	/**
	 * Factory method
	 * @return a new instance of the linked class
	 */
	public ObstructionType createObstruction();
	
	
	/**
	 * use before creating a new Obstruction
	 * @return gives a list of options before construction
	 */
	public List<Prerequisite<? extends Object>> getPrerequisites();
	
}
