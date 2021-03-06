package chess;

import java.util.ArrayList;
import java.util.List;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
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
	
	private boolean enPassant;
	private Position enPassantPawnPosition;
	private int enPassantTurnCounter;
	
	private boolean promotionStatus;
	private Position promotedPawnPosition;
	
	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		enPassant = false;
		enPassantPawnPosition = null;
		enPassantTurnCounter = 0;
		promotionStatus = false;
		promotedPawnPosition = null;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getEnPassant() {
		return enPassant;
	}
	
	public Position getEnPassantPawnPosition() {
		return enPassantPawnPosition;
	}
	
	public int getEnPassantTurnCounter() {
		return enPassantTurnCounter;
	}
	
	public boolean wasPromotionMade() {
		return promotionStatus;
	}
	
	public ChessPosition getPromotedPawnPosition() {
		return ChessPosition.fromPosition(promotedPawnPosition);
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
		chessPosition = new ChessPosition(1, 'b');
		Knight leftWhiteKnight = new Knight(board, Color.WHITE);
		board.placePiece(leftWhiteKnight, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(leftWhiteKnight);
		chessPosition = new ChessPosition(1, 'g');
		Knight rightWhiteKnight = new Knight(board, Color.WHITE);
		board.placePiece(rightWhiteKnight, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(rightWhiteKnight);
		chessPosition = new ChessPosition(1, 'c');
		Bishop leftWhiteBishop = new Bishop(board, Color.WHITE);
		board.placePiece(leftWhiteBishop, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(leftWhiteBishop);
		chessPosition = new ChessPosition(1, 'f');
		Bishop rightWhiteBishop = new Bishop(board, Color.WHITE);
		board.placePiece(rightWhiteBishop, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(rightWhiteBishop);
		chessPosition = new ChessPosition(1, 'd');
		Queen whiteQueen = new Queen(board, Color.WHITE);
		board.placePiece(whiteQueen, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(whiteQueen);
		chessPosition = new ChessPosition(1, 'e');
		King whiteKing = new King(board, Color.WHITE, this);
		whiteKingReference = chessPosition.toPosition();
		board.placePiece(whiteKing, chessPosition.toPosition());
		whitePiecesOnTheBoard.add(whiteKing);
		int row = 2;
		char column = 'a';
		Pawn whitePawn;
		for (int i = 0; i < board.getColumns(); i++) {
			whitePawn = new Pawn(board, Color.WHITE, this);
			chessPosition = new ChessPosition(row, column);
			board.placePiece(whitePawn, chessPosition.toPosition());
			whitePiecesOnTheBoard.add(whitePawn);
			column++;
		}
		chessPosition = new ChessPosition(8, 'a');
		Rook leftBlackRook = new Rook(board, Color.BLACK);
		board.placePiece(leftBlackRook, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(leftBlackRook);
		chessPosition = new ChessPosition(8, 'h');
		Rook rightBlackRook = new Rook(board, Color.BLACK);
		board.placePiece(rightBlackRook, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(rightBlackRook);
		chessPosition = new ChessPosition(8, 'b');
		Knight leftBlackKnight = new Knight(board, Color.BLACK);
		board.placePiece(leftBlackKnight, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(leftBlackKnight);
		chessPosition = new ChessPosition(8, 'g');
		Knight rightBlackKnight = new Knight(board, Color.BLACK);
		board.placePiece(rightBlackKnight, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(rightBlackKnight);
		chessPosition = new ChessPosition(8, 'c');
		Bishop leftBlackBishop = new Bishop(board, Color.BLACK);
		board.placePiece(leftBlackBishop, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(leftBlackBishop);
		chessPosition = new ChessPosition(8, 'f');
		Bishop rightBlackBishop = new Bishop(board, Color.BLACK);
		board.placePiece(rightBlackBishop, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(rightBlackBishop);
		chessPosition = new ChessPosition(8, 'd');
		Queen blackQueen = new Queen(board, Color.BLACK);
		board.placePiece(blackQueen, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(blackQueen);
		chessPosition = new ChessPosition(8, 'e');
		King blackKing = new King(board, Color.BLACK, this);
		blackKingReference = chessPosition.toPosition();
		board.placePiece(blackKing, chessPosition.toPosition());
		blackPiecesOnTheBoard.add(blackKing);
		row = 7;
		column = 'a';
		Pawn blackPawn;
		for (int i = 0; i < board.getColumns(); i++) {
			blackPawn = new Pawn(board, Color.BLACK, this);
			chessPosition = new ChessPosition(row, column);
			board.placePiece(blackPawn, chessPosition.toPosition());
			blackPiecesOnTheBoard.add(blackPawn);
			column++;
		}
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
		Piece piece = null;
		if (wasCastlingChosen(sourcePosition, targetPosition)) {
			performCastling(sourcePosition, targetPosition);
		} else {
			piece = board.removePiece(sourcePosition.toPosition());
			chessPiece = makeMove(piece, sourcePosition, targetPosition);
			testCheck(piece, chessPiece, sourcePosition, targetPosition);
			verifyEnPassantCondition(piece, sourcePosition, targetPosition);
			verifyPromotionStatus(piece, targetPosition);
		}
		if (promotionStatus == false) {
			nextTurn();
		}
		return chessPiece;
	}
	
	public void performPromotion(ChessPiece chosenPromotedPiece) {
		if (promotionStatus == true) {
			Piece promotedPawn = board.removePiece(promotedPawnPosition);
			Color promotedPawnColor = ((ChessPiece)promotedPawn).getColor();
			if (promotedPawnColor == Color.WHITE) {
				whitePiecesOnTheBoard.remove(promotedPawn);
			} else {
				blackPiecesOnTheBoard.remove(promotedPawn);
			}
			if (chosenPromotedPiece instanceof Knight) {
				Knight knight = new Knight(board, promotedPawnColor);
				board.placePiece(knight, promotedPawnPosition);
				if (promotedPawnColor == Color.WHITE) {
					whitePiecesOnTheBoard.add(knight);
				} else {
					blackPiecesOnTheBoard.add(knight);
				}
			} else if (chosenPromotedPiece instanceof Bishop) {
				Bishop bishop = new Bishop(board, promotedPawnColor);
				board.placePiece(bishop, promotedPawnPosition);
				if (promotedPawnColor == Color.WHITE) {
					whitePiecesOnTheBoard.add(bishop);
				} else {
					blackPiecesOnTheBoard.add(bishop);
				}
			} else if (chosenPromotedPiece instanceof Rook) {
				Rook rook = new Rook(board, promotedPawnColor);
				board.placePiece(rook, promotedPawnPosition);
				if (promotedPawnColor == Color.WHITE) {
					whitePiecesOnTheBoard.add(rook);
				} else {
					blackPiecesOnTheBoard.add(rook);
				}
			} else if (chosenPromotedPiece instanceof Queen) {
				Queen queen = new Queen(board, promotedPawnColor);
				board.placePiece(queen, promotedPawnPosition);
				if (promotedPawnColor == Color.WHITE) {
					whitePiecesOnTheBoard.add(queen);
				} else {
					blackPiecesOnTheBoard.add(queen);
				}
			}
			promotionStatus = false;
			promotedPawnPosition = null;
			nextTurn();
		}
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
		if (enPassant) {
			if (enPassantTurnCounter == 0) {
				enPassantTurnCounter++;
			} else {
				enPassantTurnCounter = 0;
				enPassant = false;
				enPassantPawnPosition = null;
			}
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
	
	private ChessPiece makeMove(Piece piece, ChessPosition sourcePosition, ChessPosition targetPosition) {
		ChessPiece capturedPiece = null;
		if (wasEnPassantChosen(piece, sourcePosition, targetPosition)) {
			capturedPiece = performEnPassant(piece, sourcePosition, targetPosition);
		} else {
			if (board.thereIsAPiece(targetPosition.toPosition())) {
				capturedPiece = (ChessPiece) board.removePiece(targetPosition.toPosition());
				if (currentPlayer == Color.WHITE) {
					blackPiecesOnTheBoard.remove(capturedPiece);
				} else {
					whitePiecesOnTheBoard.remove(capturedPiece);
				}
			} 
			board.placePiece(piece, targetPosition.toPosition());
			if (piece instanceof King) {
				updateKingTargetReference(targetPosition.toPosition());
			}
		}
		((ChessPiece)piece).increaseMoveCount();
		return capturedPiece;
	}
	
	private void undoMove(Piece piece, ChessPiece capturedPiece, ChessPosition sourcePosition, ChessPosition targetPosition) {
		board.removePiece(targetPosition.toPosition());
		if (wasEnPassantChosen(piece, sourcePosition, targetPosition)) {
			undoEnPassant(piece, capturedPiece, sourcePosition, targetPosition);
		} else {
			board.placePiece(piece, sourcePosition.toPosition());
			if (piece instanceof King) {
				updateKingSourceReference(sourcePosition.toPosition());
			} 
			if (capturedPiece != null) {
				board.placePiece(capturedPiece, targetPosition.toPosition());
				if (currentPlayer == Color.WHITE) {
					blackPiecesOnTheBoard.add(capturedPiece);
				} else {
					whitePiecesOnTheBoard.add(capturedPiece);
				}
			}
		}
		((ChessPiece)piece).decreaseMoveCount();
	}
	
	private void updateKingTargetReference (Position target) {
		if (currentPlayer == Color.WHITE) {
			whiteKingReference = target;
		} else {
			blackKingReference = target;
		}
	}
	
	private void updateKingSourceReference(Position source) {
		if (currentPlayer == Color.WHITE) {
			whiteKingReference = source;
		} else {
			blackKingReference = source;
		}
	}
	
	private void testCheck(Piece piece, ChessPiece chessPiece, ChessPosition sourcePosition, ChessPosition targetPosition) {
		if (check()) {
			undoMove(piece, chessPiece, sourcePosition, targetPosition);
			throw new ChessException("You can not put yourself in check");
		}
	}
	
	private List<Position> getCommonPossibleMovesBetweenPieces(ChessPiece piece, ChessPiece opponentPiece) {
		//Possible moves for the piece to protect the king
		List<Position> possibleMovesForThePiece = new ArrayList<>();
		boolean[][] pieceMoves = piece.possibleMoves();
		boolean[][] opponentPieceMoves = opponentPiece.possibleMoves();
		if (piece.possibleMove(opponentPiece.getChessPosition().toPosition())) {
			possibleMovesForThePiece.add(opponentPiece.getChessPosition().toPosition());
		}
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				if (pieceMoves[i][j] == true && opponentPieceMoves[i][j] == true) {
					possibleMovesForThePiece.add(new Position(i, j));
				}
			}
		}
		return possibleMovesForThePiece;
	}
		
	public boolean checkMate() {
		//Is the King in check?
		if (check()) {
			boolean[][] movesOfKing;
			if (currentPlayer == Color.WHITE) {
				movesOfKing = getPossibleMoves(ChessPosition.fromPosition(whiteKingReference));
			} else {
				movesOfKing = getPossibleMoves(ChessPosition.fromPosition(blackKingReference));
			}
			
			List<Position> possibleMovesOfKing = new ArrayList<>();
			for (int i = 0; i < movesOfKing.length; i++) {
				for (int j = 0; j < movesOfKing[0].length; j++) {
					if (movesOfKing[i][j] == true) {
						if(currentPlayer == Color.WHITE && Math.abs(j - whiteKingReference.getColumn()) < 2) {
							possibleMovesOfKing.add(new Position(i, j));
						} else if (currentPlayer == Color.BLACK && Math.abs(j - blackKingReference.getColumn()) < 2) {
							possibleMovesOfKing.add(new Position(i, j));
						}
					}
				}
			}
			
			Position source;
			if (currentPlayer == Color.WHITE) {
				source = new Position(whiteKingReference.getRow(), whiteKingReference.getColumn());
			} else {
				source = new Position(blackKingReference.getRow(), blackKingReference.getColumn());
			}
			List<ChessPiece> opponentPiecesOnBoard = new ArrayList<>();
			if (currentPlayer == Color.WHITE) {
				opponentPiecesOnBoard = blackPiecesOnTheBoard;
			} else {
				opponentPiecesOnBoard = whitePiecesOnTheBoard;
			}
			List<ChessPiece> opponentPiecesThreateningTheKing = new ArrayList<>();
			
			//Is the King unable to move?
			for (ChessPiece opponentPiece : opponentPiecesOnBoard) {
				if(opponentPiece.possibleMove(source)) {
					opponentPiecesThreateningTheKing.add(opponentPiece);
				}
				for (Position position : possibleMovesOfKing) {
					Piece king = board.removePiece(source);
					ChessPiece capturedPiece = makeMove(king, ChessPosition.fromPosition(source), ChessPosition.fromPosition(position));
					if (!check()) {
						undoMove(king, capturedPiece, ChessPosition.fromPosition(source), ChessPosition.fromPosition(position));
						return false;
					}
					if(opponentPiece.possibleMove(position)) {
						opponentPiecesThreateningTheKing.add(opponentPiece);
					}
					undoMove(king, capturedPiece, ChessPosition.fromPosition(source), ChessPosition.fromPosition(position));
				}
			}
			
			List<ChessPiece> piecesOnBoard = new ArrayList<>();
			if (currentPlayer == Color.WHITE) {
				piecesOnBoard = whitePiecesOnTheBoard;
			} else {
				piecesOnBoard = blackPiecesOnTheBoard;
			}
			//Can the King be protected by other pieces of the same color?
			for (ChessPiece piece : piecesOnBoard) {
				ChessPiece capturedPiece = null;
				List<Position> possibleMovesForThePiece = new ArrayList<>();
				ChessPosition sourcePosition = new ChessPosition(piece.getChessPosition().getRow(), piece.getChessPosition().getColumn());
				if (piece instanceof King) {
					continue;
				} else {
					for (ChessPiece opponentPiece : opponentPiecesThreateningTheKing) {
						possibleMovesForThePiece = getCommonPossibleMovesBetweenPieces(piece, opponentPiece);
						for (Position target : possibleMovesForThePiece) {
							board.removePiece(sourcePosition.toPosition());
							capturedPiece = makeMove(piece, sourcePosition, ChessPosition.fromPosition(target));
							if (!check()) {
								undoMove(piece, capturedPiece, sourcePosition, ChessPosition.fromPosition(target));
								return false;
							}
							undoMove(piece, capturedPiece, sourcePosition, ChessPosition.fromPosition(target));
						}
						
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean stalemate() {
		if (check()) {
			return false;
		} else {
			List<ChessPiece> piecesOnBoard = new ArrayList<>();
			if (currentPlayer == Color.WHITE) {
				piecesOnBoard = whitePiecesOnTheBoard;
			} else {
				piecesOnBoard = blackPiecesOnTheBoard;
			}
			for (ChessPiece pieceOnBoard : piecesOnBoard) {
				boolean[][] possibleMovesOfPiece;
				possibleMovesOfPiece = pieceOnBoard.possibleMoves();
				Piece piece = null;
				ChessPiece capturedPiece = null;
				ChessPosition source = pieceOnBoard.getChessPosition();
				ChessPosition target = null;
				for (int i = 0; i < possibleMovesOfPiece.length; i++) {
					for (int j = 0; j < possibleMovesOfPiece[0].length; j++) {
						if (possibleMovesOfPiece[i][j] == true) {
							target = ChessPosition.fromPosition(new Position(i, j));
							piece = board.removePiece(source.toPosition());
							capturedPiece = makeMove(piece, source, target);
							if (!check()) {
								undoMove(piece, capturedPiece, source, target);
								return false;
							}
							undoMove(piece, capturedPiece, source, target);
						}
					}
				}
			}
			return true;
		}
	}
	
	public boolean hasPieceMoved(Piece piece) {
		if (((ChessPiece)piece).getMoveCount() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean wasCastlingChosen(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		Piece piece = board.piece(source);
		if (piece instanceof King) {
			if (Math.abs(target.getColumn() - source.getColumn()) == 2) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	private void performCastling(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		boolean typeOfCastling;
		Position position = new Position(source.getRow(), source.getColumn());
		Piece king;
		Piece rook;
		if (check()) {
			throw new ChessException("You can not perform castling if you are in check");
		} else {
			//typeOfCastling equals true if the left rook needs to be moved to perform castling, and 
			//equals false in case the right rook is involved
			if (target.getColumn() - source.getColumn() == -2) {
				typeOfCastling = true;
			} else {
				typeOfCastling = false;
			}
			if (typeOfCastling == true) {
				for (int i = 1; source.getColumn() - i >= target.getColumn(); i++) {
					position.setColumn(source.getColumn() - i);
					king = board.removePiece(source);
					makeMove(king, sourcePosition, ChessPosition.fromPosition(position));
					if (check()) {
						undoMove(king, null, sourcePosition, ChessPosition.fromPosition(position));
						throw new ChessException("You can not put yourself in check while castling");
					}
					undoMove(king, null, sourcePosition, ChessPosition.fromPosition(position));
				}
				king = board.removePiece(source);
				makeMove(king, sourcePosition, targetPosition);
				position.setColumn(0);
				rook = board.removePiece(position);
				position.setColumn(3);
				board.placePiece(rook, position);
				((ChessPiece)rook).increaseMoveCount();
			} else {
				for (int i = 1; source.getColumn() + i <= target.getColumn(); i++) {
					position.setColumn(source.getColumn() + i);
					king = board.removePiece(source);
					makeMove(king, sourcePosition, ChessPosition.fromPosition(position));
					if (check()) {
						undoMove(king, null, sourcePosition, ChessPosition.fromPosition(position));
						throw new ChessException("You can not put yourself in check while castling");
					}
					undoMove(king, null, sourcePosition, ChessPosition.fromPosition(position));
				}
				king = board.removePiece(source);
				makeMove(king, sourcePosition, targetPosition);
				position.setColumn(board.getColumns() - 1);
				rook = board.removePiece(position);
				position.setColumn(board.getColumns() - 3);
				board.placePiece(rook, position);
				((ChessPiece)rook).increaseMoveCount();
			}
			
		}
	}
	
	private void verifyEnPassantCondition(Piece piece, ChessPosition sourcePosition, ChessPosition targetPosition) {
		if (piece instanceof Pawn && Math.abs(targetPosition.toPosition().getRow() - sourcePosition.toPosition().getRow()) == 2) {
			enPassant = true;
			enPassantPawnPosition = targetPosition.toPosition();
			if (enPassantTurnCounter > 0) {
				enPassantTurnCounter = 0;
			}
		}
	}
	
	private boolean wasEnPassantChosen(Piece piece, ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		if (piece instanceof Pawn && Math.abs(target.getRow() - source.getRow()) == 1 && Math.abs(target.getColumn() - source.getColumn()) == 1 && !board.thereIsAPiece(target)) {
			return true;
		} else {
			return false;
		}
	}
	
	private ChessPiece performEnPassant(Piece pawn, ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		Position opponentPawnPosition = new Position(source.getRow(), target.getColumn());
		Piece opponentPawn = board.removePiece(opponentPawnPosition);
		board.placePiece(pawn, target);
		if (currentPlayer == Color.WHITE) {
			blackPiecesOnTheBoard.remove((ChessPiece)opponentPawn);
		} else {
			whitePiecesOnTheBoard.remove((ChessPiece)opponentPawn);
		}
		return (ChessPiece)opponentPawn;
	}
	
	private void undoEnPassant(Piece pawn, ChessPiece opponentPawn, ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		Position opponentPawnPosition = new Position(source.getRow(), target.getColumn());
		board.placePiece(pawn, source);
		board.placePiece(opponentPawn, opponentPawnPosition);
		if (currentPlayer == Color.WHITE) {
			blackPiecesOnTheBoard.add(opponentPawn);
		} else {
			whitePiecesOnTheBoard.add(opponentPawn);
		}
	}
	
	private void verifyPromotionStatus(Piece piece, ChessPosition targetPosition) {
		Position target = targetPosition.toPosition();
		if (piece instanceof Pawn && (target.getRow() == 0 || target.getRow() == board.getRows() - 1)) {
			promotionStatus = true;
			promotedPawnPosition = ((ChessPiece)piece).getChessPosition().toPosition();
		}
	}
}
