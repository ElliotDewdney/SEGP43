package uk.ac.soton.ecs.model.obstruction;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * used to get the Factories by reflection
 * @author Elliot Dewdney
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ObstructionFactoryType {
	
	/**
	 * the Name of the Factory
	 * @return the Name
	 */
	String obstructionName();
	
}
