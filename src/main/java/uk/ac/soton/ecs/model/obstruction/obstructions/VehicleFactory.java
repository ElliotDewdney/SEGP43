package uk.ac.soton.ecs.model.obstruction.obstructions;

import java.util.LinkedList;
import java.util.List;

import uk.ac.soton.ecs.model.obstruction.ObstructionFactory;
import uk.ac.soton.ecs.model.obstruction.ObstructionFactoryType;
import uk.ac.soton.ecs.model.obstruction.ObstructionType;
import uk.ac.soton.ecs.model.obstruction.Prerequisite;

@ObstructionFactoryType(obstructionName = "Vehicle")
public class VehicleFactory implements ObstructionFactory{
	
	private double height;
	private double length;
	private double width;
	private double bearing;

	@Override
	public ObstructionType createObstruction() {
		return new Vehicle(length, width, height, bearing);
	}

	@Override
	public List<Prerequisite<? extends Object>> getPrerequisites() {
		LinkedList<Prerequisite<? extends Object>> prerequisites = new LinkedList<>();
		
		prerequisites.add(new Prerequisite<Float>("set Height : ", (f -> {
			if(f < 0 || f > 1000) return false;
			this.height = f;
			return true;
		})));
		
		prerequisites.add(new Prerequisite<Float>("set Length : ", (f -> {
			if(f < 0 || f > 1000) return false;
			this.length = f;
			return true;
		})));
		
		prerequisites.add(new Prerequisite<Float>("set Width : ", (f -> {
			if(f < 0 || f > 1000) return false;
			this.width = f;
			return true;
		})));
		
		prerequisites.add(new Prerequisite<Float>("set Bearing : ", (f -> {
			if(f < 0 || f > 360) return false;
			this.bearing = f;
			return true;
		})));
		
		return prerequisites;
	}
}
