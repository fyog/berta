
public class Spring {
	
	double k;
	ThreeVector distance, equilibrium;
	Mass end1;
	FixedPoint fixpoint;
	
	/**
	 * Constructor method.
	 * @param k
	 * @param end1
	 * @param fixedpoint
	 */
	public Spring(double k, Mass end1, FixedPoint fixedpoint) {
		this.k = k;
		this.end1 = end1;
		this.fixpoint = fixedpoint;
	}
	
	/**
	 * NOT IMPLEMENTED
	 * @return
	 */
	public ThreeVector getAccel() {
		double deltax = end1.getPosition().getX() - fixpoint.getPosition().getX();
		double deltay = end1.getPosition().getY() - fixpoint.getPosition().getY();
		double deltaz = end1.getPosition().getZ() - fixpoint.getPosition().getZ();
		double accelx = (-k * deltax)/end1.getMass();
		double accely = (-k * deltay)/end1.getMass();
		double accelz = (-k * deltaz)/end1.getMass();
		ThreeVector accel = new ThreeVector(accelx, accely, accelz);
		return accel;	
	}
}
