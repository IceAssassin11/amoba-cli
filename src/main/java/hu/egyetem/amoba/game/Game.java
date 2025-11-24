package hu.egyetem.amoba.game;

import java.util.Scanner;

import hu.egyetem.amoba.io.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private final Board board;
    private final Player human;
    private final Player ai;
    private final Scanner scanner;

    public Game(Board board, Player human, Player ai, Scanner scanner) {
        this.board = board;
        this.human = human;
        this.ai = ai;
        this.scanner = scanner;
    }

    public GameResult start() {
        int midRow = board.getRows() / 2;
        int midCol = board.getCols() / 2;
        board.placeMove(new Move(midRow, midCol), human);

        System.out.println("Üdvözöllek az Amőba játékban!");
        board.printBoard();

        while (true) {
            Move humanMove = null;
            do {
                humanMove = readUserMove(board.getCols(), board.getRows());
                if (!board.isValidMove(humanMove, human)) {
                    System.out.println("Érvénytelen lépés!");
                    humanMove = null;
                }
            } while (humanMove == null);

            board.placeMove(humanMove, human);
            board.printBoard();

            if (board.checkWin(human)) {
                System.out.println(human.getName() + " nyert!");
                return GameResult.HUMAN_WIN;
            }

            if (board.isFull()) {
                System.out.println("Nincs több üres mező - döntetlen!");
                return GameResult.DRAW;
            }

            Move aiMove = getRandomAIMove();
            if (aiMove == null) {
                System.out.println("Nincs több lépés - döntetlen!");
                return GameResult.DRAW;
            }

            System.out.println("A gép lép: "
                    + (char) ('a' + aiMove.getCol()) + (aiMove.getRow() + 1));
            board.placeMove(aiMove, ai);

            board.printBoard();

            if (board.checkWin(ai)) {
                System.out.println("A gép nyert!");
                return GameResult.AI_WIN;
            }

            if (board.isFull()) {
                System.out.println("Nincs több üres mező - döntetlen!");
                return GameResult.DRAW;
            }
        }
    }

    private final Random random = new Random();

    public Move getRandomAIMove() {
        List<Move> validMoves = new ArrayList<>();

        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Move move = new Move(r, c);
                if (board.isValidMove(move, ai)) {
                    validMoves.add(move);
                }
            }
        }

        if (validMoves.isEmpty())
            return null;

        return validMoves.get(random.nextInt(validMoves.size()));
    }

    private Move readUserMove(int cols, int rows) {
        while (true) {
            System.out.print("Add meg a lépést (pl. a1) vagy parancsot ('save', 'exit'): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("save")) {
                System.out.print("Add meg a mentés fájl nevét: ");
                String filename = scanner.nextLine();
                FileManager.saveBoardToFile(filename, board);
                System.out.println("Játék elmentve!");
                continue;
            }

            if (input.equals("exit")) {
                System.out.println("Kiléptél a játékból.");
                System.exit(0);
            }

            if (input.length() < 2) {
                System.out.println("Hibás formátum! Példa: a1");
                continue;
            }

            char colChar = input.charAt(0);
            String rowPart = input.substring(1);

            int col = colChar - 'a';

            int row;
            try {
                row = Integer.parseInt(rowPart) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Hibás formátum! Példa: a1");
                continue;
            }

            if (col < 0 || col >= cols || row < 0 || row >= rows) {
                System.out.println("A megadott mező kívül esik a táblán.");
                continue;
            }

            return new Move(row, col);
        }
    }
}