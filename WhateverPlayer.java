import java.util.*;

/**
 * The WhateverPlayer class.
 * In this department we can build a WhateverPlayer .
 * This player will choose random places for his turn.
 */
public class WhateverPlayer implements Player{

    //#################Public methods###############
    /**
     * Default random player constructor
     */
    public WhateverPlayer() {
    }

    /**
     * play a turn randomly
     * @param board the board we use
     * @param mark the mark of the player
     */
    @Override
    public void playTurn(Board board, Mark mark) {
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
}
