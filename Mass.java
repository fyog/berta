public class Mass {
		
	// Fields
	private double mass;
	private double G;
	private ThreeVector	position, velocity, acceleration;
	
	// Constructors -----------------------------------------------------------
	
	/**
	 * Constructor method.
	 * 
	 * @param mass
	 * @param position
	 */
	public Mass(double mass, ThreeVector position, double G) {
		this.mass = mass;
		this.position = position;
		this.velocity = new ThreeVector();
		this.acceleration = new ThreeVector();
		this.G = G;
	}

	// Getters and setters ----------------------------------------------------
	
	/**
	 * Getter for mass field.
	 * 
	 * @return double mass
	 */
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Getter for position field.
	 * 
	 * @return ThreeVector position
	 */
	public ThreeVector getPosition() {
		return position;
	}
	
	/**
	 * Getter for velocity field.
	 * 
	 * @return ThreeVector velocity
	 */
	public ThreeVector getVelocity() {
		return velocity;
	}
	
	/**
	 * Getter for acceleration field.
	 * 
	 * @return ThreeVector acceleration
	 */
	public ThreeVector getAcceleration() {
		return acceleration;
	}
	
	/**
	 * Setter for mass field.
	 * 
	 * @param mass
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	/**
	 * Setter for position field
	 * 
	 * @param position
	 */
	public void setPosition(ThreeVector position) {
		this.position = position;
	}
	
	/**
	 * Setter for velocity field.
	 * 
	 * @param velocity
	 */
	public void setVelocity(ThreeVector velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Setter for acceleration field.
	 * 
	 * @param acceleration
	 */
	public void setAcceleration(ThreeVector acceleration) {
		this.acceleration = acceleration;
	}
	
	@Override
	public String toString() {
		String str = "X: " + this.getPosition().getX() + " Y: " + 
				this.getPosition().getY() + " Z: " + this.getPosition().getZ();
		return str;
	}
	
	/**
	 * Using the passed acceleration, this method predicts and updates the 
	 * position and velocity of the mass object it is called upon.
	 * 
	 * @param deltaTime
	 */
	public void predict(double deltaTime) {
		
		// Newton's method for velocity
		ThreeVector velocity = this.getVelocity().add(this.getAcceleration()
				.scale(deltaTime));
		
		// Update velocity
		this.setVelocity(velocity);
		
		ThreeVector position = this.getPosition().add(this.getVelocity()
				.scale(deltaTime));
		
		// Update position
		this.setPosition(position);
	}
	
	/**
	 * Calculates the acceleration due to the gravitational pull of the passed
	 * mass object.
	 * 
	 * @param fixPoint
	 * @return ThreeVector acceleration
	 */
	public ThreeVector gravity(Mass other) {
		
		// Determine acceleration due to gravity using Newton's Law of 
		// Universal Gravitation
		ThreeVector delta = this.getPosition().minus(other.getPosition());
		double dist = delta.getMagnitude();
		double F = (G * this.getMass() * other.getMass()) / (dist * dist);
		if (dist < 1) return new ThreeVector();
		else return delta.direction().scale(F);
	}
}
