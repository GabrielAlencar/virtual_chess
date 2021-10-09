package application;

import java.security.InvalidParameterException;
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
		ChessPiece[][] chessPieces = chessMatch.getPieces();
		UI.clearScreen();
		UI.initialScreen(sc, chessPieces);
		
		while(true) {
			try {
				UI.clearScreen();
				chessPieces = chessMatch.getPieces();
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
				} else if (chessMatch.stalemate()) {
					System.out.printf("%n%nStalemate: the %s pieces have no legal move%n", chessMatch.getCurrentPlayer().toString());
					System.out.printf("The match ended in a draw");
					break;
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
				if (targetCoordinate.equals(sourceCoordinate)) {
					continue;
				}
				chessMatch.capturePiece(chessMatch.performChessMove(source, UI.readChessPosition(targetCoordinate)));
				if (chessMatch.wasPromotionMade()) {
					chessPieces = chessMatch.getPieces();
					UI.clearScreen();
					System.out.printf("Turn: %d%n", chessMatch.getTurn());
					System.out.printf("Current player: %s%n", chessMatch.getCurrentPlayer());
					UI.printCapturedPieces(chessMatch.getCapturedPieces(), chessMatch.getCurrentPlayer());
					System.out.println();
					UI.printBoard(chessPieces);
					System.out.println("\n");
					System.out.printf("The pawn at coordinate %s has been promoted%n", chessMatch.getPromotedPawnPosition().toString());
					System.out.printf("Choose one of the following pieces to substitute it: N, B, R, Q%n");
					char answer = ' ';
					while(answer != 'N' && answer != 'B' && answer != 'R' && answer != 'Q') {
						try {
							answer = sc.nextLine().charAt(0);
							UI.verifyPromotionAnswer(answer);
						} catch (InvalidParameterException e) {
							System.out.printf("%s%n", e.getMessage());
							System.out.printf("%nPress ENTER to continue ");
							sc.nextLine();
							UI.clearScreen();
							System.out.printf("Turn: %d%n", chessMatch.getTurn());
							System.out.printf("Current player: %s%n", chessMatch.getCurrentPlayer());
							UI.printCapturedPieces(chessMatch.getCapturedPieces(), chessMatch.getCurrentPlayer());
							System.out.println();
							UI.printBoard(chessPieces);
							System.out.println("\n");
							System.out.printf("The pawn at coordinate %s has been promoted%n", chessMatch.getPromotedPawnPosition().toString());
							System.out.printf("Choose one of the following pieces to substitute it: N, B, R, Q%n");	
						}
					}
					chessMatch.performPromotion(UI.getChosenPromotedPiece(answer));
				}
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
