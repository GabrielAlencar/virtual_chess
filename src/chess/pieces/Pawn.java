package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		int row = position.getRow();
		int column = position.getColumn();
		int displacement = 0;
		
		if (getColor() == Color.WHITE) {
			displacement = -1;
		} else {
			displacement = 1;
		}
		
		if (getBoard().positionExists(new Position(row + displacement, column)) && getBoard().piece(row + displacement, column) == null) {
			matrix[row + displacement][column] = true;
			if (getMoveCount() == 0 && getBoard().piece(row + 2*displacement, column) == null) {
				matrix[row + 2*displacement][column] = true;
			}
		}
		
		if (getBoard().positionExists(new Position(row + displacement, column - 1)) && isThereOpponentPiece(new Position(row + displacement, column - 1))) {
			matrix[row + displacement][column - 1] = true;
		}
		
		if (getBoard().positionExists(new Position(row + displacement, column + 1)) && isThereOpponentPiece(new Position(row + displacement, column + 1))) {
			matrix[row + displacement][column + 1] = true;
		}
		
		if (chessMatch.getEnPassant()) {
			Position opponentPawnPosition = chessMatch.getEnPassantPawnPosition();
			
			if (opponentPawnPosition.getRow() == row && Math.abs(opponentPawnPosition.getColumn() - column) == 1) {
				matrix[row + displacement][opponentPawnPosition.getColumn()] = true;
			}
		}
		
		return matrix;
	}
}
