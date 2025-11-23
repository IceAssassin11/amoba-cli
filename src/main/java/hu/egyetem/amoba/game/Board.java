package hu.egyetem.amoba.game;

import java.util.Arrays;

public class Board {
    private final int rows;
    private final int cols;
    private final char[][] cells;

    private static final char EMPTY = '.';

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new char[rows][cols];
        for (char[] row : cells) {
            Arrays.fill(row, EMPTY);
        }
    }

    public boolean placeMove(Move move, Player player) {
        int r = move.getRow();
        int c = move.getCol();

        if (r < 0 || r >= rows || c < 0 || c >= cols)
            return false;
        if (cells[r][c] != EMPTY)
            return false;

        cells[r][c] = player.getSymbol();
        return true;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int c = 0; c < cols; c++) {
            System.out.print((char) ('a' + c) + " ");
        }
        System.out.println();

        for (int r = 0; r < rows; r++) {
            System.out.print((r + 1) + " ");
            for (int c = 0; c < cols; c++) {
                System.out.print(cells[r][c] + " ");
            }
            System.out.println();
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isValidMove(Move move, Player player) {
        int r = move.getRow();
        int c = move.getCol();

        if (cells[r][c] != EMPTY)
            return false;

        boolean hasAnyMove = false;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (cells[i][j] != EMPTY) {
                    hasAnyMove = true;
                    break;
                }
            }
            if (hasAnyMove)
                break;
        }

        if (!hasAnyMove)
            return true;

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0)
                    continue;
                int nr = r + dr;
                int nc = c + dc;
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    if (cells[nr][nc] != EMPTY) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}