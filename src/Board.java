import java.util.ArrayList;

public class Board {
    private char[] board;
    private int turn;

    /**
     * Creates a new Alquerque board in the starting state:
     * each player has twelve pieces in their original position, and it is white's turn.
     */
    public Board() {
        turn = 1;
        board = new char[26];
        for (int i = 1; i < 26; i++) { // IDK if this curly bracket should be here.
            if (i < 13)
                board[i] = 'B';
            else if (i == 13)
                board[i] = ' ';
            else
                board[i] = 'W';
        } // same with this one.
    }
    /**
     * Returns the positions of all black pieces on the board.
     * @return the positions of all black pieces on the board.
     */
    public int[] black() {
        ArrayList<Integer> blackPieces = new ArrayList<Integer>();
        for (int i = 1; i <= 25; i++)
            if (this.board[i] == 'B')
                blackPieces.add(i);
        int[] black = new int[blackPieces.size()];
        for (int i = 0; i < blackPieces.size(); i++)
            black[i] = blackPieces.get(i);
        return black;
    }

    /**
     * Returns the positions of all white pieces on the board.
     * @return the positions of all white pieces on the board.
     */
    public int[] white() {
        ArrayList<Integer> whitePieces = new ArrayList<Integer>();
        for (int i = 1; i <= 25; i++)
            if (this.board[i] == 'W')
                whitePieces.add(i);
        int[] white = new int[whitePieces.size()];
        for (int i = 0; i < whitePieces.size(); i++)
            white[i] = whitePieces.get(i);
        return white;
    }


    /**
     * Moves a piece and updates the board correspondingly.
     * Precondition: move must be a valid Move
     * @param move the move to simulate.
     */
    public void move(Move move) {
        /*
        board[to] = board[from];
        board[from] = ' ';
        this.turn++;
        */
        board[move.to()] = board[move.from()];
        board[move.from()] = ' ';
        this.turn++;
    }




}
