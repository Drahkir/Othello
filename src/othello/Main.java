/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rikh Mikac
 */



public class Main extends JApplet implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        //Necessary for abstract class
    }
 
    public static void main(String[] args) {
        Board board = new Board(); //uncomment for application version
        //board.init(); //uncomment for applet version

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((board.tile_height * board.board_row) + 3, (board.tile_width * board.board_col) + 33);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(board); //Uncomment for application version
        int frame_height, frame_width;
        frame_height = frame.getHeight();
        frame_width = frame.getWidth();
        MyMouseHandler mh = new MyMouseHandler();
        frame.addMouseListener(mh);

        Game game = new Game(board, mh, true, false); //true for human player, false for CPU
        game.PlayGame();
        System.out.println("Game over");
        String string = board.declareWinner();
        System.out.println(string);
        JLabel winner = new JLabel(string);
        frame.add(winner);
        frame.repaint();
    }
}