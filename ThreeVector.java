
public class ThreeVector {
	
	double x, y, z;
	
	// Constructor
	public ThreeVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// Getters and setters
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public String toString() {
		String str = "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
		return str;
	}
	// Returns the vector's magnitude
	public double getMagnitude() {
		double magnitude = Math.sqrt(x*x+y*y+z*z);
		return magnitude;
	}
	
	// Returns the distance between two vectors
	public double getDistance(ThreeVector otherEnd) {
		double distance = Math.sqrt((x-otherEnd.x)*(x-otherEnd.x)+(y-otherEnd.y)*(y-otherEnd.y)+(z-otherEnd.z)*(z-otherEnd.z));
		return distance;
	}
}
