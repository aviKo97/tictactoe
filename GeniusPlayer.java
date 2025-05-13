import java.util.Random;

/**
 * The GeniusPlayer class.
 * In this department we can build a GeniusPlayer.
 * This player will perform better than lever player.
 * The class allows a much better choice of squares for the player
 */
public class GeniusPlayer implements Player{

    //#################Public methods###############

    /**
     * Default genius player constructor
     */
    GeniusPlayer(){

    }

    /**
     * play a turn according to the player strategy
     * @param board the board we use
     * @param mark the mark of the player
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        for (int i = 0; i< board.getSize();i++){
            for (int j = 0; j< board.getSize();j++){
                if(board.getMark(i,j)==mark){
                    if (insertPlace(board,mark,i,j)){
                        return;
                    }
                }
            }
        }
        if (insertNextTo(board,mark)){
            return;
        }
        randomPlace(board,mark);
    }

    //#################Private methods###############

    /**
     * picks a random square and put the mark there
     * @param board the board we use
     * @param mark the mark of the player
     */
    private void randomPlace(Board board, Mark mark){
        Random rand = new Random();
        while (true) {
            int random_row = rand.nextInt(board.getSize());
            int random_col = rand.nextInt(board.getSize());
            if (board.getMark(random_row, random_col) != Mark.BLANK){
                continue;
            }
            board.putMark(mark, random_row, random_col);
            break;
        }
    }

    /**
     * finds a square and put the same mark next to it
     * @param board the board we use
     * @param mark the mark of the player
     * @param row the row we want
     * @param col the col we want
     * @return true if succeeded to put the mark there
     */
    private boolean insertNextToHelper(Board board,Mark mark,int row,int col){
        int size = board.getSize()-1;
        boolean flag = false;
        if (row >= 1 && col >= 1){
            flag = board.putMark(mark,row-1,col-1);
        }
        if (!flag && col < size && row >= 1){
            flag = board.putMark(mark,row-1,col+1);
        }
        if (!flag && row < size && col >= 1){
            flag = board.putMark(mark,row+1,col-1);
        }
        if (!flag && row < size && col < size){
            flag = board.putMark(mark,row+1,col+1);
        }
        if (!flag && row >= 1){
            flag = board.putMark(mark,row-1,col);
        }
        if (!flag && row < size){
            flag = board.putMark(mark,row+1,col);
        }
        if (!flag && col >= 1){
            flag = board.putMark(mark,row,col-1);
        }
        if (!flag && col < size){
            flag = board.putMark(mark,row,col+1);
        }

        return flag;
    }

    /**
     * finds a square and put the same mark next to it
     * @param board the board we use
     * @param mark the mark of the player
     * @return true if succeeded to put the mark anywhere
     */
    private boolean insertNextTo(Board board, Mark mark){
        boolean flag = false;
        for (int i = 0; i< board.getSize();i++){
            for (int j = 0; j< board.getSize();j++){
                if(board.getMark(i,j)==mark){
                    flag = insertNextToHelper(board,mark,i,j);
                    if (flag){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * insert a mark next to 2 marks in the same direction
     * @param board the board we use
     * @param mark the mark of the player
     * @param row number of row
     * @param col number of col
     * @return true if succeeded to put the mark there
     */
    private boolean insertPlace(Board board, Mark mark, int row, int col) {
        //up
        if (row >= 2 && board.getMark(row - 1, col) == mark && board.putMark(mark, row - 2, col)) {
            return true;
        }
        //left
        if (col >= 2 && board.getMark(row, col-1) == mark && board.putMark(mark, row, col-2)) {
            return true;
        }
        //down
        if (board.getSize()-2 > row && board.getMark(row + 1, col) == mark &&
                board.putMark(mark, row + 2, col)) {
            return true;
        }
        //right
        if (board.getSize()-2 > col && board.getMark(row, col+1) == mark &&
                board.putMark(mark, row, col+2)) {
            return true;
        }
        //up-left
        if (row >= 2 && col >= 2 && board.getMark(row - 1, col-1) == mark &&
                board.putMark(mark, row - 2, col-2)) {
            return true;
        }
        //up-right
        if (row >= 2 && board.getSize()-2 > col && board.getMark(row - 1, col+1) == mark &&
                board.putMark(mark, row - 2, col+2)) {
            return true;
        }
        //down-left
        if (board.getSize()-2 > row && col >= 2 && board.getMark(row + 1, col-1) == mark &&
                board.putMark(mark, row + 2, col-2)) {
            return true;
        }
        //down-right
        if (board.getSize()-2 > row && board.getSize()-2 > col && board.getMark(row + 1, col+1) == mark &&
                board.putMark(mark, row + 2, col+2)) {
            return true;
        }

        return false;
    }

}

