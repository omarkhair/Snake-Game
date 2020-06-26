package tiles;

import java.awt.Color;
import java.awt.Graphics;

public class FoodTile extends Tile {

	public FoodTile(int id) {
		super(Color.ORANGE, id);
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, TILEWIDTH, TILEHEIGHT);
		g.setColor(color);
		g.fillOval(x, y, TILEWIDTH, TILEHEIGHT);
//		g.drawString("HI", x, y);
	}
}
