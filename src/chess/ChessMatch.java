package chess;

import boardgame.Board;
import boardgame.Piece;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
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
		ChessPosition chessPosition = new ChessPosition(1, 'a');
		board.placePiece(new Rook(board, Color.WHITE), chessPosition.toPosition());
		chessPosition = new ChessPosition(8, 'a');
		board.placePiece(new Rook(board, Color.BLACK), chessPosition.toPosition());
		chessPosition = new ChessPosition(1, 'd');
		board.placePiece(new King(board, Color.WHITE), chessPosition.toPosition());
		chessPosition = new ChessPosition(8, 'd');
		board.placePiece(new King(board, Color.BLACK), chessPosition.toPosition());
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		ChessPiece chessPiece = null;
		if (!board.positionExists(sourcePosition.toPosition())) {
			throw new ChessException("Source position does not exist");
		}
		if (!board.thereIsAPiece(sourcePosition.toPosition())) {
			throw new ChessException("There is no piece on source position");
		}
		Color pieceColor = ((ChessPiece)board.piece(sourcePosition.toPosition())).getColor();
		if (pieceColor != currentPlayer) {
			throw new ChessException("You can not move " + pieceColor + " pieces because it is " + currentPlayer + " player's turn");
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
		nextTurn();
		return chessPiece;
	}
	
	public boolean[][] getPossibleMoves(ChessPosition chessPosition) {
		if (!board.positionExists(chessPosition.toPosition())) {
			throw new ChessException("Chess position does not exist");
		}
		if (!board.thereIsAPiece(chessPosition.toPosition())) {
			throw new ChessException("There is no piece on coordinate: " + chessPosition);
		}
		Color pieceColor = ((ChessPiece)board.piece(chessPosition.toPosition())).getColor();
		if (pieceColor != currentPlayer) {
			throw new ChessException("You can not move " + pieceColor + " pieces because it is " + currentPlayer + " player's turn");
		}
		return board.piece(chessPosition.toPosition()).possibleMoves();
	}
	
	private void nextTurn() {
		turn++;
		if (currentPlayer == Color.WHITE) {
			currentPlayer = Color.BLACK;
		} else {
			currentPlayer = Color.WHITE;
		}
	}

}
