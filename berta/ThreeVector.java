package berta;
public class ThreeVector {
	
	// Fields
	private double x, y, z;
	
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
	
	/**
	 * Empty constructor.
	 */
	public ThreeVector() {
		this.x = -5.0;
		this.y = 0.0;
		this.z = 0.0;
	}
	
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
	
	/**
	 * To string method.
	 * 
	 * @return string representation of the ThreeVector object
	 */
	@Override
	public String toString() {
		String str = "X: " + this.x + " Y: " + this.y + " Z: " + this.z;
		return str;
	}
	
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
	 * Scales the three vector by the given amount.
	 * 
	 * @param scale_factor
	 * @return ThreeVector scaled vectors
	 */
	public ThreeVector scale(double scale_factor) {
		double x = this.getX() * scale_factor;
		double y = this.getY() * scale_factor;
		double z = this.getZ() * scale_factor;
		return new ThreeVector(x, y, z);
	}
	
	/**
	 * Adds the passed ThreeVector object to the current ThreeVector object.
	 * @param other
	 * 
	 * @return ThreeVector sum vector
	 */
	public ThreeVector add(ThreeVector other) {
		double x = this.getX() + other.getX();
		double y = this.getY() + other.getY();
		double z = this.getZ() + other.getZ();
		return new ThreeVector(x, y, z);

	}
	
	/**
	 * Subtracts the current ThreeVector from the passed ThreeVector object.
	 * 
	 * @param other
	 * @return ThreeVector difference vector
	 */
	public ThreeVector minus(ThreeVector other) {
		double x = other.getX() - this.getX();
		double y = other.getY() - this.getY();
		double z = other.getZ() - this.getZ();
		return new ThreeVector(x, y, z);
	}
	
	/**
	 * Returns a unit vector pointing in the same direction as the current
	 * vector.
	 * 
	 * @return ThreeVector unit vector
	 */
	public ThreeVector direction() {
        if (this.getMagnitude() == 0.0) {
        	System.out.println("Zero length vector!");
        }
        return this.scale(1.0 / this.getMagnitude());
    }
	
	/**
	 * Returns the distance to the passed Mass object from this one.
	 * 
	 * @param mass
	 * @return distance
	 */
	public double getDistance(Mass mass) {
		double distance = Math.sqrt((x - mass.getPosition().getX()) * (x 
				- mass.getPosition().getX()) + (y - mass.getPosition().getY())
				* (y - mass.getPosition().getY()) + (z - 
				mass.getPosition().getZ() * (z - mass.getPosition().getZ())));
		return distance;
	}
}
