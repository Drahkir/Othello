/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import java.awt.event.*;


/**
 *
 * @author rikmikac
 */
    public class MyMouseHandler implements MouseListener {
            private int lastX, lastY;

            public int getLastX() {
                return this.lastX;
            }

            public int getLastY() {
                return this.lastY;
            }
            public void mouseEntered(MouseEvent e) { }

            public void mouseExited(MouseEvent e) { }

            public void mousePressed(MouseEvent e) {  }

            public void mouseReleased(MouseEvent e) { }

            public void mouseClicked(MouseEvent e) {
                // e.getY() - 25 is a hard-coded fix... will likely cause problems later
                lastX = (e.getY() - 25) / 50 ; //x is actually col
                lastY = e.getX() / 50;  //y is actually row
            }
    }
