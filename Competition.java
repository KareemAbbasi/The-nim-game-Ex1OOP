import java.util.Scanner;
/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition {
	
	private Player firstPlayer;
	private Player secondPlayer;
    private Player currentPlayer;

    private Board gameBoard;

	private boolean withMessage;

	private int player1Score;
	private int player2Score;


    /**
     * Receives two Player objects, representing the two competing opponents,
     * and a flag determining whether messages should be displayed.
     * @param player1 The Player objects representing the first player.
     * @param player2 The Player objects representing the second player.
     * @param displayMessage a flag indicating whether game play messages should be printed to the console.
     */
	public Competition(Player player1, Player player2, boolean displayMessage){
	    firstPlayer = player1;
	    secondPlayer = player2;
	    withMessage = displayMessage;
	    currentPlayer = player1;
	    gameBoard = new Board();

    }

    /**
     * If playerPosition = 1, the results of the first player is returned.
     * If playerPosition = 2, the result of the second player is returned.
     * If playerPosition equals neiter, -1 is returned.
     * @param playerPosition The position of the player (first or second player)
     * @return the number of victories of a player.
     */
	public int getPlayerScore(int playerPosition){
	    if (playerPosition == 1){
	        return player1Score;
        } else if (playerPosition == 2){
	        return player2Score;
        } else {
	        return -1;
        }
    }
	/*
	 * Returns the integer representing the type of player 1; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer1Type(String[] args){
		try{
			return Integer.parseInt(args[0]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parsePlayer2Type(String[] args){
		try{
			return Integer.parseInt(args[1]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args){
		try{
			return Integer.parseInt(args[2]);
		} catch (Exception E){
			return -1;
		}
	}

    /**
     * Plays one turn of the Nim competition by asking the current player to make a move and checking if it
     * is a valid move. It plays the move if it is legal and asks for another one otherwise.
     */
	private void playOneTurn(){
	    int moveIsLegal = -1; /*This variable equals 0 if the move that was made is legal,
	    -1 if the coordinates exceed the boundaries of the board and -2
	    if the current move overlaps with previously marked sticks */

        int currentPlayerId = currentPlayer.getPlayerId();

        if (withMessage)
            System.out.println("Player " + currentPlayerId + ", it is now your turn!");
        while (moveIsLegal != 0) {


            Move playersMove = currentPlayer.produceMove(gameBoard);
            moveIsLegal = gameBoard.markStickSequence(playersMove);

            if (moveIsLegal != 0 && withMessage) {
                System.out.println("Invalid move. Enter another:");

            } else if (moveIsLegal == 0){
                if (withMessage)
                    System.out.println("Player " + currentPlayerId + " made the move: " + playersMove.toString());
            }
        }
        if (currentPlayer == firstPlayer){
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }

    }

    /**
     * Plays a single round between two players by calling the playOneTurn() function until there are no
     * more unmarked sticks in the board.
     */
    private void playSingleRound(){
	    while (gameBoard.getNumberOfUnmarkedSticks() != 0){
	        playOneTurn();
        }

        Player winner = currentPlayer;
	    if (winner == firstPlayer){
	        player1Score ++;
        } else {
	        player2Score ++;
        }
        if (withMessage)
            System.out.println("Player " + winner.getPlayerId() + " won!");


    }

    /**
     * Run the game for the given number of rounds.
     * @param numberOfRounds number of rounds to play.
     */
    public void playMultipleRounds(int numberOfRounds){

        System.out.println("Starting a Nim competition of " + numberOfRounds + " rounds between a " +
                firstPlayer.getTypeName() + " player and a " + secondPlayer.getTypeName() + " player.");

	    if (withMessage)
	        System.out.println("Welcome to the sticks game!");
	    for (int i = 0; i < numberOfRounds; i++){
	        playSingleRound();
	        currentPlayer = firstPlayer;
	        gameBoard = new Board();
        }
        System.out.println("The results are " + player1Score + ":" + player2Score);
    }


	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {

		int p1Type = parsePlayer1Type(args);
		int p2Type = parsePlayer2Type(args);
		int numGames = parseNumberOfGames(args);

		Scanner p1Scanner = new Scanner(System.in);
		Scanner p2Scanner = new Scanner(System.in);
		Player p1 = new Player(p1Type, 1, p1Scanner);
		Player p2 = new Player(p2Type, 2, p2Scanner);

		Competition currentCompetition = new Competition(p1, p2, true);

        currentCompetition.playMultipleRounds(numGames);

	}	
	
}
