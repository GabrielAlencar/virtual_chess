package chess;

import boardgame.Board;
import boardgame.Piece;
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
		
		System.out.println(((ChessPiece)board.piece(new ChessPosition(5, 'd').toPosition())).isThereOpponentPiece(new ChessPosition(8, 'd').toPosition()));
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		ChessPiece chessPiece = null;
		if (!board.positionExists(sourcePosition.toPosition())) {
			throw new ChessException("Source position does not exist");
		}
		if (!board.thereIsAPiece(sourcePosition.toPosition())) {
			throw new ChessException("There is no piece on source position");
		}
		if (!board.piece(sourcePosition.toPosition()).isThereAnyPossibleMove()) {
			throw new ChessException("The selected piece has no possible moves");
		}
		if (!board.positionExists(targetPosition.toPosition())) {
			throw new ChessException("Target position does not exist");
		}
		if (!board.piece(sourcePosition.toPosition()).possibleMove(targetPosition.toPosition())) {
			throw new ChessException("Target position is not a possible move");
		}
		Piece piece = board.removePiece(sourcePosition.toPosition());
		if (board.thereIsAPiece(targetPosition.toPosition())) {
			chessPiece = (ChessPiece) board.removePiece(targetPosition.toPosition());
		} 
		board.placePiece(piece, targetPosition.toPosition());
		return chessPiece;
	}
	
	public boolean[][] getPossibleMoves(ChessPosition chessPosition) {
		if (!board.positionExists(chessPosition.toPosition())) {
			throw new ChessException("Chess position does not exist");
		}
		if (!board.thereIsAPiece(chessPosition.toPosition())) {
			throw new ChessException("There is no piece on coordinate: " + chessPosition);
		}
		return board.piece(chessPosition.toPosition()).possibleMoves();
	}

}
