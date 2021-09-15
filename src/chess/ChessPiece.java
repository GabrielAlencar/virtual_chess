package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece{

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);	
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Position position) {
		if (!getBoard().positionExists(position)) {
			throw new ChessException("The position " + position + " does not exist on board");
		}
		Piece piece = getBoard().piece(position);
		if(piece == null) {
			return false;
		} else if (((ChessPiece)piece).color != this.color) {
			return true;
		} else {
			return false;
		}
	}

}

