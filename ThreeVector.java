
public class ThreeVector {
	
	private double x, y, z;
	
	// Constructors ---------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Constructor method.
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public ThreeVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public ThreeVector() {
		this.x = 0.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
	// Getters and setters --------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Getter for x field.
	 * 
	 * @return double x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * Getter for y field.
	 * 
	 * @return double y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Getter for z field.
	 * 
	 * @return double z
	 */
	public double getZ() {
		return z;
	}
	
	/**
	 * Setter for x field.
	 * 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Setter for y field.
	 * 
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Setter for z field.
	 * 
	 * @param z
	 */
	public void setZ(double z) {
		this.z = z;
	}
	
	// Overwritten methods --------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String str = "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
		return str;
	}
	
	// Numerical Analysis ---------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Returns the ThreeVector object's magnitude.
	 * 
	 * @return double magnitude
	 */
	public double getMagnitude() {
		double magnitude = Math.sqrt(x * x + y * y + z * z);
		return magnitude;
	}
	
	/**
	 * Returns the distance to the passed ThreeVector object from this one.
	 * 
	 * @param otherEnd
	 * @return
	 */
	public double getDistance(ThreeVector otherEnd) {
		double distance = Math.sqrt((x - otherEnd.x) * (x - otherEnd.x) + (y - otherEnd.y) * (y - otherEnd.y) + (z - otherEnd.z) * (z - otherEnd.z));
		return distance;
	}
}
