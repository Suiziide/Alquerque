import java.util.Scanner;
public class Alquerque {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String playerOneName = "NoName";
        String playerTwoName = "NoName";
        boolean isOneCPU;
        boolean isTwoCPU;
        boolean startGame = false;
        int option;

        System.out.println("Welcome to Alquerque, Master.");
        do {
            printOptions();
            option = reader.nextInt();
            switch (option) {
                case 0:
                    System.out.println("You have chosen option" + option + ": Exit program");
                    System.out.println("Thank you for playing, have a nice day!");
                    break;
                case 1: // Player vs Player
                    System.out.println("You have chosen option " + option + ": Player vs Player");
                    System.out.print("Please enter the name of player 1: ");
                    reader.nextLine(); // clears terminal input
                    playerOneName = reader.nextLine().trim();
                    System.out.print("Please enter the name of player 2: ");
                    playerTwoName = reader.nextLine().trim();
                    startGame = true;
                    break;
                case 2: // Player vs CPU
                    System.out.println("You have chosen option " + option + ": Player vs CPU");
                    char color;
                    do {
                        System.out.print("Please enter the color you want to play black or white (B/W): ");
                        color = reader.next().charAt(0);
                    } while(color != 'B' && color != 'W');
                    if (color == 'B') {
                        System.out.println("\n You have chosen to play black the CPU will therefore play white");
                        System.out.print("Please enter the name of the player: ");
                        reader.nextLine(); // clears input
                        playerTwoName = reader.nextLine().trim();
                        isOneCPU = true;
                        playerOneName = "CPU";
                    } else {
                        System.out.println("\n You have chosen to play white the CPU will therefore play black");
                        System.out.print("Please enter the name of the player: ");
                        reader.nextLine(); // clears input
                        playerOneName = reader.nextLine().trim();
                        isTwoCPU = true;
                        playerTwoName = "CPU";
                    }
                    startGame = true;
                    break;
                case 3: // CPU vs CPU
                    isOneCPU = true;
                    isTwoCPU = true;
                    startGame = true;
                    playerOneName = "CPU1";
                    playerTwoName = "CPU2";
                    break;
                default:
                    System.out.println("Invalid option, " + option + " is not a valid option");
            }
        } while (!startGame);
        System.out.println("The two players names are:");
        System.out.println("Player 1 (White): " + playerOneName);
        System.out.println("Player 2 (Black): " + playerTwoName);
    }

    /**
     * Initializes the program.
     */
    private static void init() {

    }

    /**
     * Prints the option menu to the terminal.
     */
    public static void printOptions() {
        System.out.println("What do you wish to do?");
        System.out.println();
        System.out.println("Option 0: Exit program");
        System.out.println("Option 1: Player vs Player");
        System.out.println("Option 2: Player vs CPU");
        System.out.println("Option 3: CPU vs CPU");
        System.out.println();
        System.out.print("Please enter the number corresponding to the option you want executed: ");
    }
}
