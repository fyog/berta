package berta;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Universe extends Canvas implements ActionListener {

	// Fields
	private static final long serialVersionUID = 1L; // ?
	private static final int SIM_WINDOW_WIDTH = 850;
	private static final int SIM_WINDOW_HEIGHT = 200;
	Mass[] masses;
	boolean paused = false;
	Random rand = new Random();
	private static final double deltaTime = 0.001;

	/**
	 * Constructor method.
	 * 
	 * @param Mass[] masses
	 */

	public Universe(Mass[] masses) {
		this.masses = masses;
	}

	/*
	 * Setter for the paused field.
	 * 
	 * @param boolean value for the paused field
	 */
	public void setPaused(boolean value) {
		this.paused = value;
	}

	/*
	 * Getter for the paused field.
	 * 
	 * @returns boolean paused
	 */
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
			g.fillOval((int) m.getPosition().getX(), (int) m.getPosition().getY(), (int) m.getPosition().getZ() / 10,
					(int) m.getPosition().getZ() / 10);
		}

		if (masses.length == 2) {
			Mass mass = masses[0];
			Mass mass2 = masses[1];

			// Draw a line connecting the centers of mass
			g.setColor(Color.BLUE);
			g.drawLine((int) mass.getPosition().getX() + (int) mass.getPosition().getZ() / 20,
					(int) mass.getPosition().getY() + (int) mass.getPosition().getZ() / 20,
					(int) mass2.getPosition().getX() + (int) mass2.getPosition().getZ() / 20,
					(int) mass2.getPosition().getY() + (int) mass2.getPosition().getZ() / 20);
		}
	}

	/*
	* Sets up the universe.
	*/
	public void setUpUniverse() {

		// Create window
		JFrame frame = new JFrame("Eric's Universe");
		JPanel panel = new JPanel(new FlowLayout());
		this.setSize(SIM_WINDOW_WIDTH, SIM_WINDOW_HEIGHT);
		this.setBackground(Color.BLACK);

		JButton pause_button = new JButton("Pause");
		JButton reset_button = new JButton("Reset");

		pause_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (paused) {
					paused = false;
				} else {
					paused = true;
				}
			}
		});

		reset_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// If paused, then unpause
				paused = false;

				// Recreate system components
				ThreeVector pos1 = new ThreeVector(500, 150, 700);
				ThreeVector pos2 = new ThreeVector(300, 50, 100);
				ThreeVector vel1 = new ThreeVector(-5, 0, 0);
				ThreeVector vel2 = new ThreeVector(5, 0, 0);
				masses[0].setPosition(pos1);
				masses[0].setVelocity(vel1);
				masses[1].setPosition(pos2);
				masses[1].setVelocity(vel2);
			}
		});

		// Configure UI
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(SIM_WINDOW_WIDTH, 300));
		panel.add(this);
		panel.add(pause_button);
		panel.add(reset_button);
		frame.add(panel);
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
		double time = 0;
		ThreeVector pos1 = new ThreeVector(500, 150, 575);
		ThreeVector pos2 = new ThreeVector(300, 50, 100);
		ThreeVector vel1 = new ThreeVector(-5, 0, 0);
		ThreeVector vel2 = new ThreeVector(5, 0, 0);
		Mass[] masses = { new Mass(5, pos1, vel1, 1, Color.RED), new Mass(5000, pos2, vel2, 1, Color.GREEN) };
		Universe universe = new Universe(masses);
		universe.setUpUniverse();

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

			//Repaint the canvas
			if ((int) time % 1 == 0) {
				universe.repaint();
			}

			time += deltaTime;

			while (universe.getPaused()) {
				Thread.sleep(100);
			}
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
