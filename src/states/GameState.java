package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import engine.Game;
import entities.Cell;
import entities.Snake;
import tiles.Tile;

public class GameState extends State {

	private static int maxScore = 0;
	public static final int ROWS = 20, COLUMNS = 20;
	private int startX = 0, startY = 60;
	private int score;
	private int[][] grid;
	// Carries the ids of the cells: Ground -> 0 , Food-> 1, SnakeBody ->2,
	// SnakeHead -> 3
	private Snake snake;
	private Cell food;

	public GameState(Game game) {
		super(game);
		Tile.TILEWIDTH = 20;
		Tile.TILEHEIGHT = 20;
		score = 0;
		grid = new int[ROWS][COLUMNS];
		snake = new Snake(new Cell(ROWS / 4, COLUMNS / 4), 1, 0);
		food = generateFood();
	}

	@Override
	public void tick() {
//		detectDirection();		// detect input from user and perform changes
		if (snake.getHead().equals(food)) {
			score++;
			snake.tick(true);
			food = generateFood();
		} else {
			snake.tick(false);
		}
		if (snake.getHead().x == -1 || snake.getHead().x == COLUMNS || snake.getHead().y == -1
				|| snake.getHead().y == ROWS || snake.bitesItself()) {
			// snake hits the boundaries or eats itself, you loses
			maxScore = Math.max(score, maxScore);
			game.onGameOver();
			return;
		}
		rebuildGrid();
//		game.render();

	}

	@Override
	public void render(Graphics g) {
		for (int y = 0; y < ROWS; y++)
			for (int x = 0; x < COLUMNS; x++) // gives the location in pexels
				getTile(x, y).render(g, startX + x * Tile.TILEWIDTH, startY + y * Tile.TILEHEIGHT);

		// draw score to screen
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Microsoft Sans Serif", 0, 20));
		g.drawString("Score: " + score, 30, 30);
		g.drawString("Max Score: " + maxScore, 250, 30);
	}

	private Tile getTile(int x, int y) {
		return Tile.tiles[grid[x][y]];
	}

	public void detectDirection() {
		if (game.getKeyBoard().isUp() && snake.getyDirection() == 0) {
			snake.setyDirection(-1);
			snake.setxDirection(0);
		} else if (game.getKeyBoard().isDown() && snake.getyDirection() == 0) {
			snake.setyDirection(1);
			snake.setxDirection(0);
		} else if (game.getKeyBoard().isRight() && snake.getxDirection() == 0) {
			snake.setyDirection(0);
			snake.setxDirection(1);
		} else if (game.getKeyBoard().isLeft() && snake.getxDirection() == 0) {
			snake.setyDirection(0);
			snake.setxDirection(-1);
		}

	}

	// this method puts the values inside the grid based on snake and food cells
	public void rebuildGrid() {
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLUMNS; j++)
				grid[i][j] = 0;

		grid[food.x][food.y] = 1;
		for (Cell c : snake.getCells())
			grid[c.x][c.y] = 2;
		grid[snake.getHead().x][snake.getHead().y] = 3;

	}

	public Cell generateFood() {
		Cell food;
		do {
			food = new Cell(generateRandom(), generateRandom());
		} while (snake.getCells().contains(food));

		return food;
	}

	public int generateRandom() {
		return (int) (Math.random() * ROWS);
	}
	public int getScore() {
		return score;
	}
}
