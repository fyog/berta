package berta;

import java.awt.Color;

public class Mass {
		
	// Fields
	private double mass;
	private double G;
	private ThreeVector	position, velocity, acceleration;
	private Color color;
		
	/**
	 * Constructor method.
	 * 
	 * @param mass
	 * @param position
	 */
	public Mass(double mass, ThreeVector position, ThreeVector velocity,
	double G, Color color) {
		this.mass = mass;
		this.position = position;
		this.velocity = velocity;
		this.acceleration = new ThreeVector();
		this.G = G;
		this.color = color;
	}
	
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
	 * Getter for G field.
	 * 
	 * @return double G value
	 */
	public double getG() {
		return G;
	}
	
	/**
	 * Getter for color field.
	 * 
	 * @return Color color of the mass
	 */
	public Color getColor() {
		return color;
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
	
	/**
	 * Setter for G field.
	 * 
	 * @param G
	 */
	public void setG(double G) {
		this.G = G;
	}
	
	/**
	 * Setter for Color field.
	 * 
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	/**
	 * To string method.
	 * 
	 * @return string representation of the Mass object
	 */
	@Override
	public String toString() {
		String str = "Mass: " + this.getMass() + "X: " + this.getPosition()
		.getX() + " Y: " + this.getPosition().getY() + " Z: " + this.
		getPosition().getZ() + "G: " + this.getG() + "Color: " + 
		this.getColor();
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
		velocity.toString();
		
		ThreeVector position = this.getPosition().add(this.getVelocity()
				.scale(deltaTime));
		
		// Update position
		this.setPosition(position);
		position.toString();
	}
	
	/**
	 * Calculates the acceleration of the current mass due to the gravitational
	 * pull of the passed mass object.
	 * 
	 * @param fixPoint
	 * @return ThreeVector acceleration
	 */
	public ThreeVector gravity(Mass other) {
		
		// Determine acceleration due to gravity using Newton's Law of 
		// Universal Gravitation
		ThreeVector delta = this.getPosition().minus(other.getPosition());
		double dist = delta.getMagnitude();
		double force = (G * this.getMass() * other.getMass()) / (dist * dist);
		if (dist < 1 && dist > -1) return new ThreeVector();
		else return delta.direction().scale(force);
	}
}
