package edu.pendu;

import java.util.Locale;

public class HangmanGame {
    private final String word;
    private final char[] state;
    private int attempts;

    public HangmanGame(String word, int attempts) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("word must not be null or empty");
        }
        if (attempts < 1) {
            throw new IllegalArgumentException("attempts must be >= 1");
        }
        this.word = word.toLowerCase(Locale.ROOT);
        this.attempts = attempts;
        this.state = new char[word.length()];
        for (int i = 0; i < state.length; i++) state[i] = '_';
    }

    /**
     * Make a guess. Letter is treated case-insensitively.
     * If the letter exists in the word, all matching positions are revealed and the method returns true.
     * Otherwise the remaining attempts are decreased and the method returns false.
     */
    public boolean guess(char letter) {
        if (isOver()) {
            throw new IllegalStateException("Game is already over");
        }
        char c = Character.toLowerCase(letter);
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                state[i] = c;
                found = true;
            }
        }
        if (!found) {
            attempts -= 1;
        }
        return found;
    }

    public String getCurrentState() {
        return new String(state);
    }

    public int getRemainingAttempts() {
        return attempts;
    }

    public boolean isWon() {
        for (char c : state) if (c == '_') return false;
        return true;
    }

    public boolean isOver() {
        return attempts <= 0 || isWon();
    }

    public String getWord() {
        return word;
    }
}
