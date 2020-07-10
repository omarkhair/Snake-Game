package entities;

public class Cell {
	public int x;
	public int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Cell)
			return x == ((Cell) o).x && y == ((Cell) o).y;
		else
			return false;
	}

}
