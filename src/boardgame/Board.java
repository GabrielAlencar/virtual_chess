package boardgame;

public class Board {

	private Integer rows;
	private Integer columns;
	private Piece[][] pieces;
	
	public Board(Integer rows, Integer columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}
	
	public Piece piece(Integer row, Integer column) {
		if (!positionExists(row,column)) {
			throw new BoardException("Error accessing board cordinate: row or column out of range");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error accessing board coordinate: row or column out of range");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Piece piece, Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Error placing piece on board: row or column out of range");
		}
		if (thereIsAPiece(position)) {
			throw new BoardException("Error placing piece on board: there is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	private boolean positionExists(Integer row, Integer column) {
		if (row >= 0 && row < rows && column >= 0 && column < columns) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean positionExists(Position position) {
		if (position.getRow() >= 0 && position.getRow() < rows && position.getColumn() >= 0 && position.getColumn() < columns) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean thereIsAPiece(Position position) {
		if (piece(position) != null) {
			return true;
		} else {
			return false;
		}
	}

}
