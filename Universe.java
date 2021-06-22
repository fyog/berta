import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Universe extends Canvas {
	
	private Mass mass;
	private FixedPoint fixedPoint;

	// Constructors ---------------------------------------------------------------------------------------------------------------------------------------------

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
	
	// Overwritten methods --------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public void paint(Graphics g) {
		
		// Draw a circle to represent the fixed point
		g.setColor(Color.RED);
		g.fillOval((int) fixedPoint.getPosition().getX(), (int) fixedPoint.getPosition().getY(), 30, 30);
		
		// Draw a circle to represent the mass
		g.setColor(Color.GREEN);
		g.fillOval((int) mass.getPosition().getX(), (int) mass.getPosition().getY(), 15, 15);
	}
	
	// Main method -----------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create system components
		ThreeVector vec1 = new ThreeVector(700, 600, 0);
		ThreeVector vec2 = new ThreeVector(800, 800, 0);
		Mass mass = new Mass(5000, vec1);
		FixedPoint fixPoint = new FixedPoint(500000, vec2);
		
		// Set mass' initial velocity and initial acceleration
		mass.setVelocity(new ThreeVector(0, 0, 0));
		mass.setAcceleration(new ThreeVector(0, 0, 0));
		
		// Create window
		JFrame frame = new JFrame("Eric's Universe");
		Universe universe = new Universe(mass, fixPoint);
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
			ThreeVector accel = mass.gravity(fixPoint);
			
			// Set acceleration of mass
			mass.setAcceleration(accel);
			 
			// Predict new position and velocity based off of new acceleration
			mass.predict(deltaTime);
			
			// Repaint the canvas every ten 10 time steps
			if ( (int) time % 1 == 0) {
				universe.repaint();
			}
			
			// Increment time counter
			time += deltaTime;
		}
	}
}
