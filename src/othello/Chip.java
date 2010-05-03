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
    private ChipColor chip_color = ChipColor.GREEN;
    private boolean status = false; //True for a set chip, false for blank space

    public Chip(ChipColor color, boolean status) {
        this.chip_color = color;
        this.status = status;
    }

    public ChipColor getChipColor() {
        return this.chip_color;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setColor(ChipColor color) {
        this.chip_color = color;
        this.status = true;
    }
    public String toString() {

        return "" + this.getChipColor();
    }
}
