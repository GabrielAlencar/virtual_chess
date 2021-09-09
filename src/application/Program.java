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
		char answer = 's';

		while(answer == 's') {
			try {
				UI.clearScreen();
				ChessPiece[][] chessPieces = chessMatch.getPieces();
				UI.printBoard(chessPieces);
				System.out.println("\n");
				System.out.printf("Digite a coordenada da peca que deve ser movida: ");
				String sourceCoordinate = sc.nextLine();
				ChessPosition source = UI.readChessPosition(sourceCoordinate);
				boolean[][] possibleMoves = chessMatch.getPossibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessPieces, possibleMoves);
				System.out.println("\n");
				System.out.printf("Coordenada da peca a ser movida: %s%n", sourceCoordinate);
				System.out.printf("Digite a coordenada alvo da peca a ser movida: ");
				String targetCoordinate = sc.nextLine();
				ChessPiece chessPiece = chessMatch.performChessMove(source, UI.readChessPosition(targetCoordinate));
				if (chessPiece != null) {
					System.out.printf("%nPeca capturada: %s%n", chessPiece.toString());
				}
			} catch (BoardException e) {
				System.out.printf("%s%n", e.getMessage());
			} catch (ChessException e) {
				System.out.printf("%s%n", e.getMessage());
			} catch (RuntimeException e) {
				System.out.printf("Erro inesperado%n");
			} catch (Exception e) {
				System.out.printf("Erro inesperado%n");
			} finally {
				System.out.printf("%nDeseja continaur jogando (s/n)? ");
				answer = sc.nextLine().charAt(0);
			}
		}
		
		sc.close();
	}

}
