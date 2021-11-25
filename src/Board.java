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
     *
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
     *
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
     *
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
     *
     * @param move move input to evaluate.
     */
    public boolean isLegal(Move move) {
        if (board[move.to()] != ' ') {
            System.out.println("Not empty");
            return false;
        } else if ((isWhite && board[move.from()] != 'W') || (!isWhite && board[move.from()] != 'B')) {
            System.out.println("Wrong piece");
            return false;
        } else if (fileDiff(move) > 2) {
            System.out.println("Trying to move to an illegal column");
            return false;
        } else if (!isTakeMove(move)) {
            if ((isWhite && (pieceDiff(move) < -6 || pieceDiff(move) > -4)) ||
                    (!isWhite && (pieceDiff(move) < 4 || pieceDiff(move) > 6)))
                return false;
            else if (move.from() % 2 == 0 && move.to() % 2 == 0)
                return false;
        } else if (isTakeMove(move)) {
            if (Math.abs(pieceDiff(move)) != 2 && Math.abs(pieceDiff(move)) != 8 &&
                    Math.abs(pieceDiff(move)) != 10 && Math.abs(pieceDiff(move)) != 12) {
                return false;
            }else if (move.from() % 2 == 0 && move.to() % 2 == 1)
                return false;
            else if (move.from() % 2 == 0 && Math.abs(pieceDiff(move)) != 10 && Math.abs(pieceDiff(move)) != 2)
                return false;
        }
        System.out.println("From: " + move.from());
        System.out.println("To: " + move.to());
        System.out.println("isWhite: " + isWhite);
        return true;
    }


    /*
     * Auxillerary methods to check how far there are between columns columns
     */
    private int fileDiff(Move move){
        return Math.abs(((move.from() - 1) % 5 + 1) - ((move.to() - 1) % 5 + 1));
    }
    /*
     * Auxillerary method to check how far there is between two pieces
     */
    private int pieceDiff(Move move) {
        return (move.to() - move.from());
    }
    /*
     * checks whether the move is a take move
     */
    private boolean isTakeMove(Move move) {
        return ((Math.abs(pieceDiff(move)) > 6 || Math.abs(pieceDiff(move)) < 4) && (
                (isWhite && board[(move.to() + move.from()) / 2] == 'B') || //checks if opponent piece is taken
                        (!isWhite && board[(move.to() + move.from()) / 2] == 'W'))); //checks if opponent piece is taken
        //ought to be revisited - might be better written as if-statements
    }
}
