/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class Human_Player extends Player {

    public Human_Player(boolean human, Color color, Game game) {
        super(human, color, game);
    }

    public boolean nextMove(int row, int col) {
    //Returns true for a valid and successful move
        Coords current_coords = new Coords(row, col);
        Chip current_chip = this.game.getBoard().getChip(row, col);
        Color current_color = current_chip.getColor();

        if(current_chip.getStatus() == false && this.game.getBoard().isValid(current_coords, this, true)) {
            return true;
        }
        else
            return false;
    }
}
