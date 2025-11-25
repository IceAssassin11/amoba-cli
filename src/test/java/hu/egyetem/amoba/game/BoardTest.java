package hu.egyetem.amoba.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testPlaceMoveValid() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');

        boolean result = board.placeMove(new Move(2, 2), p);

        assertTrue(result);
        assertEquals('x', board.getCell(2, 2));
    }

    @Test
    void testPlaceMoveAlreadyTaken() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');
        board.placeMove(new Move(2, 2), p);

        boolean result = board.placeMove(new Move(2, 2), p);
        assertFalse(result);
    }

    @Test
    void testIsValidMoveFirstMoveAnywhere() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');

        assertTrue(board.isValidMove(new Move(4, 4), p));
        assertTrue(board.isValidMove(new Move(0, 0), p));
    }

    @Test
    void testIsValidMoveMustTouch() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');

        board.placeMove(new Move(2, 2), p);

        assertTrue(board.isValidMove(new Move(2, 3), p));
        assertFalse(board.isValidMove(new Move(0, 0), p));
    }

    @Test
    void testCheckWinHorizontal() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');

        for (int c = 0; c < 5; c++)
            board.setCell(0, c, 'x');

        assertTrue(board.checkWin(p));
    }

    @Test
    void testCheckWinVertical() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');

        for (int r = 0; r < 5; r++)
            board.setCell(r, 0, 'x');

        assertTrue(board.checkWin(p));
    }

    @Test
    void testCheckWinDiagonal() {
        Board board = new Board(5, 5);
        Player p = new Player("P", 'x');

        for (int i = 0; i < 5; i++)
            board.setCell(i, i, 'x');

        assertTrue(board.checkWin(p));
    }

    @Test
    void testIsFull() {
        Board board = new Board(4, 4);

        for (int r = 0; r < 4; r++)
            for (int c = 0; c < 4; c++)
                board.setCell(r, c, 'x');

        assertTrue(board.isFull());
    }

    @Test
    void testNotFull() {
        Board board = new Board(4, 4);
        assertFalse(board.isFull());
    }
}