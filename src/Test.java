public class Test {
    public static void main(String[] args) {
        Board board = new Board();
        MinimaxTree tree = new MinimaxTree(board,3,true);
        for (Board t : tree) {
            printBoard(t);
        }

    }

    /**
     * Creates a representation of the game board with the pieces correctly placed
     * in the form of a two dimensional array.
     * Precondition: Relies on method black() and white() to return valid positions numbered from 1-25
     * @return a two dimensional array 5 x 5 with the game pieces placed correctly
     */
    private static char[][] boardWithPieces(Board myBoard) {
        char[][] boardArr = new char[6][5]; //A-E & (no 0) 1-5
        for (int j = 1; j < boardArr.length; j++)
            for (int i = 0; i < boardArr[j].length; i++)
                boardArr[j][i] = ' '; // Fills board with empty spaces
        for (int i = 0; i < myBoard.black().length; i++)
            boardArr[((myBoard.black()[i] - 1) / 5) + 1][((myBoard.black()[i] - 1) % 5)] = 'B'; // Places black pieces
        for (int i = 0; i < myBoard.white().length; i++)
            boardArr[((myBoard.white()[i] - 1) / 5) + 1][((myBoard.white()[i] - 1) % 5)] = 'W'; // Places white pieces
        return boardArr;
    }

    /**
     * prints a representation of the board to the terminal
     */
    private static void printBoard(Board myBoard){
        System.out.println(); // new line
        int i = 0, j = 1;
        System.out.println("   A   B   C   D   E"); //upper-coordinate-line (A-E)
        char[][] boardWithPieces = boardWithPieces(myBoard);
        while (j < 6) {
            System.out.print(j + " "); //left-hand coordinate (1-5)
            while (i < 5) {
                System.out.print("[" + boardWithPieces[j][i] + "]");
                if (i < 4)
                    System.out.print("-");
                i++;
            }
            System.out.print(" " + (j)); //right-hand coordinate (1-5)
            System.out.println("");
            i = 0;
            if (j % 2 == 1 && j < 5)
                System.out.println("   | \\ | / | \\ | / |");
            else if (j % 2 == 0)
                System.out.println("   | / | \\ | / | \\ |");
            j++;
        }
        System.out.println("   A   B   C   D   E"); //bottom-coordinate-line (A-E)
        System.out.println(""); // new line
    }
}
