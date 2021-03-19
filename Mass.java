public class Mass {
	
	double mass;
	ThreeVector	position, velocity, acceleration;

	/**
	 * Constructor method.
	 * @param mass
	 * @param position
	 */
	public Mass(float mass, ThreeVector position) {
		this.mass = mass;
		this.position = position;
		this.velocity = new ThreeVector(0, 0, 0);
		this.acceleration = new ThreeVector(0, 0, 0);
	}

	// Getters and setters
	
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
	public ThreeVector getAccel() {
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
	 * @param accel
	 */
	public void setAccel(ThreeVector accel) {
		this.acceleration = accel;
	}
	
	@Override
	public String toString() {
		String str = "X: " + this.getPosition().getX() + " Y: " + this.getPosition().getY() + " Z: " + this.getPosition().getZ();
		return str;
	}

	// Numerical Analysis
	
	/**
	 * Using the passed acceleration, this method predicts and updates the position and velocity of the mass object it is called upon. 
	 * @param deltaTime
	 */
	public void predict(double deltaTime) {
		
		// Leap-frog method
		double x_vel = this.getVelocity().getX() + deltaTime * this.getAccel().getX();
		double y_vel = this.getVelocity().getY() + deltaTime * this.getAccel().getY();
		double z_vel = this.getVelocity().getZ() + deltaTime * this.getAccel().getZ();
		
		// Update velocity
		ThreeVector newVelocity = new ThreeVector(x_vel, y_vel, z_vel);
		this.setVelocity(newVelocity);
		
		// Leap-frog method
		double x_pos = this.getPosition().getX() + deltaTime * this.getVelocity().getX();
		double y_pos = this.getPosition().getY() + deltaTime * this.getVelocity().getY();
		double z_pos = this.getPosition().getZ() + deltaTime * this.getVelocity().getZ();

		// Update position
		ThreeVector newPosition = new ThreeVector(x_pos, y_pos, z_pos);
		this.setPosition(newPosition);
	}
	
	/**
	 * Calculates the acceleration due to the gravitational pull of the passed fixed point object. If the distance between the two objects
	 * is less than one unit then the gravity calculation is calculated as though the objects are only one unit apart. This avoids singularity.
	 * @param fixPoint
	 * @return ThreeVector accel
	 */
	public ThreeVector gravity(FixedPoint fixPoint) {
		
		// Big G constant
		final double G = 6.67 * Math.pow(10, -7);
		
		double accelX, accelY, accelZ;
		
		// Currently, does not operate in the z-direction
		accelZ = 0;
		
		// Determine acceleration due to gravity using Newton's Law of Universal Gravitation
		double distanceX = this.getPosition().getX() - fixPoint.getPosition().getX();
		
		// If distance is between -1 and 1, then calculate the accleration as though the distance is one unit
		if (distanceX > 1 || distanceX < -1) {
			accelX = fixPoint.getMass() * G / (distanceX * distanceX);
		} else {
			accelX = fixPoint.getMass() * G / (1);
		}

		double distanceY = this.getPosition().getY() - fixPoint.getPosition().getY();
		// If distance is between -1 and 1, then calculate the accleration as though the distance is one unit
		if (distanceY > 1 || distanceY < -1 ) {
			accelY = fixPoint.getMass() * G / (distanceY * distanceY);
		} else {
			accelY = fixPoint.getMass() * G / (1);
		}
		
		// Determine the correct direction for the acceleration vector
		if (distanceX > 0) {
			accelX *= -1;
		}
		if (distanceY > 0) {
			accelY *= -1;
		}
		
		// Create new vector
		ThreeVector accel = new ThreeVector(accelX, accelY, accelZ);
		
		// Return the acceleration
		return accel;	
	}
}
