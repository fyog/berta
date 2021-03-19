public class FixedPoint {
	
	ThreeVector position;
	double mass;
	
	// Constructors
	public FixedPoint(ThreeVector position) {
		this.position = position;
	}
	public FixedPoint(ThreeVector position, double mass) {
		this.position = position;
		this.mass = mass;
	}
	
	// Getters and setters
	public ThreeVector getPosition() {
		return position;
	}
	public void setPosition(ThreeVector position) {
		this.position = position;
	}
}
