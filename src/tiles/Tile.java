package tiles;

import java.awt.Color;
import java.awt.Graphics;

abstract public class Tile {
	// STATIC STUFF HERE

	public static Tile[] tiles = new Tile[256];
	public static Tile GroundTile = new GroundTile(0);
	public static Tile FoodTile = new FoodTile(1);
	public static Tile SnakeBodyTile = new SnakeBodyTile(2);
	public static Tile SnakeHeadTile = new SnakeHeadTile(3);
	
	// Class
	protected Color color;
	protected final int id;
	public static int TILEWIDTH, TILEHEIGHT;

	public Tile(Color color, int id) {
		this.color = color;
		this.id = id;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.setColor(color);
		g.drawRect(x, y, TILEWIDTH, TILEHEIGHT);
	}
}
