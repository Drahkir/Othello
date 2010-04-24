/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;
import java.util.*;
/**
 *
 * @author Rikh Mikac
 */

public class Main {
    //private Board board = new Board();

    public static void main(String[] args) {
        boolean play_new_game = true;
        boolean continue_current_game = true;

        while(play_new_game) {
            boolean human_one = false;
            boolean human_two = false;

            System.out.println("Welcome... TO OTHELLO!");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Human for Player One?");
            String answer_one = scanner.nextLine();

            if(Game.isYes(answer_one))
                human_one  = true;

            System.out.println("Human for Player Two?");
            String answer_two = scanner.nextLine();

            if(Game.isYes(answer_two))
                human_two  = true;
            
            Board board = new Board();
            Game game = new Game(board, human_one, human_two); //true for human player, false for CPU
            int row = 0;
            int col = 0;

            while(continue_current_game) {
                if(board.hasMove(game.getActivePlayer()) == false) {
                    game.switchPlayer();

                    if(board.hasMove(game.getActivePlayer()) == false) {
                        continue_current_game = false;
                        break;
                    }
                }
                System.out.println("Active Player is " + game.getActivePlayer().getColor().toString());
                board.printBoard();
                System.out.println("row col >");

                if(game.getActivePlayer().human) {
                    //Scanner scanner = new Scanner(System.in);

                    row = scanner.nextInt();
                    col = scanner.nextInt();
                }

                else {
                    row = 0;
                    col = 0;
                }
                if(((row >= 0 && row <= 7) &&
                   (col >= 0 && col <= 7)) &&
                   game.getActivePlayer().nextMove(row, col)) {
                        game.switchPlayer();
                }
            }
            board.printBoard();
            System.out.println("Would you like to play again?");
            String answer = scanner.nextLine();
            if(Game.isYes(answer)) {
                play_new_game = true;
                continue_current_game = true;
            }
            else
                play_new_game = false;
        }
        System.out.println("Thanks for playing!");
    }
}

