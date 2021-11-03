import java.util.Scanner;
public class Alquerque {
    private static Scanner reader = new Scanner(System.in);
    private static String whiteName = "White(CPU)", blackName = "Black(CPU)";  // Placeholder names for player 1 and player 2.
    private static boolean isWhiteCPU, isBlackCPU;
    private static Board board = new Board();
    private static int cpuDepth;

    public static void main(String[] args) {
        //init();
        int moveFrom = 0;
        int moveTo = 0;
        Move nextMove;
        printBoard(); // flyt måske til init eller et andet sted.

        do {
            if (!isWhiteCPU && !board.isGameOver()) { // fjern måske !board.isGameOver();
                do {
                    System.out.print("\nIt's "+ whiteName + ", please enter which piece you want to move: ");
                    moveFrom = reader.nextInt(); // input validation on convertCoordinate method
                    System.out.print("Where do you want to move that piece: ");
                    moveTo = reader.nextInt(); // input validation on convertCoordinate method
                    System.out.println(); // newline
                    nextMove = new Move(moveFrom, moveTo);
                } while(!board.isLegal(nextMove));
                board.move(nextMove);
                printBoard();
            } else if (!board.isGameOver()) {
                // make CPU do a move.
            }

            if (!isBlackCPU && !board.isGameOver()) { // fjern måske !board.isGameOver();
                do {
                    System.out.print("\nIt's " + blackName + ", please enter which piece you want to move: ");
                    moveFrom = reader.nextInt(); // input validation on convertCoordinate method
                    System.out.print("Where do you want to move that piece: ");
                    moveTo = reader.nextInt(); // input validation on convertCoordinate method
                    System.out.println(); // newline;
                    nextMove = new Move(moveFrom, moveTo);
                } while(!board.isLegal(nextMove));
                board.move(nextMove);
                printBoard();
            } else if (!board.isGameOver()) {
                // make CPU do a move.
            }
        } while(!board.isGameOver());
    }


    /**
     * Initializes the program and runs the start menu.
     */
    private static void init() {
        int option;
        System.out.println("Greetings Master! And welcome to Alquerque.");
        do {
            printOptions();
            System.out.print("Please enter the number corresponding to the option you want executed: ");
            option = reader.nextInt();
            switch (option) {
                case 0:
                    System.out.println("You have chosen option " + option + ": Exit program");
                    System.out.println("Thank you for playing, have a nice day!");
                    break;
                case 1: // Player vs Player
                    System.out.println("You have chosen option " + option + ": Player vs Player");
                    System.out.print("Please enter the name of player 1: ");
                    reader.nextLine(); // clears terminal input
                    whiteName = reader.nextLine().trim();
                    System.out.print("Please enter the name of player 2: ");
                    blackName = reader.nextLine().trim();
                    break;
                case 2: // Player vs CPU
                    System.out.println("You have chosen option " + option + ": Player vs CPU");
                    char color;
                    do {
                        System.out.print("Please enter the color you want to play black or white (B/W): ");
                        color = Character.toUpperCase(reader.next().charAt(0));
                        switch (color) {
                            case 'B':
                                System.out.println("\nYou have chosen to play black the CPU will therefore play white");
                                System.out.print("Please enter the name of the player: ");
                                reader.nextLine(); // clears input
                                blackName = reader.nextLine().trim();
                                System.out.println();
                                isWhiteCPU = true;
                                break;
                            case 'W':
                                System.out.println("\nYou have chosen to play white the CPU will therefore play black");
                                System.out.print("Please enter the name of the player: ");
                                reader.nextLine(); // clears input
                                whiteName = reader.nextLine().trim();
                                isBlackCPU = true;
                                break;
                            default:
                                System.out.println("'" + color + "'" + " is not a valid input option, please try again.\n");
                        }
                    } while(color != 'B' && color != 'W');
                    System.out.print("How far ahead do you want the CPU to analyze: ");
                    cpuDepth = reader.nextInt();
                    break;
                case 3: // CPU vs CPU
                    System.out.println("You have chosen option " + option + ": CPU vs CPU");
                    System.out.print("How far ahead do you want the CPU's to analyze: ");
                    cpuDepth = reader.nextInt();
                    isWhiteCPU = true;
                    isBlackCPU = true;
                    break;
                default:
                    System.out.println("Invalid option, " + option + " is not a valid option\n");
            }
        } while (option > 3 && option < 0);
    }

    /**
     * Prints the option menu to the terminal.
     */
    private static void printOptions() {
        System.out.println();
        System.out.println("Now, what do you wish to do?");
        System.out.println();
        System.out.println("Option 0: Exit program");
        System.out.println("Option 1: Player vs Player");
        System.out.println("Option 2: Player vs CPU");
        System.out.println("Option 3: CPU vs CPU");
        System.out.println();
    }

    /**
     * Returns a two dimensional array 5 x 5 with the game pieces placed in correct positions
     * Precondition: Relies on method black() and white() to return valid positions numbered from 1-25
     */
    private static char[][] boardWithPieces(){
        char[][] boardArr = new char[6][5]; //A-E & (no 0) 1-5
        for (int j = 1; j < boardArr.length; j++)
            for (int i = 0; i < boardArr[j].length; i++)
                boardArr[j][i] = ' '; // Fills board with empty spaces
        for (int i = 0; i < board.black().length; i++)
            boardArr[((board.black()[i] - 1) / 5) + 1][((board.black()[i] - 1) % 5)] = 'B'; // Places black pieces
        for (int i = 0; i < board.white().length; i++)
            boardArr[((board.white()[i] - 1) / 5) + 1][((board.white()[i] - 1) % 5)] = 'W'; // Places white pieces
        return boardArr;
    }

    /**
     * prints a representation of the board to the terminal
     */
    private static void printBoard(){
        int i = 0, j = 1;
        System.out.println("   A   B   C   D   E"); //upper-coordinate-line (A-E)
        char[][] boardWithPieces = boardWithPieces();
        while (j < 6){
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
    }
}//m.i.s.
