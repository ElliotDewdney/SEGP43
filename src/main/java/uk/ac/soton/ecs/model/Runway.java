package uk.ac.soton.ecs.model;

import java.util.ArrayList;

import uk.ac.soton.ecs.model.obstruction.ObstructionType;

/**
 * The base class for the Project model used as a entry-point.
 * a Runway is defined by
 * 
 * 	- A list of current obstructions
 * 	- TORA (Take-off run available)
 * 	- TODA (Take-off distance available)
 * 	- ASDA (Accelerate-Stop	Distance available)
 * 	- LDA  (Landing	Distance Available)
 *  
 * @author Elliot Dewdney
 *
 */
public class Runway {
	
	private ArrayList<ObstructionType> obstructions = new ArrayList<>();
	private int rBearing;
	private int lBearing;
	
	
	public Runway(int rBearing, int lBearing, double rTORA, double lTORA, double rLDA, double lLDA, double rASDA, double lASDA, double rTODA, double lTODA) {
		
	}
	
	public Runway(int rBearing, int lBearing, double rTORA, double lTORA, double rStopway, double lStopway, double rClearway, double lClearway) {
		
	}
}
