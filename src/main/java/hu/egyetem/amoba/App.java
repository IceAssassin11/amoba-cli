package hu.egyetem.amoba;

import hu.egyetem.amoba.game.Game;
import hu.egyetem.amoba.game.GameResult;
import hu.egyetem.amoba.game.Move;
import hu.egyetem.amoba.game.Player;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Game game = new Game(5, 5);
        GameResult result = game.start();

        System.out.println("Játék vége: " + result);
    }

    public static Move readUserMove(int cols, int rows) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Add meg a lépést (pl. b4): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() < 2) {
                System.out.println("Hibás formátum!");
                continue;
            }

            char colChar = input.charAt(0);
            int col = colChar - 'a';
            String rowStr = input.substring(1);
            int row;

            try {
                row = Integer.parseInt(rowStr) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Sor szám nem értelmezhető!");
                continue;
            }

            if (col < 0 || col >= cols || row < 0 || row >= rows) {
                System.out.println("A lépés kívül esik a táblán!");
                continue;
            }

            return new Move(row, col);
        }
    }
}