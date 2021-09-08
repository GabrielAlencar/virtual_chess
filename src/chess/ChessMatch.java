package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		ChessPosition chessPosition = new ChessPosition(5, 'd');
		board.placePiece(new Rook(board, Color.WHITE), chessPosition.toPosition());
		chessPosition = new ChessPosition(5, 'e');
		board.placePiece(new Rook(board, Color.BLACK), chessPosition.toPosition());
		chessPosition = new ChessPosition(1, 'd');
		board.placePiece(new King(board, Color.WHITE), chessPosition.toPosition());
		chessPosition = new ChessPosition(8, 'd');
		board.placePiece(new King(board, Color.BLACK), chessPosition.toPosition());
	}

}
