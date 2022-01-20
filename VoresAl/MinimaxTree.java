import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MinimaxTree implements Iterable<Board> {
    private Node root;

    /**
     * Creates a new instance of MinimaxTree
     * @param board
     * @param depth
     * @param isWhite
     */
    public MinimaxTree(Board board, int depth, boolean isWhite) {
        this.root = new Node(board, null, depth, isWhite, true);
    }

    /**
     *
     * Returns the best move of this MinimaxTree by passing the children of the root to bestMove() and returning the
     * move corrosponding to the board with the highest value.
     * Precondition: the game is not over
     * @return best move for the next player on this MinimaxTree
     */
    public Move nextMove() {
        int[] moveValues = new int[root.next.length];
        for (int i = 0; i < moveValues.length; i++)
            moveValues[i] = bestMove(root.next[i]);
        int indexOfMax = 0;
        int maxValue = moveValues[0];
        for (int i = 1; i < moveValues.length; i++)
            if (maxValue < moveValues[i]) {
                indexOfMax = i;
                maxValue = moveValues[i];
            }
        return root.next[indexOfMax].move;
    }

    /*
     * Implements a node class for representing nodes in a MinimaxTree
     */
    private static class Node {
        private Board boardState;
        boolean isMaximizeNode;
        boolean isWhite;
        private Move move;
        private Node[] next;

        /*
         * Constructor for creating nodes
         */
        private Node(Board board, Move move, int depth, boolean isWhite, boolean isMaximizeNode) {
            this.boardState = board;
            this.move = move;
            next = null;
            this.isMaximizeNode = isMaximizeNode;
            this.isWhite = isWhite;
            addNodes(depth, isWhite);
        }

        /*
         * Auxiliary method for creating nodes
         */
        private void addNodes(int depth, boolean isWhite) {
            Move[] legalMoves = boardState.legalMoves();
            if (depth != 0 && legalMoves.length != 0) {
                next = new Node[legalMoves.length];
                for (int i = 0; i < legalMoves.length; i++) {
                    Board copy = boardState.copy();
                    copy.move(legalMoves[i]);
                    next[i] = new Node(copy, legalMoves[i], depth - 1, isWhite, !isMaximizeNode);
                }
            }
        }
    }

    /*
     * Creates an iterator for the MinimaxTree
     */
    private class MinimaxTreeIterator implements Iterator<Board> {
        Stack<Board> boardList = new Stack<>();

        public MinimaxTreeIterator() {
            boardList.push(root.boardState);
            addChildren(root);
        }

        public boolean hasNext() {
            return (!boardList.isEmpty());
        }

        public Board next() throws NoSuchElementException {
            if (boardList.isEmpty())
                throw new NoSuchElementException();
            else
                return boardList.pop();
        }

        /*
         * Auxiliary method for adding all the board states from each node to a stack
         */
        private void addChildren(Node parent) {
            if (parent.next != null)
                for (int i = 0; i < parent.next.length; i++) {
                    boardList.push(parent.next[i].boardState);
                    addChildren(parent.next[i]);
                }
        }
    }

    /**
     * Constructor for the iterator
     * @return new instance of the MinimaxTreeIterator()
     */
    public Iterator<Board> iterator() throws NoSuchElementException {
        return new MinimaxTreeIterator();
    }

    /*
     * Auxiliary method for recursively finding the value of boards in terminal state or at max depth,
     * and returning the value of these boards
     */
    private int bestMove(Node n) {
        if (n.next != null) { // checks if n has child nodes
            int[] valArr = new int[n.next.length];
            for (int i = 0; i < valArr.length; i++) {
                if (n.next[i].next == null) // checks if n's child nodes do not have any child nodes
                    valArr[i] = valueOfBoard(n.next[i].boardState, n.next[i].isWhite);
                else
                    valArr[i] = bestMove(n.next[i]);
            }
            return (n.isMaximizeNode) ? maxValue(valArr) : minValue(valArr);
        }
        return valueOfBoard(n.boardState, n.isWhite);
    }

    /*
     * Auxiliary method for finding the maximum value in an array
     */
    private int maxValue(int[] v) {
        int max = v[0];
        for (int i = 1; i < v.length; i++)
            if (max < v[i])
                max = v[i];
        return max;
    }

    /*
     * Auxiliary method for finding the minimum value in an array
     */
    private int minValue(int[] v) {
        int min = v[0];
        for (int i = 1; i < v.length; i++)
            if (min > v[i])
                min = v[i];
        return min;
    }

    /*
     * Auxiliary method to calculate the value of a specific board state
     */
    private static int valueOfBoard(Board board, boolean isWhite) {
        if (board.legalMoves().length == 0) {
            if (isWhite && board.black().length == 0)
                return Integer.MAX_VALUE;
            else if (!isWhite && board.white().length == 0)
                return Integer.MAX_VALUE;
            else if (isWhite && board.white().length == 0)
                return Integer.MIN_VALUE;
            else if (!isWhite && board.black().length == 0)
                return Integer.MIN_VALUE;
            else
                return -20; // if further behind than -20, and a draw is possible, the autoplayer will try to draw
        } else {
            int value = 0;
            int[] array = (isWhite) ? board.white() : board.black(); //new array is set to the current players array of pieces
            int[] enemyArray = (!isWhite) ? board.white() : board.black(); //new array is set to the enemy players array of pieces
            for (int i = 0; i < array.length; i++) {        //for every allied piece;
                value = value + 5;          //+5 per piece
                if (array[i] % 2 == 1)
                    value = value + 1;      //+1 per piece on odd numbers (more options)
                if (array[i] % 5 == 1 || array[i] % 5 == 0)
                    value = value + 1;      //+1 per piece on the borders (can't be taken diagonally)
            }
            for (int i = 0; i < enemyArray.length; i++) {   //for every enemy piece
                value = value - 5;          //-5 per piece
                if (enemyArray[i] % 2 == 1)
                    value = value - 1;      //-1 per piece on odd numbers
                if (enemyArray[i] % 5 == 1 || enemyArray[i] % 5 == 0)
                    value = value - 1;      //-1 per piece on the borders
                if (!isWhite && (enemyArray[i] < 6))
                    value = value - 2;      //-2 per white piece on blacks backline (when turn is black)
                else if (isWhite && (enemyArray[i] > 20))
                    value = value - 2;      //-2 per black piece on whites backline (when turn is white)
            }
            return value;
        }
    }
}
