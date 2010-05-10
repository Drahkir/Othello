/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rikh Mikac
 */

public class Main extends JApplet implements ActionListener {
    Board board;
    MyMouseHandler mh;
    boolean player_one;
    boolean player_two;

    public Main() {
        this.board = new Board();
        this.mh = new MyMouseHandler();
    }

    public void init() {
        getContentPane().add(this.board);
        getContentPane().addMouseListener(this.mh);
    }

    public void start() {
        Game game = new Game(this.board, this.mh, true, false); //true for human player, false for CPU
        game.PlayGame();
    }

    public void actionPerformed(ActionEvent e) {
        //Necessary for abstract class
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.init(); //uncomment for applet version

        JFrame frame = new JFrame();
        JMenuBar menu_bar = new JMenuBar();
        JMenu player_menu = new JMenu("Player Menu");
        menu_bar.add(player_menu);
        frame.setSize((m.board.tile_height * m.board.board_row) + 3, (m.board.tile_width * m.board.board_col) + 33);
        frame.add(menu_bar);
        frame.setVisible(true);
        m.start();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLocationRelativeTo(null);
        //frame.add(board); //Uncomment for application version
    }
}