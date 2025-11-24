package hu.egyetem.amoba;

import hu.egyetem.amoba.game.Board;
import hu.egyetem.amoba.game.Game;
import hu.egyetem.amoba.game.GameResult;
import hu.egyetem.amoba.game.Player;
import hu.egyetem.amoba.io.FileManager;

import java.util.Scanner;

public class App {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = showMenuAndGetBoard();
        if (board == null) {
            System.out.println("Program vége.");
            scanner.close();
            return;
        }

        System.out.print("Add meg a neved: ");
        String playerName = scanner.nextLine().trim();
        if (playerName.isEmpty()) {
            playerName = "Plélyer";
        }

        Player human = new Player(playerName, 'x');
        Player ai = new Player("Gép", 'o');

        Game game = new Game(board, human, ai, scanner);
        GameResult result = game.start();

        System.out.println("Játék vége: " + result);
        scanner.close();
    }

    private static Board showMenuAndGetBoard() {
        while (true) {
            System.out.println("Válassz:");
            System.out.println("1 - Pálya betöltése fájlból");
            System.out.println("2 - Üres pályával indulás");
            System.out.println("3 - Kilépés");
            System.out.print("Választás: ");

            String choice = scanner.nextLine().trim();

            if ("1".equals(choice)) {
                System.out.print("Add meg a fájl nevét: ");
                String file = scanner.nextLine().trim();
                Board board = FileManager.loadBoardFromFile(file);
                if (board == null) {
                    System.out.println("Nincs meg az input fájl. Indíts üres táblát!");
                }
                return board;
            } else if ("2".equals(choice)) {
                int rows = 0;
                int cols = 0;

                while (true) {
                    System.out.print("Add meg a sorok számát (N, 4-25): ");
                    String inputN = scanner.nextLine().trim();
                    try {
                        rows = Integer.parseInt(inputN);
                        if (rows < 4 || rows > 25) {
                            System.out.println("N legyen 4 és 25 között.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Nem számot adtál meg, próbáld újra.");
                        continue;
                    }

                    System.out.print("Add meg az oszlopok számát (M, 4-25, <= N): ");
                    String inputM = scanner.nextLine().trim();
                    try {
                        cols = Integer.parseInt(inputM);
                        if (cols < 4 || cols > 25) {
                            System.out.println("M legyen 4 és 25 között.");
                            continue;
                        }
                        if (cols > rows) {
                            System.out.println("M nem lehet nagyobb, mint N.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Nem számot adtál meg, próbáld újra.");
                        continue;
                    }

                    break;
                }

                return new Board(rows, cols);
            } else if ("3".equals(choice)) {
                System.out.println("Kiléptél a játékból.");
                System.exit(0);
            } else {
                System.out.println("Érvénytelen választás, próbáld újra.");
            }
        }
    }

}