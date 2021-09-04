package chess;

import boardgame.Board;
import boardgame.Position;

public class ChessPosition {

	private Integer row;
	private char column;
	
	private static Board board = new Board(8,8);
	
	public ChessPosition() {
	}

	public ChessPosition(Integer row, char column) {
		if (row < 1 || row > board.getRows()) {
			throw new ChessException("Row out of range");
		}
		if (column - 'a' < 0 || column - 'a' > board.getColumns() - 1) {
			throw new ChessException("Column out of range");
		}
		this.row = row;
		this.column = column;
	}

	public char getColumn() {
		return column;
	}

	public Integer getRow() {
		return row;
	}
	
	protected Position toPosition() {
		int row = board.getRows() - this.row;
		int column = (int) (this.column - 'a');
		return new Position(row, column);
	}
	
	protected static ChessPosition fromPosition(Position position) {
		if(!board.positionExists(position)) {
			throw new ChessException("Position can not be converted because it does not exist on the board");
		}
		int row = board.getRows() - position.getRow();
		int increment = position.getColumn();
		char column = (char) ('a' + increment);
		return new ChessPosition(row, column);
	}

	@Override
	public String toString() {
		return column + "" + row;
	}
	

}
