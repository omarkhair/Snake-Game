package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import input.KeyboardManager;
import view.Display;

public class Game implements Runnable { // Runnable allows this class to be run on a thread
	private int width, height;
	private String title;
	private Display display;
	private int x, y;
	private int xMove, yMove;
	private boolean running;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	// input
	private KeyboardManager keyBoard;

	// constants
	public final int step = 4;

	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		x = width / 2;
		y = width / 2;
		start();
	}

	private void init() {
		display = new Display(title, width, height);
		keyBoard = new KeyboardManager();
		display.addKeyListener(keyBoard);

	}

	@Override
	public void run() {
		init();

		int fps = 20; // screen is updated 10 times per second
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
		}
		stop();
	}

	private void tick() {
		// TODO Auto-generated method stub
//		x+=step;
		keyBoard.tick();
//		xMove=yMove=0;
		if (keyBoard.isUp()) {
			yMove -= step;
//			xMove = 0;
		}
		if (keyBoard.isDown()) {
			yMove += step;
//			xMove = 0;
		}
		if (keyBoard.isLeft()) {
			xMove -= step;
//			yMove = 0;
		}
		if (keyBoard.isRight()) {
			xMove += step;
//			yMove = 0;
		}
		xMove=Math.min(xMove, 4*step);
		xMove=Math.max(xMove, -4*step);
		yMove=Math.min(yMove, 4*step);
		yMove=Math.max(yMove, -4*step);
		x += xMove;
		y += yMove;
	}

	private void render() {
		// TODO Auto-generated method stub
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3); // buffers are used to avoid flickering
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// start drawing

		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, 32, 32);

		g.setColor(Color.green);
//		g.fillRect(y, x, 20, 20);

		// end drawing
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
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Game("Hello", 600, 600);
	}
}
