package states;

import java.awt.Graphics;

import engine.Game;

public class GameState extends State {
	private final int ROWS=100, COLUMNS=100;
	int[][] grid;
	public GameState(Game game) {
		super(game);
		grid=new int[ROWS][COLUMNS];
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
