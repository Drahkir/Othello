/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public enum Color {
    BLACK,
    WHITE,
    GREEN;

    // Returns the opposite color
    public static Color oppColor(Color color) {
        if(color == GREEN) //Should never happen, will return green if it does
            return GREEN;
        else if(color == WHITE)
            return BLACK;
        else
            return WHITE;
    }

    public static boolean isOpp(Color color1, Color color2) {
        if((color1 == BLACK && color2 == WHITE) ||
                (color1 == WHITE && color2 == BLACK))
            return true;
        else
            return false;
    }

    public static boolean isSame(Color color1, Color color2) {
        if((color1 == BLACK && color2 == BLACK) || (color1 == WHITE && color2 == WHITE) )
            return true;
        else
            return false;
    }

}


