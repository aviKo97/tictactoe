import java.util.Random;

/**
 * The Player interface, When we create a player he has to
 * implement the playTurn method
 */
interface Player {
    /**
     * the method that implements the player strategy on the board
     * @param board
     * @param mark of the player
     */
    void playTurn(Board board, Mark mark);
    
}
