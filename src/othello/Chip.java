/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class Chip {
    private Color chip_color = Color.GREEN;
    private boolean status = false; //True for a set chip, false for blank space

    public Chip(Color color, boolean status) {
        this.chip_color = color;
        this.status = status;
    }

    public Color getColor() {
        return this.chip_color;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setColor(Color color) {
        this.chip_color = color;
        this.status = true;
    }
    public String toString() {

        return "" + this.getColor();
    }
}
