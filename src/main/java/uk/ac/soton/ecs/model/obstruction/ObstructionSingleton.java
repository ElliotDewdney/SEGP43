package uk.ac.soton.ecs.model.obstruction;

public class ObstructionSingleton {
	
	private static ObstructionSingleton instance = null;
	
	public static ObstructionSingleton getInstance() {
		if(instance == null) {
			instance = new ObstructionSingleton();
		}
		
		return instance;
	}
	
	public static class FactoryRecord{
		
		
		
	}
}
