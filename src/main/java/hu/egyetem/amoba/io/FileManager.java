package hu.egyetem.amoba.io;

import java.io.*;
import hu.egyetem.amoba.game.Board;

public class FileManager {

    public static Board loadBoardFromFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String sizeLine = br.readLine();
            if (sizeLine == null) return null;

            String[] parts = sizeLine.split(" ");
            int rows = Integer.parseInt(parts[0]);
            int cols = Integer.parseInt(parts[1]);

            Board board = new Board(rows, cols);

            for (int r = 0; r < rows; r++) {
                String line = br.readLine();
                if (line == null) break;

                for (int c = 0; c < cols; c++) {
                    char ch = line.charAt(c);
                    if (ch == 'x' || ch == 'o' || ch == '.') {
                        board.setCell(r, c, ch);
                    }
                }
            }
            return board;

        } catch (IOException e) {
            System.out.println("Hiba a fájl beolvasásakor: " + e.getMessage());
            return null;
        }
    }

    public static void saveBoardToFile(String path, Board board) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(board.getRows() + " " + board.getCols());
            bw.newLine();

            for (int r = 0; r < board.getRows(); r++) {
                for (int c = 0; c < board.getCols(); c++) {
                    bw.write(board.getCell(r, c));
                }
                bw.newLine();
            }

            System.out.println("Tábla elmentve: " + path);

        } catch (IOException e) {
            System.out.println("Nem sikerült menteni: " + e.getMessage());
        }
    }
}
