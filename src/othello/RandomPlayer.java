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
public class RandomPlayer extends Player {

    public RandomPlayer(boolean human, ChipColor color, Game game) {
        super(human, color, game);
    }

    public boolean nextMove(int row, int col) {
        LinkedList<Coords> choices = new LinkedList<Coords>();
        //Returns true for a valid and successful move
        boolean result = false;
        int r = game.getBoard().board_row;
        int c = game.getBoard().board_col;

        for(int i = 0 ; i < r; i++) {
            for(int j = 0; j < c; j++) {
                Coords current_coords = new Coords(i, j);
                Chip current_chip = this.game.getBoard().getChip(i, j);
                ChipColor current_color = current_chip.getChipColor();

                if(current_chip.getStatus() == false && this.game.getBoard().isValid(current_coords, this, false)) {
                    choices.add(current_coords);
                }
            }
        }

        if(choices.size() > 0) {
            int final_indice = (int)(Math.random() * choices.size());
            System.out.printf("Ran num = %d\n", final_indice);
            Coords final_choice;
            final_choice = choices.get(final_indice);
            System.out.println("Going to " + final_choice.toString() + "\n");
            this.game.getBoard().isValid(final_choice, this, true);
            result = true;
        }
        return result;
    }
}
