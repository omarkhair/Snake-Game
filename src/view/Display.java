package view;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Display extends JFrame {
	private int width, height;
	private Canvas canvas;

	public Display(String title, int width, int height) {
		super(title);
		this.width = width;
		this.height = height;
		buildFrame();
		buildCanvas();
		add(canvas);
		
	}

	private void buildFrame() {
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void buildCanvas() {
		canvas = new Canvas();
		setSize(width, height);
		canvas.setFocusable(false);
	}
	public Canvas getCanvas() {
		return canvas;
	}
}
