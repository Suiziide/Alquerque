import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MinimaxTree implements Iterable<Board> {
    private Node root;

    /**
     * Creates a new MinimaxTree
     * @param board
     * @param depth
     * @param isWhite
     */
    public MinimaxTree(Board board, int depth, boolean isWhite) {
        this.root = new Node(board, null, depth, isWhite, true);
    }

    /**
     * returns the best move found by evaluateTree()
     * Precondition: the game is not over
     * @return best move for the next player on this MinimaxTree
     */
    public Move next() {
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
        /*
           System.out.println("maxvalue = " + maxValue);
           for (int moveValue : moveValues) {
           System.out.print("movevalues: " + moveValue + " \n");
           }
           */
        return root.next[indexOfMax].move;
    }

    /*
     * Implements a node class for representing vertecies in the MinimaxTree
     */
    private static class Node {
        private Board boardState;
        private int boardValue;
        private int depth;
        boolean isMaximizeNode;
        boolean isWhite;
        private Move move;
        private Node[] next;

        /*
         * Inner classe for creating nodes to represent a MinimaxTree
         */
        private Node(Board board, Move move, int depth, boolean isWhite, boolean isMaximizeNode) {
            this.boardState = board;
            this.move = move;
            next = null;
            this.depth = depth;
            this.isMaximizeNode = isMaximizeNode;
            this.isWhite = isWhite;
            addNodes(depth, isWhite);
        }

        /*
         * Creates parent nodes' children nodes
         */
        private void addNodes(int depth, boolean isWhite) {
            if (depth == 0 || boardState.legalMoves().length == 0)
                boardValue = MinimaxTree.valueOfBoard(boardState, isWhite);
            else {
                next = new Node[boardState.legalMoves().length];
                Move[] legalMoves = boardState.legalMoves();
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
    private class MinimaxTreeIterator implements Iterator {
        Node current;
        Stack<Board> boardList;

        public MinimaxTreeIterator() {
            current = root;
            boardList = addChildren(current);
        }

        public boolean hasNext() {
            return (!boardList.isEmpty());
        }

        public Board next() {
            if (boardList.isEmpty())
                throw new NoSuchElementException();
            else {
                return boardList.pop();
            }
        }

        /*
         * Auxiliary method for adding all the board states from each node to a stack
         */
        private Stack<Board> addChildren(Node parent) {
            Stack<Board> stack = new Stack<>();
            if (parent.next != null) {
                for (int i = 0; i < parent.next.length; i++) {
                    stack.push(parent.next[i].boardState);
                    stack.addAll(addChildren(parent.next[i]));
                }
            }
            return stack;
        }
    }

    public Iterator iterator() {
        return new MinimaxTreeIterator();
    }

    /*
     * Auxiliary method for finding the highest value of move
     */
    private int bestMove(Node n) {
        if (n.next != null) {
            int[] valArr = new int[n.next.length];
            for (int i = 0; i < valArr.length; i++) {
                if (n.next[i].next == null) {
                    valArr[i] = valueOfBoard(n.boardState, n.isWhite);
                    //System.out.println(valueOfBoard(n.boardState, n.isWhite));
                } else
                    bestMove(n.next[i]);
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
        //System.out.println("v: " + v[0]);
        for (int i = 1; i < v.length; i++)
            if (min > v[i]) {
                min = v[i];
                //System.out.println("v: " + v[i]);
            }
        //System.out.println("min: " + min);
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
            else
                return Integer.MIN_VALUE;
        } else {
            int value = 0;
            int[] array = (isWhite) ? board.white() : board.black();
            for (int i = 0; i < array.length; i++) {
                if (array[i] % 2 == 1) value++;
                if (array[i] % 5 == 1 || board.white()[i] % 5 == 0) value++;
                if (isWhite) {
                    value += 5;
                    if (board.white()[i] < 6) value += 2;
                } else 
                    value += 5;
                if (board.black()[i] > 20) value += 2;

            }
            return value;
        }
    }

}
