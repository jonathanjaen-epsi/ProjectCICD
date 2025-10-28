package edu.pendu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class HangmanCliTest {

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    @AfterEach
    public void restoreIo() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        System.clearProperty("hangman.word");
    }

    @Test
    public void testCliWinPath() {
        // Word is 'a' so guessing 'a' wins immediately
        String input = "a\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

        // Force the chosen word
        System.setProperty("hangman.word", "a");
        HangmanCli.main(new String[0]);

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Bienvenu dans le jeu du pendu !"));
        assertTrue(output.contains("Bien joué !"));
        assertTrue(output.contains("Bravo tu as trouvé le mot a"));
    }

    @Test
    public void testCliLosePath() {
        // Word is 'b', provide wrong guesses until attempts exhausted
        StringBuilder sb = new StringBuilder();
        // 7 wrong guesses (single letters not 'b')
        for (int i = 0; i < 7; i++) sb.append((char) ('x' + i)).append('\n');
        ByteArrayInputStream in = new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out, true, StandardCharsets.UTF_8));

        System.setProperty("hangman.word", "b");
        HangmanCli.main(new String[0]);

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Bienvenu dans le jeu du pendu !"));
        assertTrue(output.contains("Dommage le mot était : b"));
    }
}
