public class FixedPoint {
	
	ThreeVector position;
	double mass;
	
	/**
	 * Constructor method.
	 * @param position
	 */
	public FixedPoint(ThreeVector position) {
		this.position = position;
	}
	
	/**
	 * Constructor method.
	 * @param position
	 * @param mass
	 */
	public FixedPoint(ThreeVector position, double mass) {
		this.position = position;
		this.mass = mass;
	}
	
	// Getters and setters
	
	/**
	 * Getter for the position field.
	 * @return ThreeVector position
	 */
	public ThreeVector getPosition() {
		return position;
	}
	
	/**
	 * Setter for the position field.
	 * @param position
	 */
	public void setPosition(ThreeVector position) {
		this.position = position;
	}
	
	/**
	 * Getter for the mass field.
	 * @return double mass
	 */
	public double getMass() {
		return this.mass;
	}
}
