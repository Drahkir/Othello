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
    Color player_color;
    Game game;

    public Player(boolean human, Color color, Game game) {
        this.human = human;
        this.player_color = color;
        this.game = game;
    }

    public Color getColor() {
        return this.player_color;
    }

    public abstract boolean nextMove(int row, int col);
}
