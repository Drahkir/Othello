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

    public Main() {
        //empty constructor
    }
    public void init() {
        Board board = new Board();
        
        JFrame frame = new JFrame();
        frame.add(board);
        int frame_height, frame_width;
        frame_height = frame.getHeight();
        frame_width = frame.getWidth();
        frame.setSize((board.tile_height * board.board_row) + 3, (board.tile_width * board.board_col) + 33);
        MyMouseHandler mh = new MyMouseHandler();
        frame.addMouseListener(mh);
        frame.setVisible(true);


        Game game = new Game(board, mh, true, false); //true for human player, false for CPU
        game.PlayGame();
    }
    public void actionPerformed(ActionEvent e) {
        //Necessary for abstract class
    }
 
    public static void main(String[] args) {
        //Board board = new Board(); //uncomment for application version
        Main m = new Main();
        m.init(); //uncomment for applet version

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        //frame.add(board); //Uncomment for application version
        
    }
}