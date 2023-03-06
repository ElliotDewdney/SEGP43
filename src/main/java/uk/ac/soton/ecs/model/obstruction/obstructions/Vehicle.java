package uk.ac.soton.ecs.model.obstruction.obstructions;

import uk.ac.soton.ecs.model.obstruction.ObstructionType;

public class Vehicle extends ObstructionType {
	
	/**
	 * constructor for creating a Vehicle Obstruction
	 * @param length the length of the object
	 * @param width the width of the object
	 * @param height the height of the object
	 * @param bearing the bearing the object is facing
	 */
	public Vehicle(double length, double width, double height, double bearing) {
		super(new Vertex[8]);
		double[] ls = new double[] {-(length/2), (length/2)};
		double[] ws = new double[] {-(width/2), (width/2)};
		double[] hs = new double[] {0, height};
		int i = 0;
		for(double l : ls) for (double w : ws) for (double h : hs){
			super.getVertices(UnitSpace.LOCAL)[i++] = new Vertex(l,h,w, UnitSpace.LOCAL); 
		}
	}
	
	/**
	 * gets a human readable name for the ui
	 */
	@Override
	public String getName() {
		return "Vehicle";
	}
	
	/**
	 * get a human readable description for the ui 
	 */
	@Override
	public String getDescription() {
		return "A broken down vehicle obstructiong the runway";
	}
	
	/**
	 * gets a colour for the ui
	 */
	@Override
	public String getColour() {
		return "#000000";
	}

}
