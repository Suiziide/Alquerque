public class Minimax {
    /**
     * returns the best move for the next player.
     * Precondition: 7 > depth unless have very good pc.
     * @param board
     * @param depth
     * @param isWhite
     * @return
     */
    public static Move nextMove(Board board, int depth, boolean isWhite) {
        return ((new MinimaxTree(board, depth e, isWhite)).nextMove());
    }
}
