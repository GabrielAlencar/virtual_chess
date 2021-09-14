package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	
	private List<ChessPiece> blackPiecesOnTheBoard = new ArrayList<>();
	private List<ChessPiece> whitePiecesOnTheBoard = new ArrayList<>();
	private List<ChessPiece> capturedBlackPieces = new ArrayList<>();
	private List<ChessPiece> capturedWhitePieces = new ArrayList<>();
	private Position blackKingReference;
	private Position whiteKingReference;
	
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
		Rook leftWhiteRook = new Rook(board, Color.WHITE);
		board.placePiece(leftWhiteRook, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(leftWhiteRook);
		chessPosition = new ChessPosition(1, 'h');
		Rook rightWhiteRook = new Rook(board, Color.WHITE);
		board.placePiece(rightWhiteRook, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(rightWhiteRook);
		chessPosition = new ChessPosition(1, 'd');
		King whiteKing = new King(board, Color.WHITE);
		whiteKingReference = chessPosition.toPosition();
		board.placePiece(whiteKing, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(whiteKing);
		chessPosition = new ChessPosition(8, 'a');
		Rook leftBlackRook = new Rook(board, Color.BLACK);
		board.placePiece(leftBlackRook, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(leftBlackRook);
		chessPosition = new ChessPosition(8, 'h');
		Rook rightBlackRook = new Rook(board, Color.BLACK);
		board.placePiece(rightBlackRook, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(rightBlackRook);
		chessPosition = new ChessPosition(8, 'd');
		King blackKing = new King(board, Color.BLACK);
		blackKingReference = chessPosition.toPosition();
		board.placePiece(blackKing, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(blackKing);
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
			if (currentPlayer == Color.WHITE) {
				blackPiecesOnTheBoard.remove(chessPiece);
			} else {
				whitePiecesOnTheBoard.remove(chessPiece);
			}
		} 
		board.placePiece(piece, targetPosition.toPosition());
		testCheck(piece, chessPiece, sourcePosition, targetPosition);
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
	
	public void capturePiece(ChessPiece chessPiece) {
		if (chessPiece != null) {
			if(chessPiece.getColor() == Color.WHITE) {
				capturedWhitePieces.add(chessPiece);
			} else {
				capturedBlackPieces.add(chessPiece);
			}
		}
	}
	
	public String getCapturedPieces() {
		StringBuilder sb = new StringBuilder();
		if (currentPlayer == Color.WHITE) {
			for (int i = 0; i < capturedBlackPieces.size(); i++) {
				sb.append(capturedBlackPieces.get(i).toString());
				if (i < capturedBlackPieces.size() - 1) {
					sb.append(", ");
				}
			}
		} else {
			for (int i = 0; i < capturedWhitePieces.size(); i++) {
				sb.append(capturedWhitePieces.get(i).toString());
				if (i < capturedWhitePieces.size() - 1) {
					sb.append(", ");
				}
			}
		}
		return sb.toString();
	}
	
	public boolean check() {
		if (currentPlayer == Color.WHITE) {
			for (ChessPiece blackPiece : blackPiecesOnTheBoard) {
				if (blackPiece.possibleMove(whiteKingReference)) {
					return true;
				}
			}
			return false;
		} else {
			for (ChessPiece whitePiece : whitePiecesOnTheBoard) {
				if (whitePiece.possibleMove(blackKingReference)) {
					return true;
				}
			}
			return false;
		}
	}
	
	private void testCheck(Piece piece, ChessPiece chessPiece, ChessPosition sourcePosition, ChessPosition targetPosition) {
		if (piece.toString() == "K") {
			if (currentPlayer == Color.WHITE) {
				whiteKingReference = targetPosition.toPosition();
			} else {
				blackKingReference = targetPosition.toPosition();
			}
		}
		if (check()) {
			board.placePiece(piece, sourcePosition.toPosition());
			board.removePiece(targetPosition.toPosition());
			if (chessPiece != null) {
				board.placePiece(chessPiece, targetPosition.toPosition());
				if (currentPlayer == Color.WHITE) {
					blackPiecesOnTheBoard.add(chessPiece);
				} else {
					whitePiecesOnTheBoard.add(chessPiece);
				}
			}
			if (piece.toString() == "K") {
				if (currentPlayer == Color.WHITE) {
					whiteKingReference = sourcePosition.toPosition();
				} else {
					blackKingReference = sourcePosition.toPosition();
				}
			}
			throw new ChessException("You can not put yourself in check");
		}
	}

}
