import java.util.Random;

/**
 * The clever player class.
 * In this department we can build a clever player.
 * This player will perform better than whatever player.
 * The class allows a better choice of squares for the player
 */
public class CleverPlayer implements Player{

    //#################Public methods###############

    /**
     * default constructor for clever player
     */
    public CleverPlayer(){

    }

    /**
     * play a turn according to the player strategy
     * @param board the board we use
     * @param mark the mark of the player
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        if (insertNextTo(board,mark)) {
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

}
