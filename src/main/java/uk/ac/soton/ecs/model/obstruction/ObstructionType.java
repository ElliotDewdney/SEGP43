package uk.ac.soton.ecs.model.obstruction;

import org.la4j.LinearAlgebra;
import org.la4j.LinearAlgebra.InverterFactory;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.la4j.vector.DenseVector;

public abstract class ObstructionType {

	public abstract String getName();
	public abstract String getDescription();
	public abstract String getColour();
	
	
	// the external corners of the object (not including any concave vertexes)
	private Vertex[] localVertices;
	
	// the origin of the OB, either in UnitSpace.GLOBAL or RUNWAYUNIT
	private Vertex obstructionOrigin;
	

	// the measure of either the global unit in runway units or the inverse
	private VertexMatrix runwayUnit;
	
	public enum UnitSpace{
		GLOBAL, LOCAL, RUNWAYUNIT
	}
	
	public class VertexMatrix{
		private Matrix conv;
		private UnitSpace in;
		private UnitSpace to;
		private VertexMatrix inverse;
		
		public UnitSpace getIn() {
			return in;
		}
		public void setIn(UnitSpace in) {
			this.in = in;
		}
		public UnitSpace getTo() {
			return to;
		}
		public void setTo(UnitSpace to) {
			this.to = to;
		}
		public VertexMatrix(Vertex x, Vertex y, Vertex z, UnitSpace in, UnitSpace to) {
			conv = new Basic2DMatrix(new double[][]{
				   { x.getX(), x.getY(), x.getZ() },
				   { y.getX(), y.getY(), y.getZ() },
				   { z.getX(), z.getY(), z.getZ() },
				});
			
			this.in = in;
			this.to = to;
		}
		private VertexMatrix(Matrix a, UnitSpace in, UnitSpace to){
			conv = a;
			this.in = in;
			this.to = to;
		}
		
		public Vertex mult(Vertex vertex) {
			org.la4j.Vector tv = DenseVector.fromArray(new double[] {vertex.getX(), vertex.getY(), vertex.getZ()}).apply(LinearAlgebra.OO_PLACE_VECTOR_BY_MATRIX_MULTIPLICATION, conv);
			return new Vertex(tv.get(0), tv.get(1), tv.get(2), to);
		}
		
		public VertexMatrix getInverse() {
			if (inverse == null) {
				inverse = new VertexMatrix(conv.withInverter(InverterFactory.SMART).inverse(), to, in);
			}
			return inverse;
		}
		
	}
	
	
	public static class Vertex{
		private double x;
		private double y;
		private double z;
		private UnitSpace unit;
		public Vertex(double x, double y, double z, UnitSpace unit) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.unit = unit;
		}
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public double getZ() {
			return z;
		}
		public void setZ(double z) {
			this.z = z;
		}
		public UnitSpace getUnit() {
			return unit;
		}
		public void setUnit(UnitSpace unit) {
			this.unit = unit;
		}
		
		public Vertex add(Vertex vertex) {
			return new Vertex(this.x + vertex.x, this.y + vertex.y, this.z + vertex.z, this.unit);
		}
		
		public Vertex subtract(Vertex vertex) {
			return new Vertex(this.x - vertex.x, this.y - vertex.y, this.z - vertex.z, this.unit);
		}
		
		public Vertex convertUsing(VertexMatrix matrix, UnitSpace newUnit) {
			VertexMatrix tmatrix;
			if(matrix.getTo() == newUnit && matrix.getIn() == getUnit()) {
				tmatrix = matrix;
			} else if(matrix.getIn() == newUnit && matrix.getTo() == getUnit()) {
				tmatrix = matrix.inverse;
			} else return null;
			return tmatrix.mult(this);
		}
		
	}
	
	public ObstructionType(Vertex[] localVertices) {
		this.localVertices = localVertices;
	}
	
	public Vertex convert(Vertex vertex, UnitSpace unit) {
		
		switch(unit) {
		case GLOBAL :
			
			switch(vertex.getUnit()) {
			case GLOBAL :
				return vertex;
			case LOCAL :
				if(obstructionOrigin.getUnit() == UnitSpace.GLOBAL) {
					return obstructionOrigin.add(vertex);
				}else {
					return obstructionOrigin.convertUsing(runwayUnit, UnitSpace.GLOBAL).add(vertex);
				}
			case RUNWAYUNIT :
				return vertex.convertUsing(runwayUnit, UnitSpace.GLOBAL);
			}
	
		case LOCAL :
			
			switch(vertex.unit) {
			case GLOBAL :
				if(obstructionOrigin.getUnit() == UnitSpace.GLOBAL) {
					return obstructionOrigin.subtract(vertex);
				}else {
					return obstructionOrigin.convertUsing(runwayUnit, UnitSpace.GLOBAL).subtract(vertex);
				}
			case LOCAL :
				return vertex;
			case RUNWAYUNIT :
				if(obstructionOrigin.getUnit() == UnitSpace.RUNWAYUNIT) {
					return obstructionOrigin.subtract(vertex).convertUsing(runwayUnit, UnitSpace.GLOBAL);
				}else {
					return obstructionOrigin.subtract(vertex.convertUsing(runwayUnit, UnitSpace.GLOBAL));
				}
			}
			
		case RUNWAYUNIT :
			
			switch(vertex.unit) {
			case GLOBAL :
				return vertex.convertUsing(runwayUnit, UnitSpace.RUNWAYUNIT);
			case LOCAL :
				if(obstructionOrigin.getUnit() == UnitSpace.RUNWAYUNIT) {
					return obstructionOrigin.add(vertex.convertUsing(runwayUnit,UnitSpace.RUNWAYUNIT));
				}else {
					return obstructionOrigin.add(vertex).convertUsing(runwayUnit, UnitSpace.RUNWAYUNIT);
				}
			case RUNWAYUNIT :
				return vertex;
			}
			
		}
		return null;
	}
	
	public Vertex[] getVertices(UnitSpace unit) {
		switch (unit) {
		case GLOBAL:
			Vertex[] tempGlobal = new Vertex[localVertices.length];
			for(int i = 0; i < localVertices.length; i++) {
				tempGlobal[i] = convert(localVertices[i], UnitSpace.GLOBAL);
			}
			return localVertices;
		case LOCAL:
			return localVertices;
		case RUNWAYUNIT:
			Vertex[] tempRunway = new Vertex[localVertices.length];
			for(int i = 0; i < localVertices.length; i++) {
				tempRunway[i] = convert(localVertices[i], UnitSpace.GLOBAL);
			}
			return localVertices;
		}
		return null;
	}
}
