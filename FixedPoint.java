public class FixedPoint {
	
	private ThreeVector position;
	private double mass;
	
	// Constructors ---------------------------------------------------------------------------------------------------------------------------------------------
	
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
	public FixedPoint(double mass, ThreeVector position) {
		this.position = position;
		this.mass = mass;
	}
	
	// Getters and setters --------------------------------------------------------------------------------------------------------------------------------------
	
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
	
	/*
	 * Setter for the mass field.
	 * @param mass
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
}
