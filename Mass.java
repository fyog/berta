public class Mass {
	
	double mass;
	ThreeVector	position, velocity, acceleration;

	// Constructor
	public Mass(float mass, ThreeVector position) {
		this.mass = mass;
		this.position = position;
		this.velocity = new ThreeVector(0, 0, 0);
		this.acceleration = new ThreeVector(0, 0, 0);
	}

	// Getters and setters
	public double getMass() {
		return this.mass;
	}
	
	public ThreeVector getPosition() {
		return position;
	}
	
	public ThreeVector getVelocity() {
		return velocity;
	}
	
	public ThreeVector getAccel() {
		return acceleration;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public void setPosition(ThreeVector position) {
		this.position = position;
	}
	
	public void setVelocity(ThreeVector velocity) {
		this.velocity = velocity;
	}
	
	public void setAccel(ThreeVector accel) {
		this.acceleration = accel;
	}

	// Predictor-corrector methods
	public void predict(double deltaTime) {
		
		// Leapfrog method
		double x_vel = this.getVelocity().getX() + deltaTime * this.getAccel().getX();
		double y_vel = this.getVelocity().getY() + deltaTime * this.getAccel().getY();
		double z_vel = this.getVelocity().getZ() + deltaTime * this.getAccel().getZ();
		
		// Update velocity
		ThreeVector newVelocity = new ThreeVector(x_vel, y_vel, z_vel);
		this.setVelocity(newVelocity);
		
		// Leapfrog method
		double x_pos = this.getPosition().getX() + deltaTime * this.getVelocity().getX();
		double y_pos = this.getPosition().getY() + deltaTime * this.getVelocity().getY();
		double z_pos = this.getPosition().getZ() + deltaTime * this.getVelocity().getZ();

		// Update position
		ThreeVector newPosition = new ThreeVector(x_pos, y_pos, z_pos);
		this.setPosition(newPosition);
	}
	
	// toString method
	public String toString() {
		String str = "X: " + this.getPosition().getX() + " Y: " + this.getPosition().getY() + " Z: " + this.getPosition().getZ();
		return str;
	}
	
	// Gravity method
	public ThreeVector gravity(FixedPoint fixPoint) {
		
		// Big G constant
		final double G = 6.67 * Math.pow(10, -11);
		
		// Currently, does not operate in the z-direction
		double accelZ = 0;
		
		// Determine acceleration due to gravity using Newton's Law of Universal Gravitation
		double distanceX = (this.getPosition().getX() - fixPoint.getPosition().getX());
		double accelX = fixPoint.getMass() * G / (distanceX * distanceX);
		double distanceY = (this.getPosition().getY() - fixPoint.getPosition().getY());
		double accelY = fixPoint.getMass() * G / (distanceY * distanceY);
		
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
