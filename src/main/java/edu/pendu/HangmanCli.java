package edu.pendu;

import java.util.Scanner;

public class HangmanCli {
    public static void main(String[] args) {
        String[] mots = {"poisson", "chaise", "bureau"};
        String mot = mots[(int) (Math.random() * mots.length)];
        HangmanGame game = new HangmanGame(mot, 7);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenu dans le jeu du pendu !");
        System.out.println("Mot : " + spacedState(game.getCurrentState()));

        while (!game.isOver()) {
            System.out.print("Propose une lettre : ");
            String line = scanner.nextLine();
            if (line == null || line.isEmpty()) continue;
            char lettre = line.charAt(0);
            boolean ok = game.guess(lettre);
            if (ok) {
                System.out.println("Bien joué !");
            } else {
                System.out.println("Raté ! Il te reste " + game.getRemainingAttempts() + " essais.");
            }
            System.out.println("Mot : " + spacedState(game.getCurrentState()));
        }

        if (game.isWon()) {
            System.out.println("Bravo tu as trouvé le mot " + game.getWord());
        } else {
            System.out.println("Dommage le mot était : " + game.getWord());
        }
        scanner.close();
    }

    private static String spacedState(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
