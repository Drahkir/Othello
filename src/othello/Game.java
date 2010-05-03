/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class Game {
    private Board board;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;
    private boolean playerOneIsActive; //true is player one, false is player two
    private int playerOneScore;
    private int playerTwoScore;

    public Game(Board board, boolean human_one, boolean human_two) {
        this.board = board;
        if(human_one)
            this.playerOne = new Human_Player(human_one, Color.WHITE, this);
        else
            this.playerOne = new Easy_Player(human_one, Color.WHITE, this);
        if(human_two)
            this.playerTwo = new Human_Player(human_two, Color.BLACK, this);
        else
            this.playerTwo = new Easy_Player(human_two, Color.BLACK, this);
        this.playerOneIsActive = true;
        this.activePlayer = this.playerOne;
        this.playerOneScore = 0;
        this.playerTwoScore = 0;
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
    }

    public int getPlayerOneScore() {
        return this.playerOneScore;
    }

    public int getPlayerTwoScore() {
        return this.playerTwoScore;
    }

    public Player getActivePlayer() {
        return this.activePlayer;
    }
    public  void switchPlayer() {
        if(playerOneIsActive) {
            this.playerOneIsActive = false;
            this.activePlayer = this.playerTwo;
        }
        else {
            this.playerOneIsActive = true;
            this.activePlayer = this.playerOne;
        }
    }

    public Board getBoard() {
        return this.board;
    }

    public static boolean isYes(String answer) {
    if(answer.equals("yes") || answer.equals("Yes") ||
                    answer.equals("y") || answer.equals("Y"))
        return true;
    else
        return false;
}
    
}
