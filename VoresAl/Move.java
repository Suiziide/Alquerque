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

    /**
     * Creates a copy of this move
     * @return a copy of this move
     */
    public Move copy() {
        return new Move(this.from, this.to);
    }

    /**
     * Checks whether other move is equal to this move
     * @param other
     * @return whether other move is equal to this move
     */
    public boolean equals(Object other) {
        if (other == null) return false;
        else if (this == other) return true;
        else if (!(other instanceof Move)) return false;
        Move otherMove = (Move) other;
        return (this.from == otherMove.from && this.to == otherMove.to);
    }

    /**
     * creates a hashCode for this move
     * @return the hashCode for this move
     */
    public int hashCode() {
        return (this.from + this.to*31);
    }
}
