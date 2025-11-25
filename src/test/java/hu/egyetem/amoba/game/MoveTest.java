package hu.egyetem.amoba.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    void testMoveGetter() {
        Move m = new Move(3, 4);
        assertEquals(3, m.getRow());
        assertEquals(4, m.getCol());
    }
}