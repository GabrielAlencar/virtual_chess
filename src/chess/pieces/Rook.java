package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		int row = position.getRow();
		int column = position.getColumn();
		
		for (int j = 1; column + j < matrix[0].length; j++) {
			if (getBoard().piece(row, column + j) == null) {
				matrix[row][column + j] = true;
			} else if (isThereOpponentPiece(new Position(row, column + j))) {
				matrix[row][column + j] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row, column + j)).getColor() == getColor() ) {
				break;
			}
		}
		
		for (int j = 1; column - j >= 0; j++) {
			if (getBoard().piece(row, column - j) == null) {
				matrix[row][column - j] = true;
			} else if (isThereOpponentPiece(new Position(row, column - j))) {
				matrix[row][column - j] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row, column - j)).getColor() == getColor() ) {
				break;
			}
		}
		
		for (int i = 1; row + i < matrix.length; i++) {
			if (getBoard().piece(row + i, column) == null) {
				matrix[row + i][column] = true;
			} else if (isThereOpponentPiece(new Position(row + i, column))) {
				matrix[row + i][column] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row + i, column)).getColor() == getColor() ) {
				break;
			}
		}
		
		for (int i = 1; row - i >= 0; i++) {
			if (getBoard().piece(row - i, column) == null) {
				matrix[row - i][column] = true;
			} else if (isThereOpponentPiece(new Position(row - i, column))) {
				matrix[row - i][column] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row - i, column)).getColor() == getColor() ) {
				break;
			}
		}
		
		return matrix;
	}

}
