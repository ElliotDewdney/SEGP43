package uk.ac.soton.ecs.model.obstruction.obstructions;

import uk.ac.soton.ecs.model.obstruction.ObstructionType;

public class Vehicle extends ObstructionType {
	
	
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
	
	@Override
	public String getName() {
		return "Vehicle";
	}

	@Override
	public String getDescription() {
		return "A broken down vehicle obstructiong the runway";
	}

	@Override
	public String getColour() {
		return "#000000";
	}

}
