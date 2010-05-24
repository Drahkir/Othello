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
    private MyMouseHandler mh;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;
    private boolean playerOneIsActive; //true is player one, false is player two
    boolean human_one = true;
    boolean human_two = true;

    public Game(Board board, MyMouseHandler mh, boolean human_one, boolean human_two) {
        this.board = board;
        this.mh = mh;
        this.human_one = human_one;
        this.human_two = human_two;

        if(human_one)
            this.playerOne = new HumanPlayer(human_one, ChipColor.WHITE, this);
        else
            this.playerOne = new HardPlayer(human_one, ChipColor.WHITE, this);
        if(human_two)
            this.playerTwo = new HumanPlayer(human_two, ChipColor.BLACK, this);
        else
            this.playerTwo = new HardPlayer(human_two, ChipColor.BLACK, this);
    }

    public void PlayGame() {
        boolean play_new_game = true;
        boolean continue_current_game = true;
        this.playerOneIsActive = true;
        this.activePlayer = this.playerOne;

        int row = 0;
        int col = 0;

        while(continue_current_game) {
            if(board.hasMove(getActivePlayer()) == false) {
                switchPlayer();

                if(board.hasMove(getActivePlayer()) == false) {
                    continue_current_game = false;
                    break;
                }
            }

            if(getActivePlayer().human) {
                //Scanner scanner = new Scanner(System.in);
                row = mh.getLastX();
                col = mh.getLastY();
            }

            else {
               row = 0;
               col = 0;
            }
            if(((row >= 0 && row <= 7) && (col >= 0 && col <= 7)) &&
                    getActivePlayer().nextMove(row, col)) {
                //System.out.println("a;lsfj");
                 switchPlayer();
                 board.repaint();
                 //System.out.println(board.toString());
                 try {
                        Thread.sleep(1000);
                 }
                 catch (InterruptedException e) {
                        e.printStackTrace();
                 }
            }
        }
        board.declareWinner();
    }

    public Player getPlayerOne() {
        return this.playerOne;
    }

    public Player getPlayerTwo() {
        return this.playerTwo;
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
