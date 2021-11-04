package mvandijk.boterkaaseieren;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    // write your code here
        printWelcome();

        switch (mainMenu()) {
            case 1:
                System.out.println("Eerste keuze");
                break;
            case 2:
                System.out.println("Tweede keuze");
                break;
            case 0:
                System.exit(0);
        }
    }

    /*
    --------------------------------------
        Classes
    --------------------------------------
    */
    private static class Board {
        private String[] fields = new String[]{"1","2","3","4","5","6","7","8","9"};

        public void printBoard() {
            for (String field: fields) {
                if (field.equals("2") || field.equals("5")) {
                    //TODO add print command 1
                }
                else if (field.equals("9")) {
                    //TODO add print command 2
                }
                else {
                    //TODO add print command 3
                }
            }
        }

        public void updateBoard(int field, String token) {
            this.fields[field - 1] = token;
        }
    }

    private class Player {
        private String playerName;
        private String playerToken;
        private boolean isPlayerTurn;

        public Player() {
            Scanner inputPrompt = new Scanner(System.in);

            System.out.println("Kies een naam: ");
            playerName = inputPrompt.nextLine();

            System.out.println("Kies een teken om mee te spelen: ");
            playerToken = inputPrompt.nextLine();
        }

        public boolean isPlayerTurn() {
            return isPlayerTurn;
        }

        public void setPlayerTurn(boolean playerTurn) {
            isPlayerTurn = playerTurn;
        }

    }

    private class GameLogic {
        //TODO implement Game logic
    }

    /*
    --------------------------------------
        Methods (classless)
    --------------------------------------
    */
    private static void printWelcome() throws IOException {
        Path path = Path.of("bestanden/Welcome_Logo.txt");
        String welcomeBanner = new String(Files.readAllBytes(path));
        System.out.println(welcomeBanner);
    }

    private static int mainMenu() {
        Scanner menuInput = new Scanner(System.in);

        System.out.println("1: New Game\n" + "2: Credits\n" + "0: Exit\n\n" + "What do you want to do? [1/2/0]");

        int choice = Integer.parseInt(menuInput.nextLine());

        return choice;
        }
    }
