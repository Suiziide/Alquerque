import java.util.Scanner;
public class MainTest {
    public static Board myBoard = new Board();
    public static Scanner reader = new Scanner(System.in);
    public static final char EMPTY = ' ';

    public static void main(String[] args) {
        System.out.println(myBoard.hashCode());
        Move[] m = myBoard.legalMoves();
        for (int i = 0; i < m.length; i++) {
            System.out.println("From: " + m[i].from() + ", to: " + m[i].to());
        }

        /*
        boolean isWhite = true;
        String userInput;
        boolean endGame = false;
        Move nextMove = new Move(0,0);
        printBoard();
        int i = 1;
        boolean legalMove = false;
        do {
            do {
                System.out.print("Please enter the piece you want to move: ");
                int from = reader.nextInt();
                System.out.print("Please enter where you want to move the piece: ");
                int to = reader.nextInt();
                nextMove = new Move(from, to);
                if (!myBoard.isLegal(nextMove)) {
                    System.out.println("Not a legal move, try again.");
                    legalMove = false;
                } else
                    legalMove = true;
            } while(!legalMove);
            myBoard.move(nextMove); // says there is a problem but compiles and runs fine.
            printBoard();
            i++;
        } while(i < 1000000000);
        System.out.println("Game was aborted");
        */
    }

    // these methods were previously developed for our own Alquerque client.
    /**
     * Creates a representation of the game board with the pieces correctly placed
     * in the form of a two dimensional array.
     * Precondition: Relies on method black() and white() to return valid positions numbered from 1-25
     * @return a two dimensional array 5 x 5 with the game pieces placed correctly
     */
    private static char[][] boardWithPieces() {
        char[][] boardArr = new char[6][5]; //A-E & (no 0) 1-5
        for (int j = 1; j < boardArr.length; j++)
            for (int i = 0; i < boardArr[j].length; i++)
                boardArr[j][i] = EMPTY; // Fills board with empty spaces
        for (int i = 0; i < myBoard.black().length; i++)
            boardArr[((myBoard.black()[i] - 1) / 5) + 1][((myBoard.black()[i] - 1) % 5)] = 'B'; // Places black pieces
        for (int i = 0; i < myBoard.white().length; i++)
            boardArr[((myBoard.white()[i] - 1) / 5) + 1][((myBoard.white()[i] - 1) % 5)] = 'W'; // Places white pieces
        return boardArr;
    }

    /**
     * prints a representation of the board to the terminal
     */
    private static void printBoard() {
        System.out.println(); // new line
        int i = 0, j = 1;
        System.out.println("   A   B   C   D   E"); //upper-coordinate-line (A-E)
        char[][] boardWithPieces = boardWithPieces();
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
