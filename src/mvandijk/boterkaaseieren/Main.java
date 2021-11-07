package mvandijk.boterkaaseieren;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

@SuppressWarnings("resource")

public class Main {

    public static void main(String[] args) throws IOException {
	    // write your code here
        printWelcome();

        while (true) {
            switch (mainMenu()) {
                case 1:
                    System.out.println("\nNew Game\n---------\n");

                    GameLogic newGame = new GameLogic();

                    newGame.gameFlow();
                    
                    break;
                case 2:
                    System.out.println("Tweede keuze");

                    break;
                case 0:
                    System.exit(0);
            }
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
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].equals("3") || fields[i].equals("6")) {
                    System.out.print(" " + fields[i] + " \n" + "----------\n");
                }
                else if (fields[i].equals("9")) {
                    System.out.print(" " + fields[i] + " \n");
                }
                else {
                    System.out.print(" " + fields[i] + " |");
                }
            }
        }

        public void updateBoard(int field, String token) {
            this.fields[field - 1] = token;
        }
    }

    private static class Player {
        private String playerName;
        private String playerToken;
        private boolean isPlayerTurn;
        private boolean hasWon;

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
            this.isPlayerTurn = playerTurn;
        }

        public boolean getHasWon() {
            return this.hasWon;
        }
        public void setHasWon (boolean hasWon) {
            this.hasWon = hasWon;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getPlayerToken() {
            return playerToken;
        }

    }

    private static class GameLogic {
        public void gameFlow() {
            Board speelbord = new Board();
            Player playerO = new Player();
            Player playerX = new Player();
            playerO.setPlayerTurn(true);
            playerX.setPlayerTurn(false);

            speelbord.printBoard();

            while (true) {
                if (playerO.getHasWon()) {
                    System.out.println("Game Over, player " + playerO.getPlayerName() + "has won!");
                    break;
                } else if (playerX.getHasWon()) { 
                    System.out.println("Game Over, player " + playerX.getPlayerName() + "has won!");
                    break;
                } else {
                    if (playerO.isPlayerTurn()) { 
                        //TODO: Print game info to screen
                        speelbord.updateBoard(queryPlayerMove(), playerO.getPlayerToken());
        
                        playerO.setHasWon(checkWinCondition(playerO.getPlayerToken(), speelbord));
        
                    } else if (playerX.isPlayerTurn()) { 
                        //TODO: Print game info to screen
                        speelbord.updateBoard(queryPlayerMove(), playerX.getPlayerToken());
        
                        playerO.setHasWon(checkWinCondition(playerX.getPlayerToken(), speelbord));
                    }
                }
            }
        }

        public Boolean checkWinCondition (String token, Board bord) {

            boolean horizontalWin = 
                (bord.fields[0].equals(bord.fields[1]) && bord.fields[1].equals(bord.fields[2])) ||
                (bord.fields[3].equals(bord.fields[4]) && bord.fields[4].equals(bord.fields[5])) ||
                (bord.fields[6].equals(bord.fields[7]) && bord.fields[7].equals(bord.fields[8]));
                
            boolean verticalWin = 
                (bord.fields[0].equals(bord.fields[3]) && bord.fields[3].equals(bord.fields[6])) ||
                (bord.fields[1].equals(bord.fields[4]) && bord.fields[4].equals(bord.fields[7])) ||
                (bord.fields[2].equals(bord.fields[5]) && bord.fields[5].equals(bord.fields[8]));

            boolean diagonalWin = 
                (bord.fields[0].equals(bord.fields[4]) && bord.fields[4].equals(bord.fields[8])) ||
                (bord.fields[2].equals(bord.fields[4]) && bord.fields[4].equals(bord.fields[6])); 

                if (horizontalWin || verticalWin || diagonalWin) {
                    return true;
                } else {
                    return false;
                }
        }
        
        public int queryPlayerMove() {
            int playerMoveChoice;
            Scanner playerMoveInput = new Scanner(System.in);
            
            do {
                System.out.println("Choose a number between 1 and 9");
                playerMoveChoice = playerMoveInput.nextInt();
            } while (playerMoveChoice < 1 && playerMoveChoice > 9);

            return playerMoveChoice;
        }
    }

    /*
    --------------------------------------
        Methods (classless)
    --------------------------------------
    */
    private static void printWelcome() throws IOException {
        Path path = Path.of("src/mvandijk/boterkaaseieren/bestanden/Welcome_Logo.txt");
        String welcomeBanner = new String(Files.readAllBytes(path));
        System.out.println(welcomeBanner);
    }

    private static int mainMenu() {
        Scanner menuInput = new Scanner(System.in);

        System.out.print("1: New Game\n" + "2: Credits\n" + "0: Exit\n\n" + "What do you want to do? [1/2/0]: ");

        int choice = Integer.parseInt(menuInput.nextLine());

        return choice;
        }
}
