import java.util.ArrayList;

public class Board {
    private char[] board;
    private int turn;
    private boolean isWhite;

    /**
     * Creates a new Alquerque board in the starting state:
     * each player has twelve pieces in their original position, and it is white's turn.
     */
    public Board() {
        turn = 1;
        board = new char[26];
        for (int i = 1; i < 26; i++) {
            if (i < 13)
                board[i] = 'B';
            else if (i == 13)
                board[i] = ' ';
            else
                board[i] = 'W';
        }
        isWhite = (turn % 2 == 1);
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
        board[move.to()] = board[move.from()];
        board[move.from()] = ' ';
        if (isTakeMove(move))    //if the move is a take, the taken piece is removed
            board[(move.to() + move.from()) / 2] = ' '; //calculates average position value and removes piece
        this.turn++;
        isWhite = (turn % 2 == 1);
        System.out.println("Turn: " + this.turn);
    }

    /**
     * Checks whether a move is legal.
     * Precondition: move must be an int from 1 through 25
     * @param move move input to evaluate.
     */
    public boolean isLegal(Move move){
        if (board[move.to()] != ' ')
            return false;
        else if ((isWhite && board[move.from()] != 'W') || (!isWhite && board[move.from()] != 'B')) {
            return false;
            //  } else if (!isTakeMove(move)) {
            //     return false;
        } else {
            System.out.println("From: " + move.from());
            System.out.println("To: " + move.to());
            System.out.println("isWhite: " + isWhite);
            return true;
        }
    }

    /*
     * checks whether the move is a take move
     */
    private boolean isTakeMove(Move move) {
        return ((Math.abs(move.to() - move.from()) > 6 || Math.abs(move.to() - move.from()) < 4) && (
                (isWhite && board[(move.to() + move.from()) / 2] == 'B') || //checks if opponent piece is taken
                (!isWhite && board[(move.to() + move.from()) / 2] == 'W'))); //checks if opponent piece is taken
        //ought to be revisited - might be better written as if-statements
    }





}
