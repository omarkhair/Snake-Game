package entities;

import java.util.LinkedList;

public class Snake {
	private LinkedList<Cell> cells;			// first cell is the head of the snake
	private int xDirection, yDirection;		// the movement directions of the snake
	public Snake(Cell startCell, int xDirection, int yDirection) {
		cells=new LinkedList<Cell>();
		this.xDirection=xDirection;
		this.yDirection=yDirection;
		for(int i=0;i<3;i++)
			cells.add(new Cell(startCell.x-i, startCell.y-i));
	}
	
	public void tick() {
		Cell newHead= new Cell(cells.get(0).x+xDirection, cells.get(0).y+yDirection);
		cells.removeLast();
		cells.addFirst(newHead);
	}
	
	
	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	public LinkedList<Cell> getCells(){
		return cells;
	}
}
