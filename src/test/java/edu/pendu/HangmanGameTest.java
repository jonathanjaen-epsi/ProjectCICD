package edu.pendu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanGameTest {

    @Test
    public void testCorrectGuessRevealsLetters() {
        HangmanGame g = new HangmanGame("poisson", 7);
        boolean ok = g.guess('o');
        assertTrue(ok);
        // positions 1 and 4 are 'o' in "poisson"
        assertEquals("_o___o_", g.getCurrentState());
        assertEquals(7, g.getRemainingAttempts());
        assertFalse(g.isWon());
    }

    @Test
    public void testWrongGuessReducesAttempts() {
        HangmanGame g = new HangmanGame("chaise", 3);
        boolean ok = g.guess('z');
        assertFalse(ok);
        assertEquals(2, g.getRemainingAttempts());
        assertFalse(g.isOver());
    }

    @Test
    public void testWinFlow() {
        HangmanGame g = new HangmanGame("aa", 2);
        assertFalse(g.isWon());
        assertTrue(g.guess('a'));
        assertTrue(g.isWon());
        assertTrue(g.isOver());
    }

    @Test
    public void testLoseFlow() {
        HangmanGame g = new HangmanGame("b", 2);
        assertFalse(g.isWon());
        assertFalse(g.guess('x'));
        assertFalse(g.isWon());
        assertFalse(g.isOver());
        assertFalse(g.guess('y'));
        assertTrue(g.isOver());
        assertFalse(g.isWon());
    }

    @Test
    public void testInvalidConstructorArgs() {
        assertThrows(IllegalArgumentException.class, () -> new HangmanGame("", 3));
        assertThrows(IllegalArgumentException.class, () -> new HangmanGame("a", 0));
    }

    @Test
    public void testCaseInsensitivity() {
        HangmanGame g = new HangmanGame("Bureau", 3);
        assertTrue(g.guess('b'));
        // uppercase guess should also work
        HangmanGame g2 = new HangmanGame("Bureau", 3);
        assertTrue(g2.guess('B'));
    }
}
