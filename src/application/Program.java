package application;

import java.util.Scanner;

import boardgame.BoardException;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		char answer = 'y';

		while(answer == 'y') {
			try {
				UI.clearScreen();
				ChessPiece[][] chessPieces = chessMatch.getPieces();
				System.out.printf("Turn: %d%n", chessMatch.getTurn());
				System.out.printf("Current player: %s%n", chessMatch.getCurrentPlayer());
				UI.printCapturedPieces(chessMatch.getCapturedPieces(), chessMatch.getCurrentPlayer());
				System.out.println();
				UI.printBoard(chessPieces);
				if (chessMatch.check()) {
					System.out.printf("%n%nYou are in check");
				}
				System.out.println("\n");
				System.out.printf("Source position: ");
				String sourceCoordinate = sc.nextLine();
				ChessPosition source = UI.readChessPosition(sourceCoordinate);
				boolean[][] possibleMoves = chessMatch.getPossibleMoves(source);
				UI.clearScreen();
				System.out.printf("Turn: %d%n", chessMatch.getTurn());
				System.out.printf("Current player: %s%n", chessMatch.getCurrentPlayer());
				UI.printCapturedPieces(chessMatch.getCapturedPieces(), chessMatch.getCurrentPlayer());
				System.out.println();
				UI.printBoard(chessPieces, possibleMoves);
				if (chessMatch.check()) {
					System.out.printf("%n%nYou are in check");
				}
				System.out.println("\n");
				System.out.printf("Source position: %s%n", sourceCoordinate);
				System.out.printf("Target position: ");
				String targetCoordinate = sc.nextLine();
				chessMatch.capturePiece(chessMatch.performChessMove(source, UI.readChessPosition(targetCoordinate)));
			} catch (BoardException e) {
				System.out.printf("%s%n", e.getMessage());
			} catch (ChessException e) {
				System.out.printf("%s%n", e.getMessage());
			} catch (RuntimeException e) {
				System.out.printf("Unexpected error%n");
			} catch (Exception e) {
				System.out.printf("Unexpected error%n");
			} finally {
				System.out.printf("%nDo you wish to keep playing (y/n)? ");
				answer = sc.nextLine().charAt(0);
			}
		}
		
		sc.close();
	}

}
