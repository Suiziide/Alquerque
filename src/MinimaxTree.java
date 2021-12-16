import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class MinimaxTree implements Iterable<Board> {
    private Node root;

    /**
     * Creates a new MinimaxTree
     *
     * @param board
     * @param depth
     * @param isWhite
     */
    public MinimaxTree(Board board, int depth, boolean isWhite) {
        this.root = new Node(board, null, depth, isWhite, true);
    }

    /**
     * @param <Board>
     */
    private static class Node {
        private Board boardState;
        private int boardValue;
        private boolean isMaximizeNode;
        private Move move;
        private Node[] next;

       /*
        * Inner classe for creating nodes to represent a MinimaxTree
        */
        private Node(Board board, Move move, int depth, boolean isWhite, boolean isMaximizeNode) {
            this.boardState = board;
            this.move = move;
            this.isMaximizeNode = isMaximizeNode;
            next = null;
            addNodes(depth, isWhite, isMaximizeNode);
        }

        /*
         * Creates parent nodes' children nodes
         */
        private void addNodes(int depth, boolean isWhite, boolean isMaximizeNode) {
            if (depth == 0 || boardState.legalMoves().length == 0)
                boardValue = MinimaxTree.valueOfBoard(boardState, isWhite, isMaximizeNode);
            else {
                next = new Node[boardState.legalMoves().length];
                Move[] legalMoves = boardState.legalMoves();
                for (int i = 0; i < legalMoves.length; i++) {
                    Board copy = boardState.copy();
                    copy.move(legalMoves[i]);
                    System.out.println("from: " + legalMoves[i].from() + ", to: " + legalMoves[i].to());
                    next[i] = new Node(copy, legalMoves[i], depth - 1, isWhite, isMaximizeNode);
                }
            }
        }
    }

    // creates an iterator for the stack class
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
         * Auxiliary method for adding all the board states to a stack
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

    /**
     * returns the best move found by evaluateTree()
     * Precondition: the game is not over
     * @return best move for the next player on this MinimaxTree
     */
    public Move next() {
        bestMove(root);
        return (new Move(0,0));
    }

    /*
     *
     */
    private int bestMove(Node n) {
        return 1;
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