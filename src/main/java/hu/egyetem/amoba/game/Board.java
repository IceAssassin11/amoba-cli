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

    public boolean checkWin(Player player) {
        char sym = player.getSymbol();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (cells[r][c] == sym &&
                        cells[r][c + 1] == sym &&
                        cells[r][c + 2] == sym &&
                        cells[r][c + 3] == sym &&
                        cells[r][c + 4] == sym)
                    return true;
            }
        }

        for (int c = 0; c < cols; c++) {
            for (int r = 0; r <= rows - 5; r++) {
                if (cells[r][c] == sym &&
                        cells[r + 1][c] == sym &&
                        cells[r + 2][c] == sym &&
                        cells[r + 3][c] == sym &&
                        cells[r + 4][c] == sym)
                    return true;
            }
        }

        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (cells[r][c] == sym &&
                        cells[r + 1][c + 1] == sym &&
                        cells[r + 2][c + 2] == sym &&
                        cells[r + 3][c + 3] == sym &&
                        cells[r + 4][c + 4] == sym)
                    return true;
            }
        }

        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 4; c < cols; c++) {
                if (cells[r][c] == sym &&
                        cells[r + 1][c - 1] == sym &&
                        cells[r + 2][c - 2] == sym &&
                        cells[r + 3][c - 3] == sym &&
                        cells[r + 4][c - 4] == sym)
                    return true;
            }
        }

        return false;
    }

    public boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (cells[r][c] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public char getCell(int r, int c) {
        return cells[r][c];
    }

    public void setCell(int r, int c, char ch) {
        cells[r][c] = ch;
    }
}