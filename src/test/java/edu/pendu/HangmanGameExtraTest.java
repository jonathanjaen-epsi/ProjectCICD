package edu.pendu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanGameExtraTest {

    @Test
    public void testInitialStateUnderscores() {
        HangmanGame g = new HangmanGame("abc", 5);
        assertEquals("___", g.getCurrentState());
    }

    @Test
    public void testGuessSameLetterTwiceDoesNotConsumeAttempt() {
        HangmanGame g = new HangmanGame("banana", 5);
        int before = g.getRemainingAttempts();
        assertTrue(g.guess('b'));
        assertEquals(before, g.getRemainingAttempts());
        // guess 'b' again: still found, attempts should remain the same
        assertTrue(g.guess('b'));
        assertEquals(before, g.getRemainingAttempts());
    }

    @Test
    public void testGuessAfterWinThrows() {
        HangmanGame g = new HangmanGame("x", 3);
        assertTrue(g.guess('x'));
        assertTrue(g.isWon());
        assertThrows(IllegalStateException.class, () -> g.guess('y'));
    }

    @Test
    public void testLoseThenGuessThrows() {
        HangmanGame g = new HangmanGame("z", 1);
        assertFalse(g.guess('a'));
        assertTrue(g.isOver());
        assertThrows(IllegalStateException.class, () -> g.guess('z'));
    }

    @Test
    public void testNonAlphabeticCharacterGuess() {
        HangmanGame g = new HangmanGame("a-b", 2);
        // guess '-' should reveal the middle char
        assertTrue(g.guess('-'));
        assertEquals("_-_", g.getCurrentState());
    }
}
