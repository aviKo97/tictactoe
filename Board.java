/**
 * The Board class. this class is building the board for our tic-tac-tow game
 * She can mark the squares we want with an X or an O.
 * It can also provide us with information about squares.
 */
public class Board {
    //##############private fields#################
    private static final int DEFAULT_BOARD_SIZE = 4; //the default size
    private int size; // the size of the board
    private Mark[][] board; // the board arry

    //#################Public methods###############

    /**
     * Default board constructor
     * the size is 4X4
     */
    public Board(){
        this.size = DEFAULT_BOARD_SIZE;
        this.board = new Mark[this.size][this.size];
        for (int row=0; row<this.board.length; row++){
            for (int col=0;col<this.board[row].length;col++)
            {
                this.board[row][col]=Mark.BLANK;
            }
        }
    }

    /**
     * By demand board constructor
     * the size is size X size that the user provides
     */
    public Board(int size){
        this.size = size;
        this.board = new Mark[size][size];
        for (int row=0; row<this.board.length; row++){
            for (int col=0;col<this.board[row].length;col++)
            {
                this.board[row][col]=Mark.BLANK;
            }
        }
    }

    /**
     * @return the size of the board
     */
    public int getSize(){

        return this.size;
    }


    /**
     *  Gets a mark and tries to put it inside the giving coordinates.
     *  if succeed returns true, else returns false
     * @param mark we want to put
     * @param row
     * @param col
     * @return if succeed returns true, else returns false
     */
    public boolean putMark(Mark mark, int row, int col){
        if (row >= 0 && row <= size-1 && col >= 0 && col <= size-1 && getMark(row,col)==Mark.BLANK){
            this.board[row][col]=mark;
            return true;
        }
        return false;
    }

    /**
     * gets coordinates and returns the symbol inside these coordinates
     * @param row
     * @param col
     * @return the symbol inside these coordinates
     */
    public Mark getMark(int row, int col){
        if (row >= 0 && row <= size-1 && col >= 0 && col <= size-1){
            return this.board[row][col];
        }
        else
        {
            return Mark.BLANK;
        }
    }
}
