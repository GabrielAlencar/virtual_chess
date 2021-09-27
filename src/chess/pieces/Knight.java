package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

	public Knight(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {
		return "N";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		int row = position.getRow();
		int column = position.getColumn();
		
		Position position = new Position(row - 2, column - 1);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setColumn(column + 1);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setRow(row + 2);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setColumn(column - 1);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setRow(row + 1);
		position.setColumn(column - 2);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setRow(row - 1);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setColumn(column + 2);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		position.setRow(row + 1);
		if(getBoard().positionExists(position) && (getBoard().piece(position) == null || isThereOpponentPiece(position))) {
			matrix[position.getRow()][position.getColumn()] = true;
		}
		
		return matrix;
	}

}
