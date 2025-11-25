package hu.egyetem.amoba.game;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    void testPlayerFields() {
        Player p = new Player("John", 'x');
        assertEquals("John", p.getName());
        assertEquals('x', p.getSymbol());
    }
}