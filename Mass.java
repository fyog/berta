public class Mass {
	
	private double mass;
	private ThreeVector	position, velocity, acceleration;
	final private double R_VALUE = 1.;
	
	// Constructors ---------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor method.
	 * @param mass
	 * @param position
	 */
	public Mass(double mass, ThreeVector position) {
		this.mass = mass;
		this.position = position;
		this.velocity = new ThreeVector(0, 0, 0);
		this.acceleration = new ThreeVector(0, 0, 0);
	}

	// Getters and setters -------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Getter for mass field.
	 * @return double mass
	 */
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Getter for position field.
	 * @return ThreeVector position
	 */
	public ThreeVector getPosition() {
		return position;
	}
	
	/**
	 * Getter for velocity field.
	 * @return ThreeVector velocity
	 */
	public ThreeVector getVelocity() {
		return velocity;
	}
	
	/**
	 * Getter for acceleration field.
	 * @return ThreeVector acceleration
	 */
	public ThreeVector getAcceleration() {
		return acceleration;
	}
	
	/**
	 * Setter for mass field.
	 * @param mass
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	/**
	 * Setter for position field.
	 * @param position
	 */
	public void setPosition(ThreeVector position) {
		this.position = position;
	}
	
	/**
	 * Setter for velocity field.
	 * @param velocity
	 */
	public void setVelocity(ThreeVector velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Setter for acceleration field.
	 * @param acceleration
	 */
	public void setAcceleration(ThreeVector acceleration) {
		this.acceleration = acceleration;
	}
	
	// Overwritten methods -------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		String str = "X: " + this.getPosition().getX() + " Y: " + this.getPosition().getY() + " Z: " + this.getPosition().getZ();
		return str;
	}

	// Numerical Analysis --------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Using the passed acceleration, this method predicts and updates the position and velocity of the mass object it is called upon. 
	 * @param deltaTime
	 */
	public void predict(double deltaTime) {
		
		// Euler's method for velocity
		double x_vel = this.getVelocity().getX() + deltaTime * this.getAcceleration().getX();
		double y_vel = this.getVelocity().getY() + deltaTime * this.getAcceleration().getY();
		double z_vel = this.getVelocity().getZ() + deltaTime * this.getAcceleration().getZ();
		
		// Update velocity
		ThreeVector newVelocity = new ThreeVector(x_vel, y_vel, z_vel);
		this.setVelocity(newVelocity);
 
		// Euler's method for position
		double x_pos = this.getPosition().getX() + deltaTime * newVelocity.getX();
		double y_pos = this.getPosition().getY() + deltaTime * newVelocity.getY();
		double z_pos = this.getPosition().getZ() + deltaTime * newVelocity.getZ();

		// Update position
		ThreeVector newPosition = new ThreeVector(x_pos, y_pos, z_pos);

		this.setPosition(newPosition);
	}
	
	/**
	 * Calculates the acceleration due to the gravitational pull of the passed fixed point object. If the distance between the two objects is less than one 
	 * unit then the gravity calculation is calculated as though the objects are only one unit apart. This avoids singularity.
	 * @param fixPoint
	 * @return ThreeVector acceleration
	 */
	public ThreeVector gravity(FixedPoint fixPoint) {
		
		// Big G constant
		final double G = 6.67 * Math.pow(10, -3);
		
		double accelerationX, accelerationY, accelerationZ;
		
		// Currently, does not operate in the z-direction
		accelerationZ = 0;
		
		// Determine acceleration due to gravity using Newton's Law of Universal Gravitation
		
		// If x distance is between R_VALUE and -R_VALUE, then set the x component of acceleration to zero
		double distanceX = this.getPosition().getX() - fixPoint.getPosition().getX();
		if (distanceX < R_VALUE && distanceX > -R_VALUE) {
			accelerationX = 0;
		} else {
			accelerationX = fixPoint.getMass() * G / (distanceX * distanceX);
		}
		
		// If y distance is between R_VALUE and -R_VALUE, then set the y component of acceleration to zero
		double distanceY = this.getPosition().getY() - fixPoint.getPosition().getY();
		if (distanceY < R_VALUE && distanceY > -R_VALUE) {
			accelerationY = 0;
		} else {
			accelerationY = fixPoint.getMass() * G / (distanceY * distanceY);
		}
		
		// Determine the correct direction for each acceleration component
		if (distanceX > 0) {
			accelerationX *= -1;
		}
		if (distanceY > 0) {
			accelerationY *= -1;
		}
		
		// Create new vector
		ThreeVector acceleration = new ThreeVector(accelerationX, accelerationY, accelerationZ);
		
		// Return the acceleration
		return acceleration;	
	}
}
