import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final Scanner myScan = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("1 = very dumb, 9 = very smart");
        System.out.print("select ai assistance level for you (1-9): ");
        int userLvL = myScan.nextInt();
        System.out.print("Select ai assistance level of computer (1-9): ");
        int compLvl = myScan.nextInt();

        // Create a new board game
        Board newGame = new Board();
        System.out.println("New game created!");

        while (true) {
            newGame.printBoard();

            if (checkGameOver(newGame)) {
                break;
            }

            // Player X's turn
            playerMove(newGame, "X", userLvL);
            newGame.createPossibleChildBoards();

            newGame.printBoard();

            if (checkGameOver(newGame)) {
                break;
            }

            // Player O's (AI) turn
            aiMove(newGame, "O", compLvl);
            newGame.createPossibleChildBoards();
        }

        myScan.close();
    }

    private static boolean checkGameOver(Board game) {
        if (game.isXWinner()) {
            System.out.println("Player X wins!");
            return true;
        } else if (game.isOWinner()) {
            System.out.println("Player O wins!");
            return true;
        } else if (!game.hasEmptyCells()) {
            System.out.println("It's a draw!");
            return true;
        }
        return false;
    }

    private static void playerMove(Board game, String player, int aiLvl) {
        while (true) {
            int bestMove = MinMax.findBestMove(game,true, aiLvl);
            int rowSuggestion = bestMove / 3;
            int colSuggestion = bestMove % 3;
            System.out.println("AI suggests: " + rowSuggestion + " " + colSuggestion);

            System.out.print("Player " + player + ", enter row number: ");
            int row = myScan.nextInt();
            System.out.print("enter col number: ");
            int col = myScan.nextInt();

            if (game.isValidMove(row, col)) {
                game.placeMark(player, row, col);
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static void aiMove(Board game, String player, int aiLvl) {
        int bestMove = MinMax.findBestMove(game, false, aiLvl);
        int rowO = bestMove / 3;
        int colO = bestMove % 3;
        if(game.isValidMove(rowO, colO))  game.placeMark(player, rowO, colO);
            else  System.out.println("Invalid move. Computer try again.");
        System.out.println("Player " + player + " chose row " + rowO + " and column " + colO + ".");
    }
}