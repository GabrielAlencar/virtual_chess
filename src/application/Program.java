package application;

import java.util.Scanner;

import boardgame.BoardException;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		while(true) {
			try {
				UI.clearScreen();
				ChessPiece[][] chessPieces = chessMatch.getPieces();
				System.out.printf("Turn: %d%n", chessMatch.getTurn());
				System.out.printf("Current player: %s%n", chessMatch.getCurrentPlayer());
				UI.printCapturedPieces(chessMatch.getCapturedPieces(), chessMatch.getCurrentPlayer());
				System.out.println();
				UI.printBoard(chessPieces);
				if (chessMatch.check()) {
					if (chessMatch.checkMate()) {
						System.out.printf("%n%nCheckmate%n");
						if (chessMatch.getCurrentPlayer() == Color.WHITE) {
							System.out.printf("The %s player won", Color.BLACK.toString());
						} else {
							System.out.printf("The %s player won", Color.WHITE.toString());
						}
						break;
					} else {
						System.out.printf("%n%nYou are in check");
					}
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
				System.out.printf("%nPress ENTER to continue ");
				sc.nextLine();
			} catch (ChessException e) {
				System.out.printf("%s%n", e.getMessage());
				System.out.printf("%nPress ENTER to continue ");
				sc.nextLine();
			} catch (RuntimeException e) {
				System.out.printf("Unexpected error%n");
				System.out.printf("%nPress ENTER to continue ");
				sc.nextLine();
			} catch (Exception e) {
				System.out.printf("Unexpected error%n");
				System.out.printf("%nPress ENTER to continue ");
				sc.nextLine();
			}
		}
		
		sc.close();
	}

}
