/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public abstract class Player {
    boolean human;
    ChipColor player_color;
    Game game;

    public Player(boolean human, ChipColor color, Game game) {
        this.human = human;
        this.player_color = color;
        this.game = game;
    }

    public ChipColor getColor() {
        return this.player_color;
    }

    public abstract boolean nextMove(int row, int col);
}
