import java.util.Scanner;
public class Alquerque {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String playerOneName;
        String playerTwoName;
        boolean isOneCPU;
        boolean isTwoCPU;
        int option;

        System.out.println("Welcome to Alquerque, Master('s)");
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
                    playerOneName = reader.nextLine().trim();
                    System.out.print("Please enter the name of player 2: ");
                    playerTwoName = reader.nextLine().trim();
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
                        reader.nextLine();
                        playerTwoName = reader.nextLine().trim();
                        isOneCPU = true;
                    } else {
                        System.out.println("\n You have chosen to play white the CPU will therefore play black");
                        System.out.print("Please enter the name of the player: ");
                        reader.nextLine();
                        playerOneName = reader.nextLine().trim();
                        isTwoCPU = true;
                    }
                    break;
                case 3: // CPU vs CPU
                    isOneCPU = true;
                    isTwoCPU = true;
                    break;
                default:
                    System.out.println("Invalid option, " + option + " is not a valid option");
            }
        } while (option > 3 || option <-1);
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
        System.out.println("Please enter the number corresponding to the option you want executed");
    }
}
