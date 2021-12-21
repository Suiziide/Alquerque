public class Minimax {
    public static Move nextMove(Board board, int depth, boolean isWhite) {
        return ((new MinimaxTree(board, depth, isWhite)).nextMove());
    }
}
