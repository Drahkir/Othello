/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class Easy_Player extends Player {

    public Easy_Player(boolean human, Color color, Game game) {
        super(human, color, game);
    }

    public boolean nextMove(int row, int col) {
        //Returns true for a valid and successful move
        boolean result = false;
        int r = game.getBoard().board_row;
        int c = game.getBoard().board_col;

        for(int i = 0 ; i < r; i++) {
            for(int j = 0; j < c; j++) {
                Coords current_coords = new Coords(i, j);
                Chip current_chip = this.game.getBoard().getChip(i, j);
                Color current_color = current_chip.getColor();
                /*System.out.println("\nCurr Coords: " + current_coords.toString());
                System.out.println("Curr Stat: " + current_chip.getStatus());
                System.out.println("Curr Val: " + this.game.getBoard().isValid(current_coords, this, false));*/
                if(current_chip.getStatus() == false && this.game.getBoard().isValid(current_coords, this, true)) {
                    return true;
                }
            }
        }
        return result;
    }
}