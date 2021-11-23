public class Move {
    private int[] move;
    /**
     * Creates a new move with given origin and destination.
     * Precondition: from and to must be between 1 and 25.
     * @param from the place to move the piece from.
     * @param to the place to move the peiece to.
     */
    public Move(int from, int to) {
        move = new int[] {from, to};
    }

    /**
     * Returns the origin of this move.
     * @return the origin of this move.
     */
    public int from() {
        return move[0];
    }

    /**
     * Returns the destination of this move.
     * @return the destination of this move
     */
    public int to() {
        return move[1];
    }
}
