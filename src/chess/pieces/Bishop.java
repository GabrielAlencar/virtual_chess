package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		int row = position.getRow();
		int column = position.getColumn();
		
		for (int j = 1; column + j < matrix[0].length && row + j < matrix.length; j++) {
			if (getBoard().piece(row + j, column + j) == null) {
				matrix[row + j][column + j] = true;
			} else if (isThereOpponentPiece(new Position(row + j, column + j))) {
				matrix[row + j][column + j] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row + j, column + j)).getColor() == getColor() ) {
				break;
			}
		}
		
		for (int j = 1; column - j >= 0 && row - j >= 0; j++) {
			if (getBoard().piece(row - j, column - j) == null) {
				matrix[row - j][column - j] = true;
			} else if (isThereOpponentPiece(new Position(row - j, column - j))) {
				matrix[row - j][column - j] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row - j, column - j)).getColor() == getColor() ) {
				break;
			}
		}
		
		for (int j = 1; column + j < matrix[0].length && row - j >= 0; j++) {
			if (getBoard().piece(row - j, column + j) == null) {
				matrix[row - j][column + j] = true;
			} else if (isThereOpponentPiece(new Position(row - j, column + j))) {
				matrix[row - j][column + j] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row - j, column + j)).getColor() == getColor() ) {
				break;
			}
		}
		
		for (int j = 1; column - j >= 0 && row + j < matrix.length; j++) {
			if (getBoard().piece(row + j, column - j) == null) {
				matrix[row + j][column - j] = true;
			} else if (isThereOpponentPiece(new Position(row + j, column - j))) {
				matrix[row + j][column - j] = true;
				break;
			} else if (((ChessPiece)getBoard().piece(row + j, column - j)).getColor() == getColor() ) {
				break;
			}
		}
		
		return matrix;
	}

}
