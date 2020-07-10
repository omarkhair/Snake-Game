package states;

import java.awt.Graphics;

import engine.Game;
import entities.Cell;
import entities.Snake;
import tiles.Tile;

public class GameState extends State {
	public static final int ROWS=20, COLUMNS=20;
	private int startX = 0,startY=60;
	private int[][] grid;	// Carries the ids of the cells: Ground -> 0 , Food-> 1, SnakeBody ->2 , SnakeHead -> 3
	private Snake snake;
	private Cell food;
	
	public GameState(Game game) {
		super(game);
		Tile.TILEWIDTH=20;
		Tile.TILEHEIGHT=20;
		grid=new int[ROWS][COLUMNS];
		snake = new Snake(new Cell(ROWS/2,COLUMNS/2), 1,0);
		food = generateFood();
	}

	@Override
	public void tick() {
//		detectDirection();		// detect input from user and perform changes
		if(snake.getHead().equals(food)) {
			snake.tick(true);
			food=generateFood();
		}
		else {
			snake.tick(false);
		}
		rebuildGrid();
	}

	@Override
	public void render(Graphics g) {
		
		for(int y=0;y<ROWS;y++)
			for(int x=0;x<COLUMNS;x++)
				getTile(x,y).render(g,startX + x*Tile.TILEWIDTH, startY + y*Tile.TILEHEIGHT); 	// gives the location in pexels
		
	}
	
	private Tile getTile(int x, int y) {
		return Tile.tiles[grid[x][y]];
	}
	
	public void detectDirection() {
		if(game.getKeyBoard().isUp() && snake.getyDirection()==0) {
			snake.setyDirection(-1);
			snake.setxDirection(0);
		}
		else if(game.getKeyBoard().isDown() && snake.getyDirection()==0) {
			snake.setyDirection(1);
			snake.setxDirection(0);
		}
		else if(game.getKeyBoard().isRight() && snake.getxDirection()==0) {
			snake.setyDirection(0);
			snake.setxDirection(1);
		}
		else if(game.getKeyBoard().isLeft() && snake.getxDirection()==0) {
			snake.setyDirection(0);
			snake.setxDirection(-1);
		}
		
	}
	
	// this method puts the values inside the grid based on snake and food cells
	public void rebuildGrid() {	
		for(int i=0;i<ROWS;i++)
			for(int j=0;j<COLUMNS;j++)
				grid[i][j]=0;
		
		grid[food.x][food.y]=1;
		
		for(Cell c: snake.getCells())
			grid[c.x][c.y]=2;
		grid[snake.getHead().x][snake.getHead().y]=3;
	}
	
	public Cell generateFood() {
		Cell food;
		do {
			food= new Cell(generateRandom(), generateRandom());
		}
		while(snake.getCells().contains(food));
		
		return food;
	}
	
	public int generateRandom() {	
		return (int) (Math.random()*ROWS);
	}
}
