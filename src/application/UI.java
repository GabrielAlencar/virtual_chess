package application;

import boardgame.Piece;
import chess.ChessPiece;
import chess.Color;

public class UI {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static void printBoard(ChessPiece[][] chessPieces) {
		for (int i = 0; i < chessPieces.length; i++) {
			System.out.printf("%d ", chessPieces.length - i);
			for (int j = 0; j < chessPieces[0].length; j++) {
				printPiece(chessPieces[i][j]);
				System.out.printf(" ");
			}
			System.out.println();
		}
		
		System.out.printf("  ");
		char letter = 'a';
		for (int i = 0; i < chessPieces.length; i++) {
			System.out.printf("%c ", letter);
			letter++;
		}
	}
	
	private static void printPiece(Piece piece) {
		if (piece == null) {
			System.out.printf("-");
		} else {
			if (((ChessPiece)piece).getColor() == Color.WHITE) {
				System.out.printf("%s%s%s", ANSI_WHITE, piece.toString(), ANSI_RESET);
			} else {
				System.out.printf("%s%s%s", ANSI_YELLOW, piece.toString(), ANSI_RESET);
			}
			//System.out.printf("%s", piece.toString());
		}
	}
}
