package uk.ac.soton.ecs.model.obstruction;

import  uk.ac.soton.ecs.model.obstruction.obstructions.*;
import java.util.HashMap;
import java.util.Set;

import org.reflections.Reflections;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @author Elliot Dewdney
 *
 */
public class ObstructionSingleton {
	
	private static ObstructionSingleton instance = null;
	
	public static ObstructionSingleton getInstance() {
		if(instance == null) {
			try {
				instance = new ObstructionSingleton();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
	}
	
	private HashMap<String, ObstructionFactory> factories = new HashMap<>();
	
	private ObstructionSingleton() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Reflections reflections  = new Reflections(PackageInfo.class.getPackageName());
		Set<Class<?>> tempFactories = reflections.getTypesAnnotatedWith(ObstructionFactoryType.class);
		
		for(Class<?> tempFactory : tempFactories) {
			ObstructionFactory factory = (ObstructionFactory) tempFactory.getConstructor().newInstance();
			String name = tempFactory.getAnnotationsByType(ObstructionFactoryType.class)[0].obstructionName();
			factories.put(name, factory);
		}
		
	}
	
	public Set<String> getFactoryNames() {
		return factories.keySet();
	}
	
	public ObstructionFactory getFactoryByName(String name) {
		return factories.get(name);
	}
}
