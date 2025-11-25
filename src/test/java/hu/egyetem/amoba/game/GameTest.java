package hu.egyetem.amoba.game;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    void testGetRandomAIMoveReturnsValidMove() {
        Board board = new Board(5, 5);
        Player human = new Player("H", 'x');
        Player ai = new Player("AI", 'o');

        Scanner scanner = mock(Scanner.class);
        Game game = new Game(board, human, ai, scanner);

        board.setCell(2, 2, 'x');

        Move aiMove = game.getRandomAIMove();
        assertNotNull(aiMove);
        assertTrue(board.isValidMove(aiMove, ai));
    }

    @Test
    void testGetRandomAIMoveNoMovesAvailable() {
        Board board = new Board(3, 3);
        Player human = new Player("H", 'x');
        Player ai = new Player("AI", 'o');

        Scanner scanner = mock(Scanner.class);
        Game game = new Game(board, human, ai, scanner);

        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                board.setCell(r, c, 'x');

        assertNull(game.getRandomAIMove());
    }

    @Test
    void testReadUserMoveParsesCorrectly() {
        Scanner scanner = mock(Scanner.class);

        when(scanner.nextLine()).thenReturn("b3");

        Board board = new Board(5, 5);
        Player human = new Player("H", 'x');
        Player ai = new Player("AI", 'o');

        Game game = new Game(board, human, ai, scanner);

        Move mv = invokeReadUserMove(game, 5, 5);

        assertNotNull(mv);
        assertEquals(2, mv.getRow());
        assertEquals(1, mv.getCol());
    }

    private Move invokeReadUserMove(Game game, int cols, int rows) {
        try {
            var m = Game.class.getDeclaredMethod("readUserMove", int.class, int.class);
            m.setAccessible(true);
            return (Move) m.invoke(game, cols, rows);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}