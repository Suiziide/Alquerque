public class Minimax {
    public Minimax() {} // empty constructor

    public static Move nextMove(Board board, int depth, boolean isWhite) {
        MinimaxTree newTree = new MinimaxTree(board,depth, isWhite);
        // vælg bedste move fra newTree
        return newTree.next(); // returnerer det bedste move
    }
}
