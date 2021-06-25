package berta;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Universe extends Canvas implements ActionListener {
	
	// Fields
	private static final long serialVersionUID = 1L; // ?
	Mass[] masses;
	boolean paused = false;
	/**
	 * Constructor method.
	 * 
	 * @param mass 
	 * @param fixedPoint
	 */
	
	public Universe (Mass[] masses) {
		this.masses = masses;
	}
	
	public void setPaused(boolean value) {
		this.paused = value;
	}
	
	public boolean getPaused() {
		return paused;
	}
	
	@Override
	/**
	 * Paints the graphics.
	 * 
	 * @param g
	 */
	public void paint(Graphics g) {
	
		for (Mass m : masses) {
			
			g.setColor(m.getColor());
			g.fillOval((int) m.getPosition().getX(), 
				(int) m.getPosition().getY(), 30, 30);
		}
		if (masses.length == 2) {
			Mass mass = masses[0];
			Mass mass2 = masses[1];
			
			g.setColor(Color.BLUE);
			
			// Draw a line connecting the centers of mass
			g.drawLine((int) mass.getPosition().getX() + 15, 
					(int) mass.getPosition().getY() + 15, 
				(int) mass2.getPosition().getX() + 15, 
				(int) mass2.getPosition().getY() + 15);
		}
	}
	
	public void setUpUniverse() {
		
		// Create window
		JFrame frame = new JFrame("Eric's Universe");
		//JPanel panel = new JPanel(new FlowLayout());
		Universe universe = new Universe(masses);
		universe.setSize(800, 800);
		universe.setBackground(Color.BLACK);
		//JButton button = new JButton("Pause");
		
		//button.addActionListener(this);
		//panel.add(universe);
		//panel.add(button);
		frame.add(universe);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}
	/**
	 * Main method.
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		// Create system components
		ThreeVector pos1 = new ThreeVector(500, 500, 0);
		ThreeVector pos2 = new ThreeVector(400, 400, 0);
		ThreeVector vel1 = new ThreeVector(-5, 0, 0);
		ThreeVector vel2 = new ThreeVector(4, 0, 0);

		Mass[] masses = {new Mass(5, pos1, vel1, 1, Color.RED), new Mass(50000,
				pos2, vel2, 1, Color.GREEN)};
		
		Universe universe = new Universe(masses);
		universe.setUpUniverse();
        
		// Initiate time step
        double time = .0;
		double deltaTime = .000002;
		
		// Running boolean
		boolean running = true;
		
		Mass mass = masses[0];
		Mass mass2 = masses[1];
		
		// Simulations loop begins...
		while (running) {
			
			// Set acceleration due to gravity of each mass
			mass.setAcceleration(mass.gravity(mass2));
			mass2.setAcceleration(mass2.gravity(mass));
			
			// Predict new position and velocity based using new acceleration
			mass.predict(deltaTime);
			mass2.predict(deltaTime);
			
			// Repaint the canvas
			if ((int) time % 1 == 0) {
				universe.repaint();
			}
			
			time += deltaTime;
			while (universe.getPaused()) Thread.sleep(100);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (paused) {
			paused = false;
			System.out.println("Unpaused");
		} else {
			paused = true;
			System.out.println("Pause");
		}
	}
}
