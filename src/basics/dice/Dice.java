package basics.dice;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Dice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input number of players: ");
        int numberOfPlayers = scanner.nextInt();
        LinkedList<Player> players = new LinkedList<Player>();
        for(int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player(0, "Player №" + i));
        }

        players.add(new Player(0, "Computer")); // Add computer to players


        System.out.print("Input number of dices: ");
        int dices = scanner.nextInt();

        scanner.nextLine(); // read Enter from previous input

        int maxNumberOfWins = 0;

       while(maxNumberOfWins < 7) {
            int maxScore = 0;
            int maxScorePlayer = -1;
            for (int i = 0; i < players.size(); i++) {
                System.out.println("Throws " + players.get(i).getName());
                if (!players.get(i).getName().equals("Computer")) { // if player throws
                    System.out.print("Press Enter to throw...");
                    scanner.nextLine();
                }
                int score = getScore(dices);
                System.out.println("Dropped " + score);
                if(score > maxScore) {
                    maxScore = score;
                    maxScorePlayer = i;
                }
            }
            System.out.println("Round winner is " + players.get(maxScorePlayer).getName());

            Player temp = players.get(maxScorePlayer);
            temp.addScore(); // increase player's score
            if(temp.getScore() > maxNumberOfWins)
                maxNumberOfWins = temp.getScore();

            // move max score player to front
            players.remove(maxScorePlayer);
            players.offerFirst(temp);

        }

        System.out.println("********\nResults:");

        Collections.sort(players);
        for(int i = 1; i <= players.size(); i++) {
            System.out.println(i + ". " + players.get(i-1).getName() + " " + players.get(i-1).getScore());
        }

    }

    public static int getScore(int k) { // throw k dices
        int score = 0;
        for(int i = 0; i < k; i++) {
            score += ThreadLocalRandom.current().nextInt(1, 7);
        }
        return score;
    }

}


