public class MinimaxTree /*implements Iterable<Board>*/{
    private Node[] root;
    private Board board;

    // lav kopi af board og foretag et legal move repeat for alle legal moves
    // gør det samme for de nye board indtil depth er nået
    // vælg den besdste position og returner et move

    /**
     * Creates a new MinimaxTree
     * @param board
     * @param depth
     * @param isWhite
     */
    public MinimaxTree(Board board, int depth, boolean isWhite) {
        this.board = board.copy();
        root = null;
        addNodes(this.board, depth);
        // evaluateTree(); returns the best move. hjælpemetoder min() max() for at


    }

    private void addNodes(Board board, int depth) {
        if (root == null) {
            // adds the first layer of nodes
            root = new Node[board.legalMoves().length];
            for (int i = 0; i < this.board.legalMoves().length; i++)
                root[i] = new Node(this.board.copy().move(this.board.legalMoves[i]),
                        this.board.legalMoves[i], depth);
        } else
            for (int i = 0; i < root.length; i++)
                root[i].addNodes(depth-1);
    }

    /**
     *
     * @param <Board>
     */
    private static class Node<Board> {
        private Board board;
        private Move move;
        private Node[] next;
        private int size;
        private int boardValue;

        /**
         *
         * @param board
         */
        private Node(Board board, Move move, int depth) {
            this.board = board;
            this.move = move;
            this.next = null;
            this.size = board.legalMoves().length;
            if (this.board.isGameOver() || depth == 0)
                this.boardValue = board.white().length - board.black().length;

        }

        public void addNodes(int depth) {
            if (depth != 0 && !board.isGameOver()) {
                next = new Node[size];
                for (int i = 0; i < size; i++)
                    next[i] = new Node(this.board.copy().move(this.board.legalMoves[i]),
                            this.board.legalMoves[i], depth-1);
                addNodes(depth-1);
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
    private int evaluateBoard(int depth) {
        if (depth == 0 && !isWhite && board.black().length > board.white().length) {
            return 1;
        } else if (depth == 0 && !isWhite && board.black().length < board.white().length) {
            return -1;
        } else if (board.black().length > board.white().length && !isWhite) {
            return 1 + evaluateBoard(depth - 1);
        } else if (board.black().length < board.white().length && isWhite) {
                return -1 + evaluateBoard(depth - 1);
        }
    }
*/


}
