package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{
	
	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
		
		//Castling
		if (getMoveCount() == 0) {
			int firstRank;
			if (getColor() == Color.WHITE) {
				firstRank = getBoard().getRows() - 1;
			} else {
				firstRank = 0;
			}
			
			Piece piece = getBoard().piece(firstRank, 0);
			if (piece != null) {
				boolean isCastlingPossible = true;
				if (piece instanceof Rook && !chessMatch.hasPieceMoved(piece)) {
					for (int i = 1; i < 4; i++) {
						if (getBoard().piece(firstRank, i) != null) {
							isCastlingPossible = false;
							break;
						}
					}
				} else {
					isCastlingPossible = false;
				}
				
				if (isCastlingPossible) {
					matrix[row][column - 2] = true;
				}
			}
			
			piece = getBoard().piece(firstRank, getBoard().getColumns() - 1);
			if (piece != null) {
				boolean isCastlingPossible = true;
				if (piece instanceof Rook && !chessMatch.hasPieceMoved(piece)) {
					for (int i = 1; i < 3; i++) {
						if (getBoard().piece(firstRank, column + i) != null) {
							isCastlingPossible = false;
							break;
						}
					}
				} else {
					isCastlingPossible = false;
				}
				
				if (isCastlingPossible) {
					matrix[row][column + 2] = true;
				}
			}
			
		}
		
		return matrix;
	}

}
