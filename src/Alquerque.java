import java.util.Scanner;
public class Alquerque {
    private static Scanner reader = new Scanner(System.in);
    private static String whiteName = "White(CPU)", blackName = "Black(CPU)";
    private static boolean isWhiteCPU, isBlackCPU;
    private static Board board = new Board();
    private static int cpuDepth;
    // ved ikke om de nedenstående variabler skal være her, men det gjorde main mere clean.
    private static int moveFrom = 0;
    private static int moveTo = 0;
    private static String coordsFrom;
    private static String coordsTo;
    private static boolean isWhite = true;
    private static Move nextMove;
    // flyt alle initialiseringerne ind i init og ikke her.

    public static void main(String[] args) {
        init();
        //reader.nextLine(); //Den skal nok i init, men smed den her, for ellers kom der en newline når jeg tastede coords
        do {
            printBoard();
            if (!isWhiteCPU && isWhite || !isBlackCPU && !isWhite) {
                boolean inputWithinRange = false;
                do {
                    System.out.print("\nIt's " + (isWhite ? whiteName : blackName) + "'s turn" + ", please enter which " +
                            "piece you want to move: ");
                    coordsFrom = reader.nextLine().trim();
                    //moveFrom = reader.nextInt(); // Missing, input validation on convertCoordinate method
                    System.out.print("Please enter where you want to move the piece: ");
                    coordsTo = reader.nextLine().trim();
                    //moveTo = reader.nextInt(); // Missing, input validation on convertCoordinate method
                    //if (moveFrom >= 1 && moveFrom <= 25 && moveTo >= 1 && moveTo <= 25) {
                    if (isValidCoords(coordsFrom) && isValidCoords(coordsTo)){
                        //moveFrom = convertCoordinate(coordsFrom);
                        //moveTo = convertCoordinate(coordsTo);
                        //nextMove = new Move(moveFrom, moveTo);
                        nextMove = new Move(convertCoordinate(coordsFrom), convertCoordinate(coordsTo));
                        if (board.isLegal(nextMove))
                        inputWithinRange = true;
                    }
                    if (!inputWithinRange)
                        //System.out.println(moveFrom + " to " + moveTo + " is not a valid move, " +
                        System.out.println(coordsFrom + " to " + coordsTo + " is not a valid move, " +
                                "please try again.");
                } while (!inputWithinRange);
                board.move(nextMove);
            } else if (!board.isGameOver()) {
                nextMove = new Minimax().nextMove(board, cpuDepth, isWhite);
                System.out.println((isWhite ? whiteName : blackName) + " played " +
                        nextMove.from() + " to " + nextMove.to());
                board.move(nextMove);
            }
            isWhite = !isWhite; // changes whos turn it is
        } while (!board.isGameOver());
        printBoard(); // prints the state of the board when game over
        if (board.black().length > 0 && board.white().length <= 0)
            System.out.println(blackName + " is the winner!");
        else if (board.black().length <= 0 && board.white().length > 0)
            System.out.println(whiteName + " is the winner!");
        else
            System.out.println("It's a draw!");
    }


    /**
     * Initializes the program and runs the start menu.
     */
    private static void init() {
        int option;
        System.out.println("*******************************************");
        System.out.println("Greetings Master! And welcome to Alquerque.");
        System.out.println("*******************************************");
        do {
            printOptions();
            System.out.print("Please enter the coordinat corresponding " +
                    "to the option you want executed: ");
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
                        System.out.print("Please enter the color you want to play " +
                                "black or white (B/W): ");
                        color = Character.toUpperCase(reader.next().charAt(0));
                        switch (color) {
                            case 'B':
                                System.out.println("\nYou have chosen to play black the CPU " +
                                        "will therefore play white");
                                System.out.print("Please enter the name of the player: ");
                                reader.nextLine(); // clears input
                                blackName = reader.nextLine().trim();
                                System.out.println();
                                isWhiteCPU = true;
                                break;
                            case 'W':
                                System.out.println("\nYou have chosen to play white the CPU " +
                                        "will therefore play black");
                                System.out.print("Please enter the name of the player: ");
                                reader.nextLine(); // clears input
                                whiteName = reader.nextLine().trim();
                                isBlackCPU = true;
                                break;
                            default:
                                System.out.println("'" + color + "'" + " is not a valid input " +
                                        "option, please try again.\n");
                        }
                    } while (color != 'B' && color != 'W');
                    System.out.print("How far ahead do you want the CPU to analyze: ");
                    cpuDepth = reader.nextInt();
                    reader.nextLine(); // clears input
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
        System.out.println("Now, what do you wish to do?");
        System.out.println("****************************");
        System.out.println("Option 0: Exit program");
        System.out.println("Option 1: Player vs Player");
        System.out.println("Option 2: Player vs CPU");
        System.out.println("Option 3: CPU vs CPU");
        System.out.println("****************************");
        System.out.println();
    }

    /**
     * Returns a two dimensional array 5 x 5 with the game pieces placed in correct positions
     * Precondition: Relies on method black() and white() to return valid positions numbered from 1-25
     */
    private static char[][] boardWithPieces() {
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
    private static boolean isValidCoords(String coords){
        return (coords.matches("[A-E][1-5]")); // Regex for matching
    }
    private static int convertCoordinate(String coord){
        int position = 0;
        switch(coord.charAt(0)){
            case 'A':
                position = (1+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'B':
                position = (2+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'C':
                position = (3+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'D':
                position = (4+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            case 'E':
                position = (5+(5*((Integer.parseInt(coord.substring(1))-1))));
                break;
            default:
                return 0;
        }
        return position;
    }
}//m.i.s.
