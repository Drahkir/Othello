/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.net.URL;


/**
 *
 * @author Rikh Mikac
 */
public class Board extends JPanel {
    //Need to create URLs for images
    URL tile_url = this.getClass().getResource("Tile.png");
    URL white_chip_url = this.getClass().getResource("WhiteChip.png");
    URL black_chip_url = this.getClass().getResource("BlackChip.png");

    Image tile = new ImageIcon(tile_url).getImage();
    Image white_chip = new ImageIcon(white_chip_url).getImage();
    Image black_chip = new ImageIcon(black_chip_url).getImage();

    /*Image tile = new ImageIcon("/home/rikmikac/Dropbox/NetBeansProjects/othello/src/othello/Tile.png").getImage();
    Image white_chip = new ImageIcon("/home/rikmikac/Dropbox/NetBeansProjects/othello/src/othello/WhiteChip.png").getImage();
    Image black_chip = new ImageIcon("/home/rikmikac/Dropbox/NetBeansProjects/othello/src/othello/BlackChip.png").getImage();
    */
    int tile_height = tile.getHeight(this);
    int tile_width = tile.getWidth(this);

    public static final int board_row = 8;
    public static final int board_col = 8;
    public int image_width, image_height;
    private Chip[][] board_array = new Chip[board_row][board_col];

    public Board() {
        for(int i = 0; i < board_row; i++) {
            for(int j = 0; j < board_col; j++) {
                if((i == 3 && j == 3) || (i == 4 && j == 4))
                    board_array[i][j] = new Chip(ChipColor.BLACK, true);
                else if((i == 3 && j == 4) || (i == 4 && j == 3))
                    board_array[i][j] = new Chip(ChipColor.WHITE, true);
                else
                    board_array[i][j] = new Chip(ChipColor.GREEN, false);
            }
        }
    }
   /*uncomment for applet version
   public void init() {
        getContentPane().add(new Board());
    }
    */
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int k = this.getHeight() * tile_height;
        int l = this.getWidth() * tile_width;

        //REALLY needs to be rewritten!!!  May want to define for loops to halt on c and r instead of l and k
        for(int i = 0, r = 0; i < k; i += tile_height, r++) {
            for(int j = 0, c = 0; j < l; j += tile_width, c++) {
                if(board_array[r][c].getChipColor() == ChipColor.BLACK)
                    g2d.drawImage(black_chip, j, i, this);
                else if(board_array[r][c].getChipColor() == ChipColor.WHITE)
                    g2d.drawImage(white_chip, j, i, this);
                else
                    g2d.drawImage(tile, j, i, this);
                if(c == 7)
                    c = 0;

            }
            if(r == 7)
                r = 0;
        }
    }


    /* Command Line Board Constructor
    public Board() {
        for(int i = 0; i < board_row; i++) {
            for(int j = 0; j < board_col; j++) {
                if((i == 3 && j == 3) || (i == 4 && j == 4))
                    board_array[i][j] = new Chip(Color.BLACK, true);
                else if((i == 3 && j == 4) || (i == 4 && j == 3))
                    board_array[i][j] = new Chip(Color.WHITE, true);
                else
                    board_array[i][j] = new Chip(Color.GREEN, false);
            }
        }
    }
     */

    public Chip getChip(int row, int col) {
        return board_array[row][col];
    }
    public String PrintChipColor(ChipColor color) {
        String string = "";

        switch(color) {
            case BLACK:
                string += 'B';
                break;
            case WHITE:
                string += 'W';
                break;
            default:
                string += '-';
                break;
        }
        return string;
    }

    //Checks for a valid move
    public boolean isValid(Coords start, Player player, boolean if_flip) {
        Chip start_chip = this.getChip(start.getCoordsRow(), start.getCoordsCol());
        boolean result = false;

        if(start_chip.getStatus() == true)
            return result;

        if(validNeighbor(start, Direction.NORTH, player)) {
            if(sameColorInline(start, Direction.NORTH, player)) {
                if(if_flip)
                    flipChips(start, Direction.NORTH, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.SOUTH, player)) {
            if(sameColorInline(start, Direction.SOUTH, player)) {
                if(if_flip)
                    flipChips(start, Direction.SOUTH, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.EAST, player)) {
            if(sameColorInline(start, Direction.EAST, player)) {
                if(if_flip)
                    flipChips(start, Direction.EAST, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.WEST, player)) {
            if(sameColorInline(start, Direction.WEST, player)) {
                if(if_flip)
                flipChips(start, Direction.WEST, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.N_EAST, player)) {
            if(sameColorInline(start, Direction.N_EAST, player)) {
                if(if_flip)
                    flipChips(start, Direction.N_EAST, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.N_WEST, player)) {
            if(sameColorInline(start, Direction.N_WEST, player)) {
                if(if_flip)
                    flipChips(start, Direction.N_WEST, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.S_EAST, player)) {
            if(sameColorInline(start, Direction.S_EAST, player)) {
                if(if_flip)
                    flipChips(start, Direction.S_EAST, player);
                result = true;
            }
        }

        if(validNeighbor(start, Direction.S_WEST, player)) {
            if(sameColorInline(start, Direction.S_WEST, player)) {
                if(if_flip)
                    flipChips(start, Direction.S_WEST, player);
                result = true;
            }
        }
        return result;
    }

    public void flipChips(Coords start, Direction dir, Player player) {
        Chip start_chip = this.getChip(start.getCoordsRow(), start.getCoordsCol());
        ChipColor player_color = player.getColor();

        start_chip.setColor(player_color);
        Coords current_coords = nextCoords(start, dir);
        Chip current_chip = this.getChip(current_coords.getCoordsRow(), current_coords.getCoordsCol());

        while(ChipColor.isOpp(player_color, current_chip.getChipColor())) {
            current_chip.setColor(player_color);

            if(inBounds(current_coords, dir)) {
                current_coords = nextCoords(current_coords, dir);
                current_chip = this.getChip(current_coords.getCoordsRow(), current_coords.getCoordsCol());
            }
            else
                break;
        }

    }
    
    //Count total flips made at a certain move
    public int countFlips(Coords start, Player player) {
        int total_flips = 0; //total number of flips to be returned
        int add_flips = 0;   //number of flips on current run, add to total_flips if valid
        Direction dir = Direction.NORTH;
        int switch_index = 0;

        Chip start_chip = this.getChip(start.getCoordsRow(), start.getCoordsCol());
        ChipColor player_color = player.getColor();

        while(switch_index <= 7) {
            Coords current_coords = start;
            Chip current_chip = start_chip;
            add_flips = 0;

            switch(switch_index) {
                    case 0:
                        dir = Direction.NORTH;
                        break;
                    case 1:
                        dir = Direction.SOUTH;
                        break;
                    case 2:
                        dir = Direction.EAST;
                        break;
                    case 3:
                        dir = Direction.WEST;
                        break;
                    case 4:
                        dir = Direction.N_EAST;
                        break;
                    case 5:
                        dir = Direction.N_WEST;
                        break;
                    case 6:
                        dir = Direction.S_EAST;
                        break;
                    case 7:
                        dir = Direction.S_WEST;
                        break;
                }


                while(inBounds(current_coords, dir) ) {
                    current_coords = nextCoords(current_coords, dir);
                    current_chip = this.getChip(current_coords.getCoordsRow(), current_coords.getCoordsCol());

                    if(ChipColor.isOpp(player_color, current_chip.getChipColor()))
                        add_flips++;
                    else {
                        total_flips += add_flips;
                        break;
                    }
                }
                switch_index++;
            }
            return total_flips;
    }

    /* Returns true iff the coords given are within bounds */
    // Currently dead code...
    public boolean inBounds(Coords start) {
        int row = start.getCoordsRow();
        int col = start.getCoordsCol();
        boolean result = false;

        if((row >= 0 && row < board_array.length) && (col >= 0 && col < board_array.length))
            result = true;
        return result;
    }

    /* Returns true iff the next coords are within bounds */
    public boolean inBounds(Coords start, Direction dir) {
        //System.out.printf("Checking %d for inBounds", start.toString());
        int row = start.getCoordsRow();
        int col = start.getCoordsCol();
        boolean result = false;

        switch(dir) {
            case NORTH:
                if(row - 1 >= 0)
                    result = true;
                else
                    result = false;
                break;
            case SOUTH:
                if(row + 1 < board_array.length)
                    result = true;
                else
                    result = false;
                break;
            case EAST:
                if(col + 1 < board_array.length)
                    result = true;
                else
                    result = false;
                break;
            case WEST:
                if(col - 1 >= 0)
                    result = true;
                else
                    result = false;
                break;
            case N_EAST:
                if(row - 1 >= 0 && col + 1 < board_array.length)
                    result = true;
                else
                    result = false;
                break;
            case N_WEST:
                if(row - 1 >= 0 && col - 1 >= 0)
                    result = true;
                else
                    result = false;
                break;
            case S_EAST:
                if(row + 1 < board_array.length && col + 1 < board_array.length)
                    result = true;
                else
                    result = false;
                break;
            case S_WEST:
                if(row + 1 < board_array.length && col - 1 >= 0)
                    result = true;
                else
                    result = false;
                break;
        }
        return result;
    }

    /* Returns coords for a valid move iff an opposite color is in line with
     * the move chosen.
     */
    public boolean sameColorInline(Coords current, Direction dir, Player player) {
        boolean result = false;
        current = nextCoords(current, dir);
        Chip current_chip = this.getChip(current.getCoordsRow(), current.getCoordsCol());

        while(current_chip.getStatus()) {

            if(ChipColor.isSame(player.getColor(), current_chip.getChipColor())) {
                result = true;
                break;
            }
            //current = nextCoords(current, dir); //Will not crash, but will not allow you to reach the end of the column
            if(inBounds(current, dir)) {
                current = nextCoords(current, dir); // Will allow you to reach the end of column, but will crash
                current_chip = this.getChip(current.getCoordsRow(), current.getCoordsCol());
            }
            else
                break;
        }
        return result;
    }

    /*Checks for a neighbor of opposing color */
    public boolean validNeighbor(Coords start, Direction dir, Player player) {
        boolean result = false;
        Chip start_chip = this.getChip(start.getCoordsRow(), start.getCoordsCol());
        Chip neighbor_chip;
        Coords neighbor_coords = nextCoords(start, dir);
        if(inBounds(neighbor_coords, dir))
                neighbor_chip = this.getChip(neighbor_coords.getCoordsRow(), neighbor_coords.getCoordsCol());
        else return false;
        //System.out.println("Neighbor chip identified " + neighbor_coords.toString() + " " + neighbor_chip.toString());
        if(inBounds(start, dir)) {
            //System.out.println("entered inBounds if");
            
            neighbor_chip.getChipColor();
            player.getColor();
            if(ChipColor.isOpp( player.getColor() , neighbor_chip.getChipColor()))
                result = true;
                //System.out.println("Checked isOpp");
        }
        //System.out.println("Valid neighbor result for " + neighbor_coords.toString() + ": " + result);
        return result;
    }

    /* Returns Coords for the chip in the direction from the start Coords */
    public Coords nextCoords(Coords start, Direction dir) {
        Coords next = new Coords(start.getCoordsRow(), start.getCoordsCol());

        switch(dir) {
                case NORTH:
                    next = new Coords(start.getCoordsRow() - 1, start.getCoordsCol());
                    break;
                case SOUTH:
                    next = new Coords(start.getCoordsRow() + 1, start.getCoordsCol());
                    break;
                case EAST:
                    next = new Coords(start.getCoordsRow(), start.getCoordsCol() + 1);
                    break;
                case WEST:
                    next = new Coords(start.getCoordsRow(), start.getCoordsCol() - 1);
                    break;
                case N_EAST:
                    next = new Coords(start.getCoordsRow() - 1, start.getCoordsCol() + 1);
                    break;
                case N_WEST:
                    next = new Coords(start.getCoordsRow() - 1, start.getCoordsCol() - 1);
                    break;
                case S_EAST:
                    next = new Coords(start.getCoordsRow() + 1, start.getCoordsCol() + 1);
                    break;
                case S_WEST:
                    next = new Coords(start.getCoordsRow() + 1, start.getCoordsCol() - 1);
                    break;
        }
        return next;
    }

    public boolean hasMove(Player active_player) {
        boolean result = false;

        for(int i = 0 ; i < board_row; i++) {
            for(int j = 0; j < board_col; j++) {
                Coords coords = new Coords(i, j);
                if(isValid(coords, active_player, false)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public String declareWinner() {
        int white, black;
        white = black = 0;
        String string;

        for(int i = 0 ; i < board_row; i++) {
            for(int j = 0; j < board_col; j++) {
                ChipColor color = board_array[i][j].getChipColor();

                if(color == ChipColor.WHITE)
                    white++;
                else if(color == ChipColor.BLACK)
                    black++;
            }
        }
        if(white > black)
           string = "You win";
        else
            string = "You lose";
        return string;
    }

        //Command Line printBoard()
    public String toString() {
        String string = " 01234567\n";
        for(int i = 0; i < board_row; i++) {
            string += i;
            for(int j = 0; j < board_col; j++) {
                string += PrintChipColor(board_array[i][j].getChipColor());
            }
            string += "\n";
        }
        return string;
    }
}
