package hu.egyetem.amoba.io;

import hu.egyetem.amoba.game.Board;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    @Test
    void testSaveAndLoadBoard() {
        Board board = new Board(4, 4);
        board.setCell(0, 0, 'x');
        board.setCell(1, 1, 'o');

        String path = "test_board.txt";
        FileManager.saveBoardToFile(path, board);

        File file = new File(path);
        assertTrue(file.exists());

        Board loaded = FileManager.loadBoardFromFile(path);

        assertNotNull(loaded);
        assertEquals(4, loaded.getRows());
        assertEquals(4, loaded.getCols());
        assertEquals('x', loaded.getCell(0, 0));
        assertEquals('o', loaded.getCell(1, 1));

        file.delete();
    }

    @Test
    void testLoadNonexistentFile() {
        Board board = FileManager.loadBoardFromFile("does_not_exist.xyz");
        assertNull(board);
    }
}