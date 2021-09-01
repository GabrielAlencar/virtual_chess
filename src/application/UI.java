package application;

import boardgame.Piece;
import chess.ChessPiece;

public class UI {
	
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
			System.out.printf("%s", piece.toString());
		}
	}
}
