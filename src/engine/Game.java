package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JOptionPane;

import input.KeyboardManager;
import states.GameState;
import states.State;
import view.Display;

public class Game implements Runnable { // Runnable allows this class to be run on a thread
	private int width, height;
	private String title;
	private Display display;
	private volatile boolean running;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;

	// input
	private KeyboardManager keyBoard;

	// constants
	public final int step = 4;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		start();
	}
	public Game() {
		this("Snake", 407, 500);
	}

	private void init() {
		display = new Display(title, width, height);
		keyBoard = new KeyboardManager();
		display.addKeyListener(keyBoard);
		gameState=new GameState(this);
		State.setState(gameState);
		
	}

	@Override
	public void run() {
		init();

		int fps = Math.min(25, 8+((GameState)gameState).getScore()); // variable speed depending on the score
		double timePerTick = (int) 1e9 / fps; // time in nanoseconds
		double delta = 0;
		long now, lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				delta -= 1;
			}
			// To make the keyboard responsive all the time
			if(gameState instanceof GameState) {
				keyBoard.tick();
				((GameState) gameState).detectDirection();
			}
		}
		stop();
		System.out.println("after stop");
	}

	private void tick() {
		keyBoard.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}

	public void render() {
		// TODO Auto-generated method stub
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3); // buffers are used to avoid flickering
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		State.getState().render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}

	private void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this); // Game class is passed as a runnable
		thread.start(); // calls the run method

	}

	private void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Runtime.getRuntime().exit(0);
	}
	public void onGameOver() {
//		running=false;
		render();
		int a = JOptionPane.showConfirmDialog(display, "Ops! You lost. Want to play again?", "EXIT Bar", JOptionPane.YES_NO_OPTION);
		if (a == JOptionPane.YES_OPTION) {
			display.setVisible(false);
			run();
		}
		else
			System.exit(0);
			
	}
	
	public KeyboardManager getKeyBoard() {
		return keyBoard;
	}
	

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static void main(String[] args) {
		new Game("Hello", 407, 500);
	}
}
