package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		int row = position.getRow();
		int column = position.getColumn();
		
		if (column < matrix[0].length - 1) {
			if (getBoard().piece(row, column + 1) == null) {
				matrix[row][column + 1] = true;
			} else if (isThereOpponentPiece(new Position(row, column + 1))) {
				matrix[row][column + 1] = true;
			}
			
			if (row > 0) {
				if (getBoard().piece(row - 1, column + 1) == null) {
					matrix[row - 1][column + 1] = true;
				} else if (isThereOpponentPiece(new Position(row - 1, column + 1))) {
					matrix[row - 1][column + 1] = true;
				}
			}
			
			if (row < matrix.length - 1) {
				if (getBoard().piece(row + 1, column + 1) == null) {
					matrix[row + 1][column + 1] = true;
				} else if (isThereOpponentPiece(new Position(row + 1, column + 1))) {
					matrix[row + 1][column + 1] = true;
				}
			}
		}
		
		if (column > 0) {
			if (getBoard().piece(row, column - 1) == null) {
				matrix[row][column - 1] = true;
			} else if (isThereOpponentPiece(new Position(row, column - 1))) {
				matrix[row][column - 1] = true;
			}
			
			if(row > 0) {
				if (getBoard().piece(row - 1, column - 1) == null) {
					matrix[row - 1][column - 1] = true;
				} else if (isThereOpponentPiece(new Position(row - 1, column - 1))) {
					matrix[row - 1][column - 1] = true;
				}
			}
			
			if (row < matrix.length - 1) {
				if (getBoard().piece(row + 1, column - 1) == null) {
					matrix[row + 1][column - 1] = true;
				} else if (isThereOpponentPiece(new Position(row + 1, column - 1))) {
					matrix[row + 1][column - 1] = true;
				}
			}
		}
		
		if (row < matrix.length - 1) {
			if (getBoard().piece(row + 1, column) == null) {
				matrix[row + 1][column] = true;
			} else if (isThereOpponentPiece(new Position(row + 1, column))) {
				matrix[row + 1][column] = true;
			}
		}
		
		if (row > 0) {
			if (getBoard().piece(row - 1, column) == null) {
				matrix[row - 1][column] = true;
			} else if (isThereOpponentPiece(new Position(row - 1, column))) {
				matrix[row - 1][column] = true;
			}
		}
		
		return matrix;
	}

}
