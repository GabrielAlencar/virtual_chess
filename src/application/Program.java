package application;

import java.util.Scanner;

import boardgame.Position;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Enter positions (row, column): ");
		int row = sc.nextInt();
		int column = sc.nextInt();
		
		Position pos = new Position(row, column);
		
		System.out.printf("%s%n", pos.toString());
		
		sc.close();
	}

}
