/**
 * The game class
 * here we can run a single game for our players.
 * we need to choose the sizes we want for the board
 * and for the win.
 * the class let the player play on their turn
 */
public class Game {
    //##############private fields#################
    private static final int DEFAULT_BOARD_SIZE = 4; //default board size
    private static final int DEFAULT_STREAK = 3; // default board streak to win
    private Player playerX, playerO; // the players
    private int size,winStreak; //the size and the streak we get from uesr
    private Renderer renderer; // the renderer
    private Board board; // the board

    //#################Public methods###############

    /**
     * Default game maker, 4*4 board and 3 streak to win
     * @param playerX number 1 player
     * @param playerO number 2 player
     * @param renderer the screen
     */
    public Game(Player playerX,Player playerO, Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.size = DEFAULT_BOARD_SIZE;
        this.winStreak = DEFAULT_STREAK;
        this.renderer = renderer;
        this.board = new Board(size);
    }

    /**
     * By demand game maker
     * @param playerX number 1 player
     * @param playerO number 2 player
     * @param size of the board
     * @param winStreak to win
     * @param renderer the screen
     */
    public Game(Player playerX,Player playerO,int size,int winStreak,Renderer renderer){
    this.playerX = playerX;
    this.playerO = playerO;
    this.size = size;
    if (winStreak>=2 && winStreak<=size) {
        this.winStreak = winStreak;
    }
    else {
        this.winStreak = size;
    }
    this.board = new Board(size);
    this.renderer = renderer;
    }

    /**
     * @return the win streak
     */
    public int getWinStreak(){
        return this.winStreak;
    }

    /**
     * @return the board size
     */
    public int getBoardSize(){
        return this.size;
    }

    /**
     * runs a single game
     * @return the winning player mark
     */
    public Mark run(){
        int squaresOnBoard = size*size;
        int curSquares = 0;
        while (curSquares < squaresOnBoard)
        {
            playerX.playTurn(board,Mark.X);
            curSquares++;
            renderer.renderBoard(board);
            if(isGameOver(board))
            {
                return Mark.X;
            }
            if(curSquares == squaresOnBoard)
            {
                return Mark.BLANK;
            }
            playerO.playTurn(board,Mark.O);
            curSquares++;
            renderer.renderBoard(board);
            if(isGameOver(board))
            {
                return Mark.O;
            }
        }
        return Mark.BLANK;
    }

    //#################Private methods###############

    /**
     * check if there is a streak on the rows
     * @param board
     * @return true if here is a streak, otherwise - false
     */
    private boolean rowsCheck(Board board){
        for (int row = 0; row < this.size; row++) {
            int counter = 1;
            for (int col = 0; col < this.size-1; col++) {
                Mark cur = board.getMark(row,col);
                if (cur != Mark.BLANK && cur == board.getMark(row,col+1)){
                    counter++;
                    if (counter == winStreak){
                        return true;
                    }
                }
                else {
                    counter = 1;
                }
            }
        }
        return false;
    }

    /**
     * check if there is a streak on the cols
     * @param board
     * @return true if here is a streak, otherwise - false
     */
    private boolean colsCheck(Board board){
        for (int col = 0; col < this.size; col++) {
            int counter = 1;
            for (int row = 0; row < this.size-1; row++) {
                Mark cur = board.getMark(row,col);
                if (cur != Mark.BLANK && cur == board.getMark(row+1,col)){
                    counter++;
                    if (counter == winStreak){
                        return true;
                    }
                }
                else {
                    counter = 1;
                }
            }
        }
        return false;
    }

    /**
     * check if there is a streak on the first diagonal
     * @param board
     * @return true if here is a streak, otherwise - false
     */
    private boolean firstDiagonal(Board board) {
        for (int i = 0; i <= size - winStreak; i++) {
            for (int j = 0; j <= size - winStreak; j++) {
                int counter = 1;
                Mark cur = board.getMark(i, j);
                for (int k = 1; k < winStreak; k++) {
                    if (cur !=Mark.BLANK && cur == board.getMark(i + k, j + k)){
                        counter++;
                        if (counter == winStreak){
                            return true;
                            }
                        }
                }
            }
        }
        return false;
    }

    /**
     * check if there is a streak on the second diagonal
     * @param board
     * @return true if here is a streak, otherwise - false
     */
    private boolean secondDiagonal(Board board){
    for (int i = 0; i <= size - winStreak; i++) {
        for (int j = size - 1; j >= winStreak - 1; j--) {
            int counter = 1;
            Mark cur = board.getMark(i, j);
            for (int k = 1; k < winStreak; k++) {
                if (cur !=Mark.BLANK && cur == board.getMark(i + k, j - k)){
                    counter++;
                    if (counter == winStreak){
                        return true;
                    }
                }
            }
        }
    }

        return false;
    }

    /**
     * check if there is a streak on the board
     * @param board
     * @return true if here is a streak, otherwise - false
     */
    private boolean isGameOver(Board board){
        if (rowsCheck(board) || colsCheck(board) ||
                firstDiagonal(board) || secondDiagonal(board)){
            return true;
        }
        return false;
        }

}

