public class MinimaxTree /*implements Iterable<Board>*/{
    private Node root;

    /**
     * Creates a new MinimaxTree
     * @param board
     * @param depth
     * @param isWhite
     */
    public MinimaxTree(Board board, int depth, boolean isWhite) {
        this.root = new Node(board,null, depth, isWhite, true);
    }
    /* too complicated did something else. (keep for repport)
    private void addNodes(int depth) {
        // adds the first layer of nodes
        if (root == null) {
            root = new Node[legalMoves.length];
            for (int i = 0; i < legalMoves.length; i++) {
                Board copy = board.copy();
                copy.move(legalMoves[i]);
                root[i] = new Node(copy, legalMoves[i]);
                //System.out.println("NyNode");
            }
            addNodes(depth-1);
        } else
            for (int i = 0; i < 1; i++)
                root[i].addNodes(root, depth);
    }
    */

    /**
     *
     * @param <Board>
     */
    private static class Node {
        private Board boardState;
        private int boardValue;
        private boolean isMaximizeNode;
        private Move move;
        private Node[] next;

        /**
         *
         * @param board
         */
        private Node(Board board, Move move, int depth, boolean isWhite, boolean isMaximizeNode) {
            this.boardState = board;
            this.move = move;
            this.isMaximizeNode = isMaximizeNode;
            next = new Node [boardState.legalMoves().length];
            addNodes(depth, isWhite, isMaximizeNode);
        }

        private void addNodes(int depth, boolean isWhite, boolean isMaximizeNode) {
            if (depth == 0 || boardState.legalMoves().length == 0)
                boardValue = MinimaxTree.valueOfBoard(boardState, isWhite, isMaximizeNode);
            else {
                Move[] legalMoves = boardState.legalMoves();
                for (int i = 0; i < legalMoves.length; i++) {
                    Board copy = boardState.copy();
                    copy.move(legalMoves[i]);
                    System.out.println("from: " + legalMoves[i].from() + ", to: " + legalMoves[i].to());
                    next[i] = new Node(copy, legalMoves[i], depth-1, isWhite, isMaximizeNode);
                }
            }
        }
    }

    /**
     * returns the best move found by evaluateTree()
     * Precondition: the game is not over
     * @return best move for the next player on this MinimaxTree
     */
    public Move next() {
        return new Move(0,0);
    }

    /*
     * Auxiliary method to calculate the value of a specific board state
     */
    private static int valueOfBoard(Board board, boolean isWhite, boolean isMaximizeNode) {
        return ((isWhite) ? board.white().length - board.black().length : board.black().length - board.white().length);
        // mangler at tage højde for om det er en minimerings node eller en maximerings node
    }
}


// lav kopi af board og foretag et legal move repeat for alle legal moves
// gør det samme for de nye board indtil depth er nået
// vælg den besdste position og returner et move