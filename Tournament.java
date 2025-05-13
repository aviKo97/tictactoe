/**
 * The hole tournament game, run rhe games we want from the command line.
 * We need to choose the players, the rounds and the renderer and
 * we are ready to play!
 */
public class Tournament {

    //##############private fields#################
    private int rounds; // the number of rounds we want
    private Renderer renderer; //the renderer
    private Player player1, player2; // the players
    private int player1Wins; // num of wins of player 1
    private int player2Wins; // num of wins of player 2
    private int ties; //num of ties

    //#################Public methods###############

    /**
     * Create a Tournament
     * @param rounds we want to play
     * @param renderer the screen
     * @param player1 plays X at first
     * @param player2 plays O at first
     */
    public Tournament(int rounds, Renderer renderer, Player player1, Player player2) {
        this.rounds = rounds;
        this.renderer = renderer;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Wins = 0;
        this.player2Wins = 0;
        this.ties = 0;
    }


    /**
     * runs the games
     * @param size of the board
     * @param winStreak to win
     * @param playerName1 plays X at first
     * @param playerName2 plays O at first
     */
    public void playTournament(int size, int winStreak, String playerName1
            , String playerName2) {
        int gamesPlayed = 0;
        Mark winner;
        while (gamesPlayed < rounds){
            Game game = new Game(player1,player2,size,winStreak,renderer);
            winner = game.run();
            if (winner == Mark.X)
            {
                player1Wins++;
            }
            else if (winner == Mark.O){
                player2Wins++;
            }
            else
            {
                ties++;
            }
            gamesPlayed++;
            if (gamesPlayed == rounds){
                break;
            }
            game = new Game(player2,player1,size,winStreak,renderer);
            winner = game.run();
            if (winner == Mark.X)
            {
                player2Wins++;
            }
            else if (winner == Mark.O){
                player1Wins++;
            }
            else
            {
                ties++;
            }
            gamesPlayed++;
        }
        //Print the results
        System.out.println("######### Results #########");
        System.out.println("Player 1, "+playerName1.toLowerCase()+" won: "+ player1Wins +" rounds");
        System.out.println("Player 2, "+playerName2.toLowerCase()+" won: "+ player2Wins +" rounds");
        System.out.println("Ties: "+ ties);
    }

    /**
     * the main game
     * @param args rounds, size, winStreak, playerName, playerName2
     *             we are getting from the comend line number of rounds
     *             we want to play, the size od the board, the streak
     *             needed to win and the players names
     */
    public static void main(String[] args) {
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(args[3], size);
        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(args[4]);
        Player player2 = playerFactory.buildPlayer(args[5]);
        if (renderer == null || player1 == null || player2 ==null) {
            if (renderer == null) {
                System.out.println(Constants.UNKNOWN_RENDERER_NAME);
            }
            else {
                System.out.println(Constants.UNKNOWN_PLAYER_NAME);
            }
        }
        else {
            Tournament tournament = new Tournament(rounds, renderer, player1, player2);
            tournament.playTournament(size, winStreak, args[4], args[5]);
        }
    }
}