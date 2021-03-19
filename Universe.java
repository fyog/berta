import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Universe extends Canvas {
	
	Mass mass;
	FixedPoint fixedPoint;
	Spring spring;

	/**
	 * Constructor method.
	 * @param mass
	 * @param fixedPoint
	 */
	public Universe(Mass mass, FixedPoint fixedPoint) {
		this.setSize(800, 800);
		this.setBackground(Color.BLACK);
		this.mass = mass;
		this.fixedPoint = fixedPoint;
	}
	
	@Override
	public void paint(Graphics g) {
		
		// Draw a circle to represent the fixed point
		g.setColor(Color.RED);
		g.fillOval((int) fixedPoint.getPosition().getX(), (int) fixedPoint.getPosition().getY(), 30, 30);
		
		// Draw a circle to represent the mass
		g.setColor(Color.GREEN);
		g.fillOval((int) mass.getPosition().getX(), (int) mass.getPosition().getY(), 30, 30);
	}
	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create system components
		ThreeVector vec1 = new ThreeVector(300, 300, 0);
		ThreeVector vec2 = new ThreeVector(400, 400, 0);
		Mass mass = new Mass(5000, vec1);
		FixedPoint fixPoint = new FixedPoint(vec2, 500000000);
		mass.setVelocity(new ThreeVector(2,0,0));
		
		// Create window
		JFrame frame = new JFrame("Eric's Universe");
		Universe universe = new Universe(mass, fixPoint);
        frame.add(universe);
        frame.pack();
        frame.setVisible(true);
        		
		// Initiate time step
		double deltaTime = 0.00002;
		
		// Running boolean
		boolean running = true;
		
		// SIMULATION LOOP...
		while (running) {
						
			// Find and update mass acceleration due to gravity
			ThreeVector accel = mass.gravity(fixPoint);
			
			// Set acceleration of mass
			mass.setAccel(accel);
			 
			// Predict new position and velocity
			mass.predict(deltaTime);
			
			// Repaint the canvas
			universe.repaint();
		}
	}
}
