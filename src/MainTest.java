import java.util.Locale;
import java.util.Scanner;
public class MainTest {
    public static Board myBoard = new Board();
    public static Board yourBoard = new Board();
    public static Scanner reader = new Scanner(System.in);
    public static final char EMPTY = ' ';

    public static void main(String[] args) {
        /*
        // test whether an instance of Move returns the correct to and from values
        Move m1 = new Move(19,13);
        System.out.println("Move 1 excpected: 19, 13 - Got: "  + m1.from() + ", " + m1.to());
        Move m2 = new Move(2,25);
        System.out.println("Move 2 excpected: 2, 25 - Got: " + m2.from() + ", " + m2.to());
        Move m3 = new Move(23,46);
        System.out.println("Move 3 excpected: 23, 46 - Got: " + m3.from() + ", " + m3.to());
        Move m4 = new Move(-12,40);
        System.out.println("Move 4 excpected: -12, 40 - Got: " + m4.from() + ", " + m4.to());
        Move m5 = new Move(-91,-108);
        System.out.println("Move 4 excpected: -91, -108 - Got: " + m5.from() + ", " + m5.to());

        // test to see if legal moves prints the correct moves

        printBoard(myBoard);
        int from = 0;
        int to = 0;
        boolean isWhite = true;
        do {
            do {
                System.out.println("It's " + ((isWhite) ? "white to move, these are the legal moves:" : "black to move these are the legal moves:"));
                for (int i = 0; i < myBoard.legalMoves().length; i++)
                    System.out.println("From: " + myBoard.legalMoves()[i].from() + ", " + "to: " + myBoard.legalMoves()[i].to());
                System.out.println("which peice do you want to move: ");
                from = reader.nextInt();
                System.out.println("where do you want to move that piece");
                to = reader.nextInt();
            } while (!myBoard.isLegal(new Move(from, to)));
            myBoard.move(new Move(from, to));
            isWhite = !isWhite;
            printBoard(myBoard);
        } while (!myBoard.isGameOver());


        // test of white() and black()
        System.out.println("Starting positon of the board:");
        printBoard(myBoard);
        System.out.println("Position of white's pieces:");
        for (int i = 0; i < myBoard.white().length; i++)
            System.out.print(myBoard.white()[i] + ", ");
        System.out.println("\nPosition of black's pieces:");
        for (int i = 0; i < myBoard.black().length; i++)
            System.out.print(myBoard.black()[i] + ", ");

        boolean isWhite = true;
        do {
            myBoard.move(Minimax.nextMove(myBoard,5,isWhite));
            isWhite = !isWhite;
        } while (!myBoard.isGameOver());

        System.out.println("\n");
        System.out.println("Other position of board: ");
        printBoard(myBoard);
        System.out.println("Position of white's pieces:");
        for (int i = 0; i < myBoard.white().length; i++)
            System.out.print(myBoard.white()[i] + ", ");
        System.out.println("\nPosition of black's pieces:");
        for (int i = 0; i < myBoard.black().length; i++)
            System.out.print(myBoard.black()[i] + ", ");



        // test to see if the finishedGames method works and if it is incremented by MiniMax
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


        // testing hashCode and copy
        System.out.println(myBoard.hashCode());
        Move[] m = myBoard.legalMoves();
        for (int i = 0; i < m.length; i++) {
            System.out.println("From: " + m[i].from() + ", to: " + m[i].to());
        }
        Board nBoard;
        nBoard = myBoard.copy();
        System.out.println(nBoard.hashCode());

    }
*/
    // these methods were previously developed for our own Alquerque client.
    /**
     * Creates a representation of the game board with the pieces correctly placed
     * in the form of a two dimensional array.
     * Precondition: Relies on method black() and white() to return valid positions numbered from 1-25
     * @return a two dimensional array 5 x 5 with the game pieces placed correctly
     */ /*
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
     */ /*
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
 */
    }

}