package gamePlay;

public class FieldPosition {

	public int rowCoord;
	public int colCoord;
	
	public int getRowCoord() {
		return rowCoord;
	}

	public void setRowCoord(int rowCoord) {
		this.rowCoord = rowCoord;
	}

	public int getColCoord() {
		return colCoord;
	}

	public void setColCoord(int colCoord) {
		this.colCoord = colCoord;
	}

	public FieldPosition(int rowCoord, int colCoord) {
		super();
		this.rowCoord = rowCoord;
		this.colCoord = colCoord;
		
	}
	
	
}
