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
	public static int TILEWIDTH = 19 , TILEHEIGHT = 19;

	// Class
	protected Color color;
	protected Color borderColor;
	protected final int id;

	public Tile(Color color,Color borderColor, int id) {
		this.color = color;
		this.borderColor=borderColor;
		this.id = id;
		tiles[id]=this;
	}

	public void tick() {

	}

	public void render(Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(x, y, TILEWIDTH, TILEHEIGHT);
		g.setColor(borderColor);
		g.drawRect(x, y, TILEWIDTH, TILEHEIGHT);
	}
}
