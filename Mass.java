public class Mass {
	
	double mass;
	ThreeVector	acceleration;
	ThreeVector velocity;
	ThreeVector position;
	
	// Constructor
	public Mass(float mass, ThreeVector position) {
		this.mass = mass;
		this.position = position;
		this.acceleration = new ThreeVector(0, 0, 0);
		this.velocity = new ThreeVector(0, 0, 0);
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
	
	public void setMAss(double mass) {
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
		double x_pos = this.getPosition().getX() + deltaTime * x_vel;
		double y_pos = this.getPosition().getY() + deltaTime * y_vel;
		double z_pos = this.getPosition().getZ() + deltaTime * z_vel;

		// Update position and velocity
		ThreeVector newVelocity = new ThreeVector(x_vel, y_vel, z_vel);
		ThreeVector newPosition = new ThreeVector(x_pos, y_pos, z_pos);
		this.setPosition(newPosition);
		this.setVelocity(newVelocity);		
	}
	
	// toString method
	public String toString() {
		String str = "X: " + this.getPosition().getX() + " Y: " + this.getPosition().getY() + " Z: " + this.getPosition().getZ();
		return str;
	}
	
	// Gravity method
	public ThreeVector gravity(FixedPoint fixPoint) {
		
		// Currently, does not operate in the z-direction
		double accelz = 0;
		
		// Determine acceleration due to gravity using Newton's Law of Universal Gravitation
		double distancex = 0.001*(this.getPosition().getX() - fixPoint.getPosition().getX());
		double accelx = ((this.getMass() * fixPoint.mass) * 6.67 * Math.pow(10, -11) / (distancex * distancex)) / this.getMass();
		double distancey = 0.001*(this.getPosition().getY() - fixPoint.getPosition().getY());
		double accely = (this.getMass() * fixPoint.mass) * 6.67 * Math.pow(10, -11) / (distancey * distancey) / this.getMass();
		
		// Determine the correct direction for the acceleration vector
		if (this.getPosition().getX() > fixPoint.getPosition().getX()) {
			accelx *= -1;
		}
		if (this.getPosition().getY() > fixPoint.getPosition().getY()) {
			accely *= -1;
		}
		
		// Return the acceleration
		ThreeVector accel = new ThreeVector(accelx, accely, accelz);
		return accel;	
	}
}
