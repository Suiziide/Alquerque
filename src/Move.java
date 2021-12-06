public class Move {
    private int from;
    private int to;
    /**
     * Creates a new move with given origin and destination.
     * @param from the place to move the piece from.
     * @param to the place to move the peiece to.
     */
    public Move(int from, int to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the origin of this move.
     * @return the origin of this move.
     */
    public int from() {
        return from;
    }

    /**
     * Returns the destination of this move.
     * @return the destination of this move
     */
    public int to() {
        return to;
    }
}
