public class MinimaxTree /*implements Iterable<Board>*/{
    private Node[] root;
    private Board board;
    private Move[] legalMoves;

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
        this.root = null;
        this.legalMoves = board.legalMoves();
        addNodes(depth);
        // evaluateTree(); returns the best move. hjælpemetoder min() max() for at
    }

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

    /**
     *
     * @param <Board>
     */
    private static class Node {
        private Board board;
        private Move move;
        private Node[] next;
        private Move[] legalMoves;
        private int boardValue;

        /**
         *
         * @param board
         */
        private Node(Board board, Move move) {
            this.board = board;
            this.move = move;
            this.next = null;
            this.legalMoves = board.legalMoves();
            if (this.board.isGameOver())
                this.boardValue = board.white().length - board.black().length;
        }

        public void addNodes(int depth) {
            if (depth > 0) {
                next = new Node[legalMoves.length];
                for (int i = 0; i < legalMoves.length; i++) {
                    System.out.println("from: " + legalMoves[i].from() + ", to: " + legalMoves[i].to());
                    Board copy = board.copy();
                    copy.move(legalMoves[i]);
                    next[i] = new Node(this.board, legalMoves[i]);
                    //System.out.println("NyNode");
                }
                addNodes(depth-1);
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
    }
