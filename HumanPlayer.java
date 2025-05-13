/**
 * The human player class
 * Here we can let the human player choose his
 * favorite place to put his mark on.
 */
public class HumanPlayer implements Player{

    //#################Public methods###############

    /**
     * Default human player constructor
     */
    HumanPlayer(){

    }

    /**
     * play a turn the human wants
     * @param board we play on
     * @param mark of the player
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        System.out.println(Constants.playerRequestInputString(mark.toString()));
        while (true) {
            int input = KeyboardInput.readInt();
            int row;
            int col;
            if (input >= 10){
                row = input / 10;
                col = input % 10;
            }
            else {
                row = 0;
                col = input;
            }

            //check if coordinates are good
            if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
                System.out.println(Constants.INVALID_COORDINATE);
                continue;
            }
            //check if valid square
            if (board.getMark(row, col) != Mark.BLANK) {
                System.out.println(Constants.OCCUPIED_COORDINATE);
                continue;
            }
            //insert the mark to the board
            board.putMark(mark, row, col);
            break;
        }
    }
}
