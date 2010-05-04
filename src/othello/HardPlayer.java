/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import java.util.*;
import java.math.*;
/**
 *
 * @author rikmikac
 */
public class HardPlayer extends Player {

    public HardPlayer(boolean human, ChipColor color, Game game) {
        super(human, color, game);
    }

    public boolean nextMove(int row, int col) {
        HashMap<Integer, Coords> choices = new HashMap<Integer, Coords>();
        //Returns true for a valid and successful move
        boolean result = false;
        int r = game.getBoard().board_row;
        int c = game.getBoard().board_col;
        int highest_flips = 0;

        for(int i = 0 ; i < r; i++) {
            for(int j = 0; j < c; j++) {
                Coords current_coords = new Coords(i, j);
                Chip current_chip = this.game.getBoard().getChip(i, j);
                ChipColor current_color = current_chip.getChipColor();

                if(current_chip.getStatus() == false && this.game.getBoard().isValid(current_coords, this, false)) {
                    int total_flips = game.getBoard().countFlips(current_coords, this);
                    choices.put(total_flips, current_coords);
                    if(total_flips > highest_flips)
                        highest_flips = total_flips;
                }
            }
        }

        if(!choices.isEmpty()) {
            Coords final_choice;
            final_choice = choices.get(highest_flips);
            this.game.getBoard().isValid(final_choice, this, true);
            result = true;
        }
        return result;
    }
}
