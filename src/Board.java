import java.util.ArrayList;

public class Board {
    private char[] board;
    private int turn;
    private boolean isWhite;
    private static int finishedGames = 0;

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
     * Precondition: move must be a legal Move
     * @param move the move to simulate.
     */
    public void move(Move move) {
        board[move.to()] = board[move.from()];
        board[move.from()] = ' ';
        if (isTakeMove(move))    //if the move is a take, the taken piece is removed
            board[(move.to() + move.from()) / 2] = ' '; //calculates average position value and removes piece
        this.turn++;
        isWhite = (turn % 2 == 1);
    }

    /**
     * Checks whether a move is legal.
     * Precondition: move must be an int from 1 through 25
     * @param move move input to evaluate.
     */
    public boolean isLegal(Move move) {
        if (board[move.to()] != ' ') {
            return false;
        } else if ((isWhite && board[move.from()] != 'W') || (!isWhite && board[move.from()] != 'B')) {
            return false;
        } else if (fileDiff(move) > 2) {
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
        return true;
    }

    /**
     * Returns an array of all legal moves for this board
     * @return an array of all legal moves for this board
     */
    public Move[] legalMoves() {
        ArrayList<Move> legalList = new ArrayList<Move>();
        for (int i = 1; i < board.length; i++)
            if (board[i] != ' ')
                for (int j = 1; j < board.length; j++)
                    if (isLegal(new Move(i,j)))
                        legalList.add(new Move(i,j));
        Move[] legalMoves = new Move[legalList.size()];
        for (int i = 0; i < legalList.size(); i++) {
            legalMoves[i] = legalList.get(i);
        }
        return legalMoves;
    }


    /**
     * Returns if the game is over
     * @return if the game is over
     */
    public boolean isGameOver() {
        if (white().length == 0 || black().length == 0 || legalMoves().length == 0) {
            finishedGames++;
            return true;
        } else
            return false;
    }

    /**
     * Returns a copy of this board
     * @return a copy of this board
     */
    public Board copy() {
        Board newBoard = new Board();
        for (int i = 0; i < this.board.length; i++)
            newBoard.board[i] = this.board[i];
        newBoard.turn = this.turn;
        newBoard.isWhite = this.isWhite;
        return newBoard;
    }

    /**
     * Checks whether this Board is equal to other Object
     * @param other Object to check against this board
     * @return whether this Board is equal to other Object
     */
    public boolean equals(Object other){
        if (other == null)
            return false;
        else if (this == other)
            return true;
        else if (!(other instanceof Board))
            return false;
        Board otherBoard = (Board)other;
        int i = 0;
        while(i < this.board.length && this.board[i] == otherBoard.board[i]){
            i++;
        }
        return (i == this.board.length && this.turn == otherBoard.turn);
    }
    
    
    /**
     * Returns a hashCode compised of this boards attributes
     * @return a hashCode comprised of this boards attributes
     */
    public int hashCode() {
        return (this.board.hashCode() + this.turn * 31);
    }

    /**
     * Returns how many objects of type Board that represents games, that are finished games.
     * @return how many objects of type Board that represents games, that are finished games.
     */
    public int finishedGames() {
        return finishedGames;
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
