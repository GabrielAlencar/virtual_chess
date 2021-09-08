package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		ChessMatch chessMatch = new ChessMatch();
		UI.printBoard(chessMatch.getPieces());
		System.out.println("\n");
		System.out.printf("Digite a coordenada da peca que deve ser movida: ");
		String sourceCoordinate = sc.nextLine();
		System.out.printf("Digite a coordenada da peca que deve ser movida: ");
		String targetCoordinate = sc.nextLine();
		ChessPiece chessPiece = chessMatch.performChessMove(UI.readChessPosition(sourceCoordinate), UI.readChessPosition(targetCoordinate));
		System.out.println();
		UI.printBoard(chessMatch.getPieces());
		if (chessPiece != null) {
			System.out.printf("%n%nPeca capturada: %s%n", chessPiece.toString());
		}
		
		sc.close();
	}

}
