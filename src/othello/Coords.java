/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class Coords {
    private int row;
    private int col;

    public Coords(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCoordsRow() {
        return this.row;
    }

    public int getCoordsCol() {
        return this.col;
    }

    public String toString() {
        String str = row + " " + col;
        return str;
    }
}
