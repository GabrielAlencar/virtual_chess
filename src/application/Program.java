package application;

import java.util.Scanner;

import boardgame.BoardException;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		char answer = 's';

		while(answer == 's') {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println("\n");
				System.out.printf("Digite a coordenada da peca que deve ser movida: ");
				String sourceCoordinate = sc.nextLine();
				System.out.printf("Digite a coordenada alvo da peca a ser movida: ");
				String targetCoordinate = sc.nextLine();
				ChessPiece chessPiece = chessMatch.performChessMove(UI.readChessPosition(sourceCoordinate), UI.readChessPosition(targetCoordinate));
				if (chessPiece != null) {
					System.out.printf("%nPeca capturada: %s%n", chessPiece.toString());
				}
			} catch (BoardException e) {
				System.out.printf("%s%n", e.getMessage());
				System.out.printf("Pressione ENTER para continuar");
				sc.nextLine();
			} catch (ChessException e) {
				System.out.printf("%s%n", e.getMessage());
				System.out.printf("Pressione ENTER para continuar");
				sc.nextLine();
			} catch (RuntimeException e) {
				System.out.printf("Erro inesperado%n");
				System.out.printf("Pressione ENTER para continuar");
				sc.nextLine();
			} catch (Exception e) {
				System.out.printf("Erro inesperado%n");
				System.out.printf("Pressione ENTER para continuar");
				sc.nextLine();
			} finally {
				System.out.printf("%nDeseja continaur jogando (s/n)? ");
				answer = sc.nextLine().charAt(0);
			}
		} 
		
		sc.close();
	}

}
