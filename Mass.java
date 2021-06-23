public class Mass {
		
	// Fields
	private double mass;
	private double G;
	private ThreeVector	position, velocity, acceleration;
	
	// Constructors ---------------------------------------------------------------------------------------------------
	
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

	// Getters and setters --------------------------------------------------------------------------------------------
	
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
	
	// Overwritten methods --------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		String str = "X: " + this.getPosition().getX() + " Y: " + this.getPosition().getY() + " Z: " + 
	this.getPosition().getZ();
		return str;
	}

	// Numerical Analysis ---------------------------------------------------------------------------------------------
	
	/**
	 * Using the passed acceleration, this method predicts and updates the position and velocity of the mass object it 
	 * is called upon.
	 * 
	 * @param deltaTime
	 */
	public void predict(double deltaTime) {
		
		// Newton's method for velocity
		double x_vel = this.getVelocity().getX() + deltaTime * this.getAcceleration().getX();
		double y_vel = this.getVelocity().getY() + deltaTime * this.getAcceleration().getY();
		double z_vel = this.getVelocity().getZ() + deltaTime * this.getAcceleration().getZ();
	
		//  Updated velocity
		ThreeVector newVelocity = new ThreeVector(x_vel, y_vel, z_vel);
		this.setVelocity(newVelocity); 
		
		// Newton's method for position using new velocity
		double x_pos = this.getPosition().getX() + deltaTime * newVelocity.getX(); 
		double y_pos = this.getPosition().getY() + deltaTime * newVelocity.getY();
		double z_pos = this.getPosition().getZ() + deltaTime * newVelocity.getZ();

		// Updated position
		ThreeVector newPosition = new ThreeVector(x_pos, y_pos, z_pos);

		this.setPosition(newPosition);
	}
	
	/**
	 * Calculates the acceleration due to the gravitational pull of the passed fixed point object.
	 * 
	 * @param fixPoint
	 * @return ThreeVector acceleration
	 */
	public ThreeVector gravity(Mass mass) {
		
		double accelerationX, accelerationY;
		
		// Determine acceleration due to gravity using Newton's Law of Universal Gravitation
	
		double distanceX = this.getPosition().getX() - mass.getPosition().getX();
		accelerationX = mass.getMass() * G / (distanceX * distanceX);
		double distanceY = this.getPosition().getY() - mass.getPosition().getY();
		accelerationY = mass.getMass() * G / (distanceY * distanceY);
		
		
		// Determine the correct direction for each acceleration component
		if (distanceX > 0) accelerationX *= -1;
		if (distanceY > 0) accelerationY *= -1;
		
		
		// Create new vector
		ThreeVector acceleration = new ThreeVector(accelerationX, accelerationY, 0);
		
		// Return the acceleration
		if ((distanceX < 1 && distanceX > -1) || (distanceY < 1 && distanceY > -1)) return new ThreeVector();
		else return acceleration;	
	}
	/**
	 * Calculates the acceleration due to the spring force on the passed mass.
	 */
}
