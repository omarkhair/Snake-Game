package entities;

public class Cell {
	public int x;
	public int y;
	public Cell(int x, int y) {
		this.x=x;
		this.y=y;
	}
	public boolean equals(Cell o) {
		return x==o.x && y==o.y;
	}

}
