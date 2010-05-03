/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

/**
 *
 * @author Rikh Mikac
 */
public class Board {
    public static final int board_row = 8;
    public static final int board_col = 8;
    private Chip[][] board_array = new Chip[board_row][board_col];

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
    public void printBoard() {
        System.out.print(" 01234567\n");
        for(int i = 0; i < board_row; i++) {
            System.out.printf("%d", i);
            for(int j = 0; j < board_col; j++) {
                PrintColor(board_array[i][j].getColor());
            }
            System.out.print("\n");
        }
    }

    public Chip getChip(int row, int col) {
        return board_array[row][col];
    }
    public void PrintColor(Color color) {
        switch(color) {
            case BLACK:
                System.out.print('B');
                break;
            case WHITE:
                System.out.print('W');
                break;
            default:
                System.out.print('-');
                break;
        }
    }

    //Checks for a valid move
    public boolean isValid(Coords start, Player player, boolean if_flip) {
        //System.out.println("Checking " + start.toString() + " in isValid");
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
        //System.out.println("Flipping chips...");
        Chip start_chip = this.getChip(start.getCoordsRow(), start.getCoordsCol());
        Color player_color = player.getColor();

        start_chip.setColor(player_color);
        //System.out.println("fC: starting flip at: " + start.toString());
        Coords current_coords = nextCoords(start, dir);
        Chip current_chip = this.getChip(current_coords.getCoordsRow(), current_coords.getCoordsCol());

        while(Color.isOpp(player_color, current_chip.getColor())) {
            current_chip.setColor(player_color);
            //System.out.println("fC: flip @ " + current_coords.toString() + " to " + player_color);
            if(inBounds(current_coords, dir)) {
                current_coords = nextCoords(current_coords, dir);
                current_chip = this.getChip(current_coords.getCoordsRow(), current_coords.getCoordsCol());
            }
            else
                break;
        }

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
                if(row + 1 <= board_array.length)
                    result = true;
                else
                    result = false;
                break;
            case EAST:
                if(col + 1 <= board_array.length)
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
                if(row - 1 >= 0 && col + 1 <= board_array.length)
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
                if(row + 1 <= board_array.length && col + 1 <= board_array.length)
                    result = true;
                else
                    result = false;
                break;
            case S_WEST:
                if(row + 1 <= board_array.length && col - 1 >= 0)
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
        //System.out.println("Init sCL");
        //System.out.printf("Checking oppositeColorInline");
        boolean result = false;
        //Color player_color = this.getChip(start.getCoordsRow(), start.getCoordsCol()).getColor();
        current = nextCoords(current, dir);
        Chip current_chip = this.getChip(current.getCoordsRow(), current.getCoordsCol());
        while(current_chip.getStatus()) {
            //Chip start_chip = this.getChip(start.getCoordsRow(), start.getCoordsCol());
            

            if(Color.isSame(player.getColor(), current_chip.getColor())) {
                result = true;
                //System.out.println("Found same color: " + current.toString() + " " + current_chip.getColor().toString());
                break;
            }
            
            current = nextCoords(current, dir);
            if(inBounds(current, dir)) {
                current_chip = this.getChip(current.getCoordsRow(), current.getCoordsCol());
            }
            else
                break;
        }
        //System.out.println("Finished sCL: " + current_chip.getColor().toString() + " is " + result);
        return result;
    }

    /*Checks for a neighbor of opposing color */
    public boolean validNeighbor(Coords start, Direction dir, Player player) {

        //System.out.println("Checking validNeighbor");
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
            
            neighbor_chip.getColor();
            player.getColor();
            if(Color.isOpp( player.getColor() , neighbor_chip.getColor()))
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

}
