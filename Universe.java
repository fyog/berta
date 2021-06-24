import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;

public class Universe extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		this.setSize(800, 800);
		this.setBackground(Color.BLACK);
		this.mass = mass;
		this.fixedPoint = fixedPoint;
	}
	
	public Universe (Mass mass, Mass mass2) {
		this.setSize(800, 800);
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
		g.fillOval((int) mass2.getPosition().getX(), 
				(int) mass2.getPosition().getY(), 30, 30);
		
		// Draw a circle to represent the mass
		g.setColor(Color.GREEN);
		g.fillOval((int) mass.getPosition().getX(), 
				(int) mass.getPosition().getY(), 15, 15);
		
		// Draw a line connecting the centers of mass
		g.setColor(Color.YELLOW);
		g.drawLine((int) mass.getPosition().getX() + 7, 
				(int) mass.getPosition().getY() + 7, 
				(int) mass2.getPosition().getX() + 15, 
				(int) mass2.getPosition().getY() + 15);
	}
	
	public void interrupt() {
		
        this.interrupt();
		System.exit(1);
    }
	
	/**
	 * Main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Create system components
		ThreeVector vec1 = new ThreeVector(450, 450, 0);
		ThreeVector vec2 = new ThreeVector(400, 400, 0);
		Mass mass = new Mass(5, vec1, 1);
		Mass mass2 = new Mass(500, vec2, 1);
		
		// Create window
		JFrame frame = new JFrame("Eric's Universe");
		Universe universe = new Universe(mass, mass2);
        frame.add(universe);
        frame.pack();
        frame.setVisible(true);
        

		System.out.println(mass.getPosition().getX());

		System.out.println(mass.getPosition().getY());
		// Initiate time step
        double time = .0;
		double deltaTime = .000002;
		
		// Running boolean
		boolean running = true;
		
		// Simulations loop begins...
		while (running) {
			
			// Set acceleration of mass
			mass.setAcceleration(mass.gravity(mass2));
			mass2.setAcceleration(mass2.gravity(mass));
			 
			// Predict new position and velocity based off of new acceleration
			mass.predict(deltaTime);
			mass2.predict(deltaTime);
			
			// Repaint the canvas every ten 10 time steps	
			if ( (int) time % 1 == 0) {
				universe.repaint();
			}
			
			// Increment time counter
			time += deltaTime;
			
		}
	}
}
