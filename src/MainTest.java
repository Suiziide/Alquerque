import java.util.Locale;
import java.util.Scanner;
public class MainTest {
    public static Board myBoard = new Board();
    public static Board yourBoard = new Board();
    public static Scanner reader = new Scanner(System.in);
    public static final char EMPTY = ' ';

    public static void main(String[] args) {

        /*
        // to test the incrementation of finished games when isGameOver() is called.
        for (int i = 0; i < 26; i++)
            myBoard.removePiece(i);

        for (int i = 0; i < 10; i++)
            myBoard.isGameOver();

        myBoard = new Board();
        for (int i = 0; i < 26; i++)
            myBoard.removePiece(i);
        myBoard.isGameOver();

        myBoard = new Board();

        System.out.println(myBoard.finishedGames());
        */

        // test isLegal without the middel if statement
        /*
        for (int i = 0; i < 26; i++) {
            myBoard.removePiece(i);
        }
        myBoard.insertPiece(14, 'W');
        myBoard.insertPiece(8, 'B');
        printBoard(myBoard);
        Move nextMove;
        do {
            do {
                System.out.print("Make a move: ");
                int from = reader.nextInt();
                int to = reader.nextInt();
                nextMove = new Move(from, to);
            } while (!myBoard.isLegal(nextMove));
            myBoard.move(nextMove);
            printBoard(myBoard);
        } while (!myBoard.isGameOver());
      */

        /*
        // test to see if the finishedGames method works
        int white = 0;
        int black = 0;
        int draw = 0;
        boolean end = false;
        boolean isWhite = true;
        do {
            System.out.println("Games Played: " + Board.finishedGames());
            myBoard = new Board();
            printBoard(myBoard);
            do {
                Move nextMove = Minimax.nextMove(myBoard, 5, isWhite);
                myBoard.move(nextMove);
                printBoard(myBoard);
                isWhite = !isWhite;
            } while (!myBoard.isGameOver());
            if (myBoard.white().length == 0) {
                System.out.println("White won!");
                black++;
            } else if (myBoard.black().length == 0) {
                System.out.println("White won!");
                white++;
            } else {
                System.out.println("Draw.");
                draw++;
            }
            System.out.println("Stats:");
            System.out.println("White's wins: " + white);
            System.out.println("Black's wins: " + black);
            System.out.println("Draws: " + draw);

            System.out.println();
            System.out.print("Do you want to continue with another game? (y/n): ");
            end = ((reader.nextLine().toLowerCase().charAt(0) == 'n') ? true : false);
        } while (!end);
        /*

        // recreating moveing from 15 to 17 bug
        /*
        System.out.println("Boards starting positon.");

        for (int i = 0; i < 26; i++) {
            myBoard.removePiece(i);
        }
        myBoard.insertPiece(15, 'W');
        myBoard.insertPiece(16, 'B');
        printBoard();
        System.out.println("White moved from 15 to 17.");
        myBoard.move(new Move(15,17));
        myBoard.removePiece(16);
        printBoard();
        */

        // recreating moving from 19 from 13 and 16 dissapears
        /*
        myBoard.insertPiece(16, 'B');
        System.out.println("White moved from D4 to C3.");
        myBoard.move(new Move(19,13));
        printBoard();
         */

        /*
        myBoard.move(new Move(19,13));
        myBoard.removePiece(16);
        System.out.println("White moved from 19 to 13.");
        printBoard();
        */




        // testing hashCode and copy
        /*
        System.out.println(myBoard.hashCode());
        Move[] m = myBoard.legalMoves();
        for (int i = 0; i < m.length; i++) {
            System.out.println("From: " + m[i].from() + ", to: " + m[i].to());
        }
        nBoard = myBoard.copy();
        System.out.println(nBoard.hashCode());
        */
    }

    // these methods were previously developed for our own Alquerque client.
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
    private static void printBoard(Board myBoard) {
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
