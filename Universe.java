import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Universe extends Canvas {
	
	private Mass mass;
	private Mass mass2;
	private FixedPoint fixedPoint;

	/**
	 * Constructor method.
	 * 
	 * @param mass 
	 * @param fixedPoint
	 */
	public Universe(Mass mass, FixedPoint fixedPoint) {
		this.setSize(1600, 1600);
		this.setBackground(Color.BLACK);
		this.mass = mass;
		this.fixedPoint = fixedPoint;
	}
	
	public Universe (Mass mass, Mass mass2) {
		this.setSize(1600, 1600);
		this.setBackground(Color.BLACK);
		this.mass = mass;
		this.mass2 = mass2;
	}
	
	@Override
	/**
	 * Paints the graphics.
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
		
		// Draw a circle to represent the fixed point
		g.setColor(Color.RED);
		g.fillOval((int) mass2.getPosition().getX(), (int) mass2.getPosition().getY(), 30, 30);
		
		// Draw a circle to represent the mass
		g.setColor(Color.GREEN);
		g.fillOval((int) mass.getPosition().getX(), (int) mass.getPosition().getY(), 15, 15);
	}
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create system components
		ThreeVector vec1 = new ThreeVector(300, 300, 0);
		ThreeVector vec2 = new ThreeVector(400, 400, 0);
		Mass mass = new Mass(.05, vec1, 1);
		Mass mass2 = new Mass(50, vec2, 1);
		
		// Set mass' initial velocity and initial acceleration
		mass.setVelocity(new ThreeVector(1, 0, 0));
		mass.setAcceleration(new ThreeVector(0, 0, 0));
		mass2.setVelocity(new ThreeVector(0, 0, 0));
		mass2.setAcceleration(new ThreeVector(0, 0, 0));
		
		// Create window
		JFrame frame = new JFrame("Eric's Universe");
		Universe universe = new Universe(mass, mass2);
        frame.add(universe);
        frame.pack();
        frame.setVisible(true);
        		
		// Initiate time step
        double time = .0;
		double deltaTime = .00002;
		
		// Running boolean
		boolean running = true;
		
		// Simulations loop begins...
		while (running) {
			
			// Find and update mass acceleration due to gravity
			ThreeVector accel = mass.gravity(mass2);
			ThreeVector accel2 = mass2.gravity(mass);
			
			// Set acceleration of mass
			mass.setAcceleration(accel);
			mass2.setAcceleration(accel2);
			 
			// Predict new position and velocity based off of new acceleration
			mass.predict(deltaTime);
			mass2.predict(deltaTime);
			// Repaint the canvas every ten 10 time steps
			if ( (int) time % 10 == 0) {
				universe.repaint();
			}
			
			// Increment time counter
			time += deltaTime;
		}
	}
}
