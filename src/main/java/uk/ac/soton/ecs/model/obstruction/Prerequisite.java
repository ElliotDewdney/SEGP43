package uk.ac.soton.ecs.model.obstruction;

import java.util.function.Predicate;

public class Prerequisite<T> {
	
	private String name;
	private Predicate<T> predicate;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Predicate<T> getPredicate() {
		return predicate;
	}
	public void setPredicate(Predicate<T> predicate) {
		this.predicate = predicate;
	}
	
	public Prerequisite(String name, Predicate<T> predicate) {
		super();
		this.name = name;
		this.predicate = predicate;
	}
	
}
