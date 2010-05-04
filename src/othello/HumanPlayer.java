/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class HumanPlayer extends Player {

    public HumanPlayer(boolean human, ChipColor color, Game game) {
        super(human, color, game);
    }

    public boolean nextMove(int row, int col) {
    //Returns true for a valid and successful move
        Coords current_coords = new Coords(row, col);
        Chip current_chip = this.game.getBoard().getChip(row, col);
        ChipColor current_color = current_chip.getChipColor();

        if(current_chip.getStatus() == false && this.game.getBoard().isValid(current_coords, this, true)) {
            return true;
        }
        else
            return false;
    }
}
