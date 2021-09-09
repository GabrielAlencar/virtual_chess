package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		if (!board.positionExists(position)) {
			throw new BoardException("The position is out of range");
		}
		if (possibleMoves()[position.getRow()][position.getColumn()] == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] matrix = possibleMoves();
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				if (matrix[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}

}
