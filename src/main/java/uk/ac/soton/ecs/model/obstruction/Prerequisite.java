package uk.ac.soton.ecs.model.obstruction;

import java.util.function.Predicate;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface Prerequisite {
	
	String prerequisiteName();
	int defalt();
}
