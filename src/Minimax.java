public class Minimax {
    public Minimax() {} // empty constructor

    public static Move nextMove(Board board, int depth, boolean isWhite) {
        return ((new MinimaxTree(board, depth, isWhite)).next());
    }
}
